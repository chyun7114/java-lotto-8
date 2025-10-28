package lotto.infrastructure.generator;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.IntStream;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Money;

public class LottoGenerator {

    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;

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
        return money.getAmount() / LOTTO_PRICE;
    }

    private List<Integer> getNumbers() {
        return Randoms.pickUniqueNumbersInRange(
                LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX, LOTTO_NUMBERS_SIZE
        );
    }
}
