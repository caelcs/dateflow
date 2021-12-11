package com.dateflow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.TimeZone;

import static com.dateflow.Constants.TIME_ZONE;
import static org.assertj.core.api.Assertions.assertThat;

class TransformerFlowTest {

    @Test
    void shouldReturnDate() throws ParseException {
        //Given
        ZoneId zoneId = ZoneId.of(TIME_ZONE);
        String currentDate = "2021-12-04T22:35:46.786Z";

        //When
        var result = getTransformerFlow(zoneId, currentDate).date();

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
    void shouldReturnInstant() {
        //Given
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        String currentDate = "2021-12-04T22:35:46.786Z";

        //When
        var result = getTransformerFlow(zoneId, currentDate).instant();

        //Then
        assertThat(result)
                .isNotNull();
    }

    @Test
    void shouldReturnLocalDate() {
        //Given
        ZoneId zoneId = ZoneId.of(TIME_ZONE);
        String currentDate = "2021-12-04T22:35:46.786Z";

        //When
        var result = getTransformerFlow(zoneId, currentDate).localDate();

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
        //Given
        ZoneId zoneId = ZoneId.of(TIME_ZONE);
        String currentDate = "2021-12-04T22:35:46.786Z";

        //When
        var result = getTransformerFlow(zoneId, currentDate).localDateTime();

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
        //Given
        ZoneId zoneId = ZoneId.of(TIME_ZONE);
        String currentDate = "2021-12-04T22:35:46.786Z";

        //When
        var result = getTransformerFlow(zoneId, currentDate).string(format);

        //Then
        assertThat(result)
                .isNotNull()
                .isEqualTo(expectedDate);
    }

    @Test
    void shouldGetDay() {
        //Given
        ZoneId zoneId = ZoneId.of(TIME_ZONE);
        String currentDate = "2021-12-04T22:35:46.786Z";

        //When
        var result = getTransformerFlow(zoneId, currentDate).day();

        //Then
        assertThat(result)
                .isEqualTo(4);
    }

    @Test
    void shouldGetMonth() {
        //Given
        ZoneId zoneId = ZoneId.of(TIME_ZONE);
        String currentDate = "2021-12-04T22:35:46.786Z";

        //When
        var result = getTransformerFlow(zoneId, currentDate).month();

        //Then
        assertThat(result)
                .isEqualTo(12);
    }

    @Test
    void shouldGetYear() {
        //Given
        ZoneId zoneId = ZoneId.of(TIME_ZONE);
        String currentDate = "2021-12-04T22:35:46.786Z";

        //When
        var result = getTransformerFlow(zoneId, currentDate).year();

        //Then
        assertThat(result)
                .isEqualTo(2021);
    }

    @Test
    void shouldGetHour() {
        //Given
        ZoneId zoneId = ZoneId.of(TIME_ZONE);
        String currentDate = "2021-12-04T22:35:46.786Z";

        //When
        var result = getTransformerFlow(zoneId, currentDate).hour();

        //Then
        assertThat(result)
                .isEqualTo(22);
    }

    @Test
    void shouldGetMinute() {
        //Given
        ZoneId zoneId = ZoneId.of(TIME_ZONE);
        String currentDate = "2021-12-04T22:35:46.786Z";

        //When
        var result = getTransformerFlow(zoneId, currentDate).minutes();

        //Then
        assertThat(result)
                .isEqualTo(35);
    }

    @Test
    void shouldGetSeconds() {
        //Given
        ZoneId zoneId = ZoneId.of(TIME_ZONE);
        String currentDate = "2021-12-04T22:35:46.786Z";

        //When
        var result = getTransformerFlow(zoneId, currentDate).seconds();

        //Then
        assertThat(result)
                .isEqualTo(46);
    }

    private TransformerFlow getTransformerFlow(ZoneId zoneId, String currentDate) {
        return new TransformerFlow(zoneId, Instant.parse(currentDate), TimeZone.getTimeZone(zoneId), zoneId);
    }

}