package lotto.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record Lotto(List<Integer> numbers) {

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        sortedNumbers.sort(Integer::compareTo);

        this.numbers = sortedNumbers;
    }

    public void validate(List<Integer> numbers) {
        validateNull(numbers);
        validateSize(numbers);
        validateInRange(numbers);
        validateDuplicates(numbers);
    }

    private void validateNull(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 null일 수 없습니다.");
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateInRange(List<Integer> numbers) {
        numbers.stream()
                .filter(this::isOutRangeLottoNumber)
                .findAny()
                .ifPresent((i) -> {
                    throw new IllegalArgumentException("[ERROR] 로또 번호는 1~45 사이여야 합니다.");
                });
    }

    private void validateDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }
    }

    private boolean isOutRangeLottoNumber(Integer number) {
        return number < LottoProperties.LOTTO_NUMBER_MIN ||
                number > LottoProperties.LOTTO_NUMBER_MAX;
    }

    public static Lotto from(List<Integer> numbers) {
        return new Lotto(numbers);
    }
}
