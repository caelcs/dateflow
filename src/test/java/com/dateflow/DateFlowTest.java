package com.dateflow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junitpioneer.jupiter.DefaultTimeZone;

import java.text.ParseException;
import java.time.*;
import java.util.Date;

import static com.dateflow.Constants.TIME_ZONE;
import static org.assertj.core.api.Assertions.assertThat;

class DateFlowTest {

    @Test
    void shouldCreateDateFromInstant() {
        //Given
        var now = Instant.now();

        //When
        var operationsFlow = DateFlow.fromUTC(now);

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
                .isNotNull()
                .isBeforeOrEqualTo(now);
    }

    @Test
    void shouldCreateDateFromNow() {
        //When
        var operationsFlow = DateFlow.now();

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
                .isNotNull()
                .isBeforeOrEqualTo(Instant.now());
    }

    @Test
    void shouldCreateDateFromMillis() {
        //When
        var operationsFlow = DateFlow.fromUTC(Instant.now().toEpochMilli());

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
                .isNotNull()
                .isBeforeOrEqualTo(Instant.now());
    }

    @Nested
    @DisplayName("date is UTC and no transformation to specific time is required")
    class UTCToUTCTest {

        @Test
        void shouldCreateDateFromLocalDate() {
            //When
            var now = LocalDate.now();
            var operationsFlow = DateFlow.fromUTC(now);

            //Then
            assertThat(operationsFlow).isNotNull();
            assertBaseDateFlow(operationsFlow);
            assertThat(operationsFlow.instant)
                    .isNotNull()
                    .satisfies((e -> {
                        assertThat(e.atZone(operationsFlow.zoneId).getYear()).isEqualTo(now.getYear());
                        assertThat(e.atZone(operationsFlow.zoneId).getMonth()).isEqualTo(now.getMonth());
                        assertThat(e.atZone(operationsFlow.zoneId).getDayOfMonth()).isEqualTo(now.getDayOfMonth());
                        assertThat(e.atZone(operationsFlow.zoneId).getHour()).isZero();
                        assertThat(e.atZone(operationsFlow.zoneId).getMinute()).isZero();
                        assertThat(e.atZone(operationsFlow.zoneId).getSecond()).isZero();
                    }));
        }

        @Test
        void shouldCreateDateFromLocalDateTime() {
            //When
            var now = LocalDateTime.now();
            var operationsFlow = DateFlow.fromUTC(now);

            //Then
            assertThat(operationsFlow).isNotNull();
            assertBaseDateFlow(operationsFlow);
            assertThat(operationsFlow.instant)
                    .isNotNull()
                    .satisfies((e -> {
                        assertThat(e.atZone(operationsFlow.zoneId).getYear()).isEqualTo(now.getYear());
                        assertThat(e.atZone(operationsFlow.zoneId).getMonth()).isEqualTo(now.getMonth());
                        assertThat(e.atZone(operationsFlow.zoneId).getDayOfMonth()).isEqualTo(now.getDayOfMonth());
                        assertThat(e.atZone(operationsFlow.zoneId).getHour()).isEqualTo(now.getHour());
                        assertThat(e.atZone(operationsFlow.zoneId).getMinute()).isEqualTo(now.getMinute());
                        assertThat(e.atZone(operationsFlow.zoneId).getSecond()).isEqualTo(now.getSecond());
                    }));
        }

        @Test
        void shouldCreateDateFromString() throws ParseException {
            //Given
            var date = "2021-12-04T22:35:46.712Z";

            //When
            var operationsFlow = DateFlow.fromUTC(date);

            //Then
            assertThat(operationsFlow).isNotNull();
            assertBaseDateFlow(operationsFlow);
            assertThat(operationsFlow.instant)
                    .isNotNull()
                    .satisfies(it -> {
                        assertThat(it.atZone(operationsFlow.zoneId).getYear()).isEqualTo(2021);
                        assertThat(it.atZone(operationsFlow.zoneId).getMonth()).isEqualTo(Month.DECEMBER);
                        assertThat(it.atZone(operationsFlow.zoneId).getDayOfMonth()).isEqualTo(4);
                        assertThat(it.atZone(operationsFlow.zoneId).getHour()).isEqualTo(22);
                        assertThat(it.atZone(operationsFlow.zoneId).getMinute()).isEqualTo(35);
                        assertThat(it.atZone(operationsFlow.zoneId).getSecond()).isEqualTo(46);
                    });
        }

