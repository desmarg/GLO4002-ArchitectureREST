package domain;

import java.math.BigDecimal;

public class Money implements Comparable<Money> {
    private BigDecimal amount;

    public Money(){
        this.amount = new BigDecimal(0);
    }

    public Money(BigDecimal amount){
        this.amount = new BigDecimal(0);
    }

    public void multiply(Long quantity) {
        BigDecimal rhs = new BigDecimal(quantity);
        this.amount = this.amount.multiply(rhs);
    }

    public void add(Money money){
        this.amount = this.amount.add(money.amount);
    }

    @Override
    public int compareTo(Money money) {
        return 0;
    }
}
