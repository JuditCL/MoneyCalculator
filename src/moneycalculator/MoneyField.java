package moneycalculator;

import javax.swing.JTextField;
import moneycalculator.model.Money;
import moneycalculator.ui.MoneyDialog;

public class MoneyField extends JTextField implements MoneyDialog{

    @Override
    public Money get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
