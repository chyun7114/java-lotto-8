package lotto.infrastructure.generator;

import lotto.model.Lottos;
import lotto.model.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LottoGeneratorTest {

    private final LottoGenerator lottoGenerator = new LottoGenerator();

    @Test
    @DisplayName("로또가 정상적으로 발행된다.")
    void success_lotto_generate() {

        // given
        Money testMoney = Money.from(10000);
        int expectedLottoCount = 10;

        // when
        Lottos result = lottoGenerator.generateLotto(testMoney);

        // then
        assertThat(result.lottoList()).hasSize(expectedLottoCount);
        assertThat(result.lottoList()).allSatisfy(lotto -> {
            assertThat(lotto.numbers()).allMatch(number -> number >= 1 && number <= 45);
        });
    }
}