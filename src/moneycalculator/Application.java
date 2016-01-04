package moneycalculator;

import java.awt.Dimension;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import moneycalculator.control.ExchangeCommand;
import moneycalculator.control.ExtractDatabase;
import moneycalculator.persistence.MockExchangeRateReader;
import moneycalculator.ui.MockCurrencyDialog;
import moneycalculator.ui.MockMoneyDialog;
import moneycalculator.ui.MockMoneyDisplay;

public class Application extends JFrame{
    
    private final ExtractDatabase database;

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        /*ExchangeCommand exchangeCommand = new ExchangeCommand(
                new MockMoneyDialog(),
                new MockCurrencyDialog(),
                new MockExchangeRateReader(), 
                new MockMoneyDisplay()
        );*/
        
        new Application().setVisible(true);
        /*Class.forName("oracle.jdbc.driver.OracleDriver");
        String BaseDeDatos = "jdbc:oracle:thin:@192.168.200.10:1524:pc-virtual";
        Connection conexion = DriverManager.getConnection(BaseDeDatos, "system","orcl");
        Statement stmt = conexion.createStatement();
        ResultSet rset = stmt.executeQuery("select divisa from cambio_eur_a");
        while (rset.next())
          System.out.println (rset.getInt(1));   // Print col 1
        stmt.close();*/
        
        //exchangeCommand.execute();
                
    }
    public Application() throws IOException, ClassNotFoundException, SQLException{
        this.database = new ExtractDatabase();
        this.setTitle("Money Calculator               Por: Judit");
        this.setMinimumSize(new Dimension(500,200));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MoneyPanel panel = new MoneyPanel(database);
        panel.addElemnts();
        this.getContentPane().add(panel);
        this.database.initializeSet();
    }
    
}
