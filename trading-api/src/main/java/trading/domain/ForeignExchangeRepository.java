package trading.domain;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface ForeignExchangeRepository {
    public BigDecimal calculateCreditSumInCAD(ArrayList<Credits> creditList);
}
