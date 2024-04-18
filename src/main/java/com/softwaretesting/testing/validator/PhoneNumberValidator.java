package com.softwaretesting.testing.validator;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


@Service
public class PhoneNumberValidator {
    /**
     * Validates Phone Numbers to be valid
     * Uses a RegEx utilising linked resources.
     *
     * @param number the phone number
     * @return if its valid
     * @see <a href="https://www.itu.int/rec/T-REC-E.164/en">E.164 Internaltional Phone Number Specification</a>
     * @see <a href="https://www.twilio.com/docs/glossary/what-e164#regex-matching-for-e164">Twilio Phone Number RegEx</a>
     */
    public boolean validate(String number) {
        Pattern pattern = Pattern.compile("^\\+[1-9]\\d{1,14}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.find();
    }
}
