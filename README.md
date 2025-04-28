# Most Active Cookie - Quantcast Coding Challenge

## Overview

This project is a Java command-line application that processes a cookie log file and returns the most active cookie(s) for a specified date.

- **Language:** Java 11
- **Build Tool:** Maven
- **Testing Framework:** JUnit 5
- **Containerization:** Docker

The application reads a CSV log file containing cookies and their timestamps, identifies the cookie(s) that appear most frequently on a given day, and outputs the result(s) to standard output.

---

## Project Structure
/src/main/java Main.java CookieFileParser.java CookieMap.java MostActiveCookieService.java

/src/test/java MostActiveCookieServiceTest.java

Dockerfile pom.xml README.md


---

## Build Instructions

Make sure you have Docker installed.

To build the project inside a Docker image:

Run Instructions
Run the application using Docker by mounting the cookie log file:

```bash
docker run -v $(pwd)/cookie_log.csv:/app/cookie_log.csv most-active-cookie -f cookie_log.csv -d <yyyy-MM-dd>
```

## Assumptions
The input CSV file has a header (cookie,timestamp).
Timestamps are in ISO 8601 format and in UTC.
The cookie log is sorted by timestamp in descending order (most recent first).
The application expects exact file path mounting using Docker volumes.
The application processes the entire file but only counts cookies for the given date.
If no cookies are found for the specified date, an appropriate message is shown.

## Testing
Unit tests are provided for the core business logic (MostActiveCookieService) using JUnit 5.

To run tests locally (without Docker):
```bash
mvn clean test
```

## Running Locally (Optional)
If you prefer to run the application directly without Docker:
```bash
mvn clean compile exec:java -Dexec.mainClass="org.example.Main" -Dexec.args="-f cookie_log.csv -d 2018-12-09"
```

Ensure Maven is installed and Java 11 is available.

## Example Input Files
Along with the project, several example cookie log files are provided for testing:

File Name | Description
cookie_log.csv | The original provided sample log file
cookieFile_multipleActiveCookies.csv | Contains multiple cookies tied as most active
cookieFile_singleWinner.csv | Contains a single most active cookie
cookieFile_noCookie.csv | No matching cookies for the test date
