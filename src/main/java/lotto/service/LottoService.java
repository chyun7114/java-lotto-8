package lotto.service;

import lotto.infrastructure.generator.LottoGenerator;
import lotto.model.Lottos;
import lotto.model.Money;

public class LottoService {

    private final LottoGenerator lottoGenerator;

    public LottoService(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public Lottos purchaseLottos(int purchaseAmount) {
        Money money = Money.from(purchaseAmount);
        return lottoGenerator.generateLotto(money);
    }
}
