# Dateflow

DateFlow is a fluent interface to deal with java Date utils methods that you would normally have in your project.

The idea is provides a way to use local date/datetime, string, date among other types, some of them bind to the 
timezone where you generate from; consequently the DateFlow allows you to pass the date timezone and/or format. 
once this has been set up DateFlow use this information to create an Instant (UTC) representation that will be used
later on to apply different operations and finally export to different Date Time classes as UTC or the timezone 
of your convenience.
As I said normally you would not need something like this in your project but if you are bind to Date class or 
format based on String you might find DateFlow helpful.         

## define the source date time from different classes.

```java```
DateFlow
    .now() (create Instant from current date time)
    .fromMillis(long millis) (create Instant from epoc milliseconds)
    .from(LocalDate localDate) (create Instant from local date (no time required because is not bind to that))
    .from(LocalDateTime localDateTime, ZoneId zoneId) (Create Instant from LocalDateTime, and ZoneId needs to be provided so can it can be transformed to UTC internally)
    .from(String date, String dateFormat, ZoneId zoneId) (Create Instant from String, and ZoneId and format needs to be provided so can it can be transformed to UTC internally)
    .from(LocalDateTime localDateTime) (Create Instant from LocalDateTime, using system default ZoneId)
    .from(String date, String dateFormat) (Create Instant from String, using system default ZoneId)
    .fromUTC(Date date) (Create Instant from Date, the assumption here is that the date time is already in UTC)
    .fromUTC(String date, String dateFormat) (Create Instant from String, format needs to be provided and the assumption here is that the date time is already in UTC)
    .fromUTC(String date) (Create Instant from Date, the assumption here is that the date time is already in UTC and using a default datetime format)
    .fromUTC(LocalDateTime dateTime) (Create Instant from LocalDateTime, the assumption here is that the date time is already in UTC)
    .fromUTC(LocalDate localDate) (Create Instant from Date, the assumption here is that the date time is already in UTC)
```java```

## Reset time to 00:00:00 or 23:59:59:999
```java```
    .resetTime() 
    .resetTimeToLastSecondOfDay()
```java```

## Basic manipulation of the date time
```java```
    .plusMonths(int months) 
    .plusDays(int days) 
    .plusMinutes(int minutes) 
    .plusHours(int hours) 
    .plusSeconds(int seconds) 
    .minusMonths(int months) 
    .minusMinutes(int minutes) 
    .minusHours(int hours) 
    .minusDays(int days)
```java```
## exporting the datetime as
```java```
    .as()
        .date() 
        .localDate() 
        .localDateTime() 
        .string(String format) 
        .day() 
        .month() 
        .hour() 
        .minutes() 
        .seconds()
```java```
### define export ZoneId to be use when you export date time
    .as()
        .zoneIdUTC() 
        .zoneId(String zoneId)

## Requirements:

- [Java 17](https://openjdk.java.net/projects/jdk/17/)
- [Gradle 7.3.1](https://gradle.org/releases/)

## Run

in your root project execute the following commands:

Generate the gradle wrapper that allows you to have an executable for that gradle version that you specify
`gradle wrapper --gradle-version 7.3.1`

Run all the tests
`./gradlew test`
