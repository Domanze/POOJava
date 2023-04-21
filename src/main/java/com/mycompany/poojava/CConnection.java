
package com.mycompany.poojava;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class CConnection {
    Connection connect = null;
    
    String user = "useradmin";
    String password = "root";
    String db = "BasedeDatosPoo";
    String ip = "localhost";
    String port = "1433";
    
    String cadena = "jdbc:sqlserver://"+ip+":"+port+";"+"databaseName="+db;
    
    public Connection establishConnetion(){
    
        try{
        connect = DriverManager.getConnection(cadena,user,password);
        JOptionPane.showMessageDialog(null,"Conexion a la base de datos exitosa");
                }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al conectar a la base de datos, error: "+ e.toString());
        }
        return connect;
    }
}
