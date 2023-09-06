package fr.univ_lyon1.info.m1.mes.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public interface EasyDateFormatter {   


    static String dateToString(final Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(date);
    }

    static String ldateToString(final LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
