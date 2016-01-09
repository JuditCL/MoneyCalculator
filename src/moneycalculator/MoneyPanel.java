package moneycalculator;
/******************************************************/
/*MoneyCalculator por Judit del Carmen Correa Luciano */
/******************************************************/
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import moneycalculator.control.ExchangeCommand;
import moneycalculator.control.ExtractDatabase;
import moneycalculator.model.CurrencySet;

public class MoneyPanel extends JPanel {

    private JTextField money;
    private JTextField result;
    private final ExtractDatabase database;
    private JComboBox de;
    private JComboBox a;
    private final CurrencySet currencySet;
    private JTextField myDivisa;

    public MoneyPanel(ExtractDatabase database) throws SQLException {
        this.database = database;
        this.currencySet = new CurrencySet(database.initializeSet());
    }

    public void addElemnts() throws SQLException {
        this.setLayout(new BorderLayout());
        this.add(panel(), "North");
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

    private JPanel panel() {
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new BorderLayout());
        myPanel.add(money(), "South");
        myPanel.add(new MoneyMenuBar().addMenu(), "North");
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

    String deGet() {
        return String.valueOf(de.getSelectedItem());
    }

    public String aGet() {
        return String.valueOf(a.getSelectedItem());
    }

    class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                if (deGet().equals("euro")) {
                    ExchangeCommand exchangeCommand = new ExchangeCommand(new moneycalculator.model.Money(getValue(), currencySet.get(deGet())),
                            new moneycalculator.model.ExchangeRate(currencySet.get(deGet()), currencySet.get(aGet()), database.getExchange(aGet())),
                            new moneycalculator.process.MoneyExchanger());
                    simpleConversor(exchangeCommand);
                } else {
                    ExchangeCommand exchangeCommand = new ExchangeCommand(new moneycalculator.model.Money(getValue(), currencySet.get(deGet())),
                            new moneycalculator.model.ExchangeRate(currencySet.get(deGet()), currencySet.get("euro"), 1 / database.getExchange(deGet())),
                            new moneycalculator.process.MoneyExchanger());
                    doubleConversor(exchangeCommand);
                    exchangeCommand = new ExchangeCommand(exchangeCommand.getResult(),
                            new moneycalculator.model.ExchangeRate(currencySet.get("euro"), currencySet.get(aGet()), database.getExchange(aGet())),
                            new moneycalculator.process.MoneyExchanger());
                    simpleConversor(exchangeCommand);

                }
            } catch (SQLException ex) {
                JOptionPane.showOptionDialog(null, "Se ha detectado un error al acceder a la base de datos", "Error en la base de datos", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" Cancelar "}, "Cancelar");
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
