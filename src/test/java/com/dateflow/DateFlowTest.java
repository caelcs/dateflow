package com.dateflow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junitpioneer.jupiter.DefaultTimeZone;

import java.sql.Date;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static com.dateflow.Constants.TIME_ZONE;
import static org.assertj.core.api.Assertions.assertThat;

class DateFlowTest {

    @Test
    void shouldCreateDateFromInstant() {
        //Given
        var now = Instant.now();

        //When
        var operationsFlow = DateFlow.from(now);

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
        var operationsFlow = DateFlow.from(Instant.now().toEpochMilli());

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
                .isNotNull()
                .isBeforeOrEqualTo(Instant.now());
    }

    @Test
    void shouldCreateDateFromLocal() {
        //When
        var operationsFlow = DateFlow.from(LocalDate.now());

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
                .isNotNull()
                .isBeforeOrEqualTo(Instant.now());
    }

    @Nested
    @DisplayName("transforming to UTC assuming the date is default zone")
    @DefaultTimeZone("Australia/Sydney")
    class DefaultZoneToUTCTest {

        @Test
        void shouldCreateDateFromDate() {
            //When
            Instant now = Instant.now();
            var date = Date.from(now);
            var operationsFlow = DateFlow.from(date);

            //Then
            assertThat(operationsFlow).isNotNull();
            assertBaseDateFlow(operationsFlow);
            assertThat(operationsFlow.instant)
                    .isNotNull()
                    .satisfies(e -> {
                        assertThat(e.atZone(operationsFlow.zoneId).getYear()).isEqualTo(now.atZone(operationsFlow.zoneId).getYear());
                        assertThat(e.atZone(operationsFlow.zoneId).getMonth()).isEqualTo(now.atZone(operationsFlow.zoneId).getMonth());
                        assertThat(e.atZone(operationsFlow.zoneId).getDayOfMonth()).isEqualTo(now.atZone(operationsFlow.zoneId).getDayOfMonth());
                        assertThat(e.atZone(operationsFlow.zoneId).getHour()).isEqualTo(now.atZone(operationsFlow.zoneId).getHour());
                        assertThat(e.atZone(operationsFlow.zoneId).getMinute()).isEqualTo(now.atZone(operationsFlow.zoneId).getMinute());
                        assertThat(e.atZone(operationsFlow.zoneId).getSecond()).isEqualTo(now.atZone(operationsFlow.zoneId).getSecond());
                    });
        }

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
