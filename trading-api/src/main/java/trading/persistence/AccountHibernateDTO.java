package trading.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "ACCOUNTS")
public class AccountHibernateDTO implements Serializable {

    @Id
    public String accountNumber;
    @Column(unique = true)
    public Long investorId;
    @Column
    public String profileType;
    @ElementCollection(fetch = FetchType.EAGER)
    public List<String> focusAreas;
    @Column
    public String investorName;
    @Column
    public BigDecimal credits;
    @Column
    public BigDecimal initialCredits;
    @ElementCollection(fetch = FetchType.EAGER)
    public Map<UUID, Long> remainingStocksMap;

    public AccountHibernateDTO() {
    }
}
