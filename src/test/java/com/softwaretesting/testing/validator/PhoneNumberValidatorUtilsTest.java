package com.softwaretesting.testing.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberValidatorUtilsTest {


    @ParameterizedTest
    @CsvFileSource(resources = "PhoneNumberValidatorUtilsData.csv")
    void testDataSet(String have, String want) {
        assertEquals(want, PhoneNumberValidatorUtils.clean(have));
    }

    @Test
    void shouldRemoveRegexSensitiveSpecialCharacterDot() {
        String have = "#^$*.[]{}()\\\\+?|\";\n";
        String want = "";
        assertEquals(PhoneNumberValidatorUtils.clean(have), want);
    }

    @Test
    void shouldFailWithNull() {
        String input = null;
        assertThrows(NullPointerException.class, () -> PhoneNumberValidatorUtils.clean(input));
    }
}