package com.softwaretesting.testing.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberValidatorTest {
    PhoneNumberValidator validator = new PhoneNumberValidator();

    @Test
    void falseForEmpty() {
        assertFalse(validator.validate(""));
    }

    @Test
    void falseForPlaceHolderCharacterOnly() {
        String placeHolderCharacters = "----";
        assertFalse(validator.validate(placeHolderCharacters));
    }

    @Test
    void falseForLettersInNumber() {
        String lettersInNumber = "+1-800-DOGS";
        assertFalse(validator.validate(lettersInNumber));
    }

    @Test
    void falseForInvalidCharactersInNumber() {
        String invalidCharactersInNumber = "+49}}5512948234";
        assertFalse(validator.validate(invalidCharactersInNumber));
    }

    @Test
    void trueForRegexMatch() {
        String sampleNumber = "+49551123455";
        assertTrue(validator.validate(sampleNumber));
    }

    @Test
    void falseForRegexMisMatchWithoutPlus() {
        String mismatchingNumber = "049551123455";
        assertFalse(validator.validate(mismatchingNumber));
    }

    @Test
    void falseForRegexMisMatchWithNoise() {
        String mismatchingNumber = "+49abc-(55112)3455def";
        assertFalse(validator.validate(mismatchingNumber));
    }

    @Test
    void trueForNoisyValidNumberWithDash() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Test
    void trueForNoisyValidNumberWithSpaces() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Test
    void trueForNoisyValidNumberWithDots() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Test
    void trueForNoisyValidNumberWithSlash() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Test
    void trueForNoisyValidNumberMixed() {
        throw new UnsupportedOperationException("Not implemented");
    }
}