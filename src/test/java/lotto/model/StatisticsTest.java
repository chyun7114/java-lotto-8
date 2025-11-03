package lotto.model;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {

    @Nested
    @DisplayName("createEmpty 정적 메서드는")
    class DescribeCreateEmpty {

        @Test
        @DisplayName("MISS를 제외한 모든 등수를 0으로 초기화한 Statistics 객체를 생성한다")
        void success_create_all_miss_statistics() {
            // when
            Statistics statistics = Statistics.createEmpty();

            // then
            assertAll(
                    () -> Arrays.stream(WinningRank.values())
                            .filter(rank -> rank != WinningRank.MISS)
                            .forEach(rank -> assertThat(statistics.getCount(rank)).isZero()),
                    () -> assertThat(statistics.calculateTotalPrize()).isZero()
            );
        }
    }

    @Nested
    @DisplayName("통계 데이터가 있는 경우")
    class Context_with_statistics_data {

        private Statistics statistics;

        @BeforeEach
        void setUp() {
            // given
            statistics = Statistics.createEmpty();
            statistics.putRank(WinningRank.FIFTH);
            statistics.putRank(WinningRank.FIFTH);
            statistics.putRank(WinningRank.FOURTH);
            statistics.putRank(WinningRank.THIRD);
        }

        @Test
        @DisplayName("putRank는 해당 등수의 카운트를 1 증가시킨다")
        void success_put_rank_count_increase_1() {
            // when
            statistics.putRank(WinningRank.FIRST);

            // then
            assertThat(statistics.getCount(WinningRank.FIRST)).isEqualTo(1);
        }

        @Test
        @DisplayName("getCount는 해당 등수의 정확한 당첨 횟수를 반환한다")
        void success_getCount_returns_correct_count_for_a_rank() {
            // then
            assertAll(
                    () -> assertThat(statistics.getCount(WinningRank.FIFTH)).isEqualTo(2),
                    () -> assertThat(statistics.getCount(WinningRank.FOURTH)).isEqualTo(1),
                    () -> assertThat(statistics.getCount(WinningRank.THIRD)).isOne()
            );
        }

        @Test
        @DisplayName("calculateTotalPrize는 총상금을 정확하게 계산한다")
        void success_calculateTotalPrize_returns_correct_total_prize() {
            // given
            long expectedTotalPrize =
                    (long) WinningRank.FIFTH.getPrizeMoney() * 2 + (long) WinningRank.FOURTH.getPrizeMoney()
                            + (long) WinningRank.THIRD.getPrizeMoney();

            // when
            long totalPrize = statistics.calculateTotalPrize();

            // then
            assertThat(totalPrize).isEqualTo(expectedTotalPrize);
        }
    }
}
