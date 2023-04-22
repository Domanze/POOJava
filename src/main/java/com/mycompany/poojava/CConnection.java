
package com.mycompany.poojava;

import java.sql.*;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class CConnection {
    Connection cn = null;
    Statement st = null;
    
    String user = "useradmin";
    String password = "root";
    String db = "BasedeDatosPoo";
    String ip = "localhost";
    String port = "1433";
    
    String url = "jdbc:sqlserver://"+ip+":"+port+";"+"databaseName="+db;
    
    public Statement establishConnection(){
    
        try{
        cn = DriverManager.getConnection(url,user,password);
        st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
        return st;
    }
}
