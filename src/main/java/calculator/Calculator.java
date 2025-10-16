package calculator;

import java.util.List;

public class Calculator {

    public long execute(List<Long> numbers) {
        return numbers.stream().mapToLong(num -> num).sum();
    }
}
