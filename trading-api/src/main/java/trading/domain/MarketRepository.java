package trading.domain;

import trading.domain.datetime.DateTime;

public interface MarketRepository {
    boolean isMarketOpenAtHour(String market, DateTime transactionDateTime);
}
