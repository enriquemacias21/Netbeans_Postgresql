/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.sistemamatricula.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



/**
 *
 * @author Usuario
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testConexion()  {
    Conexion con= new Conexion();
    Connection connection;    
    
    connection = con.establecerConexion();
    
    assertNotNull(connection, "La conexión debe ser exitosa");
    
   if (connection != null) {
            try {
                assertFalse(connection.isClosed(), "La conexión no debe estar cerrada");
            } catch (Exception e) {
                fail("Error al verificar la conexión: " + e.getMessage());
            }
        } else {
            fail("No se pudo establecer la conexión.");
        }
    
    }

  
}
