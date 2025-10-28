package lotto.infrastructure.generator;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.IntStream;
import lotto.model.Lotto;
import lotto.model.LottoProperties;
import lotto.model.Lottos;
import lotto.model.Money;

public class LottoGenerator {

    public Lottos generateLotto(Money money) {
        List<Lotto> lottoList = IntStream.range(0, getLottoCount(money))
                .mapToObj(i -> generateSingleLotto())
                .toList();

        return Lottos.from(lottoList);
    }

    private Lotto generateSingleLotto() {
        return Lotto.from(getNumbers());
    }

    private int getLottoCount(Money money) {
        return money.getAmount() / LottoProperties.LOTTO_PRICE;
    }

    private List<Integer> getNumbers() {
        return Randoms.pickUniqueNumbersInRange(
                LottoProperties.LOTTO_NUMBER_MIN,
                LottoProperties.LOTTO_NUMBER_MAX,
                LottoProperties.LOTTO_NUMBERS_SIZE
        );
    }
}
