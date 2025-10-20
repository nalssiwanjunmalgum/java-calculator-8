package calculator;

import java.util.regex.Pattern;

public class RegexPatterns {
    public static final Pattern NUMBER = Pattern.compile("^[1-9][0-9]*$");
    public static final Pattern COMMON = Pattern.compile("^[1-9][0-9]*(\\*[1-9][0-9]*)*$");
    public static final Pattern BASIC = Pattern.compile("^[1-9][0-9]*([,:][1-9][0-9]*)*$");
    public static final Pattern CUSTOM = Pattern.compile("^//(.)\\\\n([1-9][0-9]*(?:\\1[1-9][0-9]*)*)$");

    private RegexPatterns() {
    }
}
