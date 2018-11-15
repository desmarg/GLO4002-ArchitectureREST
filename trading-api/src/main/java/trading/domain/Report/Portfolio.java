package trading.domain.Report;

import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;

import java.util.List;

public class Portfolio {
    public DateTime date;
    private List<StockPortfolio> stocks;

    public Portfolio(DateTime date, List<StockPortfolio> stocks) {
        this.date = date;
        this.stocks = stocks;
    }


    public Credits portfolioValue() {
        Credits portfolioValue = new Credits();
        for (StockPortfolio stockPortfolio : this.stocks) {
            portfolioValue.add(stockPortfolio.value());
        }
        return portfolioValue;
    }


}
