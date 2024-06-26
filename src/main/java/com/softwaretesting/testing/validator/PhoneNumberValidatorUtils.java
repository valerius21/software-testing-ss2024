package com.softwaretesting.testing.validator;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneNumberValidatorUtils {
    PhoneNumberValidatorUtils() {
    }

    private static final String[] codes = {
            "+1",
            "+20",
            "+212",
            "+213",
            "+216",
            "+218",
            "+220",
            "+221",
            "+222",
            "+223",
            "+224",
            "+225",
            "+226",
            "+227",
            "+228",
            "+229",
            "+230",
            "+231",
            "+232",
            "+233",
            "+234",
            "+235",
            "+236",
            "+237",
            "+238",
            "+239",
            "+240",
            "+241",
            "+242",
            "+243",
            "+244",
            "+245",
            "+248",
            "+249",
            "+250",
            "+251",
            "+252",
            "+253",
            "+254",
            "+255",
            "+256",
            "+257",
            "+258",
            "+260",
            "+261",
            "+262",
            "+263",
            "+264",
            "+265",
            "+266",
            "+267",
            "+268",
            "+269",
            "+27",
            "+290",
            "+291",
            "+297",
            "+298",
            "+299",
            "+30",
            "+31",
            "+32",
            "+33",
            "+34",
            "+350",
            "+351",
            "+352",
            "+353",
            "+355",
            "+356",
            "+357",
            "+358",
            "+359",
            "+36",
            "+370",
            "+371",
            "+372",
            "+373",
            "+374",
            "+375",
            "+376",
            "+377",
            "+378",
            "+380",
            "+381",
            "+382",
            "+385",
            "+386",
            "+387",
            "+389",
            "+39",
            "+40",
            "+41",
            "+420",
            "+421",
            "+423",
            "+43",
            "+44",
            "+45",
            "+46",
            "+47",
            "+48",
            "+49",
            "+500",
            "+501",
            "+502",
            "+503",
            "+504",
            "+505",
            "+506",
            "+507",
            "+508",
            "+509",
            "+51",
            "+52",
            "+53",
            "+54",
            "+55",
            "+56",
            "+57",
            "+58",
            "+590",
            "+591",
            "+592",
            "+593",
            "+595",
            "+597",
            "+598",
            "+599",
            "+60",
            "+61",
            "+62",
            "+63",
            "+64",
            "+65",
            "+66",
            "+670",
            "+672",
            "+673",
            "+674",
            "+675",
            "+676",
            "+677",
            "+678",
            "+679",
            "+680",
            "+681",
            "+682",
            "+683",
            "+685",
            "+686",
            "+687",
            "+688",
            "+689",
            "+690",
            "+691",
            "+692",
            "+7",
            "+81",
            "+82",
            "+84",
            "+850",
            "+852",
            "+853",
            "+855",
            "+856",
            "+86",
            "+870",
            "+880",
            "+886",
            "+90",
            "+91",
            "+92",
            "+93",
            "+94",
            "+95",
            "+960",
            "+961",
            "+962",
            "+963",
            "+964",
            "+965",
            "+966",
            "+967",
            "+968",
            "+971",
            "+972",
            "+973",
            "+974",
            "+975",
            "+976",
            "+977",
            "+98",
            "+992",
            "+993",
            "+994",
            "+995",
            "+996",
            "+998"
    };

    /**
     * All Country Calling Codes
     *
     * @see <a href="https://gist.github.com/tahirSmartboy/14cd096f25c24b30c203#file-gistfile1-txt-L223">Borrowed from here.</a>
     */
    protected static final List<String> COUNTRY_CALLING_CODES = Arrays.stream(codes).collect(Collectors.toList());

    /**
     * Removes all characters, except a leading "+", if there is one.
     *
     * @param inputString the dirty string
     * @return the sanitized string
     */
    @NotNull
    public static String clean(String inputString) {
        if (inputString.isEmpty()) return "";
        boolean plusIsPresentAtBeginning = inputString.charAt(0) == '+';
        inputString = inputString.replaceAll("[^a-zA-Z0-9]", "");
        inputString = inputString.replaceAll("[a-zA-Z]", "");
        return plusIsPresentAtBeginning && !inputString.isEmpty() ? "+" + inputString : inputString;
    }

    /**
     * checks if a string starts with a country code
     *
     * @param inputString the string to be checked
     * @return if the string starts with a country code
     */
    @NotNull
    public static boolean startsWithCountryCode(String inputString) {
        boolean startsWithSubstring = false;
        for (String substring : PhoneNumberValidatorUtils.COUNTRY_CALLING_CODES) {
            if (inputString.startsWith(substring)) {
                startsWithSubstring = true;
                break;
            }
        }
        return startsWithSubstring;
    }
}
