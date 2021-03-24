package com.coolprojects.learning.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;

public class HttpTesting {

    private static final String PATH_NAME = "src\\com\\coolprojects\\learning\\http\\files.txt";

    public static void main(String[] args) {
        testHttpRequest();
        checkStatusOfLinks();
        checkStatusOfLinksAsync();
    }

    private static void checkStatusOfLinks() {
        try(Stream<String> lines =  Files.lines(Path.of(PATH_NAME));){
            lines.forEach(HttpTesting::printLinkWithStatus);
            System.out.println();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private static void checkStatusOfLinksAsync() {
        try(Stream<String> lines =  Files.lines(Path.of(PATH_NAME));){
            List<CompletableFuture<String>> list =  lines.map(HttpTesting::printLinkWithStatusASync)
                                                            .collect(toList());

            list.stream()
                .map(CompletableFuture::join)
                .forEach(System.out::println);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private static CompletableFuture<String> printLinkWithStatusASync(String uriString) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(uriString)).GET().build();
        CompletableFuture<HttpResponse<Void>> responseFuture = client.sendAsync(request,HttpResponse.BodyHandlers.discarding());
        return responseFuture.thenApply(response -> response.uri() + " - " + response.statusCode())
                                .exceptionally(Throwable::toString);

        //callable future applies print function asynchronously in the background using the thenAccept() method

    }

    private static Void printException(Throwable e){
        e.printStackTrace();
        return null;
    }

    private static void printLinkWithStatus(String uriString){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(uriString)).GET().build();
        try{
            HttpResponse<Void> response = client.send(request,HttpResponse.BodyHandlers.discarding());

            int status = response.statusCode();
            URI uri = response.uri();
            System.out.println(uri + " - " + status);
        }
        catch(IOException | InterruptedException e){
            e.printStackTrace();
        }

    }

    private static void testHttpRequest() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("http://pluralsight.com/")).build();
        try {
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}
