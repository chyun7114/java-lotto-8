package lotto.model;

import java.util.EnumMap;
import java.util.Map;

public record Statistics(Map<WinningRank, Integer> statistics) {

    public static Statistics createEmpty() {
        Map<WinningRank, Integer> emptyStatistics = new EnumMap<>(WinningRank.class);
        for (WinningRank rank : WinningRank.values()) {
            if (rank != WinningRank.MISS) {
                emptyStatistics.put(rank, 0);
            }
        }
        return new Statistics(emptyStatistics);
    }

    public void putRank(WinningRank rank) {
        statistics.put(rank, statistics.getOrDefault(rank, 0) + 1);
    }

    public int getCount(WinningRank rank) {
        return statistics.getOrDefault(rank, 0);
    }

    public long calculateTotalPrize() {
        return statistics.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }
}
