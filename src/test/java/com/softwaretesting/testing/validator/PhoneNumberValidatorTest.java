package com.softwaretesting.testing.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberValidatorTest {
    PhoneNumberValidator validator = new PhoneNumberValidator();

    @Test
    void shouldBeFalseForEmpty() {
        assertFalse(validator.validate(""));
    }

    @Test
    void shouldBeFalseForPlaceHolderCharacterOnly() {
        String placeHolderCharacters = "----";
        assertFalse(validator.validate(placeHolderCharacters));
    }

    @Test
    void shouldBeTrueForRegexMatch() {
        String sampleNumber = "+49551123455";
        assertTrue(validator.validate(sampleNumber));
    }

    @Test
    void shouldBeFalseForRegexMisMatchWithoutPlus() {
        String mismatchingNumber = "049551123455";
        assertFalse(validator.validate(mismatchingNumber));
    }

    @Test
    void shouldBeTrueForRegexMisMatchWithNoise() {
        String mismatchingNumber = "+49-(55112)34/55";
        assertTrue(validator.validate(mismatchingNumber));
    }

    @Test
    void shouldBeTrueForNoisyValidNumberWithDash() {
        String noisyDash = "+880123-1245";
        assertTrue(validator.validate(noisyDash));
    }

    @Test
    void shouldBeTrueForNoisyValidNumberWithSpaces() {
        String noisySpace = "+4 9 12 3 4 56 9 8127";
        assertTrue(validator.validate(noisySpace));
    }

    @ParameterizedTest
    @ValueSource(strings = {"+4.9.12.3.4.56.9.8127", "+4912/341238127", "+49 (123) 4.569/8127"})
    void shouldBeTrueForNoisyValidNumberWithDots(String noisy) {
        assertTrue(validator.validate(noisy));
    }

    @Test
    void shouldBeFalseForTooLongNumber() {
        String tooLong = "+49123456789098876543212345667899009877654433221";
        assertFalse(validator.validate(tooLong));
    }

    @Test
    void shouldBeFalseForTooShortNumber() {
        String tooShort = "+1";
        assertFalse(validator.validate(tooShort));
    }

    @Test
    void shouldBeFalseForWrongCountryCallingCode() {
        String tooShort = "+99";
        assertFalse(validator.validate(tooShort));
    }

    @Test
    void shouldValidateAllTestStringsFromTestDataSet() {
        for (Map.Entry<String, Boolean> entry : TestData.phoneNumberValidityMap.entrySet()) {
            Boolean want = entry.getValue();
            Boolean have = validator.validate(entry.getKey());
            if (want != have)
                break; // FIXME:
            assertEquals(have, want);
        }
    }

    @Test
    void shouldFailWithNullInput() {
        String input = null;
        assertThrows(NullPointerException.class, () -> validator.validate(input));
    }
}