        @ParameterizedTest
        @CsvSource({
                "2021-12-04T22:35:46.777Z,yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                "2021-12-04T22:35:46Z,yyyy-MM-dd'T'HH:mm:ss'Z'",
                "2021-12-04T22:35:46,yyyy-MM-dd'T'HH:mm:ss"})
        void shouldCreateDateFromStringAndFormat(String date, String dateFormat) throws ParseException {
            //When
            var operationsFlow = DateFlow.fromUTC(date, dateFormat);

            //Then
            assertThat(operationsFlow).isNotNull();
            assertBaseDateFlow(operationsFlow);
            assertThat(operationsFlow.instant)
                    .isNotNull()
                    .satisfies(it -> {
                        assertThat(it.atZone(operationsFlow.zoneId).getYear()).isEqualTo(2021);
                        assertThat(it.atZone(operationsFlow.zoneId).getMonth()).isEqualTo(Month.DECEMBER);
                        assertThat(it.atZone(operationsFlow.zoneId).getDayOfMonth()).isEqualTo(4);
                        assertThat(it.atZone(operationsFlow.zoneId).getHour()).isEqualTo(22);
                        assertThat(it.atZone(operationsFlow.zoneId).getMinute()).isEqualTo(35);
                        assertThat(it.atZone(operationsFlow.zoneId).getSecond()).isEqualTo(46);
                    });
        }

        @Test
        void shouldCreateDateFromStringAndDateFormat() throws ParseException {
            var date = "2021-12-04";
            var dateFormat = "yyyy-MM-dd";

            //When
            var operationsFlow = DateFlow.fromUTC(date, dateFormat);

            //Then
            assertThat(operationsFlow).isNotNull();
            assertBaseDateFlow(operationsFlow);
            assertThat(operationsFlow.instant)
                    .isNotNull()
                    .satisfies(it -> {
                        assertThat(it.atZone(operationsFlow.zoneId).getYear()).isEqualTo(2021);
                        assertThat(it.atZone(operationsFlow.zoneId).getMonth()).isEqualTo(Month.DECEMBER);
                        assertThat(it.atZone(operationsFlow.zoneId).getDayOfMonth()).isEqualTo(4);
                        assertThat(it.atZone(operationsFlow.zoneId).getHour()).isZero();
                        assertThat(it.atZone(operationsFlow.zoneId).getMinute()).isZero();
                        assertThat(it.atZone(operationsFlow.zoneId).getSecond()).isZero();
                    });
        }

        @Test
        void shouldCreateDateFromDate() {
            //When
            var operationsFlow = DateFlow.fromUTC(new Date());

            //Then
            assertThat(operationsFlow).isNotNull();
            assertBaseDateFlow(operationsFlow);
            assertThat(operationsFlow.instant)
                    .isNotNull()
                    .isBeforeOrEqualTo(Instant.now());
        }
    }

    @Nested
    @DisplayName("transforming to UTC assuming the date is Not UTC")
    class NonUTCToUTCTest {

        @Test
        void shouldCreateDateFromLocalDateTime() {
            //When
            var localDateTime = LocalDateTime.parse("2021-12-04T22:35:46");
            var operationsFlow = DateFlow.from(localDateTime, ZoneId.of("Australia/Sydney"));

            //Then
            assertThat(operationsFlow).isNotNull();
            assertBaseDateFlow(operationsFlow);
            assertThat(operationsFlow.instant)
                    .isNotNull()
                    .satisfies(e -> {
                        assertThat(e.atZone(operationsFlow.zoneId).getYear()).isEqualTo(localDateTime.getYear());
                        assertThat(e.atZone(operationsFlow.zoneId).getMonth()).isEqualTo(localDateTime.getMonth());
                        assertThat(e.atZone(operationsFlow.zoneId).getDayOfMonth()).isEqualTo(localDateTime.getDayOfMonth());
                        assertThat(e.atZone(operationsFlow.zoneId).getHour()).isEqualTo(11);
                        assertThat(e.atZone(operationsFlow.zoneId).getMinute()).isEqualTo(localDateTime.getMinute());
                        assertThat(e.atZone(operationsFlow.zoneId).getSecond()).isEqualTo(localDateTime.getSecond());
                    });
        }

