package com.example.RentalCarProject.util;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Slf4j
public class DateFormatterUtil {

    public static LocalDate getDateFromDateTime(Instant date) {
        LocalDate ld = LocalDate.ofInstant(date, ZoneId.of("UTC+3"));
       return ld;
    }
}
