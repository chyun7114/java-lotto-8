package lotto.config;

public interface BaseErrorCode<T extends Exception>{
    String getMessage();
    T toException();
}
