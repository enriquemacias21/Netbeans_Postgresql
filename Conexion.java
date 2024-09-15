/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemamatricula;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Conexion {
     Connection conectar= null;
     
     String user="postgres";
     String pass="admin123";
     String bd="sistema_matricula";
     String ip="localhost";
     String port="5435";
     
     String cadena="jdbc:postgresql://"+ip+":"+port+"/"+bd;
     //localhost:5435/sistema_matricula", "postgres", "admin123";
     
     public Connection establecerConexion(){
         try{
             //Class.forName("org.postgresql.Driver");
             
             //conectar= DriverManager.getConnection("jdbc:postgresql://localhost:5435/sistema_matricula", "postgres", "admin123");
             conectar= DriverManager.getConnection(cadena,user,pass);
             
             //JOptionPane.showMessageDialog(null, "Conexión exitosa");
             
         } catch (Exception e){
             JOptionPane.showMessageDialog(null, "Error:"+ e.toString());
         }
         return conectar;
     }
}
