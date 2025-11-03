package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("WinningRank Enum 테스트")
class WinningRankTest {

    @Nested
    @DisplayName("valueOf 정적 메서드 테스트")
    class Describe_valueOf {

        @ParameterizedTest
        @CsvSource({
                "6, false, FIRST",
                "6, true,  FIRST",
                "5, true,  SECOND",
                "5, false, THIRD",
                "4, false, FOURTH",
                "4, true,  FOURTH",
                "3, false, FIFTH",
                "3, true,  FIFTH",
                "2, false, MISS",
                "1, true,  MISS",
                "0, false, MISS"
        })
        @DisplayName("일치하는 번호 개수와 보너스 볼 일치 여부에 따라 정확한 등수를 반환한다")
        void success_returns_correct_rank_based_on_matches(
                int matchCount,
                boolean bonusMatch,
                WinningRank expectedRank
        ) {
            // when
            WinningRank actualRank = WinningRank.valueOf(matchCount, bonusMatch);

            // then
            assertThat(actualRank).isEqualTo(expectedRank);
        }
    }

    @Nested
    @DisplayName("getter 메서드 테스트")
    class DescribeGetters {

        @Test
        @DisplayName("각 등급에 맞는 상금과 일치 개수를 정확히 반환한다")
        void success_returns_correct_prize_and_match_count() {
            // then
            assertAll(
                    () -> assertThat(WinningRank.FIRST.getPrizeMoney()).isEqualTo(2_000_000_000),
                    () -> assertThat(WinningRank.FIRST.getMatchCount()).isEqualTo(6),

                    () -> assertThat(WinningRank.SECOND.getPrizeMoney()).isEqualTo(30_000_000),
                    () -> assertThat(WinningRank.SECOND.getMatchCount()).isEqualTo(5),

                    () -> assertThat(WinningRank.FIFTH.getPrizeMoney()).isEqualTo(5_000),
                    () -> assertThat(WinningRank.FIFTH.getMatchCount()).isEqualTo(3),

                    () -> assertThat(WinningRank.MISS.getPrizeMoney()).isZero(),
                    () -> assertThat(WinningRank.MISS.getMatchCount()).isZero()
            );
        }
    }
}
