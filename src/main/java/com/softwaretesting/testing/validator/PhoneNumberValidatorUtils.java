package com.softwaretesting.testing.validator;

public class PhoneNumberValidatorUtils {
    private PhoneNumberValidatorUtils() {
    }

    /**
     * Removes all characters, except a leading "+", if there is one.
     *
     * @param inputString the dirty string
     * @return the sanitized string
     */
    public static String clean(String inputString) {
        if (inputString.isEmpty()) return "";
        boolean plusIsPresentAtBeginning = inputString.charAt(0) == '+' && inputString.length() > 1;
        inputString = inputString
                .replaceAll("\\+", "")
                .replaceAll("[^\\d.]", "");
        return plusIsPresentAtBeginning && !inputString.isEmpty() ? "+" + inputString : inputString;
    }
}
