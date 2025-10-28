package lotto.controller;

import lotto.infrastructure.validator.LottoInputValidator;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final LottoService lottoService;
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoInputValidator lottoInputValidator;

    public LottoController(
            LottoService lottoService, InputView inputView, OutputView outputView,
            LottoInputValidator lottoInputValidator
    ) {
        this.lottoService = lottoService;
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoInputValidator = lottoInputValidator;
    }

    public void purchase() {
        String priceInput = inputView.inputPurchasePrice();
        int price = lottoInputValidator.lottoPriceInputValidator(priceInput);
        Lottos lottos = lottoService.purchaseLottos(Money.from(price));

        outputView.printPurchaseCount(lottos.lottoList().size());
        outputView.printPurchasesLottos(lottos);
    }
}
