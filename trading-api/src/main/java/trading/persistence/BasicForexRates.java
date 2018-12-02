package trading.persistence;

import trading.domain.Credits;
import trading.domain.Currency;
import trading.domain.ForeignExchangeRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class BasicForexRates implements ForeignExchangeRepository {
    @Override
    public BigDecimal calculateCreditSumInCAD(ArrayList<Credits> creditList) {
        BigDecimal totalCreditsInCAD = BigDecimal.ZERO;
        HashMap<Currency, BigDecimal> ratesToCAD = getRatesToCAD();

        for (Credits credits : creditList) {
            Currency creditCurrency = credits.getCurrency();
            if (ratesToCAD.containsKey(creditCurrency)) {
                BigDecimal rateMultiplier = ratesToCAD.get(creditCurrency);
                rateMultiplier = rateMultiplier.multiply(credits.getAmount());
                totalCreditsInCAD = totalCreditsInCAD.add(rateMultiplier);
            }
        }

        return totalCreditsInCAD;
    }

    private HashMap<Currency, BigDecimal> getRatesToCAD() {
         HashMap<Currency, BigDecimal> rates = new HashMap<>();
        rates.put(Currency.USD, new BigDecimal("1.31"));
        rates.put(Currency.CHF, new BigDecimal("1.45"));
        rates.put(Currency.JPY, new BigDecimal("0.01"));
        rates.put(Currency.CAD, new BigDecimal("1"));

        return rates;
    }
}
