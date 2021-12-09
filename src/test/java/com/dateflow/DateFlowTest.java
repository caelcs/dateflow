package com.dateflow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;

import static com.dateflow.Constants.TIME_ZONE;
import static org.assertj.core.api.Assertions.assertThat;

class DateFlowTest {

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
    void shouldCreateDateFromLocalDate() {
        //When
        var operationsFlow = DateFlow.from(LocalDate.now());

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
                .isNotNull()
                .isBeforeOrEqualTo(Instant.now());
    }

    @Test
    void shouldCreateDateFromLocalDateTime() {
        //When
        var operationsFlow = DateFlow.from(LocalDateTime.now());

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
                .isNotNull()
                .isBeforeOrEqualTo(Instant.now());
    }

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
    void shouldCreateDateFromString() throws ParseException {
        //Given
        var date = "2021-12-04T22:35:46Z";

        //When
        var operationsFlow = DateFlow.from(date);

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
                .isNotNull()
                .satisfies(it -> {
                    assertThat(it.atZone(operationsFlow.zoneId).getYear())
                            .isEqualTo(2021);
                    assertThat(it.atZone(operationsFlow.zoneId).getMonth())
                            .isEqualTo(Month.DECEMBER);
                    assertThat(it.atZone(operationsFlow.zoneId).getDayOfMonth())
                            .isEqualTo(4);
                    assertThat(it.atZone(operationsFlow.zoneId).getHour())
                            .isEqualTo(22);
                    assertThat(it.atZone(operationsFlow.zoneId).getMinute())
                            .isEqualTo(35);
                    assertThat(it.atZone(operationsFlow.zoneId).getSecond())
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
        var operationsFlow = DateFlow.from(date, dateFormat);

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
                .isNotNull()
                .satisfies(it -> {
                    assertThat(it.atZone(operationsFlow.zoneId).getYear())
                            .isEqualTo(2021);
                    assertThat(it.atZone(operationsFlow.zoneId).getMonth())
                            .isEqualTo(Month.DECEMBER);
                    assertThat(it.atZone(operationsFlow.zoneId).getDayOfMonth())
                            .isEqualTo(4);
                    assertThat(it.atZone(operationsFlow.zoneId).getHour())
                            .isEqualTo(22);
                    assertThat(it.atZone(operationsFlow.zoneId).getMinute())
                            .isEqualTo(35);
                    assertThat(it.atZone(operationsFlow.zoneId).getSecond())
                            .isEqualTo(46);
                });
    }

    @Test
    void shouldCreateDateFromStringAndDateFormat() throws ParseException {
        var date = "2021-12-04";
        var dateFormat = "yyyy-MM-dd";

        //When
        var operationsFlow = DateFlow.from(date, dateFormat);

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
                .isNotNull()
                .satisfies(it -> {
                    assertThat(it.atZone(operationsFlow.zoneId).getYear())
                            .isEqualTo(2021);
                    assertThat(it.atZone(operationsFlow.zoneId).getMonth())
                            .isEqualTo(Month.DECEMBER);
                    assertThat(it.atZone(operationsFlow.zoneId).getDayOfMonth())
                            .isEqualTo(4);
                    assertThat(it.atZone(operationsFlow.zoneId).getHour())
                            .isZero();
                    assertThat(it.atZone(operationsFlow.zoneId).getMinute())
                            .isZero();
                    assertThat(it.atZone(operationsFlow.zoneId).getSecond())
                            .isZero();
                });
    }

    @Test
    void shouldCreateDateFromStringAndTimeFormat() throws ParseException {
        var date = "2021-12-04";
        var dateFormat = "yyyy-MM-dd";

        //When
        var operationsFlow = DateFlow.from(date, dateFormat);

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
                .isNotNull()
                .satisfies(it -> {
                    assertThat(it.atZone(operationsFlow.zoneId).getYear())
                            .isEqualTo(2021);
                    assertThat(it.atZone(operationsFlow.zoneId).getMonth())
                            .isEqualTo(Month.DECEMBER);
                    assertThat(it.atZone(operationsFlow.zoneId).getDayOfMonth())
                            .isEqualTo(4);
                    assertThat(it.atZone(operationsFlow.zoneId).getHour())
                            .isZero();
                    assertThat(it.atZone(operationsFlow.zoneId).getMinute())
                            .isZero();
                    assertThat(it.atZone(operationsFlow.zoneId).getSecond())
                            .isZero();
                });
    }


    @Test
    void shouldCreateDateFromDate() {
        //When
        var operationsFlow = DateFlow.from(new Date());

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

    static void assertBaseDateFlow(OperationsFlow operationsFlow) {
        assertThat(operationsFlow.zoneId).isNotNull()
                .satisfies(it -> assertThat(it.getId()).isEqualTo(TIME_ZONE));
        assertThat(operationsFlow.timeZone).isNotNull()
                .satisfies(it -> assertThat(it.toZoneId().getId()).isEqualTo(TIME_ZONE));
    }
}
