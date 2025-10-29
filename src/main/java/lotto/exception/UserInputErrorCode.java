package lotto.exception;

public enum UserInputErrorCode implements BaseErrorCode<IllegalArgumentException>{

    IS_NOT_NUMERIC_PRICE("로또 구매 금액은 숫자만 입력 가능합니다."),
    IS_NOT_NEGATIVE_PRICE("로또 구매 금액은 양수여야 합니다."),
    WINNING_NUMBERS_NOT_NUMERIC("당첨 번호는 숫자와 쉼표(,)로만 입력해야 합니다."),
    BONUS_NUMBER_NOT_NUMERIC("보너스 번호는 숫자만 입력 가능합니다.");


    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    UserInputErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return ERROR_PREFIX + message;
    }

    @Override
    public IllegalArgumentException toException() {
        return new IllegalArgumentException(getMessage());
    }
}
