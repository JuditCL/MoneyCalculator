package moneycalculator.model;
import java.util.List;

public class CurrencySet {

    private final List<Currency> list;

    public CurrencySet(List<Currency> list) {
        this.list = list;
    }
    
    
    public Currency get(String text) {
        for (Currency currency : list) 
            if (
                    currency.getCode().equals(text) || 
                    currency.getName().contains(text) || 
                    currency.getSymbol().equals(text)
                ) return currency;
        return null;
    }
    
}
