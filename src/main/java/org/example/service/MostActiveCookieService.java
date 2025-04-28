package org.example.service;

import org.example.model.CookieMap;

import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;

public class MostActiveCookieService {

    private final CookieMap cookieMap;
    private static final Logger logger = Logger.getLogger(MostActiveCookieService.class.getName());

    public MostActiveCookieService(CookieMap cookieMap) {
        this.cookieMap = cookieMap;
    }

    public List<String> getMostActiveCookies(LocalDate date) {
        List<String> cookies = cookieMap.getCookiesForDate(date);
        if (cookies.isEmpty()) {
            return Collections.emptyList();
        }

        // Count occurrences of the cookies for a particular date
        Map<String, Integer> cookieFrequencyMap = new HashMap<>();
        for(String cookie : cookies) {
            cookieFrequencyMap.put(cookie, cookieFrequencyMap.getOrDefault(cookie, 0) + 1);
        }

        // Find maximum frequency of the cookies on this date
        int maxCount = 0;
        for (int count : cookieFrequencyMap.values()) {
            maxCount = Math.max(maxCount, count);
        }

        // Collect all cookies with maximum frequency
        List<String> mostActiveCookies = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : cookieFrequencyMap.entrySet()) {
            if (entry.getValue() == maxCount) {
                mostActiveCookies.add(entry.getKey());
            }
        }

        logger.info("Found " + mostActiveCookies.size() + " most active cookie(s) for date " + date);
        return mostActiveCookies;
    }
}