        @ParameterizedTest
        @CsvSource({
                "2021-12-04T22:35:46,yyyy-MM-dd'T'HH:mm:ss",
                "2021-12-04T22:35,yyyy-MM-dd'T'HH:mm"})
        void shouldCreateDateFromStringAndFormat(String date, String dateFormat) throws ParseException {
            //When
            var operationsFlow = DateFlow.from(date, dateFormat, ZoneId.of("Australia/Sydney"));
            var localDateTime = LocalDateTime.parse(date);
            //Then
            assertThat(operationsFlow).isNotNull();
            assertBaseDateFlow(operationsFlow);
            assertThat(operationsFlow.instant)
                    .isNotNull()
                    .satisfies(it -> {
                        assertThat(it.atZone(operationsFlow.zoneId).getYear()).isEqualTo(2021);
                        assertThat(it.atZone(operationsFlow.zoneId).getMonth()).isEqualTo(Month.DECEMBER);
                        assertThat(it.atZone(operationsFlow.zoneId).getDayOfMonth()).isEqualTo(4);
                        assertThat(it.atZone(operationsFlow.zoneId).getHour()).isEqualTo(11);
                        assertThat(it.atZone(operationsFlow.zoneId).getMinute()).isEqualTo(localDateTime.getMinute());
                        assertThat(it.atZone(operationsFlow.zoneId).getSecond()).isEqualTo(localDateTime.getSecond());
                    });
        }

        @Test
        void shouldCreateDateFromStringAndDateFormat() throws ParseException {
            var date = "2021-12-04";
            var dateFormat = "yyyy-MM-dd";

            //When
            var operationsFlow = DateFlow
                    .from(date, dateFormat, ZoneId.of("Australia/Sydney"));

            //Then
            assertThat(operationsFlow).isNotNull();
            assertBaseDateFlow(operationsFlow);
            assertThat(operationsFlow.instant)
                    .isNotNull()
                    .satisfies(it -> {
                        assertThat(it.atZone(operationsFlow.zoneId).getYear()).isEqualTo(2021);
                        assertThat(it.atZone(operationsFlow.zoneId).getMonth()).isEqualTo(Month.DECEMBER);
                        assertThat(it.atZone(operationsFlow.zoneId).getDayOfMonth()).isEqualTo(3);
                        assertThat(it.atZone(operationsFlow.zoneId).getHour()).isEqualTo(13);
                        assertThat(it.atZone(operationsFlow.zoneId).getMinute()).isZero();
                        assertThat(it.atZone(operationsFlow.zoneId).getSecond()).isZero();
                    });
        }

        @Test
        void shouldCreateDateFromStringAndTimeFormat() throws ParseException {
            var date = "2021-12-04T23:04";
            var dateFormat = "yyyy-MM-dd'T'HH:mm";

            //When
            var operationsFlow = DateFlow
                    .from(date, dateFormat, ZoneId.of("Australia/Sydney"));

            //Then
            assertThat(operationsFlow).isNotNull();
            assertBaseDateFlow(operationsFlow);
            assertThat(operationsFlow.instant)
                    .isNotNull()
                    .satisfies(it -> {
                        assertThat(it.atZone(operationsFlow.zoneId).getYear()).isEqualTo(2021);
                        assertThat(it.atZone(operationsFlow.zoneId).getMonth()).isEqualTo(Month.DECEMBER);
                        assertThat(it.atZone(operationsFlow.zoneId).getDayOfMonth()).isEqualTo(4);
                        assertThat(it.atZone(operationsFlow.zoneId).getHour()).isEqualTo(12);
                        assertThat(it.atZone(operationsFlow.zoneId).getMinute()).isEqualTo(4);
                        assertThat(it.atZone(operationsFlow.zoneId).getSecond()).isZero();
                    });
        }
    }

    @Nested
    @DisplayName("transforming to UTC assuming the date is default zone")
    @DefaultTimeZone("Australia/Sydney")
    class DefaultZoneToUTCTest {

        @Test
        void shouldCreateDateFromLocalDateTime() {
            //When
            var localDateTime = LocalDateTime.parse("2021-12-04T22:35:46");
            var operationsFlow = DateFlow.from(localDateTime);

            //Then
            assertThat(operationsFlow).isNotNull();
            assertBaseDateFlow(operationsFlow);
            assertThat(operationsFlow.instant)
                    .isNotNull()
                    .satisfies(e -> {
                        assertThat(e.atZone(operationsFlow.zoneId).getYear()).isEqualTo(localDateTime.getYear());
                        assertThat(e.atZone(operationsFlow.zoneId).getMonth()).isEqualTo(localDateTime.getMonth());
                        assertThat(e.atZone(operationsFlow.zoneId).getDayOfMonth()).isEqualTo(localDateTime.getDayOfMonth());
                        assertThat(e.atZone(operationsFlow.zoneId).getHour()).isEqualTo(11);
                        assertThat(e.atZone(operationsFlow.zoneId).getMinute()).isEqualTo(localDateTime.getMinute());
                        assertThat(e.atZone(operationsFlow.zoneId).getSecond()).isEqualTo(localDateTime.getSecond());
                    });
        }

