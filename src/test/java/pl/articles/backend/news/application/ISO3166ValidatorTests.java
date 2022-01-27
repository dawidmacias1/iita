package pl.articles.backend.news.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class ISO3166ValidatorTests {

    @ParameterizedTest
    @ValueSource(strings = {"pl", "US", "fr", "de", "ua", "IT", "gb"})
    void validate_shouldReturnTrueIfCountryCodeIsValid(String country) {
        //when
        boolean result = ISO3166Validator.validate(country);

        //then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"pln", "11", "AAA", "test", "@#$", "poland", "  "})
    void validate_shouldReturnFalseIfCountryCodeIsInvalid(String country) {
        //when
        boolean result = ISO3166Validator.validate(country);

        //then
        assertThat(result).isFalse();
    }

    @Test
    void validate_shouldReturnFalseIfCountryCodeIsNull() {
        //given
        String country = null;

        //when
        boolean result = ISO3166Validator.validate(country);

        //then
        assertThat(result).isFalse();
    }
}
