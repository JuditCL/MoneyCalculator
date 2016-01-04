package moneycalculator;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import moneycalculator.control.ExchangeCommand;
import moneycalculator.control.ExtractDatabase;
import moneycalculator.model.CurrencySet;
import moneycalculator.model.Money;
import moneycalculator.model.ExchangeRate;
import moneycalculator.process.MoneyExchanger;

public class MoneyPanel extends JPanel {

    private JPanel panel;
    private JTextField money;
    private JTextField result;
    private ExtractDatabase database;
    private JComboBox de;
    private JComboBox a;
    private CurrencySet currencySet;
    private JTextField myDivisa;

    public MoneyPanel() {
    }

    MoneyPanel(ExtractDatabase database) throws SQLException {
        this.panel = new JPanel();
        this.database = database;
        this.currencySet = new CurrencySet(database.initializeSet());
    }

    public void addElemnts() throws SQLException {
        this.setLayout(new BorderLayout());
        this.add(money(), "North");
        this.add(divisas(), "Center");
        this.add(result(), "South");
    }

    private JPanel money() {
        JPanel myPanel = new JPanel();
        JLabel myText = new JLabel("Por favor inserte aquí la cantidad de dinero: ");
        money = new JTextField(10);
        money.setEditable(true);
        myPanel.add(myText);
        myPanel.add(money);
        return myPanel;
    }

    private JPanel divisas() throws SQLException {
        JPanel myPanel = new JPanel();
        JLabel myText = new JLabel("de ");
        JLabel myText1 = new JLabel("a ");
        JButton calculate = new JButton("Calcular");
        String[] combo = database.getNameDivisas();
        de = new JComboBox(combo);
        a = new JComboBox(combo);
        myPanel.add(myText);
        myPanel.add(de);
        myPanel.add(myText1);
        myPanel.add(a);
        myPanel.add(calculate);
        ActionListener loginButtonListener = new LoginButtonListener();
        calculate.addActionListener(loginButtonListener);
        return myPanel;
    }

    private JPanel result() {
        JPanel myPanel = new JPanel();
        JLabel myText = new JLabel("El resultado es: ");
        result = new JTextField("0.00", 12);
        myDivisa = new JTextField("€", 2);
        result.setEditable(false);
        myDivisa.setEditable(false);
        myPanel.add(myText);
        myPanel.add(result);
        myPanel.add(myDivisa);
        return myPanel;
    }

    public float getValue() {
        String result = money.getText();
        if (result.equals("")) {
            return 0;
        } else {
            return Float.parseFloat(result);
        }
    }

    public void setValue(float value) {
        result.setText(Float.toString(value));
    }

    class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(String.valueOf(de.getSelectedItem()).equals("euro")){
                try {
                    ExchangeCommand exchangeCommand = new ExchangeCommand(new Money(getValue(), currencySet.get(String.valueOf(de.getSelectedItem()))),
                            new ExchangeRate(currencySet.get(String.valueOf(de.getSelectedItem())), currencySet.get(String.valueOf(a.getSelectedItem())), database.getExchange(String.valueOf(a.getSelectedItem()))),
                            new MoneyExchanger());
                simpleConversor(exchangeCommand);
            } catch (SQLException ex) {
                JOptionPane.showOptionDialog(null, "Se ha detectado un error al acceder a la base de datos", "Error en la base de datos", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" Cancelar "},"Cancelar"); 
            }
            }else{
                try {
                    ExchangeCommand exchangeCommand = new ExchangeCommand(new Money(getValue(), currencySet.get(String.valueOf(de.getSelectedItem()))),
                            new ExchangeRate(currencySet.get(String.valueOf(de.getSelectedItem())), currencySet.get("euro"), 1/database.getExchange(String.valueOf(de.getSelectedItem()))),
                            new MoneyExchanger());
                doubleConversor(exchangeCommand);
                exchangeCommand = new ExchangeCommand(exchangeCommand.getResult(),
                            new ExchangeRate(currencySet.get("euro"), currencySet.get(String.valueOf(a.getSelectedItem())), database.getExchange(String.valueOf(a.getSelectedItem()))),
                            new MoneyExchanger());
                simpleConversor(exchangeCommand);
            } catch (SQLException ex) {
                JOptionPane.showOptionDialog(null, "Se ha detectado un error al acceder a la base de datos", "Error en la base de datos", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" Cancelar "},"Cancelar"); 
            }
            }
        }

        private void doubleConversor(ExchangeCommand exchangeCommand) {
            exchangeCommand.execute();
        }

        private void simpleConversor(ExchangeCommand exchangeCommand) {
            exchangeCommand.execute();
            setValue((float) exchangeCommand.getResult().getAmount());
            setSimbol(String.valueOf(exchangeCommand.getResult().getCurrency().getSymbol()));
        }

        private void setSimbol(String valueOf) {
            myDivisa.setText(valueOf);
        }
    }
}
