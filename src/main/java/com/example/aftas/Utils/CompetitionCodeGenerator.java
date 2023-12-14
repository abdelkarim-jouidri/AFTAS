package com.example.aftas.Utils;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class CompetitionCodeGenerator {
    public static String generateCode(String location, Date date){
        String locationCode = location.substring(0, Math.min(location.length(), 3));
        String dateCode = date.toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-yy"));
        return locationCode.toLowerCase()+"-"+dateCode;
    }
}
