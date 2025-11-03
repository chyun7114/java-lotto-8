package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @Test
    @DisplayName("수익률을 정확하게 계산한다.")
    void calculateProfitRateTest() {
        // given
        Statistics statistics = Statistics.createEmpty();
        statistics.putRank(WinningRank.FIFTH);
        Money purchaseMoney = Money.from(8000);

        // when
        LottoResult lottoResult = new LottoResult(statistics, purchaseMoney);

        // then
        assertThat(lottoResult.getProfitRate()).isEqualTo((double) 5000 / 8000 * 100);
    }

}