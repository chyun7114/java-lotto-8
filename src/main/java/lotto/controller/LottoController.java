package lotto.controller;

import java.util.List;
import lotto.infrastructure.parser.LottoInputParser;
import lotto.infrastructure.validator.LottoInputValidator;
import lotto.model.LotteryNumber;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.Statistics;
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

    public void run() {
        Lottos lottos = purchaseLottos();
        LotteryNumber lotteryNumber = drawLotteryNumbers();
        Statistics statistics = getLottoStatistics(lottos, lotteryNumber);
    }

    private Lottos purchaseLottos() {
        Money money = askMoney();
        Lottos lottos = lottoService.purchaseLottos(money);
        printPurchaseResult(lottos);
        return lottos;
    }

    private Money askMoney() {
        String priceInput = inputView.inputPurchasePrice();
        int price = lottoInputValidator.lottoPriceInputValidator(priceInput);
        return Money.from(price);
    }

    private void printPurchaseResult(Lottos lottos) {
        outputView.printPurchaseCount(lottos.lottoList().size());
        outputView.printPurchasesLottos(lottos);
    }

    private LotteryNumber drawLotteryNumbers() {
        List<Integer> winningNumbers = askingWInningNumbers();
        int bonusNumber = askBonusNumber();
        return lottoService.createLotteryNumber(winningNumbers, bonusNumber);
    }

    private List<Integer> askingWInningNumbers() {
        String winningNumbersInput = inputView.inputWinningNumber();
        lottoInputValidator.winningNumberValidator(winningNumbersInput);
        return LottoInputParser.parseWinningNumbers(winningNumbersInput);
    }

    private int askBonusNumber() {
        String bonusNumberInput = inputView.inputBounsNumber();
        return lottoInputValidator.bonusNumberValidator(bonusNumberInput);
    }

    private Statistics getLottoStatistics(Lottos lottos, LotteryNumber lotteryNumber) {
        return lottoService.calculateWinningResult(lottos, lotteryNumber);
    }
}
