package lotto.model;

public class LottoProperties {

    public static final int LOTTO_PRICE = 1000;
    public static final int LOTTO_NUMBERS_SIZE = 6;
    public static final int LOTTO_NUMBER_MIN = 1;
    public static final int LOTTO_NUMBER_MAX = 45;
    public static final int LOTTO_MAX_LIMIT = 50;
    public static final int LOTTO_PRICE_UNIT = 1000;

    public static final boolean isOutOfRange(int number) {
        return number < LOTTO_NUMBER_MIN || number > LOTTO_NUMBER_MAX;
    }
}
