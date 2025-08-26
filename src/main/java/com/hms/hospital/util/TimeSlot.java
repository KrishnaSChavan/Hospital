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
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TimeSlot {
    /**
     * Generates a list of time slots from a schedule string like "10:00-16:00".
     *
     * @param availabilitySchedule The schedule in "HH:mm-HH:mm" format.
     * @return A list of time slots in "HH:mm" format.
     */
    public static List<String> generateTimeSlots(String availabilitySchedule) {
        List<String> timeSlots = new ArrayList<>();

        // Handle null or invalid input format
        if (availabilitySchedule == null || !availabilitySchedule.contains("-")) {
            return timeSlots;
        }

        String[] parts = availabilitySchedule.split("-");
        if (parts.length < 2) {
            return timeSlots;
        }

        try {
            LocalTime startTime = LocalTime.parse(parts[0]);
            LocalTime endTime = LocalTime.parse(parts[1]);

            // Ensure valid time range
            if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
                return timeSlots;
            }

            // Generate slots in 30-minute increments
            LocalTime currentTime = startTime;
            while (currentTime.isBefore(endTime)) {
                timeSlots.add(currentTime.toString());
                currentTime = currentTime.plusMinutes(30); // You can change this to 15, 60, etc.
            }

        } catch (DateTimeParseException e) {
            System.err.println("Error parsing time format from availability schedule: " + availabilitySchedule);
            e.printStackTrace();
            return new ArrayList<>(); // Return empty list on parse error
        }

        return timeSlots;
    }
}