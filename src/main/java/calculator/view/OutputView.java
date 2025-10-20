package calculator.view;

public class OutputView {
    private static final String RESULT_FORMAT = "결과 : %d";

    public void printSum(Long longValue) {
        System.out.printf(RESULT_FORMAT, longValue);
    }
}
