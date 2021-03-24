package com.coolprojects.learning.time;

import java.time.*;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Scanner;

import static java.time.format.DateTimeFormatter.*;

public class TestingDateTime {

    private enum Mode{
        DATE_DIFF, TIME_DIFF, INITIAL_MODE
    }

    public static void main(String[] args) {
        //testDateTimeFormatter();
        runTimeDifferenceLoop();
    }

    private static void runTimeDifferenceLoop() {
        Scanner scanner = new Scanner(System.in);
        String input;
        Mode currentMode = Mode.INITIAL_MODE;

        System.out.println("To find the difference between dates, type in: \"-date-diff\" or \"date-diff\"");
        System.out.println("To find the difference between times in the day, type in: \"-time-diff\" or \"time-diff\"");
        System.out.println("Type in \"exit\" to exit the loop");

        while(true){

            System.out.println("\nEnter your desired operation:");
            input = scanner.nextLine();

            if(input.equals("-date-diff") || input.equals("date-diff")){
                currentMode = Mode.DATE_DIFF;
            }
            else if( input.equals("-time-diff") || input.equals("time-diff")){
                currentMode = Mode.TIME_DIFF;
            }
            try{
                switch(currentMode){
                    case INITIAL_MODE:
                        System.out.println("Please provide a proper operation to start the program");
                        break;
                    case DATE_DIFF:
                        System.out.println("Enter dates in mm/dd/yyyy format");

                        System.out.println("\nStart Date: ");
                        String startDateString = scanner.nextLine();

                        System.out.println("End Date: ");
                        String endDateString = scanner.nextLine();

                        LocalDate startDate = LocalDate.from(ofPattern("MM/dd/yyyy").parse(startDateString));
                        LocalDate endDate = LocalDate.from(ofPattern("MM/dd/yyyy").parse(endDateString));

                        Period period = Period.between(startDate,endDate);
                        int numbOfYears = period.getYears();
                        int numbOfMonths = period.getMonths();
                        int numbOfDays = period.getDays();
                        System.out.format("\nThe dates are %d years, %d months, and %d days away from each other\n",numbOfYears,numbOfMonths,numbOfDays);
                        break;
                    case TIME_DIFF:
                        System.out.println("Enter times in hh:mm am/pm");

                        System.out.println("\nStart Time:");
                        String startTimeString = scanner.nextLine();

                        System.out.println("\nEnd Time:");
                        String endTimeString = scanner.nextLine();

                        LocalTime startTime = LocalTime.from(ofPattern("hh:mm a").parse(startTimeString));
                        LocalTime endTime = LocalTime.from(ofPattern("hh:mm a").parse(endTimeString));

                        Duration duration = Duration.between(startTime,endTime);
                        long hours = duration.toHours();
                        long minutes =  duration.toMinutes() - duration.toHours() * 60;

                        System.out.format("\nThe times are %d hour(s) and %d minute(s) away from each other\n",hours,minutes);
                        break;
                }
            }catch(DateTimeParseException e){
                System.out.println("\nMake sure to use proper formatting when writing dates and times");
            }

        }
    }


    private static void testDateTimeFormatter() {
        LocalDate currentDay = LocalDate.now();
        LocalDate birthday = LocalDate.of(1998,1,7);

        System.out.println("birthday = " + birthday);
        System.out.println("Formated Date: " + ofLocalizedDate(FormatStyle.FULL).format(birthday));
        System.out.println("Formated Date: " + ofLocalizedDate(FormatStyle.MEDIUM).format(birthday));
        System.out.println("Formated Date: " + ofLocalizedDate(FormatStyle.SHORT).format(birthday));
        System.out.println();

        ZonedDateTime timeOfBirth = LocalDate.of(1998,1,7).atTime(12,30).atZone(ZoneId.systemDefault());
        System.out.println("Formatted time of birth: " + ofLocalizedDateTime(FormatStyle.FULL).format(timeOfBirth));
        System.out.println("Formatted time of birth: " + ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT).format(timeOfBirth));
        System.out.println("Formatted time of birth: " + ofLocalizedDateTime(FormatStyle.SHORT).format(timeOfBirth));
    }
}
