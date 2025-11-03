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
    public void printPurchaseCount(int purchasedCount) {
        System.out.printf(PrintingFormatEnum.OUTPUT_PURCHASED_COUNT_STRING.getFormat(), purchasedCount);
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
        System.out.println(PrintingFormatEnum.OUTPUT_WINNIG_STATISTICS_HEADER.getFormat());
        Arrays.stream(WinningRank.values())
                .filter(rank -> rank != WinningRank.MISS)
                .sorted(Comparator.comparing(WinningRank::getPrizeMoney))
                .forEach(rank -> printRankResult(rank, statistics));
    }

    private void printRankResult(WinningRank rank, Statistics statistics) {
        String message = formatRankMessage(rank);
        int count = statistics.getCount(rank);
        System.out.printf(
                PrintingFormatEnum.OUTPUT_WINNING_LOTTO_COUNT_MESSAGE.getFormat(),
                message,
                count
        );
    }

    private String formatRankMessage(WinningRank rank) {
        if (rank == WinningRank.SECOND) {
            return String.format(
                    PrintingFormatEnum.OUTPUT_BONUS_COUNT_MESSAGE.getFormat(),
                    rank.getMatchCount(),
                    numberFormat(rank.getPrizeMoney())
            );
        }
        return String.format(
                PrintingFormatEnum.OUTPUT_NORMAL_COUNT_MESSAGE.getFormat(),
                rank.getMatchCount(),
                numberFormat(rank.getPrizeMoney())
        );
    }

    private void printProfitRate(double profitRate) {
        System.out.printf(PrintingFormatEnum.OUTPUT_PROFIT_RATE_MESSAGE.getFormat(), numberFormat(profitRate));
    }

    private <T extends Number> String numberFormat(T number) {
        NumberFormat formatter = NumberFormat.getInstance();
        formatter.setMaximumFractionDigits(1);
        return formatter.format(number);
    }
}
