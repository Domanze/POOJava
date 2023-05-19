/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poojava;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carna
 */
public class CTablaFacturas {
    
    public void MostrarTabla(JTable paramTablaFacturas){
    CConnection cn = new CConnection();
    DefaultTableModel model = new DefaultTableModel();
    
    String sql="";
    
    model.addColumn("ID");
    model.addColumn("ID_Usuario");
    model.addColumn("ID Producto");
    model.addColumn("Cantidad");
    model.addColumn("Fecha");
    model.addColumn("Total");
    
    paramTablaFacturas.setModel(model);
    
    sql="SELECT*FROM Orden;";
    
    String [] datosOrd = new String [6];
    
    Statement st;
    
    try { 
        st = cn.establishConnectionCN().createStatement();
        ResultSet rs = st.executeQuery(sql);
         while (rs.next()){
         datosOrd[0]=rs.getString(1);
         datosOrd[1]=rs.getString(2);
         datosOrd[2]=rs.getString(4);
         datosOrd[3]=rs.getString(5);
         datosOrd[4]=rs.getString(6);
         datosOrd[5]=rs.getString(3);
         
         model.addRow(datosOrd);
         }
         paramTablaFacturas.setModel(model);
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,"Error:"+ e.toString());
    
    }
    
    }
        
    public void Recibo(JTable paramTablaFacturas, JLabel paramFecha,JLabel paramNombre,JLabel paramApellido, JLabel paramProducto,JLabel paramCantidad,JLabel paramTotal){
    try{
        int nrow = paramTablaFacturas.getSelectedRow();
        if (nrow>=0){
        paramFecha.setText(paramTablaFacturas.getValueAt(nrow, 4).toString());
        paramCantidad.setText(paramTablaFacturas.getValueAt(nrow, 3).toString());
        paramTotal.setText(paramTablaFacturas.getValueAt(nrow, 5).toString());
        
        
        String ID_Usr=paramTablaFacturas.getValueAt(nrow, 1).toString();
        String ID_Prod=paramTablaFacturas.getValueAt(nrow, 2).toString();
        CConnection cn = new CConnection();
        String sqlUsr="SELECT*FROM Usuarios WHERE ID_Usuario=" + ID_Usr;
        String sqlProd="SELECT*FROM Productos WHERE ID_Producto=" + ID_Prod;
        Statement st,st2;
        st = cn.establishConnectionCN().createStatement();
        st2 = cn.establishConnectionCN().createStatement();
        ResultSet rsUsr = st.executeQuery(sqlUsr);
        ResultSet rsProd=st2.executeQuery(sqlProd);
        
        String nombre="",apellido="",producto="";
        while (rsUsr.next()) {
        nombre = rsUsr.getString(2);
        apellido=rsUsr.getString(3);
        }
        while (rsProd.next()) {
        producto = rsProd.getString(3);
        }
        
        paramNombre.setText(nombre);
        paramApellido.setText(apellido);
        paramProducto.setText(producto);
        
        
        
        
        }else{
        JOptionPane.showMessageDialog(null,"Error, no se pudo seleccionar");
        }
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,"Error:"+ e.toString());
    }
    }
    
}
