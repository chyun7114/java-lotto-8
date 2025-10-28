package lotto;

import java.util.stream.Stream;
import lotto.exception.LottoErrorCode;
import lotto.model.Lotto;
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
}
