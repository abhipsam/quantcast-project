import org.example.model.CookieMap;
import org.example.service.MostActiveCookieService;
import org.example.util.CookieFileParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MostActiveCookieServiceTest {

    @Test
    void testOriginalSampleFile() throws IOException {
        CookieMap cookieMap = CookieFileParser.parseFile("src/main/resources/cookieFile.csv");
        MostActiveCookieService service = new MostActiveCookieService(cookieMap);

        List<String> result = service.getMostActiveCookies(LocalDate.of(2018, 12, 9));

        assertEquals(1, result.size());
        assertEquals("AtY0laUfhglK3lC7", result.get(0));
    }

    @Test
    void testMultipleActiveCookies() throws IOException {
        CookieMap cookieMap = CookieFileParser.parseFile("src/main/resources/cookieFile_multipleActiveCookies.csv");
        MostActiveCookieService service = new MostActiveCookieService(cookieMap);

        List<String> result = service.getMostActiveCookies(LocalDate.of(2018, 12, 10));

        assertEquals(2, result.size());
        assertTrue(result.contains("AtY0laUfhglK3lC7"));
        assertTrue(result.contains("SAZuXPGUrfbcn5UA"));
    }

    @Test
    void testSingleWinnerCookie() throws IOException {
        CookieMap cookieMap = CookieFileParser.parseFile("src/main/resources/cookieFile_singleWinner.csv");
        MostActiveCookieService service = new MostActiveCookieService(cookieMap);

        List<String> result = service.getMostActiveCookies(LocalDate.of(2018, 12, 12));

        assertEquals(1, result.size());
        assertEquals("AtY0laUfhglK3lC7", result.get(0));
    }

    @Test
    void testNoCookiesFound() throws IOException {
        CookieMap cookieMap = CookieFileParser.parseFile("src/main/resources/cookieFile_noCookie.csv");
        MostActiveCookieService service = new MostActiveCookieService(cookieMap);

        List<String> result = service.getMostActiveCookies(LocalDate.of(2018, 12, 9));

        assertTrue(result.isEmpty());
    }

    @Test
    void testStressTestLargeFile() throws IOException {
        CookieMap cookieMap = CookieFileParser.parseFile("src/main/resources/cookieFile_1000CookiesTest.csv");
        MostActiveCookieService service = new MostActiveCookieService(cookieMap);

        List<String> result = service.getMostActiveCookies(LocalDate.of(2018, 12,9));

        assertEquals(1, result.size());
        assertEquals("AtY0laUfhglK3lC7", result.get(0));
    }
}