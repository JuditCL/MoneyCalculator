package moneycalculator.model;

import java.util.ArrayList;
import java.util.List;

public class CurrencySet {

    private List<Currency> list = new ArrayList<Currency>();

    public CurrencySet() {
        list.add(new Currency("USD", "Dolar americano", "$"));
        list.add(new Currency("EUR", "Euro", "€"));
        list.add(new Currency("GPB", "Libra britanica", "£"));
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
