package lotto.model;

import lotto.exception.MoneyErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class MoneyTest {

    @ParameterizedTest
    @DisplayName("Money 객체 생성에 성공한다.")
    @ValueSource(ints = {1000, 2000, 3000})
    void success_money_create(int amount) {
        assertThatCode(() -> Money.from(amount))
                .doesNotThrowAnyException();
    }

    @Nested
    @DisplayName("Money 객체 생성 실패 테스트")
    class FailCreateMoney {

        @ParameterizedTest
        @DisplayName("1000 단위가 아닌 경우 오류를 발생한다.")
        @ValueSource(ints = {999, 1500, 2100, 3500})
        void fail_invalid_unit(int amount) {
            assertThatThrownBy(() -> Money.from(amount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(MoneyErrorCode.PURCHASE_AMOUNT_NOT_DIVISIBLE_BY_THOUSAND.getMessage());
        }

        @ParameterizedTest
        @DisplayName("로또를 구매하기 위해 소지할 수 있는 돈은 최대 50이다.")
        @ValueSource(ints = {51000, 100000})
        void fail_exceed_lotto_purchase_limit(int amount) {
            assertThatThrownBy(() -> Money.from(amount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(MoneyErrorCode.EXCEED_LOTTO_PURCHASE_LIMIT.getMessage());
        }
    }

}