package lotto.model;

import lotto.exception.LottoErrorCode;

public class Money {

    private static final int LOTTO_MAX_LIMIT = 50;
    private static final int LOTTO_PRICE_UNIT = 1000;

    private final int amount;

    public Money(int amount) {
        validateMoney(amount);
        this.amount = amount;
    }

    public void validateMoney(int amount) {
        validateAmountUnit(amount);
        exceedLottoPurchaseLimit(amount);
    }

    public int getAmount() {
        return amount;
    }

    private void validateAmountUnit(int amount) {
        if (amount % LOTTO_PRICE_UNIT != 0) {
            throw LottoErrorCode.PURCHASE_AMOUNT_NOT_DIVISIBLE_BY_THOUSAND.toException();
        }
    }

    private void exceedLottoPurchaseLimit(int price) {
        if (price / LOTTO_PRICE_UNIT > LOTTO_MAX_LIMIT) {
            throw LottoErrorCode.EXCEED_LOTTO_PURCHASE_LIMIT.toException();
        }
    }

    public static Money from(int amount) {
        return new Money(amount);
    }
}
