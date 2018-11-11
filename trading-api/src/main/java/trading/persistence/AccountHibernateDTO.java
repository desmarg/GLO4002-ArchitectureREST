package trading.persistence;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AccountHibernateDTO {
    public Long investorId;
    public String profileType;
    public List<String> focusAreas;
    public String investorName;
    public BigDecimal credits;
    public String accountNumber;
    public Map<UUID, Long> remainingStocksMap;

    public AccountHibernateDTO(){}
}
