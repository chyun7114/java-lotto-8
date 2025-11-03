package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String inputPurchasePrice() {
        System.out.println(PrintingFormatEnum.INPUT_PURCHASE_PRICE_FORMAT.getFormat());
        return Console.readLine();
    }

    public String inputWinningNumber() {
        System.out.println(PrintingFormatEnum.INPUT_WINNING_NUMBER_FORMAT.getFormat());
        return Console.readLine();
    }

    public String inputBounsNumber() {
        System.out.println(PrintingFormatEnum.INPUT_BONUS_NUMBER_FORMAT.getFormat());
        return Console.readLine();
    }
}
