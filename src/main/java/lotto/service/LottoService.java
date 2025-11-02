package lotto.service;

import java.util.List;
import lotto.infrastructure.generator.LottoGenerator;
import lotto.model.LotteryNumber;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.Statistics;
import lotto.model.WinningRank;

public class LottoService {

    private final LottoGenerator lottoGenerator;

    public LottoService(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public Lottos purchaseLottos(Money money) {
        return lottoGenerator.generateLotto(money);
    }

    public LotteryNumber createLotteryNumber(List<Integer> winningNumbers, int bonusNumber) {
        return LotteryNumber.from(winningNumbers, bonusNumber);
    }

    public Statistics calculateWinningResult(Lottos lottos, LotteryNumber lotteryNumber) {
        return calculateStatistics(lottos, lotteryNumber);
    }

    private Statistics calculateStatistics(Lottos lottos, LotteryNumber lotteryNumber) {
        Statistics statistics = Statistics.createEmpty();
        for(Lotto lotto : lottos.lottoList()) {
            WinningRank rank = lotto.calculateRank(lotteryNumber);
            statistics.putRank(rank);
        }

        return statistics;
    }
}
