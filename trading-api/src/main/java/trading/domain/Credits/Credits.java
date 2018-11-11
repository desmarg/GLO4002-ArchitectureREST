package trading.domain.Credits;

import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Credits implements Comparable<Credits> {
    private BigDecimal amount;

    public Credits() {
        this.amount = new BigDecimal(0);
    }

    public Credits(BigDecimal amount) {
        this.amount = amount;
    }

    public Credits(Credits credits) {
        this.amount = credits.amount;
    }

    public Credits(Integer amount) {
        this(new BigDecimal(amount));
    }

    public Credits(double amount) {
        this(fromDouble(amount));
    }

    public static Credits fromDouble(double amount) {
        BigDecimal bigDecimalAmount = BigDecimal.valueOf(amount);
        return new Credits(bigDecimalAmount);
    }

    public void add(Credits amount) {
        this.amount = this.amount.add(amount.amount);
    }

    public void subtract(Credits amount) {
        this.amount = this.amount.subtract(amount.amount);
    }

    public void multiply(Long quantity) {
        BigDecimal rhs = new BigDecimal(quantity);
        this.amount = this.amount.multiply(rhs);
    }

    public void multiply(double quantity) {
        BigDecimal rhs = new BigDecimal(quantity);
        this.amount = this.amount.multiply(rhs);
    }

    @JsonValue
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

    public Float valueToFloat() {
        BigDecimal scaledDecimal = this.amount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return scaledDecimal.floatValue();
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Credits) {
            return this.amount.compareTo(((Credits) obj).getAmount()) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (this.amount.multiply(new BigDecimal(1000))).intValue();
    }

    @Override
    public int compareTo(Credits credits) {
        return this.amount.compareTo(credits.getAmount());
    }
}
