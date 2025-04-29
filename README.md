
# Most Active Cookie - Quantcast Coding Challenge

## Overview

This project is a Java command-line application that processes a cookie log file and returns the most active cookie(s) for a specified date.

- **Language:** Java 11  
- **Build Tool:** Maven  
- **Testing Framework:** JUnit 5  
- **Containerization:** Docker (optional)

The application reads a CSV log file containing cookies and their timestamps, identifies the cookie(s) that appear most frequently on a given day, and outputs the result(s) to standard output.

---

## 📁 Project Structure

```
/src/main/java
    org.example.Main
    util.CookieFileParser
    model.CookieMap
    service.MostActiveCookieService

/src/test/java
    service.MostActiveCookieServiceTest

Dockerfile
pom.xml
README.md
most-active-cookie (CLI wrapper script)

cookieFile.csv
cookieFile_multipleActiveCookies.csv
cookieFile_singleWinner.csv
cookieFile_noCookie.csv
cookieFile_1000CookiesTest.csv
```

---

## 🧱 Build Instructions (Local)

To compile and package the project:

```bash
mvn clean package
```

This creates the executable `.jar`:

```
target/QuantcastProj-1.0-SNAPSHOT.jar
```

---

## 🚀 CLI Usage

You can run the project using the included CLI script:

```bash
./most-active-cookie -f <filename.csv> -d <yyyy-MM-dd>
```

### Example:

```bash
./most-active-cookie -f cookieFile.csv -d 2018-12-09
```

### ✅ Before running:

Make sure the file `most-active-cookie` is executable:

```bash
chmod +x most-active-cookie
```

The CSV file should be in the **root directory** (same level as the script).

---

## 🐳 Docker Instructions (Alternative)

To build the Docker image:

```bash
docker build -t most-active-cookie .
```

To run:

```bash
docker run -v $(pwd)/cookieFile.csv:/app/cookie_log.csv most-active-cookie -f cookie_log.csv -d 2018-12-09
```

---

## 🧪 Testing

To run unit tests:

```bash
mvn clean test
```

Tests are written using JUnit 5 for the `MostActiveCookieService` class.

---

## 🔬 Assumptions

- Input CSV file has a header: `cookie,timestamp`
- Timestamps are in ISO 8601 format and UTC timezone
- The log is sorted by timestamp (most recent first)
- Only cookies from the specified date are counted
- If no cookies match the date, a message is printed

---

## 📂 Example Input Files

These test files are included in the root directory:

| File Name                         | Description                                                                 |
|----------------------------------|-----------------------------------------------------------------------------|
| `cookieFile.csv`                 | Original sample file                                                        |
| `cookieFile_multipleActiveCookies.csv` | Contains multiple cookies tied as most active                          |
| `cookieFile_singleWinner.csv`    | Contains a single most active cookie                                        |
| `cookieFile_noCookie.csv`        | No cookies for the test date                                                |
| `cookieFile_1000CookiesTest.csv` | Large test file: 1000 cookies, one appearing 500 times as the clear winner |

To test any file:

```bash
./most-active-cookie -f cookieFile_multipleActiveCookies.csv -d 2018-12-10
```

---

## ✅ Ready for Submission

This project matches all assignment requirements and includes:

- CLI support via `./most-active-cookie`
- Docker support
- Unit tests
- Example input files
- Clean and maintainable code structure
