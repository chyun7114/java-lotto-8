package lotto.controller;

import java.util.List;
import lotto.infrastructure.parser.LottoInputParser;
import lotto.infrastructure.validator.LottoInputValidator;
import lotto.model.LotteryNumber;
import lotto.model.LottoResult;
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
        Money money = askMoney();
        Lottos lottos = lottoService.purchaseLottos(money);
        printPurchaseResult(lottos);

        LotteryNumber lotteryNumber = drawLotteryNumbers();

        LottoResult lottoResult = getLottoStatistics(lottos, lotteryNumber);
        outputView.printLottoResult(lottoResult);
    }

    private Money askMoney() {
        while (true) {
            try {
                String priceInput = inputView.inputPurchasePrice();
                int price = lottoInputValidator.lottoPriceInputValidator(priceInput);
                return Money.from(price);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printPurchaseResult(Lottos lottos) {
        outputView.printPurchaseCount(lottos.lottoList().size());
        outputView.printPurchasesLottos(lottos);
    }

    private LotteryNumber drawLotteryNumbers() {
        while (true) {
            try {
                List<Integer> winningNumbers = askingWInningNumbers();
                int bonusNumber = askBonusNumber();
                return lottoService.createLotteryNumber(winningNumbers, bonusNumber);
            } catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
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

    private LottoResult getLottoStatistics(Lottos lottos, LotteryNumber lotteryNumber) {
        Statistics statistics = lottoService.calculateWinningResult(lottos, lotteryNumber);
        return new LottoResult(statistics, lottos);
    }
}
