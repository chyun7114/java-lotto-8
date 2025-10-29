package lotto.infrastructure.validator;

import java.util.Arrays;
import java.util.List;
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

    public void winningNumberValidator(String userInput) {
        validateEndsWIthDelimeter(userInput);
        validateNoblankInNumbers(userInput);
    }

    private void validateEndsWIthDelimeter(String userInput) {
        if (userInput.startsWith(",") || userInput.endsWith(",")) {
            throw UserInputErrorCode.WINNING_NUMBERS_NOT_NUMERIC.toException();
        }
    }

    private void validateNoblankInNumbers(String userInput) {
        String[] numbers = userInput.split(",");
        if (checkHasBlank(numbers)) {
            throw UserInputErrorCode.WINNING_NUMBERS_NOT_NUMERIC.toException();
        }
    }

    private boolean checkHasBlank(String[] numbers) {
        return Arrays.stream(numbers)
                .anyMatch(s -> s.isBlank() || s.contains(" "));
    }

    public int bonusNumberValidator(String userInput) {
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw UserInputErrorCode.BONUS_NUMBER_NOT_NUMERIC.toException();
        }

    }
}
