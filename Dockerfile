# Build Stage
FROM maven:3.9.6-eclipse-temurin-11 AS build

WORKDIR /app

# Copy everything
COPY . .

# Build the project
RUN mvn clean package

# Run Stage
FROM eclipse-temurin:11-jre

WORKDIR /app

# Copy the compiled JAR from build stage
COPY --from=build /app/target/QuantcastProj-1.0-SNAPSHOT.jar QuantcastProj.jar

# Default command
ENTRYPOINT ["java", "-cp", "QuantcastProj.jar", "org.example.Main"]