package com.dateflow;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class DateFlowTest {

    @Test
    void shouldCreateDateFromNow() {
        //When
        DateFlow dateFlow = DateFlow.now();

        //Then
        assertThat(dateFlow).isNotNull();
        assertThat(dateFlow.clock).isNotNull();
        assertThat(dateFlow.timeZone).isNotNull();
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
        assertThat(dateFlow.clock).isNotNull();
        assertThat(dateFlow.timeZone).isNotNull();
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
        assertThat(dateFlow.clock).isNotNull();
        assertThat(dateFlow.timeZone).isNotNull();
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
        assertThat(dateFlow.clock).isNotNull();
        assertThat(dateFlow.timeZone).isNotNull();
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
        assertThat(dateFlow.clock).isNotNull();
        assertThat(dateFlow.timeZone).isNotNull();
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
        assertThat(dateFlow.clock).isNotNull();
        assertThat(dateFlow.timeZone).isNotNull();
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
        assertThat(dateFlow.clock).isNotNull();
        assertThat(dateFlow.timeZone).isNotNull();
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
                .addMonths(2);

        //Then
        assertThat(dateFlow).isNotNull();
        assertThat(dateFlow.clock).isNotNull();
        assertThat(dateFlow.timeZone).isNotNull();
        assertThat(dateFlow.instant)
                .isNotNull()
                .satisfies(it ->
                    assertThat(it.atZone(dateFlow.clock.getZone()).getMonth())
                            .isEqualTo(Month.FEBRUARY));
    }

    @Test
    void shouldAddDays() {
        //When
        DateFlow dateFlow = DateFlow.from(1638657346786L)
                .addDays(2);

        //Then
        assertThat(dateFlow).isNotNull();
        assertThat(dateFlow.clock).isNotNull();
        assertThat(dateFlow.timeZone).isNotNull();
        assertThat(dateFlow.instant)
                .isNotNull()
                .satisfies(it ->
                        assertThat(it.atZone(dateFlow.clock.getZone()).getDayOfMonth())
                                .isEqualTo(6));
    }
}
