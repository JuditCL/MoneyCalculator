/*
 *Clase perteneciente al control que proporciona datos de la base de datos.
 */
package moneycalculator.control;
/******************************************************/
/*MoneyCalculator por Judit del Carmen Correa Luciano */
/******************************************************/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import moneycalculator.model.Currency;

public class ExtractDatabase {
    private final Statement statement;
    public ExtractDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection cn = DriverManager.getConnection("jdbc:sqlite:Divisas");
        this.statement  = cn.createStatement();
    }
    
    public String[] getNameDivisas() throws SQLException{
        ResultSet leng = statement.executeQuery("SELECT count (*) as nombre from cambio_eur_a");
        String[] result = new String[leng.getInt(1)];
        ResultSet rs = statement.executeQuery("SELECT nombre FROM CAMBIO_EUR_A");
        int index = 0;
        while(rs.next()){
            result[index] = rs.getString("nombre");
            index++;
        }
        return result;
    }

    public List<Currency> initializeSet() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM CAMBIO_EUR_A");
        List<Currency> list = new ArrayList<Currency>();
        while(rs.next()){
            list.add(new Currency(rs.getString("divisa"), rs.getString("nombre"), rs.getString("codigo")));
        }
        return list;
    }
    
    public float getExchange(String name) throws SQLException{
        ResultSet rs = statement.executeQuery("SELECT * FROM CAMBIO_EUR_A");
        while(rs.next()){
            if(rs.getString("nombre").equals(name)){
                return rs.getFloat("cambio");
            }
        }
        return 0;
    }
}
