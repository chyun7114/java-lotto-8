package lotto.view;

import lotto.model.Lotto;
import lotto.model.Lottos;

public class OutputView {

    private static final String PURCHASED_COUNT_STRING = "%d개를 구매했습니다.\n";

    public void printPurchaseCount(int purchasedCount) {
        System.out.printf(PURCHASED_COUNT_STRING, purchasedCount);
    }

    public void printPurchasesLottos(Lottos lottos) {
        for(Lotto lotto : lottos.lottoList()) {
            System.out.println(lotto);
        }
    }
}
