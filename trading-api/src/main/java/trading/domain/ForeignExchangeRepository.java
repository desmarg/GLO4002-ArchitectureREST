package trading.domain;

import java.math.BigDecimal;
import java.util.HashMap;

public interface ForeignExchangeRepository {
    BigDecimal calculateCreditSumInCAD(HashMap<Currency, Credits> creditMap);

    Credits convertToCAD(Credits credits);
}
