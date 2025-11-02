package lotto.model;

import java.util.Map;

public record LottoResult(
    Map<WinningRank, Integer> statistics,
    double profitRate
) {
}
