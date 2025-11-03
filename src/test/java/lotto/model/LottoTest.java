package lotto.model;

import java.util.stream.Stream;
import lotto.exception.LottoErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.*;

class LottoTest {

    @Test
    @DisplayName("로또 생성 성공 테스트")
    void success_create_lotto() {
        assertThatCode(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6)))
                .doesNotThrowAnyException();
    }

    @Nested
    @DisplayName("로또 생성 실패 테스트")
    class LottoCreateError {

        @Test
        @DisplayName("로또 리스트가 null인 경우 오류가 발생한다.")
        void fail_lotto_list_is_null() {
            assertThatThrownBy(() -> Lotto.from(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(LottoErrorCode.NUMBERS_IS_NOT_NULL.getMessage());
        }

        @ParameterizedTest
        @DisplayName("로또 번호의 개수가 6개가 아니면 오류가 발생한다.")
        @MethodSource("invalidSizeLottoNumbers")
        void fail_lotto_number_is_over_six(List<Integer> numbers) {
            assertThatThrownBy(() -> Lotto.from(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(LottoErrorCode.INVALID_SIZE.getMessage());
        }

        static Stream<List<Integer>> invalidSizeLottoNumbers() {
            return Stream.of(
                    List.of(1, 2, 3, 4, 5),
                    List.of(1, 2, 3, 4, 5, 6, 7),
                    List.of()
            );
        }

        @ParameterizedTest
        @DisplayName("로또 번호는 1~45 사이의 값만 가질 수 있다.")
        @MethodSource("rangeLottoNumber")
        void fail_lotto_numbers_is_in_range(List<Integer> numbers) {
            assertThatThrownBy(() -> Lotto.from(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(LottoErrorCode.LOTTO_NUMBERS_IS_IN_NOT_RANGE.getMessage());
        }

        static Stream<List<Integer>> rangeLottoNumber() {
            return Stream.of(
                    List.of(1, 2, 3, 4, 5, 46),
                    List.of(0, 2, 3, 4, 5, 6),
                    List.of(0, 20, 30, 40, 40, 46)
            );
        }

        @ParameterizedTest
        @DisplayName("로또 번호에 중복된 숫자가 있으면 오류가 발생한다.")
        @MethodSource("duplicateLottoNumbers")
        void fail_lotto_numbers_is_duplicated(List<Integer> numbers) {
            assertThatThrownBy(() -> Lotto.from(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(LottoErrorCode.IS_NOT_DUPLICATES.getMessage());
        }

        static Stream<List<Integer>> duplicateLottoNumbers() {
            return Stream.of(
                    List.of(1, 2, 3, 4, 5, 5),
                    List.of(1, 1, 1, 1, 1, 1),
                    List.of(10, 20, 30, 40, 40, 45)
            );
        }
    }

    @Nested
    @DisplayName("당첨 결과 정상 반환 테스트")
    class WinningLottoTest {

        @Test
        @DisplayName("3개 미만으로 일치하는 경우 MISS를 반환한다.")
        void success_return_miss() {
            // given
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            LotteryNumber lotteryNumber = LotteryNumber.from(List.of(7, 8, 9, 10, 11, 12), 13);

            // when
            WinningRank result = lotto.calculateRank(lotteryNumber);

            // then
            assertThat(result).isEqualTo(WinningRank.MISS);
        }

        @Test
        @DisplayName("3개 일치하는 경우 5등을 반환한다.")
        void success_return_fifth() {
            // given
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            LotteryNumber lotteryNumber = LotteryNumber.from(List.of(1, 2, 3, 10, 11, 12), 13);

            // when
            WinningRank result = lotto.calculateRank(lotteryNumber);

            // then
            assertThat(result).isEqualTo(WinningRank.FIFTH);
        }

        @Test
        @DisplayName("4개 일치하는 경우 4등을 반환한다.")
        void success_return_fourth() {
            // given
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            LotteryNumber lotteryNumber = LotteryNumber.from(List.of(1, 2, 3, 4, 11, 12), 13);

            // when
            WinningRank result = lotto.calculateRank(lotteryNumber);

            // then
            assertThat(result).isEqualTo(WinningRank.FOURTH);
        }

        @Test
        @DisplayName("5개 일치하는 경우 3등을 반환한다.")
        void success_return_third() {
            // given
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            LotteryNumber lotteryNumber = LotteryNumber.from(List.of(1, 2, 3, 4, 5, 12), 13);

            // when
            WinningRank result = lotto.calculateRank(lotteryNumber);

            // then
            assertThat(result).isEqualTo(WinningRank.THIRD);
        }

        @Test
        @DisplayName("5개의 숫자와 보너스 숫자가 일치하는 경우 2등을 반환한다.")
        void success_return_second() {
            // given
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            LotteryNumber lotteryNumber = LotteryNumber.from(List.of(1, 2, 3, 4, 5, 12), 6);

            // when
            WinningRank result = lotto.calculateRank(lotteryNumber);

            // then
            assertThat(result).isEqualTo(WinningRank.SECOND);
        }

        @Test
        @DisplayName("모든 숫자가 맞는 경우 1등을 반환한다.")
        void success_return_first() {
            // given
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            LotteryNumber lotteryNumber = LotteryNumber.from(List.of(1, 2, 3, 4, 5, 6), 13);

            // when
            WinningRank result = lotto.calculateRank(lotteryNumber);

            // then
            assertThat(result).isEqualTo(WinningRank.FIRST);
        }

        @Test
        @DisplayName("보너스 숫자가 맞더라도 2등을 제외하고는 순위 산정에 추가되지 않는다")
        void success_if_bonus_number_match_but_not_count() {
            // given
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            LotteryNumber lotteryNumber = LotteryNumber.from(List.of(1, 2, 3, 4, 7, 8), 6);

            // when
            WinningRank result = lotto.calculateRank(lotteryNumber);

            // then
            assertThat(result).isEqualTo(WinningRank.FOURTH);
        }
    }
}
