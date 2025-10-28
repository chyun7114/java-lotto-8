package lotto.infrastructure;

import lotto.model.Lottos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LottoGeneratorTest {

    private final LottoGenerator lottoGenerator = new LottoGenerator();

    @Test
    @DisplayName("로또가 정상적으로 발행된다.")
    void success_lotto_generate() {

        // given
        int testAmount = 10000;
        int expectedLottoCount = 10;

        // when
        Lottos result = lottoGenerator.generateLotto(testAmount);

        // then
        assertThat(result.lottoList()).hasSize(expectedLottoCount);
        assertThat(result.lottoList()).allSatisfy(lotto -> {
            assertThat(lotto.getNumbers()).allMatch(number -> number >= 1 && number <= 45);
        });
    }
}