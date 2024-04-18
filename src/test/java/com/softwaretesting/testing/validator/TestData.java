package com.softwaretesting.testing.validator;

import java.util.HashMap;
import java.util.Map;

public class TestData {
    private TestData() {
    }

    public static Map<String, Boolean> phoneNumberValidityMap = new HashMap<>() {{
        put("+1 555-123-4567", true);
        put("(555) 123-4567", false);
        put("+1 555-123-4567 ext. 1234", true);
        put("555 123 4567", false);
        put("555.123.4567", false);
        put("555-123-4567", false);
        put("(555) 123.4567", false);
        put("123-4567", false);
        put("+1234567890", true);
        put("0123456789", false);
        put("(555)1234567", false);
        put("+(44) 1234 567890", true);
        put("011441234567890", false);
        put("5555555555", false);
        put("1-800-FLOWERS", false);
        put("ABCDEFGHIJ", false);
        put("+1 (555) 123-4567 ext. 12345", false);
        put("+1 555#1234567", true);
        put("+1 555-123-4567 ext. 123456", false);
        put("+995 55-123-4567", true);
        put("+1 000-123-4567", true);
        put("+1 555-123-4567 ext. ABC", false);
        put("", false);
        put("         ", false);
        put("---------", false);
        put(".........", false);
    }};
}
