package lotto.exception;

public interface BaseErrorCode<T extends Exception>{
    String getMessage();
    T toException();
}
