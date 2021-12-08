package com.dateflow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;
import java.time.*;
import java.util.Date;

import static com.dateflow.DateFlow.TIME_ZONE;
import static org.assertj.core.api.Assertions.assertThat;

class DateFlowTest {

    @Test
    void shouldCreateDateFromNow() {
        //When
        var dateFlow = DateFlow.now();

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .isBeforeOrEqualTo(Instant.now());
    }

    @Test
    void shouldCreateDateFromLocalDate() {
        //When
        var dateFlow = DateFlow.from(LocalDate.now());

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .isBeforeOrEqualTo(Instant.now());
    }

    @Test
    void shouldCreateDateFromLocalDateTime() {
        //When
        var dateFlow = DateFlow.from(LocalDateTime.now());

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .isBeforeOrEqualTo(Instant.now());
    }

    @Test
    void shouldCreateDateFromInstant() {
        //Given
        var now = Instant.now();

        //When
        var dateFlow = DateFlow.from(now);

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .isBeforeOrEqualTo(now);
    }

    @Test
    void shouldCreateDateFromString() throws ParseException {
        //Given
        var date = "2021-12-04T22:35:46Z";

        //When
        var dateFlow = DateFlow.from(date);

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .satisfies(it -> {
                    assertThat(it.atZone(dateFlow.zoneId).getYear())
                            .isEqualTo(2021);
                    assertThat(it.atZone(dateFlow.zoneId).getMonth())
                            .isEqualTo(Month.DECEMBER);
                    assertThat(it.atZone(dateFlow.zoneId).getDayOfMonth())
                            .isEqualTo(4);
                    assertThat(it.atZone(dateFlow.zoneId).getHour())
                            .isEqualTo(22);
                    assertThat(it.atZone(dateFlow.zoneId).getMinute())
                            .isEqualTo(35);
                    assertThat(it.atZone(dateFlow.zoneId).getSecond())
                            .isEqualTo(46);
                });
    }

    @ParameterizedTest
    @CsvSource({
            "2021-12-04T22:35:46.777Z,yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            "2021-12-04T22:35:46Z,yyyy-MM-dd'T'HH:mm:ss'Z'",
            "2021-12-04T22:35:46,yyyy-MM-dd'T'HH:mm:ss"})
    void shouldCreateDateFromStringAndFormat(String date, String dateFormat) throws ParseException {
        //When
        var dateFlow = DateFlow.from(date, dateFormat);

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .satisfies(it -> {
                    assertThat(it.atZone(dateFlow.zoneId).getYear())
                            .isEqualTo(2021);
                    assertThat(it.atZone(dateFlow.zoneId).getMonth())
                            .isEqualTo(Month.DECEMBER);
                    assertThat(it.atZone(dateFlow.zoneId).getDayOfMonth())
                            .isEqualTo(4);
                    assertThat(it.atZone(dateFlow.zoneId).getHour())
                            .isEqualTo(22);
                    assertThat(it.atZone(dateFlow.zoneId).getMinute())
                            .isEqualTo(35);
                    assertThat(it.atZone(dateFlow.zoneId).getSecond())
                            .isEqualTo(46);
                });
    }

    @Test
    void shouldCreateDateFromStringAndDateFormat() throws ParseException {
        var date = "2021-12-04";
        var dateFormat = "yyyy-MM-dd";

        //When
        var dateFlow = DateFlow.from(date, dateFormat);

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .satisfies(it -> {
                    assertThat(it.atZone(dateFlow.zoneId).getYear())
                            .isEqualTo(2021);
                    assertThat(it.atZone(dateFlow.zoneId).getMonth())
                            .isEqualTo(Month.DECEMBER);
                    assertThat(it.atZone(dateFlow.zoneId).getDayOfMonth())
                            .isEqualTo(4);
                    assertThat(it.atZone(dateFlow.zoneId).getHour())
                            .isZero();
                    assertThat(it.atZone(dateFlow.zoneId).getMinute())
                            .isZero();
                    assertThat(it.atZone(dateFlow.zoneId).getSecond())
                            .isZero();
                });
    }

    @Test
    void shouldCreateDateFromStringAndTimeFormat() throws ParseException {
        var date = "2021-12-04";
        var dateFormat = "yyyy-MM-dd";

        //When
        var dateFlow = DateFlow.from(date, dateFormat);

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .satisfies(it -> {
                    assertThat(it.atZone(dateFlow.zoneId).getYear())
                            .isEqualTo(2021);
                    assertThat(it.atZone(dateFlow.zoneId).getMonth())
                            .isEqualTo(Month.DECEMBER);
                    assertThat(it.atZone(dateFlow.zoneId).getDayOfMonth())
                            .isEqualTo(4);
                    assertThat(it.atZone(dateFlow.zoneId).getHour())
                            .isZero();
                    assertThat(it.atZone(dateFlow.zoneId).getMinute())
                            .isZero();
                    assertThat(it.atZone(dateFlow.zoneId).getSecond())
                            .isZero();
                });
    }


