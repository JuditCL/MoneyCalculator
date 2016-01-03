package moneycalculator.persistence;

import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;

public interface ExchangeRateReader {
    
    public ExchangeRate get(Currency from, Currency to);
    
}
