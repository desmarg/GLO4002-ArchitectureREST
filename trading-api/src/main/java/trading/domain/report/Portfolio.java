package trading.domain.report;

import trading.domain.Credits;
import trading.domain.Currency;

import java.util.HashMap;

public class Portfolio {
    public final Credits portfolioValue;
    public final HashMap<Currency, Credits> accountCredits;

    public Portfolio(Credits portfolioValue, HashMap<Currency, Credits> accountCredits) {
        this.accountCredits = accountCredits;
        this.portfolioValue = portfolioValue;
    }
}
