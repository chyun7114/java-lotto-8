package lotto.exception;

public enum MoneyErrorCode implements BaseErrorCode<IllegalArgumentException> {

    PURCHASE_AMOUNT_NOT_DIVISIBLE_BY_THOUSAND("로또 구매 금액은 1,000원으로 나누어 떨어져야 합니다."),
    EXCEED_LOTTO_PURCHASE_LIMIT("로또는 한번에 최대 50장까지만 구매 가능합니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    MoneyErrorCode(String message) {
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
