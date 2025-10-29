package lotto.infrastructure.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.exception.UserInputErrorCode;

public final class LottoInputParser {

    private static final String DELIMITER = ",";

    private LottoInputParser() {

    }

    public static List<Integer> parseWinningNumbers(String userInput) {
        try {
            return Arrays.stream(userInput.split(DELIMITER))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw UserInputErrorCode.WINNING_NUMBERS_NOT_NUMERIC.toException();
        }
    }

}
