package moneycalculator;

import java.awt.Dimension;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFrame;
import moneycalculator.control.ExtractDatabase;

public class Application extends JFrame{
    
    private final ExtractDatabase database;

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {        
        new Application().setVisible(true);
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
