package trading.domain;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Credits implements Comparable<Credits> {
    public static final Credits ZERO = new Credits(new BigDecimal(0), Currency.XXX);

    private final BigDecimal amount;
    private final Currency currency;

    public Credits(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public static Credits fromInteger(Integer amount, Currency currency) {
        return new Credits(new BigDecimal(amount), currency);
    }

    public static Credits fromLong(Long amount, Currency currency) {
        return new Credits(new BigDecimal(amount), currency);
    }

    public static Credits fromString(String text, Currency currency) {
        return new Credits(new BigDecimal(text), currency);
    }

    public Credits add(Credits other) {
        return new Credits(this.amount.add(other.amount), this.currency);
    }

    public Credits add(BigDecimal amountToAdd) {
        return new Credits(this.amount.add(amountToAdd), this.currency);
    }

    public Credits subtract(Credits other) {
        return new Credits(this.amount.subtract(other.amount), this.currency);
    }

    public Credits subtract(BigDecimal amountToSubtract) {
        return new Credits(this.amount.subtract(amountToSubtract), this.currency);
    }

    public Credits multiply(Credits other) {
        return new Credits(this.amount.multiply(other.amount), this.currency);
    }

    public Credits multiply(BigDecimal multiplier) {
        return new Credits(this.amount.multiply(multiplier), this.currency);
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Credits)) {
            return false;
        }

        Credits c = (Credits) obj;
        if (!areCreditsComparable(this, c)) {
            return false;
        }
        return this.amount.compareTo(c.getAmount()) == 0;
    }

    @Override
    public int hashCode() {
        return (this.amount.multiply(new BigDecimal(27))).intValue() + this.currency.hashCode();
    }

    @Override
    public int compareTo(@NotNull Credits credits) {
        if (!areCreditsComparable(this, credits)) {
            throw new NotComparableCreditsException();
        }
        return this.amount.compareTo(credits.getAmount());
    }

    @Override
    public String toString() {
        return this.amount.setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
    }

    public boolean isGreater(Credits other) {
        return this.compareTo(other) > 0;
    }

    public boolean isSmaller(Credits other) {
        return this.compareTo(other) < 0;
    }

    public boolean isSmallerOrEqual(Credits other) {
        return this.compareTo(other) <= 0;
    }

    private boolean areCreditsComparable(Credits c1, Credits c2) {
        return c1.currency == Currency.XXX || c2.currency == Currency.XXX || c1.currency == c2.currency;
    }
}
