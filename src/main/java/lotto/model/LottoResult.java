package lotto.model;

public class LottoResult {

    private final Statistics statistics;
    private final double profitRate;

    public LottoResult(Statistics statistics, Lottos lottos) {
        this.statistics = statistics;
        this.profitRate = calculateProfitRate(statistics, lottos);
    }

    private double calculateProfitRate(Statistics statistics, Lottos lottos) {
        long totalPrize = statistics.calculateTotalPrize();
        return (((double) totalPrize / lottos.getLottoPrice()) * 100);
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
