/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemamatricula;

//import java.sql.Date;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;




/**
 *
 * @author Usuario
 */
public class GEstudiante {

    String nombre;
    String apellido;
    String cedula;
    String fecha_nacimiento;
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    
    public void MostrarEstudiante(JTable paramTablaTotalEstudiante){
        Conexion con = new Conexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql="";
        
        modelo.addColumn("Cedula");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Nombres");
        modelo.addColumn("Fecha de Nacimiento");
        
        paramTablaTotalEstudiante.setModel(modelo);
        
        sql="Select cedula, apellido, nombre, fecha_nacimiento from estudiantes order by apellido, nombre";
        
        String [] datos = new String [4];
        
        Statement st;
        
        try {
            st=con.establecerConexion().createStatement();
            
            ResultSet rs=st.executeQuery(sql);
            
            while (rs.next()){
                datos[0]= rs.getString(1);
                datos[1]= rs.getString(2);
                datos[2]= rs.getString(3);
                datos[3]= rs.getString(4);
                
                modelo.addRow(datos);
            }
            
            paramTablaTotalEstudiante.setModel(modelo);
            
            rs.close();
            con.establecerConexion().close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error:"+e.toString());
        }
        
    }
    
    public void insertarEstudiante(JTextField paraCedula, JTextField paraApellido, JTextField paraNombre, JFormattedTextField paraFecha) {
        setCedula(paraCedula.getText());
        setApellido(paraApellido.getText());
        setNombre(paraNombre.getText());
        setFecha_nacimiento(paraFecha.getText());
        
        //DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
        //java.util.Date fecha= formato.parse(getFecha_nacimiento());
        
        Conexion con = new Conexion();
        
        String sql= "insert into estudiantes (cedula, apellido, nombre, fecha_nacimiento) values(?,?,?,?)";
        try{
            
            java.sql.Date fecha = java.sql.Date.valueOf(getFecha_nacimiento());//formato.parse(getFecha_nacimiento());
            
            PreparedStatement pst = con.establecerConexion().prepareStatement(sql);
            pst.setString(1, getCedula());
            pst.setString(2, getApellido());
            pst.setString(3, getNombre());
            pst.setDate(4, fecha);
            
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Se grabo correctamente");

            pst.close();
            con.establecerConexion().close();
            
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error:"+e.toString());
        }
        
    }
    
    public void SeleccionarEstudiante(JTable paramTablaEstudiante, JTextField paraCedula, JTextField paraApellido, JTextField paraNombre, JFormattedTextField paraFecha) {
        try {
            int fila =paramTablaEstudiante.getSelectedRow();
            
            if (fila >=0){
                
                paraCedula.setText(paramTablaEstudiante.getValueAt(fila, 0).toString());
                paraApellido.setText(paramTablaEstudiante.getValueAt(fila, 1).toString());
                paraNombre.setText(paramTablaEstudiante.getValueAt(fila, 2).toString());
                paraFecha.setText(paramTablaEstudiante.getValueAt(fila, 3).toString());
                
            }
            else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error: "+e.toString());
        }
        
    }
    
    public void editarEstudiante(JTextField paraCedula, JTextField paraApellido, JTextField paraNombre, JFormattedTextField paraFecha) {
        //int codigo = Integer.parseInt(nombre);
        
        setCedula(paraCedula.getText());
        setApellido(paraApellido.getText());
        setNombre(paraNombre.getText());
        setFecha_nacimiento(paraFecha.getText());
        
        Conexion con = new Conexion();
        
        String sql= "update estudiantes set apellido=?, nombre=?, fecha_nacimiento=? where cedula=?;";
        try{
            
            java.sql.Date fecha = java.sql.Date.valueOf(getFecha_nacimiento());
            
            PreparedStatement pst = con.establecerConexion().prepareStatement(sql);
            pst.setString(1, getApellido());
            pst.setString(2, getNombre());
            pst.setDate(3, fecha);
            pst.setString(4, getCedula());
            
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Se actualizó correctamente");

            pst.close();
            con.establecerConexion().close();
            
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error:"+e.toString());
        }
        
    }
    
     public void elimarEstudiante(JTextField paraCedula) {
        //int codigo = Integer.parseInt(nombre);
        
        setCedula(paraCedula.getText());
        
        Conexion con = new Conexion();
        
        String sql= "delete from estudiantes where cedula=?;";
        try{     
            PreparedStatement pst = con.establecerConexion().prepareStatement(sql);
            pst.setString(1, getCedula());
            
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Se elimino correctamente");

            pst.close();
            con.establecerConexion().close();
            
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error:"+e.toString());
        }
        
    }
     
     public JasperPrint reporteTodoEstudiante(){
         Conexion con = new Conexion();
         
        // File reporte = new File(getClass().getResource("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\SistemaMatricula\\src\\main\\java\\com\\mycompany\\sistemamatricula\\Reportes\\Estudiantes.jasper").getFile());        
        File reporte = new File("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\SistemaMatricula\\src\\main\\java\\com\\mycompany\\sistemamatricula\\Reportes\\Estudiantes.jasper");        
       
         //JOptionPane.showMessageDialog(null, reporte.getPath());
         
         
         if (!reporte.exists()){
             JOptionPane.showMessageDialog(null, "No existe archivo");
             return null;
         }
         
         try {
             InputStream is = new BufferedInputStream(new FileInputStream(reporte.getAbsoluteFile()));
             JasperReport jr = (JasperReport) JRLoader.loadObject(is);
             JasperPrint jp = JasperFillManager.fillReport(jr, null, con.establecerConexion()); 
             return jp;
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error: "+ e.toString());
         }
     return null;
         
     }
     
     public JasperPrint reporteTodosEstudiantes(){
         return reporteTodoEstudiante();
     }
}
