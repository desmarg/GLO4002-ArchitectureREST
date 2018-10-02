package domain;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Credits implements Comparable<Credits> {
    public BigDecimal amount;

    public Credits() {
        this.amount = new BigDecimal(0);
    }

    public Credits(BigDecimal amount) {
        this.amount = amount;
    }

    public static Credits fromDouble(double amount) {
        BigDecimal bigDecimalAmount = BigDecimal.valueOf(amount);
        return new Credits(bigDecimalAmount);
    }

    public Credits add(Credits amount) {
        return new Credits(this.amount.add(amount.amount));
    }

    public Credits subtract(Credits amount) {
        return new Credits(this.amount.subtract(amount.amount));
    }

    public Credits multiply(Long quantity) {
        BigDecimal rhs = new BigDecimal(quantity);
        return new Credits(this.amount.multiply(rhs));
    }

    public String valueToString() {
        DecimalFormatSymbols symbolsFormat = new DecimalFormatSymbols();
        symbolsFormat.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setDecimalFormatSymbols(symbolsFormat);
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setGroupingUsed(false);
        return decimalFormat.format(this.amount);
    }

    public float valueToFloat() {
        return this.amount.floatValue();
    }

    @Override
    public int compareTo(Credits credits) {
        return this.amount.compareTo(credits.amount);
    }

}
