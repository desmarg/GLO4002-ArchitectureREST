package trading.persistence;

import trading.domain.Credits;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    @ElementCollection(fetch = FetchType.EAGER)
    public Map<String, BigDecimal> creditList;
    @ElementCollection(fetch = FetchType.EAGER)
    public Map<String, BigDecimal> initialCredits;
    @ElementCollection(fetch = FetchType.EAGER)
    public Map<UUID, Long> remainingStocksMap;

    public AccountHibernateDTO() {
    }
}
