package trading.domain.report;

import trading.domain.Credits;

public class Portfolio {
    public Credits portfolioValue;
    public Credits accountValue;

    public Portfolio(Credits portfolioValue, Credits accountValue) {
        this.accountValue = accountValue;
        this.portfolioValue = portfolioValue;
    }
}
