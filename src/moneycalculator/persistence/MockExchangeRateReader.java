package moneycalculator.persistence;

import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;

public class MockExchangeRateReader implements ExchangeRateReader {
    private ExchangeRate exchange;

    public MockExchangeRateReader(ExchangeRate exchange) {
        this.exchange = exchange;
    }
    
    @Override
    public ExchangeRate get(Currency from, Currency to) {
        return new ExchangeRate(from, to, 1.5);
    }

}
