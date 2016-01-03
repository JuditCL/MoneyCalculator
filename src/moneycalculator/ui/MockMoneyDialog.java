package moneycalculator.ui;

import moneycalculator.model.Currency;
import moneycalculator.model.Money;

public class MockMoneyDialog implements MoneyDialog {

    @Override
    public Money get() {
        return new Money(100, new Currency("USD", "Dolar americano", "$"));
    }

}
