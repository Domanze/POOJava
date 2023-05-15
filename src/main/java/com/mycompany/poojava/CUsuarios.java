package com.mycompany.poojava;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CUsuarios {
    public void MostrarUsuarios(JTable paramTablaUsuarios){
    CConnection cn = new CConnection();
    DefaultTableModel model = new DefaultTableModel();
    
    String sql="";
    
    model.addColumn("ID");
    model.addColumn("Nombre");
    model.addColumn("Apellido");
    model.addColumn("Email");
    model.addColumn("Contraseña");
    model.addColumn("Direccion");
    model.addColumn("Estado");
    model.addColumn("Ciudad");
    model.addColumn("CP");
    model.addColumn("Telefono");
    
    paramTablaUsuarios.setModel(model);
    
    sql="SELECT*FROM Usuarios;";
    
    String [] datosUsr = new String [10];
    
    Statement st;
    
    try { 
        st = cn.establishConnectionCN().createStatement();
        ResultSet rs = st.executeQuery(sql);
         while (rs.next()){
         datosUsr[0]=rs.getString(1);
         datosUsr[1]=rs.getString(2);
         datosUsr[2]=rs.getString(3);
         datosUsr[3]=rs.getString(4);
         datosUsr[4]=rs.getString(5);
         datosUsr[5]=rs.getString(6);
         datosUsr[6]=rs.getString(7);
         datosUsr[7]=rs.getString(8);
         datosUsr[8]=rs.getString(9);
         datosUsr[9]=rs.getString(10);
         
         
         model.addRow(datosUsr);
         }
         paramTablaUsuarios.setModel(model);
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,"Error al mostrar:"+ e.toString());
    
    }
    
    }
    
    public void SeleccionarUsuarios(JTable paramTablaUsuarios, JTextField paramID, JTextField paramNombre, JTextField paramApellido, JTextField paramEmail, JTextField paramPassword, JTextField paramDireccion, JTextField paramEstado, JTextField paramCiudad, JTextField paramCP, JTextField paramTelefono){
    try{
        int nrow = paramTablaUsuarios.getSelectedRow();
        if (nrow>=0){
        paramID.setText(paramTablaUsuarios.getValueAt(nrow, 0).toString());
        paramNombre.setText(paramTablaUsuarios.getValueAt(nrow, 1).toString());
        paramApellido.setText(paramTablaUsuarios.getValueAt(nrow, 2).toString());
        paramEmail.setText(paramTablaUsuarios.getValueAt(nrow, 3).toString());
        paramPassword.setText(paramTablaUsuarios.getValueAt(nrow, 4).toString());
        paramDireccion.setText(paramTablaUsuarios.getValueAt(nrow, 5).toString());
        paramEstado.setText(paramTablaUsuarios.getValueAt(nrow, 6).toString());
        paramCiudad.setText(paramTablaUsuarios.getValueAt(nrow, 7).toString());
        paramCP.setText(paramTablaUsuarios.getValueAt(nrow, 8).toString());
        paramTelefono.setText(paramTablaUsuarios.getValueAt(nrow, 9).toString());
        }else{
        JOptionPane.showMessageDialog(null,"Error, no se pudo seleccionar");
        }
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,"Error al seleccionar:"+ e.toString());
    }
    
    }
    
    public void GuardarUsuarios(JTextField paramNombre, JTextField paramApellido, JTextField paramEmail, JTextField paramPassword, JTextField paramDireccion, JTextField paramEstado, JTextField paramCiudad, JTextField paramCP, JTextField paramTelefono){
        CConnection cn = new CConnection();
        String sqlquery = "INSERT INTO Usuarios(Nombre,Apellido,Email,Contraseña,Direccion,Estado,Ciudad,Codigo_Postal,Telefono) VALUES (?,?,?,?,?,?,?,?,?);";
        try{
            CallableStatement cs = cn.establishConnectionCN().prepareCall(sqlquery);
            cs.setString(1,paramNombre.getText());
            cs.setString(2,paramApellido.getText());
            cs.setString(3,paramEmail.getText());
            cs.setString(4,paramPassword.getText());
            cs.setString(5,paramDireccion.getText());
            cs.setString(6,paramEstado.getText());
            cs.setString(7,paramCiudad.getText());
            cs.setString(8,paramCP.getText());
            cs.setString(9,paramTelefono.getText());
            

            
            cs.execute();
            JOptionPane.showMessageDialog(null,"Se guardo correctamente");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al guardar:"+ e.toString());
        }
        
    }
    
    public void ModificarUsuarios(JTextField paramID, JTextField paramNombre, JTextField paramApellido, JTextField paramEmail, JTextField paramPassword, JTextField paramDireccion, JTextField paramEstado, JTextField paramCiudad, JTextField paramCP, JTextField paramTelefono){
        CConnection cn = new CConnection();
        String sqlquery = "UPDATE Usuarios SET Usuarios.Nombre=?, Usuarios.Apellido=?, Usuarios.Email=?, Usuarios.Contraseña=?, Usuarios.Direccion=?, Usuarios.Estado=?, Usuarios.Ciudad=?, Usuarios.Codigo_Postal=?, Usuarios.Telefono=? WHERE Usuarios.ID_Usuario=?";
        
        try{
            CallableStatement cs = cn.establishConnectionCN().prepareCall(sqlquery);
            cs.setString(1,paramNombre.getText());
            cs.setString(2,paramApellido.getText());
            cs.setString(3,paramEmail.getText());
            cs.setString(4,paramPassword.getText());
            cs.setString(5,paramDireccion.getText());
            cs.setString(6,paramEstado.getText());
            cs.setString(7,paramCiudad.getText());
            cs.setString(8,paramCP.getText());
            cs.setString(9,paramTelefono.getText());
            cs.setInt(10,Integer.parseInt(paramID.getText()));
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Se modifico correctamente");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al modificar:"+ e.toString());
        }
    }
    
    public void EliminarUsuarios(JTextField paramID){
        CConnection cn = new CConnection();
        String sqlquery = "DELETE FROM Usuarios WHERE Usuarios.ID_Usuario=?";
        
        try{
            CallableStatement cs = cn.establishConnectionCN().prepareCall(sqlquery);
            cs.setInt(1,Integer.parseInt(paramID.getText()));
            cs.execute();
            JOptionPane.showMessageDialog(null,"Se elimino correctamente");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al eliminar:"+ e.toString());
        }
    
    
    }
    
}
