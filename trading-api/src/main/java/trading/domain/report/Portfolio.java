package trading.domain.Report;

import trading.domain.Credits;

public class Portfolio {
    private final Credits portfolioValue;
    private final Credits accountValue;

    public Portfolio(Credits portfolioValue, Credits accountValue) {
        this.portfolioValue = portfolioValue;
        this.accountValue = accountValue;
    }

    public Credits getPortfolioValue() {
        return this.portfolioValue;
    }

    public Credits getAccountValue() {
        return this.accountValue;
    }
}
