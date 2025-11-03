package lotto.model;

import java.util.List;

public record Lottos(List<Lotto> lottoList) {

    public static Lottos from(List<Lotto> lottoList) {
        return new Lottos(lottoList);
    }

    public Statistics calculateStatistics(LotteryNumber lotteryNumber) {
        Statistics statistics = Statistics.createEmpty();
        for(Lotto lotto : this.lottoList) {
            WinningRank rank = lotto.calculateRank(lotteryNumber);
            statistics.putRank(rank);
        }

        return statistics;
    }
}
