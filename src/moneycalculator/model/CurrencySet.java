package moneycalculator.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CurrencySet {

    private List<Currency> list = new ArrayList<Currency>();

    public CurrencySet(List<Currency> currency) {
        this.list = currency;
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
    
    public String[] getDivisas (){
        String[] result = new  String[list.size()];
        int index = 0;
        for (Currency currency : list) {
            result[index] = currency.getName();
            index++;
        }
        return result;
    }
    
}
