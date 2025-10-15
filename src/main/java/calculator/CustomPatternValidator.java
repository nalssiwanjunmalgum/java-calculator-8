package calculator;

import java.util.regex.Pattern;

public class CustomPatternValidator {
    private static final Pattern CUSTOM_PATTERN = Pattern.compile("^(//)(.)(\\n)[1-9][0-9]*(\\2[1-9][0-9]*)+$");

    public boolean validate(String s) {
        return CUSTOM_PATTERN.matcher(s).matches();
    }
}
