package com.tasklist.api.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class DateFormat {
    public static String dateFormat(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return formatter.format(LocalDateTime.now());
    }
}
