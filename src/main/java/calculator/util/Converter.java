package calculator.util;

public class Converter {

    private static final String BLANK = " ";
    private static final String ERASED = "";

    // 공백을 제거합니다.
    public static String eraseBlank(String input) {
        return input.replaceAll(BLANK, ERASED);
    }
}
