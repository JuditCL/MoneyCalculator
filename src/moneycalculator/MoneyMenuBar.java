package moneycalculator;
/******************************************************/
/*MoneyCalculator por Judit del Carmen Correa Luciano */
/******************************************************/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MoneyMenuBar extends JMenuBar{

    public MoneyMenuBar() {
        
    }
    public JMenuBar addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);
        JMenuItem menuItem = new JMenuItem("A cerca de",
                new ImageIcon("signo-de-interrogacion.jpg"));
        ActionListener botonabout = new botonabout();
        menuItem.addActionListener(botonabout);
        menu.add(menuItem);
        menuItem = new JMenuItem("Ayuda",
                new ImageIcon("ayuda.jpg"));
        ActionListener botonhelp = new botonhelp();
        menuItem.addActionListener(botonhelp);
        menu.add(menuItem);
        menuBar.add(menu);
        return menuBar;
    }
    
    class botonabout implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            JOptionPane.showOptionDialog(null, "Este programa se encarga de realizar cambio de divisas y ha sido realizado por Judit del Carmen Correa Luciano", "Acerca de MoneyCalculator", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{" OK "}, "OK");
        }
    }
    
    class botonhelp implements ActionListener {

        public botonhelp() {
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            JOptionPane.showOptionDialog(null, "Este programa pasa una cantidad de dinero especificada a otra ayudándonos de los menús de la pantalla para ello siga los siguientes pasos: \n"
                    + "1º- Teclee la cantidad de dinero deseada en la barra editable pudiendo poner decimales\n"
                    + "2º- En el primer elemento de selección especifique la moneda en la que esta esa cantidad \n"
                    + "3º- Pulse calcular de esta manera se calculará el resultado y se mostrará la cantidad total y la divisa final abajo.", "Cómo usar", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{" OK "}, "OK");
        }

    }

}
