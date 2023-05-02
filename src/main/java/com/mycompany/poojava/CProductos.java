/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poojava;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carna
 */
public class CProductos {
    public void mostrarProductos(JTable paramTablaProductos){
    CConnection cn = new CConnection();
    DefaultTableModel model = new DefaultTableModel();
    
    String sql="";
    
    model.addColumn("ID");
    model.addColumn("Categoria");
    model.addColumn("Nombre");
    model.addColumn("Precio");
    model.addColumn("Descripcion");
    model.addColumn("Especificaciones");
    
    paramTablaProductos.setModel(model);
    
    sql="SELECT*FROM Productos;";
    
    String [] datosProd = new String [6];
    
    Statement st;
    
    try { 
        st = cn.establishConnectionCN().createStatement();
        ResultSet rs = st.executeQuery(sql);
         while (rs.next()){
         datosProd[0]=rs.getString(1);
         datosProd[1]=rs.getString(2);
         datosProd[2]=rs.getString(3);
         datosProd[3]=rs.getString(4);
         datosProd[4]=rs.getString(5);
         datosProd[5]=rs.getString(6);
         
         model.addRow(datosProd);
         }
         paramTablaProductos.setModel(model);
    }catch(Exception ex){
        JOptionPane.showMessageDialog(null,"Error:"+ ex.toString());
    
    }
    
    }
    
}
