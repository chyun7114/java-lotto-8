package lotto.model;

import java.util.List;
import java.util.stream.Stream;
import lotto.exception.LottoErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.*;

class LotteryNumberTest {

    @ParameterizedTest
    @DisplayName("당첨 번호와 보너스 숫자가 정상적으로 생성된다.")
    @MethodSource("validLotteryNumbers")
    void success_lottery_number_create(List<Integer> winningNumbers, int bonusNumber) {
        // when & then
        assertThatCode(() -> LotteryNumber.from(winningNumbers, bonusNumber))
                .doesNotThrowAnyException();
    }

    static Stream<Arguments> validLotteryNumbers() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 7),
                Arguments.of(List.of(10, 20, 30, 40, 41, 42), 43)
        );
    }

    @Nested
    @DisplayName("당첨 번호 오류 테스트")
    class FailWinningNumberCreate {

        @ParameterizedTest
        @DisplayName("당첨 번호가 6개가 아니면 오류가 발생한다.")
        @MethodSource("invalidSizeWinningNumber")
        void fail_invalid_size_winning_number(List<Integer> winningNumbers, int bonusNumber) {
            // when & then
            assertThatThrownBy(() -> LotteryNumber.from(winningNumbers, bonusNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(LottoErrorCode.INVALID_SIZE.getMessage());
        }

        static Stream<Arguments> invalidSizeWinningNumber() {
            return Stream.of(
                    Arguments.of(List.of(1, 2, 3, 4, 5), 10),
                    Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7), 11)
            );
        }

        @ParameterizedTest
        @DisplayName("당첨 번호가 1~45사이의 숫자가 아니라면 오류가 발생한다.")
        @MethodSource("isOutOfRangeWinningNumber")
        void fail_is_out_of_range_winning_number(List<Integer> winningNumbers, int bonusNumber) {
            // when & then
            assertThatThrownBy(() -> LotteryNumber.from(winningNumbers, bonusNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(LottoErrorCode.LOTTO_NUMBERS_IS_IN_NOT_RANGE.getMessage());
        }

        static Stream<Arguments> isOutOfRangeWinningNumber() {
            return Stream.of(
                    Arguments.of(List.of(1, 2, 3, 4, 5, 46), 10),
                    Arguments.of(List.of(0, 2, 3, 4, 5, 6), 11)
            );
        }
    }

    @Nested
    @DisplayName("보너스 번호 오류 테스트")
    class FailBonusNumberCreate {

        @ParameterizedTest
        @DisplayName("보너스 번호가 1~45 숫자가 아닌 경우 오류가 발생한다.")
        @MethodSource("isOutOfRangeBonusNumber")
        void fail_is_out_of_range_bonus_number(List<Integer> winningNumbers, int bonusNumber) {
            // when & then
            assertThatThrownBy(() -> LotteryNumber.from(winningNumbers, bonusNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(LottoErrorCode.LOTTO_NUMBERS_IS_IN_NOT_RANGE.getMessage());
        }

        static Stream<Arguments> isOutOfRangeBonusNumber() {
            return Stream.of(
                    Arguments.of(List.of(1, 2, 3, 4, 5, 6), -1),
                    Arguments.of(List.of(1, 2, 3, 4, 5, 6), 0),
                    Arguments.of(List.of(1, 2, 3, 4, 5, 6), 46),
                    Arguments.of(List.of(1, 2, 3, 4, 5, 6), 47)
            );
        }

        @ParameterizedTest
        @DisplayName("보너스 번호가 당첨 번호와 중복된 경우 오류가 발생한다.")
        @MethodSource("isDuplicateWithWinningNumber")
        void fail_is_duplicate_with_winning_number(List<Integer> winningNumbers, int bonusNumber) {
            // when & then
            assertThatThrownBy(() -> LotteryNumber.from(winningNumbers, bonusNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(LottoErrorCode.IS_NOT_DUPLICATES.getMessage());
        }

        static Stream<Arguments> isDuplicateWithWinningNumber() {
            return Stream.of(
                    Arguments.of(List.of(1, 2, 3, 4, 5, 6), 1),
                    Arguments.of(List.of(1, 2, 3, 4, 5, 6), 2),
                    Arguments.of(List.of(1, 2, 3, 4, 5, 6), 3),
                    Arguments.of(List.of(1, 2, 3, 4, 5, 6), 4)
            );
        }
    }
}