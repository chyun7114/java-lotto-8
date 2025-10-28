package lotto.model;

import lotto.exception.MoneyErrorCode;

public class Money {

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
        if (isLottoPriceUnit(amount)) {
            throw MoneyErrorCode.PURCHASE_AMOUNT_NOT_DIVISIBLE_BY_THOUSAND.toException();
        }
    }

    private void exceedLottoPurchaseLimit(int price) {
        if (getLottoCount(price) > LottoProperties.LOTTO_MAX_LIMIT) {
            throw MoneyErrorCode.EXCEED_LOTTO_PURCHASE_LIMIT.toException();
        }
    }

    private boolean isLottoPriceUnit(int amount) {
        return amount % LottoProperties.LOTTO_PRICE_UNIT == 0;
    }

    private int getLottoCount(int price) {
        return price / LottoProperties.LOTTO_PRICE;
    }

    public static Money from(int amount) {
        return new Money(amount);
    }
}
