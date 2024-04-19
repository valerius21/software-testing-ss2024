package com.softwaretesting.testing.validator;

import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class PhoneNumberValidator {
    /**
     * {@link PhoneNumberValidator#validate(String)}
     */
    private final Pattern pattern = Pattern.compile("^\\+[1-9]\\d{1,14}$");

    /**
     * Validates Phone Numbers to be in a valid format following the E164 specification.
     * Uses a RegEx from the linked resources.
     *
     * @param number the phone number
     * @return if its valid
     * @see <a href="https://www.itu.int/rec/T-REC-E.164/en">E.164 Internaltional Phone Number Specification</a>
     * @see <a href="https://www.twilio.com/docs/glossary/what-e164#regex-matching-for-e164">Twilio Phone Number RegEx</a>
     */
    @NotNull
    public boolean validate(String number) {
        number = PhoneNumberValidatorUtils.clean(number);
        Matcher matcher = pattern.matcher(number);
        if (matcher.find()) {
            String result = matcher.group(0);
            return PhoneNumberValidatorUtils.startsWithCountryCode(result) &&
                    !PhoneNumberValidatorUtils.COUNTRY_CALLING_CODES.contains(result);
        }
        return false;
    }
}
