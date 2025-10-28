package lotto.infrastructure.validator;

import lotto.exception.LottoErrorCode;

public class LottoInputValidator {

    private static final int LOTTO_MAX_LIMIT = 50;
    private static final int LOTTO_PRICE_UNIT = 1000;

    public int lottoPriceInputValidator(String userInput) {
        int price = parseAndValidateNumeric(userInput);

        isNotNegativePrice(price);
        validateAmountUnit(price);
        exceedLottoPurchaseLimit(price);

        return price;
    }

    private int parseAndValidateNumeric(String userInput) {
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw LottoErrorCode.IS_NOT_NUMERIC_PRICE.toException();
        }
    }

    private void isNotNegativePrice(int price) {
        if (price <= 0) {
            throw LottoErrorCode.IS_NOT_NEGATIVE_PRICE.toException();
        }
    }

    private void validateAmountUnit(int amount) {
        if (amount % LOTTO_PRICE_UNIT != 0) {
            throw LottoErrorCode.PURCHASE_AMOUNT_NOT_DIVISIBLE_BY_THOUSAND.toException();
        }
    }

    private void exceedLottoPurchaseLimit(int price) {
        int amount = price / LOTTO_PRICE_UNIT;
        if (amount > LOTTO_MAX_LIMIT) {
            throw LottoErrorCode.EXCEED_LOTTO_PURCHASE_LIMIT.toException();
        }
    }
}
