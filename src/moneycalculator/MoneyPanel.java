package moneycalculator;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.PopupMenu;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MoneyPanel extends JPanel{
    private JPanel panel;
    public MoneyPanel() {
        this.panel = new JPanel();
    }

    public void addElemnts() {
        this.setLayout(new BorderLayout());
        this.add(money(),"North");
        this.add(divisas(),"Center");
        this.add(result(),"South");
    }

    private JPanel money() {
        JPanel myPanel = new JPanel();
        JLabel myText = new JLabel("Por favor inserte aquí la cantidad de dinero: ");
        JTextField money = new JTextField(10);
        money.setEditable(true);
        myPanel.add(myText);
        myPanel.add(money);
        return myPanel;
    }

    private JPanel divisas() {
        String[] divisas = {"Euros","Libras","Rublos","Dólares"};
        JPanel myPanel = new JPanel();
        JLabel myText = new JLabel("de ");
        JLabel myText1 = new JLabel("a ");
        JComboBox de = new JComboBox(divisas);
        JComboBox a = new JComboBox(divisas);
        myPanel.add(myText);
        myPanel.add(de);
        myPanel.add(myText1);
        myPanel.add(a);
        return myPanel;
    }

    private JPanel result() {
        JPanel myPanel = new JPanel();
        JLabel myText = new JLabel("El resultado es: ");
        JTextField myField = new JTextField("0.00",5);
        JTextField myDivisa = new JTextField("€",2);
        myField.setEditable(false);
        myDivisa.setEditable(false);
        myPanel.add(myText);
        myPanel.add(myField);
        myPanel.add(myDivisa);
        return myPanel;
    }
    
}
