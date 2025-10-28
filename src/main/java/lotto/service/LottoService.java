package lotto.service;

import lotto.infrastructure.generator.LottoGenerator;

public class LottoService {

    private final LottoGenerator lottoGenerator;

    public LottoService(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }
}