    @Test
    void shouldCreateDateFromDate() {
        //When
        var dateFlow = DateFlow.from(new Date());

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .isBeforeOrEqualTo(Instant.now());
    }

    @Test
    void shouldCreateDateFromMillis() {
        //When
        var dateFlow = DateFlow.from(Instant.now().toEpochMilli());

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .isBeforeOrEqualTo(Instant.now());
    }

    @Test
    void shouldResetMidNightTime() {
        //When
        var dateFlow = DateFlow.now()
                .resetMidnightTime();

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .satisfies(it -> {
                    assertThat(it.atZone(ZoneOffset.UTC).getSecond()).isZero();
                    assertThat(it.atZone(ZoneOffset.UTC).getMinute()).isZero();
                    assertThat(it.atZone(ZoneOffset.UTC).getHour()).isZero();
                });
    }

    @Test
    void shouldResetTimeToLastSecondOfDay() {
        //When
        var dateFlow = DateFlow.now()
                .resetTimeToLastSecondOfDay();

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .satisfies(it -> {
                    assertThat(it.atZone(ZoneOffset.UTC).getSecond()).isEqualTo(59);
                    assertThat(it.atZone(ZoneOffset.UTC).getMinute()).isEqualTo(59);
                    assertThat(it.atZone(ZoneOffset.UTC).getHour()).isEqualTo(23);
                });
    }

    @Test
    void shouldAddMonths() {
        //When
        var dateFlow = DateFlow.from(Instant.parse("2021-12-04T22:35:46.786Z"))
                .plusMonths(2);

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .satisfies(it ->
                    assertThat(it.atZone(dateFlow.zoneId).getMonth())
                            .isEqualTo(Month.FEBRUARY));
    }

    @Test
    void shouldAddDays() {
        //When
        var dateFlow = DateFlow.from(Instant.parse("2021-12-04T22:35:46.786Z"))
                .plusDays(2);

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .satisfies(it ->
                        assertThat(it.atZone(dateFlow.zoneId).getDayOfMonth())
                                .isEqualTo(6));
    }

    @Test
    void shouldAddMinutes() {
        //When
        var dateFlow = DateFlow.from(Instant.parse("2021-12-04T22:35:46.786Z"))
                .plusMinutes(2);

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .satisfies(it ->
                        assertThat(it.atZone(dateFlow.zoneId).getMinute())
                                .isEqualTo(37));
    }

    @Test
    void shouldAddHours() {
        //When
        var dateFlow = DateFlow.from(Instant.parse("2021-12-04T22:35:46.786Z"))
                .plusHours(2);

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .satisfies(it ->
                        assertThat(it.atZone(dateFlow.zoneId).getHour())
                                .isZero());
    }

    @Test
    void shouldAddSeconds() {
        //When
        var dateFlow = DateFlow.from(Instant.parse("2021-12-04T22:35:46.786Z"))
                .plusSeconds(2);

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .satisfies(it ->
                        assertThat(it.atZone(dateFlow.zoneId).getSecond())
                                .isEqualTo(48));
    }

    @Test
    void shouldSubtractSeconds() {
        //When
        var dateFlow = DateFlow.from(Instant.parse("2021-12-04T22:35:46.786Z"))
                .minusSeconds(2);

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .satisfies(it ->
                        assertThat(it.atZone(dateFlow.zoneId).getSecond())
                                .isEqualTo(44));
    }

    @Test
    void shouldSubtractMinutes() {
        //When
        var dateFlow = DateFlow.from(Instant.parse("2021-12-04T22:35:46.786Z"))
                .minusMinutes(2);

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .satisfies(it ->
                        assertThat(it.atZone(dateFlow.zoneId).getMinute())
                                .isEqualTo(33));
    }

    @Test
    void shouldSubtractHours() {
        //When
        var dateFlow = DateFlow.from(Instant.parse("2021-12-04T22:35:46.786Z"))
                .minusHours(2);

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .satisfies(it ->
                        assertThat(it.atZone(dateFlow.zoneId).getHour())
                                .isEqualTo(20));
    }

    @Test
    void shouldSubtractDays() {
        //When
        var dateFlow = DateFlow.from(Instant.parse("2021-12-04T22:35:46.786Z"))
                .minusDays(2);

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .satisfies(it ->
                        assertThat(it.atZone(dateFlow.zoneId).getDayOfMonth())
                                .isEqualTo(2));
    }

    @Test
    void shouldSubtractMonths() {
        //When
        var dateFlow = DateFlow.from(Instant.parse("2021-12-04T22:35:46.786Z"))
                .minusMonths(2);

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .satisfies(it ->
                        assertThat(it.atZone(dateFlow.zoneId).getMonth())
                                .isEqualTo(Month.OCTOBER));
    }

