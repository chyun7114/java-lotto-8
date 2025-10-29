package lotto.model;

import java.util.List;
import lotto.exception.LottoErrorCode;

public record LotteryNumber(
        List<Integer> winningNumbers,
        int bonusNumber
) {

    public LotteryNumber(List<Integer> winningNumbers, int bonusNumber) {
        validateWinningNumbers(winningNumbers);
        this.winningNumbers = winningNumbers;

        validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public static LotteryNumber from(List<Integer> winningNumbers, int bonusNumber) {
        return new LotteryNumber(winningNumbers, bonusNumber);
    }

    private void validateWinningNumbers(List<Integer> winningNumbers) {
        validateSize(winningNumbers);
        validateIsInRange(winningNumbers);
    }

    private void validateSize(List<Integer> winningNumbers) {
        if(winningNumbers.size() != LottoProperties.LOTTO_NUMBERS_SIZE) {
            throw LottoErrorCode.INVALID_SIZE.toException();
        }
    }

    private void validateIsInRange(List<Integer> winningNumbers) {
        if(isOutOfRange(winningNumbers)) {
            throw LottoErrorCode.LOTTO_NUMBERS_IS_IN_NOT_RANGE.toException();
        }
    }

    private boolean isOutOfRange(List<Integer> winningNumbers) {
        return winningNumbers.stream()
                .anyMatch(LottoProperties::isOutOfRange);
    }

    private void validateBonusNumber(int bonusNumber) {
        validateIsInRange(bonusNumber);
        validateIsNotDuplicateWinningNumber(bonusNumber);
    }

    private void validateIsInRange(int bonusNumber) {
        if(LottoProperties.isOutOfRange(bonusNumber)) {
            throw LottoErrorCode.LOTTO_NUMBERS_IS_IN_NOT_RANGE.toException();
        }
    }

    private void validateIsNotDuplicateWinningNumber(int bonusNumber) {
        if(winningNumbers.contains(bonusNumber)) {
            throw LottoErrorCode.IS_NOT_DUPLICATES.toException();
        }
    }
}
