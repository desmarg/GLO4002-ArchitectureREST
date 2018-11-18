package trading.domain;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Credits implements Comparable<Credits> {
    public static final Credits ZERO = new Credits(new BigDecimal(0));

    private final BigDecimal amount;

    public Credits(BigDecimal amount) {
        this.amount = amount;
    }

    public static Credits fromInteger(Integer amount) {
        return new Credits(new BigDecimal(amount));
    }

    public static Credits fromLong(Long amount) {
        return new Credits(new BigDecimal(amount));
    }

    public static Credits fromString(String text) {
        return new Credits(new BigDecimal(text));
    }

    public Credits add(Credits other) {
        return new Credits(this.amount.add(other.amount));
    }

    public Credits subtract(Credits other) {
        return new Credits(this.amount.subtract(other.amount));
    }

    public Credits multiply(Credits other) {
        return new Credits(this.amount.multiply(other.amount));
    }

    public BigDecimal toBigDecimal() {
        return this.amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Credits) {
            return this.amount.compareTo(((Credits) obj).toBigDecimal()) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (this.amount.multiply(new BigDecimal(27))).intValue();
    }

    @Override
    public int compareTo(@NotNull Credits credits) {
        return this.amount.compareTo(credits.toBigDecimal());
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
}
