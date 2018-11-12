package trading.api.response;

import trading.domain.Account.Account;
import trading.domain.Report.Portfolio;

public class PortfolioResponse {
    public final String date;
    public final Float portfolioValue;
    public final Float credits;


    public PortfolioResponse(Portfolio portfolio, Account account) {
        this.date = portfolio.date.toInstant().toString();
        this.portfolioValue = portfolio.portfolioValue().valueToFloat();
        this.credits = account.getCredits().valueToFloat();
    }
}
