package com.dateflow;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Date;

import static com.dateflow.DateFlow.TIME_ZONE;
import static org.assertj.core.api.Assertions.assertThat;

class DateFlowTest {

    @Test
    void shouldCreateDateFromNow() {
        //When
        DateFlow dateFlow = DateFlow.now();

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
        DateFlow dateFlow = DateFlow.from(LocalDate.now());

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
        DateFlow dateFlow = DateFlow.from(LocalDateTime.now());

        //Then
        assertThat(dateFlow).isNotNull();
        assertBaseDateFlow(dateFlow);
        assertThat(dateFlow.instant)
                .isNotNull()
                .isBeforeOrEqualTo(Instant.now());
    }

    @Test
    void shouldCreateDateFromDate() {
        //When
        DateFlow dateFlow = DateFlow.from(new Date());

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
        DateFlow dateFlow = DateFlow.from(Instant.now().toEpochMilli());

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
        DateFlow dateFlow = DateFlow.now()
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
        DateFlow dateFlow = DateFlow.now()
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
        DateFlow dateFlow = DateFlow.from(1638657346786L)
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
        //Instant.parse(2021-12-04T22:35:46.786Z)
        DateFlow dateFlow = DateFlow.from(1638657346786L)
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
        DateFlow dateFlow = DateFlow.from(1638657346786L)
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
        DateFlow dateFlow = DateFlow.from(1638657346786L)
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
        DateFlow dateFlow = DateFlow.from(1638657346786L)
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
        DateFlow dateFlow = DateFlow.from(1638657346786L)
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
        DateFlow dateFlow = DateFlow.from(1638657346786L)
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
        DateFlow dateFlow = DateFlow.from(1638657346786L)
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
        DateFlow dateFlow = DateFlow.from(1638657346786L)
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
        DateFlow dateFlow = DateFlow.from(1638657346786L)
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
        DateFlow dateFlow = DateFlow.from(1638657346786L)
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

    private void assertBaseDateFlow(DateFlow dateFlow) {
        assertThat(dateFlow.zoneId).isNotNull()
                .satisfies(it -> assertThat(it.getId()).isEqualTo(TIME_ZONE));
        assertThat(dateFlow.timeZone).isNotNull()
                .satisfies(it -> assertThat(it.toZoneId().getId()).isEqualTo(TIME_ZONE));
    }
}
