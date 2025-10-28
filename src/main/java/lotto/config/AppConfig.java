package lotto.config;

import lotto.controller.LottoController;
import lotto.infrastructure.generator.LottoGenerator;
import lotto.infrastructure.validator.LottoInputValidator;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {

    private static AppConfig instance;

    private AppConfig() {

    }

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }

        return instance;
    }

    public LottoController lottoController() {
        return new LottoController(lottoService(), inputView(), outputView(), lottoInputValidator());
    }

    private LottoService lottoService() {
        return new LottoService(lottoGenerator());
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }

    private LottoGenerator lottoGenerator() {
        return new LottoGenerator();
    }

    private LottoInputValidator lottoInputValidator() {
        return new LottoInputValidator();
    }
}
