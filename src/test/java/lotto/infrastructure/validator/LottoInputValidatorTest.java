package lotto.infrastructure.validator;

import lotto.exception.UserInputErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class LottoInputValidatorTest {

    private final LottoInputValidator lottoInputValidator = new LottoInputValidator();

    @ParameterizedTest
    @DisplayName("로또 구매 비용이 정삭적으로 입력되었다.")
    @ValueSource(strings = {"1000", "2000", "3000", "50000"})
    void success_lotto_price(String userInput) {
        assertThatCode(() -> lottoInputValidator.lottoPriceInputValidator(userInput))
                .doesNotThrowAnyException();
    }

    @Nested
    @DisplayName("로또 번호 입력 오류 테스트")
    class FailLottoInput {
        @ParameterizedTest
        @DisplayName("로또 구매 비용을 숫자가 아닌 값으로 입력하면 오류가 발생한다.")
        @ValueSource(strings = {"1000a", "2%400", "abc", "", " "})
        void fail_is_not_numeric_price(String userInput) {
            assertThatThrownBy(() -> lottoInputValidator.lottoPriceInputValidator(userInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(UserInputErrorCode.IS_NOT_NUMERIC_PRICE.getMessage());
        }

        @ParameterizedTest
        @DisplayName("로또 구매 비용을 양수로 입력하지 않은 경우 오류가 발생한다.")
        @ValueSource(strings = {"-1000", "-1", "0"})
        void fail_is_not_positive_price(String userInput) {
            assertThatThrownBy(() -> lottoInputValidator.lottoPriceInputValidator(userInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(UserInputErrorCode.IS_NOT_NEGATIVE_PRICE.getMessage());
        }
    }

    @Nested
    @DisplayName("로또 당첨 번호 입력 오류 테스트")
    class FailLotteryInput {

        @ParameterizedTest
        @DisplayName("당첨 번호 입력 시 쉼표로 시작하거나 끝나는 경우 오류가 발생한다.")
        @ValueSource(strings = {",1,2,3,4,5,6", "1,2,3,4,5,6,"})
        void fail_winning_number_start_or_end_with_comma(String userInput) {
            assertThatThrownBy(() -> lottoInputValidator.winningNumberValidator(userInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(UserInputErrorCode.WINNING_NUMBERS_NOT_NUMERIC.getMessage());
        }

        @ParameterizedTest
        @DisplayName("당첨 번호 입력 시 쉼표가 연속으로 있는 경우 오류가 발생한다.")
        @ValueSource(strings = {"1,,2,3,4,5,6", "1,2,,,3,4,5,6"})
        void fail_winning_number_consecutive_commas(String userInput) {
            assertThatThrownBy(() -> lottoInputValidator.winningNumberValidator(userInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(UserInputErrorCode.WINNING_NUMBERS_NOT_NUMERIC.getMessage());
        }

        @ParameterizedTest
        @DisplayName("보너스 번호를 숫자가 아닌 값으로 입력하면 오류가 발생한다.")
        @ValueSource(strings = {"1a", "a", " ", ""})
        void fail_is_not_numeric_bonus_number(String userInput) {
            assertThatThrownBy(() -> lottoInputValidator.bonusNumberValidator(userInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(UserInputErrorCode.BONUS_NUMBER_NOT_NUMERIC.getMessage());
        }
    }
}