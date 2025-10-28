package lotto.exception;

public enum LottoErrorCode implements BaseErrorCode<IllegalArgumentException> {

    NUMBERS_IS_NOT_NULL("로또 번호는 null일 수 없습니다."),
    INVALID_SIZE("로또 번호는 6개여야 합니다."),
    LOTTO_NUMBERS_IS_IN_NOT_RANGE("로또 번호는 1~45 사이여야 합니다."),
    IS_NOT_DUPLICATES("로또 번호는 중복될 수 없습니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    LottoErrorCode(String message) {
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
