package lotto.service;

import lotto.infrastructure.generator.LottoGenerator;
import lotto.model.Lottos;
import lotto.model.Money;

public class LottoService {

    private final LottoGenerator lottoGenerator;

    public LottoService(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public Lottos purchaseLottos(Money money) {
        return lottoGenerator.generateLotto(money);
    }
}
