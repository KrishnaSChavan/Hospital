package com.hms.hospital.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TimeSlot {
    public static List<String> generateTimeSlots(String availabilitySchedule) {

        String[] parts = availabilitySchedule.split(":");
        String[] times = parts[1].trim().split("-");
        String startStr = times[0].trim();
        String endStr = times[1].trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime start = LocalTime.parse(startStr, formatter);
        LocalTime end = LocalTime.parse(endStr, formatter);

        List<String> slots = new ArrayList<>();
        while (start.isBefore(end)) {
            LocalTime next = start.plusMinutes(30);
            slots.add(start + " - " + next);
            start = next;
        }
        return slots;
    }
}
