//
package com.hms.hospital.util;
//
//import java.time.LocalTime;
//import java.time.format.DateTimeParseException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TimeSlot {
//    public static List<String> generateTimeSlots(String availabilitySchedule) {
//        List<String> timeSlots = new ArrayList<>();
//
//        // 1. Validate the input string format
//        if (availabilitySchedule == null || !availabilitySchedule.contains("-")) {
//            System.err.println("Invalid availability schedule format: " + availabilitySchedule);
//            // Return an empty list or throw a custom exception if preferred
//            return timeSlots;
//        }
//
//        String[] times = availabilitySchedule.split("-");
//
//        // 2. Check if the array has at least two elements
//        if (times.length < 2) {
//            System.err.println("Invalid availability schedule format: " + availabilitySchedule);
//            return timeSlots;
//        }
//
//        try {
//            // 3. Parse the times
//            LocalTime startTime = LocalTime.parse(times[0]);
//            LocalTime endTime = LocalTime.parse(times[1]);
//
//            // 4. Generate the slots (e.g., every 30 minutes)
//            LocalTime currentTime = startTime;
//            while (currentTime.isBefore(endTime)) {
//                timeSlots.add(currentTime.toString());
//                currentTime = currentTime.plusMinutes(30); // Or whatever the slot duration is
//            }
//        } catch (DateTimeParseException e) {
//            System.err.println("Error parsing time format from availability schedule: " + availabilitySchedule);
//            e.printStackTrace();
//            return new ArrayList<>(); // Return empty list on parse error
//        }
//
//        return timeSlots;
//    }
//}

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeSlot {
    private static final Pattern TIME_PATTERN = Pattern.compile("(\\d+)(AM|PM)-(\\d+)(AM|PM)");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("hha");

    public static List<String> generateTimeSlots(String availabilitySchedule) {
        List<String> timeSlots = new ArrayList<>();
        // Extract the time part
        String[] parts = availabilitySchedule.split(" ");
        if (parts.length < 2) {
            return timeSlots; // Invalid format
        }
        String timePart = parts[1];

        Matcher matcher = TIME_PATTERN.matcher(timePart);
        if (!matcher.matches()) {
            return timeSlots; // Invalid time format
        }

        try {
            // Parse times with AM/PM
            LocalTime startTime = LocalTime.parse(matcher.group(1) + matcher.group(2), TIME_FORMATTER);
            LocalTime endTime = LocalTime.parse(matcher.group(3) + matcher.group(4), TIME_FORMATTER);

            // Handle the case where the end time is before the start time (e.g., 10PM-4AM)
            // ... This is getting very complex.

            // Generate slots
            // ...
        } catch (Exception e) {
            // Handle parsing exceptions
        }
        return timeSlots;
    }
}