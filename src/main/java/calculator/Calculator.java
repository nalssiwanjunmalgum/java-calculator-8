package calculator;

import java.util.List;

public class Calculator {

    public long calculate(List<Long> numbers) {
        return numbers.stream().mapToLong(num -> num).sum();
    }
}
