package domain;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Credits implements Comparable<Credits> {
    private BigDecimal amount;

    public Credits(){
        this.amount = new BigDecimal(0);
    }

    public Credits(BigDecimal amount){
        this.amount = amount;
    }

    public static Credits fromFloat(double amount){
        BigDecimal bigDecimalAmount = BigDecimal.valueOf(amount);
        return new Credits(bigDecimalAmount);
    }

    public void add(Credits amount){
        this.amount = this.amount.add(amount.amount);
    }

    public void subtract(Credits amount) {
        this.amount = this.amount.subtract(amount.amount);
    }

    public void multiply(Long quantity) {
        BigDecimal rhs = new BigDecimal(quantity);
        this.amount = this.amount.multiply(rhs);
    }

    //TODO Méthode temporaire j'ai besoin de pouvoir faire une multiplication sans modifier l'objet
    public Credits multiplyByScalar(Long quantity) {
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

    @Override
    public int compareTo(Credits money) {
        return this.amount.compareTo(money.amount);
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Credits){
            return ((Credits) obj).amount.equals(this.amount);
        }
        return false;
    }

}