        @ParameterizedTest
        @CsvSource({
                "2021-12-04T22:35:46,yyyy-MM-dd'T'HH:mm:ss",
                "2021-12-04T22:35,yyyy-MM-dd'T'HH:mm"})
        void shouldCreateDateFromStringAndFormat(String date, String dateFormat) throws ParseException {
            //When
            var operationsFlow = DateFlow.from(date, dateFormat);
            var localDateTime = LocalDateTime.parse(date);
            //Then
            assertThat(operationsFlow).isNotNull();
            assertBaseDateFlow(operationsFlow);
            assertThat(operationsFlow.instant)
                    .isNotNull()
                    .satisfies(it -> {
                        assertThat(it.atZone(operationsFlow.zoneId).getYear()).isEqualTo(2021);
                        assertThat(it.atZone(operationsFlow.zoneId).getMonth()).isEqualTo(Month.DECEMBER);
                        assertThat(it.atZone(operationsFlow.zoneId).getDayOfMonth()).isEqualTo(4);
                        assertThat(it.atZone(operationsFlow.zoneId).getHour()).isEqualTo(11);
                        assertThat(it.atZone(operationsFlow.zoneId).getMinute()).isEqualTo(localDateTime.getMinute());
                        assertThat(it.atZone(operationsFlow.zoneId).getSecond()).isEqualTo(localDateTime.getSecond());
                    });
        }

        @Test
        void shouldCreateDateFromStringAndDateFormat() throws ParseException {
            var date = "2021-12-04";
            var dateFormat = "yyyy-MM-dd";

            //When
            var operationsFlow = DateFlow
                    .from(date, dateFormat);

            //Then
            assertThat(operationsFlow).isNotNull();
            assertBaseDateFlow(operationsFlow);
            assertThat(operationsFlow.instant)
                    .isNotNull()
                    .satisfies(it -> {
                        assertThat(it.atZone(operationsFlow.zoneId).getYear()).isEqualTo(2021);
                        assertThat(it.atZone(operationsFlow.zoneId).getMonth()).isEqualTo(Month.DECEMBER);
                        assertThat(it.atZone(operationsFlow.zoneId).getDayOfMonth()).isEqualTo(3);
                        assertThat(it.atZone(operationsFlow.zoneId).getHour()).isEqualTo(13);
                        assertThat(it.atZone(operationsFlow.zoneId).getMinute()).isZero();
                        assertThat(it.atZone(operationsFlow.zoneId).getSecond()).isZero();
                    });
        }

        @Test
        void shouldCreateDateFromStringAndTimeFormat() throws ParseException {
            var date = "2021-12-04T23:04";
            var dateFormat = "yyyy-MM-dd'T'HH:mm";

            //When
            var operationsFlow = DateFlow
                    .from(date, dateFormat);

            //Then
            assertThat(operationsFlow).isNotNull();
            assertBaseDateFlow(operationsFlow);
            assertThat(operationsFlow.instant)
                    .isNotNull()
                    .satisfies(it -> {
                        assertThat(it.atZone(operationsFlow.zoneId).getYear()).isEqualTo(2021);
                        assertThat(it.atZone(operationsFlow.zoneId).getMonth()).isEqualTo(Month.DECEMBER);
                        assertThat(it.atZone(operationsFlow.zoneId).getDayOfMonth()).isEqualTo(4);
                        assertThat(it.atZone(operationsFlow.zoneId).getHour()).isEqualTo(12);
                        assertThat(it.atZone(operationsFlow.zoneId).getMinute()).isEqualTo(4);
                        assertThat(it.atZone(operationsFlow.zoneId).getSecond()).isZero();
                    });
        }
    }

    static void assertBaseDateFlow(OperationsFlow operationsFlow) {
        assertThat(operationsFlow.zoneId).isNotNull()
                .satisfies(it -> assertThat(it.getId()).isEqualTo(TIME_ZONE));
        assertThat(operationsFlow.timeZone).isNotNull()
                .satisfies(it -> assertThat(it.toZoneId().getId()).isEqualTo(TIME_ZONE));
    }
}
