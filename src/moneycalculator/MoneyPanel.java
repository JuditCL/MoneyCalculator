package moneycalculator;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import moneycalculator.control.ExchangeCommand;
import moneycalculator.control.ExtractDatabase;
import moneycalculator.persistence.MockExchangeRateReader;
import moneycalculator.ui.MockCurrencyDialog;
import moneycalculator.ui.MockMoneyDialog;
import moneycalculator.ui.MockMoneyDisplay;

public class MoneyPanel extends JPanel{
    private JPanel panel;
    private JTextField money;
    private JTextField result;
    private ExtractDatabase database;
    public MoneyPanel() {
    }

    MoneyPanel(ExtractDatabase database) {
        this.panel = new JPanel();
        this.database = database;
    }

    public void addElemnts() throws SQLException {
        this.setLayout(new BorderLayout());
        this.add(money(),"North");
        this.add(divisas(),"Center");
        this.add(result(),"South");
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
        String[] divisas = {"Euros","Libras","Rublos","Dólares"};
        JPanel myPanel = new JPanel();
        JLabel myText = new JLabel("de ");
        JLabel myText1 = new JLabel("a ");
        JButton calculate = new JButton("Calcular");
        JComboBox de = new JComboBox(database.getNameDivisas());
        JComboBox a = new JComboBox(database.getNameDivisas());
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
        result = new JTextField("0.00",5);
        JTextField myDivisa = new JTextField("€",2);
        result.setEditable(false);
        myDivisa.setEditable(false);
        myPanel.add(myText);
        myPanel.add(result);
        myPanel.add(myDivisa);
        return myPanel;
    }
    
    public float getValue(){
        String result = money.getText();
        if(result.equals("")) return 0; 
        else return Float.parseFloat(result);
    }
    
    public void setValue(float value){
        result.setText(Float.toString(value));
    }

    class LoginButtonListener implements ActionListener {
        
    @Override
    public void actionPerformed(ActionEvent ae) {
        ExchangeCommand exchangeCommand = new ExchangeCommand(
            new MockMoneyDialog(),
            new MockCurrencyDialog(),
            new MockExchangeRateReader(), 
            new MockMoneyDisplay()
        );
        exchangeCommand.execute();
        setValue(getValue());
    }
    
}
}

