/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poojava;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CProductos {
    public void MostrarProductos(JTable paramTablaProductos){
    CConnection cn = new CConnection();
    DefaultTableModel model = new DefaultTableModel();
    
    String sql="";
    
    model.addColumn("ID");
    model.addColumn("Categoria");
    model.addColumn("Nombre");
    model.addColumn("Precio");
    model.addColumn("Descripcion");
    model.addColumn("Especificaciones");
    model.addColumn("Stock");
    
    paramTablaProductos.setModel(model);
    
    sql="SELECT*FROM Productos;";
    
    String [] datosProd = new String [7];
    
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
         datosProd[6]=rs.getString(7);
         
         model.addRow(datosProd);
         }
         paramTablaProductos.setModel(model);
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,"Error:"+ e.toString());
    
    }
    
    }
    
    public void SeleccionarProductos(JTable paramTablaProductos, JTextField paramID, JTextField paramCategoria, JTextField paramNombre, JTextField paramPrecio, JTextField paramDescripcion, JTextField paramEspecificaciones,JTextField paramStock){
    try{
        int nrow = paramTablaProductos.getSelectedRow();
        if (nrow>=0){
        paramID.setText(paramTablaProductos.getValueAt(nrow, 0).toString());
        paramCategoria.setText(paramTablaProductos.getValueAt(nrow, 1).toString());
        paramNombre.setText(paramTablaProductos.getValueAt(nrow, 2).toString());
        paramPrecio.setText(paramTablaProductos.getValueAt(nrow, 3).toString());
        paramDescripcion.setText(paramTablaProductos.getValueAt(nrow, 4).toString());
        paramEspecificaciones.setText(paramTablaProductos.getValueAt(nrow, 5).toString());
        paramStock.setText(paramTablaProductos.getValueAt(nrow, 6).toString());
        }else{
        JOptionPane.showMessageDialog(null,"Error, no se pudo seleccionar");
        }
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,"Error:"+ e.toString());
    }
    
    }
    
    public void GuardarProductos(JTextField paramCategoria, JTextField paramNombre, JTextField paramPrecio, JTextField paramDescripcion, JTextField paramEspecificaciones,JTextField paramStock){
        CConnection cn = new CConnection();
        String sqlquery = "INSERT INTO Productos(ID_Categoria,Nombre_Producto,Precio_Producto,Descripcion,Especificaciones,Stock) VALUES (?,?,?,?,?,?);";
        try{
            CallableStatement cs = cn.establishConnectionCN().prepareCall(sqlquery);
            cs.setInt(1,Integer.parseInt(paramCategoria.getText()));
            cs.setString(2,paramNombre.getText());
            cs.setFloat(3,Float.parseFloat(paramPrecio.getText()));
            cs.setString(4,paramDescripcion.getText());
            cs.setString(5,paramEspecificaciones.getText());
            cs.setString(6,paramStock.getText());
            
            cs.execute();
            JOptionPane.showMessageDialog(null,"Se guardo correctamente");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error:"+ e.toString());
        }
        
    }
    
    public void ModificarProductos(JTextField paramID, JTextField paramCategoria, JTextField paramNombre, JTextField paramPrecio, JTextField paramDescripcion, JTextField paramEspecificaciones, JTextField paramStock){
        CConnection cn = new CConnection();
        String sqlquery = "UPDATE Productos SET Productos.ID_Categoria=?, Productos.Nombre_Producto=?, Productos.Precio_Producto=?, Productos.Descripcion=?, Productos.Especificaciones=?, Productos.Stock=? WHERE Productos.ID_Producto=?";
        
        try{
            CallableStatement cs = cn.establishConnectionCN().prepareCall(sqlquery);
            cs.setInt(1,Integer.parseInt(paramCategoria.getText()));
            cs.setString(2,paramNombre.getText());
            cs.setFloat(3,Float.parseFloat(paramPrecio.getText()));
            cs.setString(4,paramDescripcion.getText());
            cs.setString(5,paramEspecificaciones.getText());
            cs.setInt(6,Integer.parseInt(paramStock.getText()));
            cs.setInt(7,Integer.parseInt(paramID.getText()));
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Se modifico correctamente");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error:"+ e.toString());
        }
    }
    
    public void EliminarProductos(JTextField paramID){
        CConnection cn = new CConnection();
        String sqlquery = "DELETE FROM Productos WHERE Productos.ID_Producto=?";
        
        try{
            CallableStatement cs = cn.establishConnectionCN().prepareCall(sqlquery);
            cs.setInt(1,Integer.parseInt(paramID.getText()));
            cs.execute();
            JOptionPane.showMessageDialog(null,"Se elimino correctamente");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error:"+ e.toString());
        }
    
    
    }
    
}
