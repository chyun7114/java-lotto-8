package lotto.infrastructure.parser;

import lotto.exception.UserInputErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class LottoInputParserTest {


    @ParameterizedTest
    @DisplayName("당첨 번호를 정상적으로 분리한다.")
    @ValueSource(strings = {"1,2,3,4,5,6", "1,2,3,4,5,7", "34,5,3,6,2,35"})
    void success_parsing(String userInput) {
        assertThatCode(() -> LottoInputParser.parseWinningNumbers(userInput))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("당첨 번호를 숫자가 아닌 값으로 입력하면 오류가 발생한다.")
    @ValueSource(strings = {"1,2,3,4,5,6a", "1,2,3,4,5,%", "1,2,3,4,5,a", "a,b,c,d,e,f"})
    void fail_is_not_numeric_winning_number(String userInput) {
        assertThatThrownBy(() -> LottoInputParser.parseWinningNumbers(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(UserInputErrorCode.WINNING_NUMBERS_NOT_NUMERIC.getMessage());
    }

}