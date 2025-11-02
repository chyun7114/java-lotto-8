package lotto.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.exception.LottoErrorCode;

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
            throw LottoErrorCode.NUMBERS_IS_NOT_NULL.toException();
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw LottoErrorCode.INVALID_SIZE.toException();
        }
    }

    private void validateInRange(List<Integer> numbers) {
        numbers.stream()
                .filter(this::isOutRangeLottoNumber)
                .findAny()
                .ifPresent((i) -> {
                    throw LottoErrorCode.LOTTO_NUMBERS_IS_IN_NOT_RANGE.toException();
                });
    }

    private void validateDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw LottoErrorCode.IS_NOT_DUPLICATES.toException();
        }
    }

    private boolean isOutRangeLottoNumber(Integer number) {
        return number < LottoProperties.LOTTO_NUMBER_MIN ||
                number > LottoProperties.LOTTO_NUMBER_MAX;
    }

    public static Lotto from(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    public WinningRank calculateRank(LotteryNumber lotteryNumber) {
        int matchCount = getMatchWinningNumberInLotto(lotteryNumber.winningNumbers());
        boolean bonusMatch = isMatchBonusNumber(lotteryNumber.bonusNumber());
        return WinningRank.valueOf(matchCount, bonusMatch);
    }

    private int getMatchWinningNumberInLotto(List<Integer> winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private boolean isMatchBonusNumber(int bonusNumber) {
        return numbers.stream()
                .anyMatch(number -> number == bonusNumber);
    }
}
