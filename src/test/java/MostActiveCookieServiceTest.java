import org.example.model.CookieMap;
import org.example.service.MostActiveCookieService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MostActiveCookieServiceTest {

    @Test
    void testSingleMostActiveCookie() {
        CookieMap cookieMap = new CookieMap();
        LocalDate date = LocalDate.of(2018, 12, 9);

        cookieMap.addCookie(date, "cookieA");
        cookieMap.addCookie(date, "cookieB");
        cookieMap.addCookie(date, "cookieA");

        MostActiveCookieService service = new MostActiveCookieService(cookieMap);
        List<String> result = service.getMostActiveCookies(date);

        assertEquals(1, result.size());
        assertEquals("cookieA", result.get(0));
    }

    @Test
    void testMultipleMostActiveCookiesTie() {
        CookieMap cookieMap = new CookieMap();
        LocalDate date = LocalDate.of(2018, 12, 9);

        cookieMap.addCookie(date, "cookieA");
        cookieMap.addCookie(date, "cookieB");
        cookieMap.addCookie(date, "cookieA");
        cookieMap.addCookie(date, "cookieB");

        MostActiveCookieService service = new MostActiveCookieService(cookieMap);
        List<String> result = service.getMostActiveCookies(date);

        assertEquals(2, result.size());
        assertTrue(result.contains("cookieA"));
        assertTrue(result.contains("cookieB"));
    }

    @Test
    void testNoCookiesForDate() {
        CookieMap cookieMap = new CookieMap();
        LocalDate date = LocalDate.of(2018, 12, 9);
        LocalDate otherDate = LocalDate.of(2018, 12, 8);

        cookieMap.addCookie(otherDate, "cookieA");

        MostActiveCookieService service = new MostActiveCookieService(cookieMap);
        List<String> result = service.getMostActiveCookies(date);

        assertTrue(result.isEmpty());
    }

    @Test
    void testSingleCookieOnly() {
        CookieMap cookieMap = new CookieMap();
        LocalDate date = LocalDate.of(2018, 12, 9);

        cookieMap.addCookie(date, "cookieA");

        MostActiveCookieService service = new MostActiveCookieService(cookieMap);
        List<String> result = service.getMostActiveCookies(date);

        assertEquals(1, result.size());
        assertEquals("cookieA", result.get(0));
    }

    @Test
    void testEmptyCookieMap() {
        CookieMap cookieMap = new CookieMap();
        LocalDate date = LocalDate.of(2018, 12, 9);

        MostActiveCookieService service = new MostActiveCookieService(cookieMap);
        List<String> result = service.getMostActiveCookies(date);

        assertTrue(result.isEmpty());
    }
}
