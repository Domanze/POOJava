
package com.mycompany.poojava;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



public class CFactura {
    public void Total(JTextField paramCantidad, JTextField paramIDProd, JTextField paramTotal){
    
    int cantidad = Integer.parseInt(paramCantidad.getText());
    String ID_Prod = paramIDProd.getText();
    float precio,total;
        
    CConnection cn = new CConnection();
    
    String sql="SELECT*FROM Productos WHERE ID_Producto=" + ID_Prod;
    Statement st;
    
    try{
        st = cn.establishConnectionCN().createStatement();
        ResultSet rs = st.executeQuery(sql);
        
        precio = 0;
        while (rs.next()) {
        precio = Float.parseFloat(rs.getString(4));
        }
        
        total = precio*2;
        
        paramTotal.setText(Float.toString(total));
        
        
    
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al calcular el total:"+ e.toString());
        }

    }
    
    public void Generar(JTextField paramIDUsuario,JTextField paramFecha,JTextField paramTotal,JTextField paramIDProd, JTextField paramCantidad){
        CConnection cn = new CConnection();
        String sqlquery = "INSERT INTO Orden(ID_Usuario,Fecha,Total,ID_Producto,Cantidad_Producto) VALUES (?,?,?,?,?);";
        try{
            CallableStatement cs = cn.establishConnectionCN().prepareCall(sqlquery);
            cs.setInt(1,Integer.parseInt(paramIDUsuario.getText()));
            cs.setString(2,paramFecha.getText());
            cs.setFloat(3,Float.parseFloat(paramTotal.getText()));
            cs.setInt(4,Integer.parseInt(paramIDProd.getText()));
            cs.setString(5,paramCantidad.getText());
            
            cs.execute();
            JOptionPane.showMessageDialog(null,"Se genero correctamente");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error:"+ e.toString());
        }
    
    }
    
    
}
