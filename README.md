# Dateflow

This library provides a fluent interface to deal with java Date utils.
        
## define the source date time from different classes.

DateFlow
    .now()
    .fromMillis(long millis) 
    .from(LocalDate localDate) 
    .from(LocalDateTime localDateTime) 
    .from(String date) 
    .from(Date date) 

## Reset time to 00:00:00 or 23:59:59:999

    .resetTime() 
    .resetTimeToLastSecondOfDay() 

## Basic manipulation of the date time

    .plusMonths(int months) 
    .plusDays(int days) 
    .plusMinutes(int minutes) 
    .plusHours(int hours) 
    .plusSeconds(int seconds) 
    .minusMonths(int months) 
    .minusMinutes(int minutes) 
    .minusHours(int hours) 
    .minusDays(int days) 

## define export ZoneId to be use when you export date time

    .zoneIdUTC() 
    .zoneId(String zoneId)

## exporting the datetime as

    .asDate() 
    .asLocalDate() 
    .asLocalDateTime() 
    .asString(String format) 
    .day() 
    .month() 
    .hour() 
    .minutes() 
    .seconds() 

## Requirements:

- [Java 17](https://openjdk.java.net/projects/jdk/17/)
- [Gradle 7.3.1](https://gradle.org/releases/)

## Run

in your root project execute the following commands:

Generate the gradle wrapper that allows you to have an executable for that gradle version that you specify
`gradle wrapper --gradle-version 7.3.1`

Run all the tests
`./gradlew test`
