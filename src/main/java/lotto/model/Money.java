package lotto.model;

public record Money(int amount) {

    public static Money from(int amount) {
        return new Money(amount);
    }
}
