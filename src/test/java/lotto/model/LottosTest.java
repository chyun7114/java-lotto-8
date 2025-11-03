package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    @DisplayName("주어진 당첨 번호로 각 로또의 통계를 올바르게 계산해야 한다.")
    void calculateStatistics_ShouldReturnCorrectStatistics() {
        // given
        LotteryNumber lotteryNumber = LotteryNumber.from(List.of(1, 2, 3, 4, 5, 6), 7);
        Lottos lottos = Lottos.from(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 8)),
                new Lotto(List.of(1, 2, 3, 4, 10, 11)),
                new Lotto(List.of(1, 2, 3, 12, 13, 14)),
                new Lotto(List.of(10, 11, 12, 13, 14, 15)),
                new Lotto(List.of(10, 11, 12, 13, 14, 16))
        ));

        // when
        Statistics statistics = lottos.calculateStatistics(lotteryNumber);

        // then
        assertThat(statistics.getCount(WinningRank.FIRST)).isEqualTo(1);
        assertThat(statistics.getCount(WinningRank.SECOND)).isEqualTo(1);
        assertThat(statistics.getCount(WinningRank.THIRD)).isEqualTo(1);
        assertThat(statistics.getCount(WinningRank.FOURTH)).isEqualTo(1);
        assertThat(statistics.getCount(WinningRank.FIFTH)).isEqualTo(1);
        assertThat(statistics.getCount(WinningRank.MISS)).isEqualTo(2);
    }
}
