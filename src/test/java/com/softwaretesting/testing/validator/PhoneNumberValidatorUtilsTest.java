package com.softwaretesting.testing.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberValidatorUtilsTest {
    @Test
    void shouldReturnEmptyStringForEmptyInput() {
        String have = "";
        String want = "";
        assertEquals(PhoneNumberValidatorUtils.clean(have), want);
    }

    @Test
    void shouldKeepTheInitialPlus() {
        String have = "+5";
        String want = "+5";
        assertEquals(PhoneNumberValidatorUtils.clean(have), want);
    }

    @Test
    void shouldReturnStringWhenPlusNotPresentAtStart() {
        String have = "555";
        String want = "555";
        assertEquals(PhoneNumberValidatorUtils.clean(have), want);
    }

    @Test
    void shouldReturnStringWhenPlusInsideString() {
        String have = "55+5";
        String want = "555";
        assertEquals(PhoneNumberValidatorUtils.clean(have), want);
    }

    @Test
    void shouldReturnEmptyWhenStringOnlyPlus() {
        String have = "+";
        String want = "";
        assertEquals(PhoneNumberValidatorUtils.clean(have), want);
    }

    @Test
    void shouldReturnEmptyWhenStringMultiplePlus() {
        String have = "+++++";
        String want = "";
        assertEquals(PhoneNumberValidatorUtils.clean(have), want);
    }

    @Test
    void shouldRemoveFromANonNumberStringAllCharacters() {
        String have = "Hello, World";
        String want = "";
        assertEquals(PhoneNumberValidatorUtils.clean(have), want);
    }

    @Test
    void shouldReturnOnlyTheNumbersInside() {
        String have = "49 Hello, World";
        String want = "49";
        assertEquals(PhoneNumberValidatorUtils.clean(have), want);
    }

    @Test
    void shouldRemoveRegexSensitiveSpecialCharacterDot() {
        String have = "String input = \"This is a test string with special characters " +
                "like # ^$*.[]{}()\\\\+?|\";\n";
        String want = "";
        assertEquals(PhoneNumberValidatorUtils.clean(have), want);
    }
}