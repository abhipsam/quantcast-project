package org.example;

import org.example.model.CookieMap;
import org.example.service.MostActiveCookieService;
import org.example.util.CookieFileParser;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length != 4) {
            printUsageAndExit();
        }

        String filePath = null;
        String dateString = null;

        // Parse CLI arguments manually
        for (int i = 0; i < args.length; i += 2) {
            if (args[i].equals("-f")) {
                filePath = args[i + 1];
            } else if (args[i].equals("-d")) {
                dateString = args[i + 1];
            } else {
                printUsageAndExit();
            }
        }

        if (filePath == null || dateString == null) {
            printUsageAndExit();
        }

        try {
            // Parse the date
            LocalDate targetDate = LocalDate.parse(dateString);

            // Parse the file
            CookieMap cookieMap = CookieFileParser.parseFile(filePath);

            // Find the most active cookies
            MostActiveCookieService service = new MostActiveCookieService(cookieMap);
            List<String> mostActiveCookies = service.getMostActiveCookies(targetDate);

            // Output result
            if (mostActiveCookies.isEmpty()) {
                System.out.println("No cookies found for date: " + targetDate);
            } else {
                for (String cookie : mostActiveCookies) {
                    System.out.println(cookie);
                }
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void printUsageAndExit() {
        System.out.println("Usage: java Main -f <filePath> -d <yyyy-MM-dd>");
        System.exit(1);
    }
}
