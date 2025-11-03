package lotto.view;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Comparator;
import lotto.model.Lotto;
import lotto.model.LottoResult;
import lotto.model.Lottos;
import lotto.model.Statistics;
import lotto.model.WinningRank;

public class OutputView {

    private static final String PURCHASED_COUNT_STRING = "%d개를 구매했습니다.\n";
    private static final String WINNIG_STATISTICS_HEADER = "당첨 통계\n---";
    private static final String PROFIT_RATE_MESSAGE = "총 수익률은 %.1f%%입니다.";

    public void printPurchaseCount(int purchasedCount) {
        System.out.printf(PURCHASED_COUNT_STRING, purchasedCount);
    }

    public void printPurchasesLottos(Lottos lottos) {
        for (Lotto lotto : lottos.lottoList()) {
            System.out.println(lotto.numbers().toString());
        }
    }

    public void printLottoResult(LottoResult lottoResult) {
        printLottoStatistics(lottoResult.getStatistics());
        printProfitRate(lottoResult.getProfitRate());
    }

    private void printLottoStatistics(Statistics statistics) {
        System.out.println(WINNIG_STATISTICS_HEADER);
        Arrays.stream(WinningRank.values())
                .filter(rank -> rank != WinningRank.MISS)
                .sorted(Comparator.comparing(WinningRank::getPrizeMoney))
                .forEach(rank -> {
                    String message = formatRankMessage(rank);
                    int count = statistics.getCount(rank);
                    System.out.printf("%s - %d개\n", message, count);
                });
    }

    private String formatRankMessage(WinningRank rank) {
        String prizeMoney = NumberFormat.getInstance().format(rank.getPrizeMoney());
        if(rank == WinningRank.SECOND) {
            return String.format("%d개 일치, 보너스 볼 일치 (%s원)", rank.getMatchCount(), prizeMoney);
        }
        return String.format("%d개 일치 (%s원)", rank.getMatchCount(), prizeMoney);
    }

    private void printProfitRate(double profitRate) {
        System.out.printf(PROFIT_RATE_MESSAGE, profitRate);
    }
}
