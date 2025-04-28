package org.example.util;

import org.example.model.CookieMap;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class CookieFileParser {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public static CookieMap parseFile(String filePath)  {
        CookieMap cookieMap = new CookieMap();


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Skip header
            if (line == null || !line.equalsIgnoreCase("cookie,timestamp")) {
                throw new IllegalArgumentException("Invalid file format. Header missing or incorrect.");
            }

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    continue; // Skip invalid lines
                }
                String cookie = parts[0].trim();
                String timestampStr = parts[1].trim();

                // Only need yyyy-MM-dd part
                LocalDate date = LocalDate.parse(timestampStr.substring(0, 10));

                cookieMap.addCookie(date, cookie);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

        return cookieMap;
    }
}

