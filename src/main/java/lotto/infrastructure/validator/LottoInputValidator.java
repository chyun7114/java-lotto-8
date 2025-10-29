package lotto.infrastructure.validator;

import lotto.exception.UserInputErrorCode;

public class LottoInputValidator {

    public int lottoPriceInputValidator(String userInput) {
        int price = parseAndValidateNumeric(userInput);
        isNotNegativePrice(price);

        return price;
    }

    private int parseAndValidateNumeric(String userInput) {
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw UserInputErrorCode.IS_NOT_NUMERIC_PRICE.toException();
        }
    }

    private void isNotNegativePrice(int price) {
        if (price <= 0) {
            throw UserInputErrorCode.IS_NOT_NEGATIVE_PRICE.toException();
        }
    }
}
