package com.machine.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHelper {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static LocalDateTime StringToLocalDateTime(String date){
        LocalDateTime dateTime = LocalDateTime.parse(date,formatter);
        return dateTime;
    }

    public static String LocalDateTimeToString(LocalDateTime dateTime){
        String stringDateTime = dateTime.format(formatter);
        return stringDateTime;
    }

}
