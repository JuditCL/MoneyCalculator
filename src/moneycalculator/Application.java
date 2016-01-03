package moneycalculator;

import java.awt.Dimension;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFrame;
import moneycalculator.control.ExchangeCommand;
import moneycalculator.persistence.MockExchangeRateReader;
import moneycalculator.ui.MockCurrencyDialog;
import moneycalculator.ui.MockMoneyDialog;
import moneycalculator.ui.MockMoneyDisplay;

public class Application extends JFrame{

    public static void main(String[] args) throws IOException {
        ExchangeCommand exchangeCommand = new ExchangeCommand(
                new MockMoneyDialog(),
                new MockCurrencyDialog(),
                new MockExchangeRateReader(), 
                new MockMoneyDisplay()
        );
        new Application().setVisible(true);
        /*Class.forName("orcale.jdb.OracleDriver");
        String BaseDeDatos = "jdbc:oracle:thin:@pc-virtual:1524:bdb";
        Connection conexion = DriverManager.getConnection(BaseDeDatos, "system","orcl");*/
        
        exchangeCommand.execute();
                
    }
    public Application() throws IOException{
        this.setTitle("Money Calculator");
        this.setMinimumSize(new Dimension(500,500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(new MoneyPanel());
    }
    
}
