package domain;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Credits implements Comparable<Credits> {
    public BigDecimal amount;

    public Credits() {
        this.amount = new BigDecimal(0);
    }

    public Credits(BigDecimal amount) {
        this.amount = amount;
    }

    public static Credits fromFloat(double amount) {
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
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setGroupingUsed(false);
        return decimalFormat.format(this.amount);
    }

    public Float valueToFloat() {
        return this.amount.floatValue();
    }

    @Override
    public int compareTo(Credits money) {
        return this.amount.compareTo(money.amount);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Credits) {
            return ((Credits) obj).amount.equals(this.amount);
        }
        return false;
    }

}
