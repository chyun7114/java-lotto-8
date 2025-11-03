package lotto.model;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum WinningRank {

    FIRST(6, 2_000_000_000, (matchCount, bonusMatch) -> matchCount == 6),
    SECOND(5, 30_000_000, (matchCount, bonusMatch) -> matchCount == 5 && bonusMatch),
    THIRD(5, 1_500_000, (matchCount, bonusMatch) -> matchCount == 5 && !bonusMatch),
    FOURTH(4, 50_000, (matchCount, bonusMatch) -> matchCount == 4),
    FIFTH(3, 5_000, (matchCount, bonusMatch) -> matchCount == 3),
    MISS(0, 0, (matchCount, bonusMatch) -> matchCount < 3);

    private final int matchCount;
    private final int prizeMoney;
    private final BiPredicate<Integer, Boolean> isMatch;

    WinningRank(int matchCount, int prizeMoney, BiPredicate<Integer, Boolean> isMatch) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.isMatch = isMatch;
    }

    public static WinningRank valueOf(int matchCount, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(rank -> rank.isMatch.test(matchCount, bonusMatch))
                .findFirst()
                .orElse(MISS);
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
