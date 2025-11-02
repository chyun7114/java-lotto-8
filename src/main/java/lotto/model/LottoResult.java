package lotto.model;

import java.util.Map;

public record LottoResult(
    Statistics statistics,
    double profitRate
) {
    private double calculateProfitRate(Statistics statistics, Money money) {
        long totalPrize = statistics.calculateTotalPrize();
        return (double) totalPrize / money.getAmount() * 100;
    }
}
