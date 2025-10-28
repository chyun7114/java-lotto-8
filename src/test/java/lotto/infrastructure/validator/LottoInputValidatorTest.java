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
    @DisplayName("로또 입력 실패 케이스 테스트")
    class FailCase {
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
}