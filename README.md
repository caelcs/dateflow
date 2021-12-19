# Dateflow

[![Release build](https://github.com/caelcs/dateflow/actions/workflows/github-actions-release.yml/badge.svg)](https://github.com/caelcs/dateflow/actions/workflows/github-actions-release.yml)

DateFlow is a fluent interface to deal with java Date utils methods that you would normally have in your project.

The motivation comes from projects where you are deeply constraint to the use of Date or String and you are 
dealing with multiple regions, no matter the reason behind it.

DateFlow helps you to convert a representation of date/datetime some of them bind to 
timezones.

By passing the source representation of your Date/time, Once this has been set up, DateFlow use 
this information to create an Instant (UTC) representation that will be used
later on to apply different operations and finally generate a representation as UTC or 
the timezone of your preference.

As I said normally you would not need something like this in your project but if you are bind to Date class or 
format based on String you might find DateFlow helpful.         

## define the source date time from different classes.

`now()`: create Instant from current date time.

`fromMillis(long millis)`:create Instant from epoc milliseconds.

`from(LocalDate localDate)`: create Instant from local date (no time required because is not bind to that).

`from(LocalDateTime localDateTime)`: Create Instant from LocalDateTime, using system default ZoneId.

`from(String date, String dateFormat)`: Create Instant from String, using system default ZoneId.

`from(Date date)`: Create Instant from Date, using the default ZoneId.

## Reset time to 00:00:00 or 23:59:59:999
```
.resetTime()
.resetTimeToLastSecondOfDay()
```
## Basic manipulation of the date time
```
.plusMonths(int months)
.plusDays(int days)
.plusMinutes(int minutes)
.plusHours(int hours)
.plusSeconds(int seconds)
.minusMonths(int months)
.minusMinutes(int minutes)
.minusHours(int hours)
.minusDays(int days)
```
## exporting the datetime as
```
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
```

### define export ZoneId to be use when you export date time
```
.as()
.zoneIdUTC()
.zoneId(String zoneId)
```

## Requirements:

- [Java 17](https://openjdk.java.net/projects/jdk/17/)
- [Gradle 7.3.1](https://gradle.org/releases/)

## Run

in your root project execute the following commands:

Generate the gradle wrapper that allows you to have an executable for that gradle version that you specify
`gradle wrapper --gradle-version 7.3.1`

Run all the tests
`./gradlew test`