    @Test
    void shouldReturnDate() {
        //When
        var result = DateFlow.from(Instant.parse("2021-12-04T22:35:46.786Z")).asDate();

        //Then
        assertThat(result)
                .isNotNull()
                .hasDayOfMonth(4)
                .hasMonth(12)
                .hasYear(2021)
                .hasHourOfDay(22)
                .hasMinute(35)
                .hasSecond(46);
    }

    @Test
    void shouldReturnLocalDate() {
        //When
        var result = DateFlow.from(Instant.parse("2021-12-04T22:35:46.786Z")).asLocalDate();

        //Then
        assertThat(result)
                .isNotNull()
                .satisfies(it -> {
                    assertThat(it.getDayOfMonth()).isEqualTo(4);
                    assertThat(it.getMonthValue()).isEqualTo(12);
                    assertThat(it.getYear()).isEqualTo(2021);
                });
    }

    @Test
    void shouldReturnLocalDateTime() {
        //When
        var result = DateFlow.from(Instant.parse("2021-12-04T22:35:46.786Z")).asLocalDateTime();

        //Then
        assertThat(result)
                .isNotNull()
                .satisfies(it -> {
                    assertThat(it.getDayOfMonth()).isEqualTo(4);
                    assertThat(it.getMonthValue()).isEqualTo(12);
                    assertThat(it.getYear()).isEqualTo(2021);
                    assertThat(it.getHour()).isEqualTo(22);
                    assertThat(it.getMinute()).isEqualTo(35);
                    assertThat(it.getSecond()).isEqualTo(46);
                });
    }

    @ParameterizedTest
    @CsvSource({
            "uuuu-MM-dd,2021-12-04"
            })
    void shouldReturnString(String format, String expectedDate) {
        //When
        var result = DateFlow
                .from(Instant.parse("2021-12-04T22:35:46.786Z"))
                .asString(format);

        //Then
        assertThat(result)
                .isNotNull()
                .isEqualTo(expectedDate);
    }

    @Test
    void shouldChangeTimeZone() {
        //When
        var result = DateFlow
                .from(Instant.parse("2021-12-04T22:35:46.786Z"))
                .zoneId(ZoneId.of("Europe/Berlin"))
                .asString(DateFlow.DATE_FORMAT);

        //Then
        assertThat(result)
                .isNotNull()
                .isEqualTo("2021-12-04T23:35:46Z");
    }

    @Test
    void shouldSetUTCTimeZone() {
        //When
        var result = DateFlow
                .from(Instant.parse("2021-12-04T22:35:46.786Z"))
                .zoneIdUTC()
                .asString(DateFlow.DATE_FORMAT);

        //Then
        assertThat(result)
                .isNotNull()
                .isEqualTo("2021-12-04T22:35:46Z");
    }

    @Test
    void shouldGetDay() {
        //When
        var result = DateFlow
                .from(Instant.parse("2021-12-04T22:35:46.786Z"))
                .day();

        //Then
        assertThat(result)
                .isEqualTo(4);
    }

    @Test
    void shouldGetMonth() {
        //When
        var result = DateFlow
                .from(Instant.parse("2021-12-04T22:35:46.786Z"))
                .month();

        //Then
        assertThat(result)
                .isEqualTo(12);
    }

    @Test
    void shouldGetYear() {
        //When
        var result = DateFlow
                .from(Instant.parse("2021-12-04T22:35:46.786Z"))
                .year();

        //Then
        assertThat(result)
                .isEqualTo(2021);
    }

    @Test
    void shouldGetHour() {
        //When
        var result = DateFlow
                .from(Instant.parse("2021-12-04T22:35:46.786Z"))
                .hour();

        //Then
        assertThat(result)
                .isEqualTo(22);
    }

    @Test
    void shouldGetMinute() {
        //When
        var result = DateFlow
                .from(Instant.parse("2021-12-04T22:35:46.786Z"))
                .minutes();

        //Then
        assertThat(result)
                .isEqualTo(35);
    }

    @Test
    void shouldGetSeconds() {
        //When
        var result = DateFlow
                .from(Instant.parse("2021-12-04T22:35:46.786Z"))
                .seconds();

        //Then
        assertThat(result)
                .isEqualTo(46);
    }

    private void assertBaseDateFlow(DateFlow dateFlow) {
        assertThat(dateFlow.zoneId).isNotNull()
                .satisfies(it -> assertThat(it.getId()).isEqualTo(TIME_ZONE));
        assertThat(dateFlow.timeZone).isNotNull()
                .satisfies(it -> assertThat(it.toZoneId().getId()).isEqualTo(TIME_ZONE));
    }
}
