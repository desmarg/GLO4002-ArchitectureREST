package trading.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "TRANSACTIONS")
public class TransactionHibernateDTO implements Serializable {
    @Id
    UUID transactionNumber;
    @Column
    String accountNumber;
    @Column
    String transactionType;
    @Column
    Long quantity;
    @Column
    Timestamp instant;
    @Column
    String market;
    @Column
    String symbol;
    @Column
    BigDecimal stockPrice;
    @Column
    String stockCurrency;
    @Column
    String referredTransactionNumber;
}
