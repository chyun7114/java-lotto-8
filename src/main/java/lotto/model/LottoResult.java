package lotto.model;

public class LottoResult {

    private final Statistics statistics;
    private final double profitRate;

    public LottoResult(Statistics statistics, Money money) {
        this.statistics = statistics;
        this.profitRate = calculateProfitRate(statistics, money);
    }

    private double calculateProfitRate(Statistics statistics, Money money) {
        long totalPrize = statistics.calculateTotalPrize();
        return (((double) totalPrize / money.getAmount()) * 100);
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
