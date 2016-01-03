package moneycalculator;

import javax.swing.JTextField;
import moneycalculator.model.Currency;
import moneycalculator.ui.CurrencyDialog;

public class CurrencyField extends JTextField  implements CurrencyDialog{

    @Override
    public Currency get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
