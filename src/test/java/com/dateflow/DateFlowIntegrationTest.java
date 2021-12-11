package com.dateflow;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.Instant;
import java.time.ZoneId;

import static com.dateflow.Constants.DATE_FORMAT;
import static org.assertj.core.api.Assertions.assertThat;

class DateFlowIntegrationTest {

    @Test
    void shouldProduceLocalDateTimeWhenZoneIdIsProvided() {
        //Given
        var instant = Instant.parse("2021-12-09T11:33:43.715281Z");

        //When
        var result = DateFlow.
                from(instant)
                .zoneId(ZoneId.of("Asia/Tokyo"))
                .as().localDateTime();

        //Then
        assertThat(result)
                .isEqualTo("2021-12-09T20:33:43.715281");
    }

    @Test
    void shouldProduceLocalTimeUsingUTCByDefault() {
        //Given
        var instant = Instant.parse("2021-12-09T11:33:43.715281Z");

        //When
        var result = DateFlow.
                from(instant)
                .as().localDateTime();

        //Then
        assertThat(result)
                .isEqualTo("2021-12-09T11:33:43.715281");
    }

    @Test
    void shouldProduceDateUsingSpecificTimeZone() throws ParseException {
        //Given
        var instant = Instant.parse("2021-12-09T11:33:43.715281Z");

        //When
        var result = DateFlow.
                from(instant)
                .zoneId(ZoneId.of("Asia/Tokyo"))
                .as().date();

        //Then
        assertThat(result)
                .isEqualTo("2021-12-09T20:33:43.715");
    }

    @Test
    void shouldProduceDateUsingUTCByDefaultTimeZone() throws ParseException {
        //Given
        var instant = Instant.parse("2021-12-09T11:33:43.715281Z");

        //When
        var result = DateFlow.
                from(instant)
                .as().date();

        //Then
        assertThat(result)
                .isEqualTo("2021-12-09T11:33:43.715");
    }

    @Test
    void shouldProduceLocalDateUsingSpecificTimeZone() {
        //Given
        var instant = Instant.parse("2021-12-09T23:33:43.715281Z");

        //When
        var result = DateFlow.
                from(instant)
                .zoneId(ZoneId.of("Asia/Tokyo"))
                .as().localDate();

        //Then
        assertThat(result)
                .isEqualTo("2021-12-10");
    }

    @Test
    void shouldProduceDateLocalDateUsingUTCByDefaultTimeZone() {
        //Given
        var instant = Instant.parse("2021-12-09T11:33:43.715281Z");

        //When
        var result = DateFlow.
                from(instant)
                .as().localDate();

        //Then
        assertThat(result)
                .isEqualTo("2021-12-09");
    }

    @Test
    void shouldProduceStringUsingSpecificTimeZone() {
        //Given
        var instant = Instant.parse("2021-12-09T23:33:43.715281Z");

        //When
        var result = DateFlow.
                from(instant)
                .zoneId(ZoneId.of("Asia/Tokyo"))
                .as().string(DATE_FORMAT);

        //Then
        assertThat(result)
                .isEqualTo("2021-12-10T08:33:43.715Z");
    }

    @Test
    void shouldProduceStringUsingUTCByDefaultTimeZone() {
        //Given
        var instant = Instant.parse("2021-12-09T11:33:43.715281Z");

        //When
        var result = DateFlow.
                from(instant)
                .as().string(DATE_FORMAT);

        //Then
        assertThat(result)
                .isEqualTo("2021-12-09T11:33:43.715Z");
    }
}
