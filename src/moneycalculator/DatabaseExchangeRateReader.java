package moneycalculator;

import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.persistence.ExchangeRateReader;

public class DatabaseExchangeRateReader implements ExchangeRateReader {

    @Override
    public ExchangeRate get(Currency from, Currency to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
