package lotto.infrastructure.generator;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.IntStream;
import lotto.model.Lotto;
import lotto.model.Lottos;

public class LottoGenerator {

    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;

    public Lottos generateLotto(int amount) {
        List<Lotto> lottoList = IntStream.range(0, getLottoCount(amount))
                .mapToObj(i -> generateSingleLotto())
                .toList();

        return Lottos.from(lottoList);
    }

    private Lotto generateSingleLotto() {
        return Lotto.from(getNumbers());
    }

    private int getLottoCount(int amount) {
        return amount / LOTTO_PRICE;
    }

    private List<Integer> getNumbers() {
        return Randoms.pickUniqueNumbersInRange(
                LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX, LOTTO_NUMBERS_SIZE
        );
    }
}
