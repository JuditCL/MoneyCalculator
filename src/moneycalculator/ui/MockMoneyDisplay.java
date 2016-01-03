package moneycalculator.ui;

import moneycalculator.model.Money;

public class MockMoneyDisplay implements MoneyDisplay {

    @Override
    public void show(Money money) {
        System.out.println(money.getAmount() + " " + money.getCurrency().getCode());
    }

}
