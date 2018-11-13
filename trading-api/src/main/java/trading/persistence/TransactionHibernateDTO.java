package trading.persistence;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "TRANSACTIONS")
public class TransactionHibernateDTO implements Serializable {
    @Id
    String transactionNumber;
    @Column
    String accountNumber;
    @Column
    String transactionType;
    @Column
    Long quantity;
    @Column
    Instant dateTime;
    @Column
    @Type(type = "date")
    Date date;
    @Column
    String market;
    @Column
    String symbol;
    @Column
    BigDecimal stockPrice;
    @Column
    String referredTransactionNumber;
}
