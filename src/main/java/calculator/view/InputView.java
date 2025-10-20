package calculator.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String START_MESSAGE = "덧셈할 문자열을 입력해 주세요.";

    public String getInput() {
        System.out.println(START_MESSAGE);
        return Console.readLine();
    }
}
