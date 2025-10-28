package lotto.config;

import lotto.infrastructure.generator.LottoGenerator;
import lotto.service.LottoService;

public class AppConfig {

    private static AppConfig instance;

    private AppConfig() {

    }

    public static AppConfig getInstance() {
        if(instance == null)
            instance = new AppConfig();

        return instance;
    }

    public LottoService lottoService() {
        return new LottoService(lottoGenerator());
    }

    private LottoGenerator lottoGenerator() {
        return new LottoGenerator();
    }
}
