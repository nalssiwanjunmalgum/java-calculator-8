package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomPatternValidator {
    private static final Pattern CUSTOM_PATTERN = Pattern.compile("^(//)(.)(\\n)[1-9][0-9]*(\\2[1-9][0-9]*)+$");

    public boolean validate(String s) {
        return CUSTOM_PATTERN.matcher(s).matches();
    }

    public String extractDelimiter(String trimmedInput) {
        Matcher matcher = CUSTOM_PATTERN.matcher(trimmedInput);
        if (matcher.matches()) {
            return CUSTOM_PATTERN.matcher(trimmedInput).group(2);
        }

        return null;
    }
}
