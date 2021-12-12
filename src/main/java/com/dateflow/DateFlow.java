package com.dateflow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.TimeZone;

import static com.dateflow.Constants.DATE_FORMAT;
import static com.dateflow.Constants.TIME_ZONE;

public class DateFlow {

    private DateFlow() {
    }

    public static OperationsFlow now() {
        return new OperationsFlow();
    }

    public static OperationsFlow from(LocalDate localDate) {
        var operationsFlow = new OperationsFlow();
        operationsFlow.instant = localDate
                .atStartOfDay()
                .atZone(operationsFlow.zoneId)
                .toInstant();
        return operationsFlow;
    }

    public static OperationsFlow from(LocalDateTime localDateTime, ZoneId zoneId) {
        var operationsFlow = new OperationsFlow();
        operationsFlow.instant = localDateTime
                .atZone(zoneId)
                .toInstant()
                .atZone(operationsFlow.zoneId)
                .toInstant();
        return operationsFlow;
    }

    public static OperationsFlow from(long millis) {
        var operationsFlow = new OperationsFlow();
        operationsFlow.instant = Instant
                .ofEpochMilli(millis)
                .atZone(operationsFlow.zoneId)
                .toInstant();
        return operationsFlow;
    }

    public static OperationsFlow from(Instant instant) {
        var operationsFlow = new OperationsFlow();
        operationsFlow.instant = instant
                .atZone(operationsFlow.zoneId)
                .toInstant();
        return operationsFlow;
    }

    public static OperationsFlow from(String date, String dateFormat, TimeZone timeZone) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        format.setTimeZone(timeZone);
        format.setLenient(false);
        Date dateParsed = format.parse(date);
        return fromUTC(dateParsed);
    }

    public static OperationsFlow fromUTC(LocalDate localDate) {
        var operationsFlow = new OperationsFlow();
        operationsFlow.instant = localDate.atStartOfDay()
                .toInstant(ZoneOffset.UTC);
        return operationsFlow;
    }

    public static OperationsFlow fromUTC(LocalDateTime dateTime) {
        var operationsFlow = new OperationsFlow();
        operationsFlow.instant = dateTime
                .toInstant(ZoneOffset.UTC);
        return operationsFlow;
    }

    public static OperationsFlow fromUTC(String date) throws ParseException {
        return fromUTC(date, DATE_FORMAT);
    }

    public static OperationsFlow fromUTC(String date, String dateFormat) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        format.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        format.setLenient(false);
        Date dateParsed = format.parse(date);
        return fromUTC(dateParsed);
    }

    public static OperationsFlow fromUTC(Date date) {
        var operationsFlow = new OperationsFlow();
        operationsFlow.instant = ZonedDateTime
                .ofInstant(date.toInstant(), operationsFlow.zoneId)
                .toInstant();
        return operationsFlow;
    }
}
