package calculator.parser;

import calculator.validator.refactorV1.RegexPatterns;
import calculator.validator.refactorV1.Result;
import java.util.regex.Matcher;

public class CustomParser {

    public static Result parse(String input) {
        Matcher m = RegexPatterns.CUSTOM.matcher(input);

        if (m.matches()) {
            String delimiter = m.group(1);   // 커스텀 구분자
            String body = m.group(2); // 구분자 확인 제외한 문자열
            return new Result(delimiter, body);
        }

        throw new IllegalArgumentException("파싱이 불가능합니다.");
    }
}
