package moneycalculator.process;
/******************************************************/
/*MoneyCalculator por Judit del Carmen Correa Luciano */
/******************************************************/
import moneycalculator.model.Money;
import moneycalculator.model.ExchangeRate;

public class MoneyExchanger {
    
    public static Money exchange(Money money, ExchangeRate exchangeRate) {
        double amount = money.getAmount() * exchangeRate.getRate();
        return new Money(amount, exchangeRate.getTo());
    }

}
