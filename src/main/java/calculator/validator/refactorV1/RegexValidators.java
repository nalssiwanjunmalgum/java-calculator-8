package calculator.validator.refactorV1;

import java.util.regex.Pattern;

public class RegexValidators {
    private static boolean matches(Pattern pattern, String input) {
        return input != null && pattern.matcher(input).matches();
    }

    public static boolean isNumber(String input) {
        return matches(RegexPatterns.NUMBER, input);
    }

    public static boolean isCustom(String input) {
        return matches(RegexPatterns.CUSTOM, input);
    }

    public static boolean isBasic(String input) {
        return matches(RegexPatterns.BASIC, input);
    }
}
