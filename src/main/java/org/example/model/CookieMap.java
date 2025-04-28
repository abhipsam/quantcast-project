package org.example.model;

import java.time.LocalDate;
import java.util.*;

public class CookieMap {

   private Map<LocalDate, List<String>> dateCookieMap;
    public CookieMap() {
        this.dateCookieMap = new HashMap<>();
    }

    public void addCookie(LocalDate date, String cookie) {
        dateCookieMap.computeIfAbsent(date, k -> new ArrayList<>()).add(cookie);
    }

    public List<String> getCookiesForDate(LocalDate date) {
        return dateCookieMap.getOrDefault(date, Collections.emptyList());
    }

    public Map<LocalDate, List<String>> getDateCookieMap() {
        return dateCookieMap;
    }
}
