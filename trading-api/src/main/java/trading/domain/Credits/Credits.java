package trading.domain.Credits;

import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Credits implements Comparable<Credits> {
    private BigDecimal amount;

    public Credits(BigDecimal amount) {
        this.amount = amount;
    }

    public static Credits fromInteger(Integer amount) {
        return new Credits(new BigDecimal(amount));
    }

    public static Credits fromLong(Long amount) {
        return new Credits(new BigDecimal(amount));
    }

    public static Credits fromDouble(Double amount) {
        return new Credits(new BigDecimal(amount));
    }

    public static Credits zero() {
        return new Credits(new BigDecimal(0));
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

    public Float amountToFloat() {
        BigDecimal scaledDecimal = this.amount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return scaledDecimal.floatValue();
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
    public int compareTo(Credits credits) {
        return this.amount.compareTo(credits.toBigDecimal());
    }

    public boolean isGreaterThan(Credits other) {
        return this.compareTo(other) > 0;
    }

    public boolean isSmallerThan(Credits other) {
        return this.compareTo(other) < 0;
    }

    public String toString() {
        DecimalFormatSymbols symbolsFormat = new DecimalFormatSymbols();
        symbolsFormat.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setDecimalFormatSymbols(symbolsFormat);
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setGroupingUsed(false);

        return decimalFormat.format(this.amount);
    }

}
