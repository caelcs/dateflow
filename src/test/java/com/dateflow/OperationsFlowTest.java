package com.dateflow;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.Month;
import java.time.ZoneOffset;

import static com.dateflow.Constants.TIME_ZONE;
import static com.dateflow.DateFlowTest.assertBaseDateFlow;
import static org.assertj.core.api.Assertions.assertThat;

class OperationsFlowTest {

    @Test
    void shouldResetMidNightTime() {
        //When
        var operationsFlow = new OperationsFlow()
                .resetMidnightTime();

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
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
        var operationsFlow = new OperationsFlow()
                .resetTimeToLastSecondOfDay();

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
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
        var daoperationsFloweFlow = getOperationsFlow()
                .plusMonths(2);

        //Then
        assertThat(daoperationsFloweFlow).isNotNull();
        assertBaseDateFlow(daoperationsFloweFlow);
        assertThat(daoperationsFloweFlow.instant)
                .isNotNull()
                .satisfies(it ->
                        assertThat(it.atZone(daoperationsFloweFlow.zoneId).getMonth())
                                .isEqualTo(Month.FEBRUARY));
    }

    @Test
    void shouldAddDays() {
        //When
        var operationsFlow = getOperationsFlow()
                .plusDays(2);

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
                .isNotNull()
                .satisfies(it ->
                        assertThat(it.atZone(operationsFlow.zoneId).getDayOfMonth())
                                .isEqualTo(6));
    }

    @Test
    void shouldAddMinutes() {
        //When
        var operationsFlow = getOperationsFlow()
                .plusMinutes(2);

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
                .isNotNull()
                .satisfies(it ->
                        assertThat(it.atZone(operationsFlow.zoneId).getMinute())
                                .isEqualTo(37));
    }

    @Test
    void shouldAddHours() {
        //When
        var operationsFlow = getOperationsFlow()
                .plusHours(2);

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
                .isNotNull()
                .satisfies(it ->
                        assertThat(it.atZone(operationsFlow.zoneId).getHour())
                                .isZero());
    }

    @Test
    void shouldAddSeconds() {
        //When
        var operationsFlow = getOperationsFlow()
                .plusSeconds(2);

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
                .isNotNull()
                .satisfies(it ->
                        assertThat(it.atZone(operationsFlow.zoneId).getSecond())
                                .isEqualTo(48));
    }

    @Test
    void shouldSubtractSeconds() {
        //When
        var operationsFlow = getOperationsFlow()
                .minusSeconds(2);

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
                .isNotNull()
                .satisfies(it ->
                        assertThat(it.atZone(operationsFlow.zoneId).getSecond())
                                .isEqualTo(44));
    }

    @Test
    void shouldSubtractMinutes() {
        //When
        var operationsFlow = getOperationsFlow()
                .minusMinutes(2);

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
                .isNotNull()
                .satisfies(it ->
                        assertThat(it.atZone(operationsFlow.zoneId).getMinute())
                                .isEqualTo(33));
    }

    @Test
    void shouldSubtractHours() {
        //When
        var operationsFlow = getOperationsFlow()
                .minusHours(2);

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
                .isNotNull()
                .satisfies(it ->
                        assertThat(it.atZone(operationsFlow.zoneId).getHour())
                                .isEqualTo(20));
    }

    @Test
    void shouldSubtractDays() {
        //When
        var operationsFlow = getOperationsFlow()
                .minusDays(2);

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
                .isNotNull()
                .satisfies(it ->
                        assertThat(it.atZone(operationsFlow.zoneId).getDayOfMonth())
                                .isEqualTo(2));
    }

    @Test
    void shouldSubtractMonths() {
        //When
        var operationsFlow = getOperationsFlow()
                .minusMonths(2);

        //Then
        assertThat(operationsFlow).isNotNull();
        assertBaseDateFlow(operationsFlow);
        assertThat(operationsFlow.instant)
                .isNotNull()
                .satisfies(it ->
                        assertThat(it.atZone(operationsFlow.zoneId).getMonth())
                                .isEqualTo(Month.OCTOBER));
    }

    @Test
    void shouldReturnTransformerFlow() {
        //When
        TransformerFlow transformerFlow = getOperationsFlow().as();

        //Then
        assertThat(transformerFlow)
                .isNotNull()
                .satisfies((it) -> {
                    assertThat(it.instant).isEqualTo(Instant.parse("2021-12-04T22:35:46.786Z"));
                    assertThat(it.zoneId.toString()).hasToString(TIME_ZONE);
                    assertThat(it.outputZoneId.toString()).hasToString(TIME_ZONE);
                    assertThat(it.timeZone).isNotNull();
                });
    }

    private OperationsFlow getOperationsFlow() {
        OperationsFlow operationsFlow = new OperationsFlow();
        operationsFlow.instant = Instant.parse("2021-12-04T22:35:46.786Z");
        return operationsFlow;
    }


}