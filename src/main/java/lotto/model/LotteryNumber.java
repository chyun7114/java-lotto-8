package lotto.model;

import java.util.List;

public record LotteryNumber(
        List<Integer> winningNumbers,
        int bonusNumber
) {

    public static LotteryNumber from(List<Integer> winningNumbers, int bonusNumber) {
        return new LotteryNumber(winningNumbers, bonusNumber);
    }
}
