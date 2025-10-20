package calculator;

import calculator.view.InputView;
import calculator.view.OutputView;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        String userInput = inputView.getInput();
        NumberExtractor numberExtractor = new NumberExtractor();
        Calculator calculator = new Calculator();

        List<Long> extractedNumbers = numberExtractor.extractFrom(userInput);
        long result = calculator.calculate(extractedNumbers);

        outputView.printSum(result);
    }
}
