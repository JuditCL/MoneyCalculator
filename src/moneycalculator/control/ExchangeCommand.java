package moneycalculator.control;
import moneycalculator.model.ExchangeRate;
import moneycalculator.model.Money;
import moneycalculator.process.MoneyExchanger;
import static moneycalculator.process.MoneyExchanger.exchange;

public class ExchangeCommand implements Command {

    private final Money money;
    private final ExchangeRate exchangeRate;
    private final MoneyExchanger moneyDisplay;
    private Money result;

    public ExchangeCommand(Money money,ExchangeRate exchangeRate, MoneyExchanger moneyExchanger) {
        this.money = money;
        this.exchangeRate = exchangeRate;
        this.moneyDisplay = moneyExchanger;
    }

    public Money getResult() {
        return result;
    }

    @Override
    public void execute() {
        this.result = exchange(money,exchangeRate);
        //Money money = moneyDialog.get();
        //ExchangeRate exchangeRate = exchangeRateReader.get(money.getCurrency(), currencyDialog.get());
        //moneyDisplay.show(new MoneyExchanger().exchange(money, exchangeRate));
    }

}
