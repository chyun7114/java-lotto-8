package lotto.infrastructure.validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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

    public List<Integer> winningNumberValidator(String userInput) {
        try {
            return Arrays.stream(userInput.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw UserInputErrorCode.WINNING_NUMBERS_NOT_NUMERIC.toException();
        }
    }

    public int bonusNumberValidator(String userInput) {
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw UserInputErrorCode.BONUS_NUMBER_NOT_NUMERIC.toException();
        }

    }
}
