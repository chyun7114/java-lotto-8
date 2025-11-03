package lotto.view;

public enum PrintingFormatEnum {

    INPUT_PURCHASE_PRICE_FORMAT("구입금액을 입력해 주세요."),
    INPUT_WINNING_NUMBER_FORMAT("\n당첨 번호를 입력해주세요"),
    INPUT_BONUS_NUMBER_FORMAT("\n보너스 번호를 입력해주세요"),

    OUTPUT_PURCHASED_COUNT_STRING("%d개를 구매했습니다.\n"),
    OUTPUT_WINNIG_STATISTICS_HEADER("당첨 통계\n---"),
    OUTPUT_PROFIT_RATE_MESSAGE("총 수익률은 %s%%입니다."),
    OUTPUT_WINNING_LOTTO_COUNT_MESSAGE("%s - %d개\n"),
    OUTPUT_BONUS_COUNT_MESSAGE("%d개 일치, 보너스 볼 일치 (%s원)"),
    OUTPUT_NORMAL_COUNT_MESSAGE("%d개 일치 (%s원)"),
    ;

    private final String format;

    PrintingFormatEnum(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
