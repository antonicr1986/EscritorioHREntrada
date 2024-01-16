package LoginLogoutTest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

import logs.*;
import vistas.*;

/**
 *Clase para comprobar tests jUnit del método conexionSocket
 * 
 * @author Antonio Company Rodriguez
 */

public class LoginTests {
    /**
     * Prueba la conexión del socket para un inicio de sesión válido como administrador.
     */
    @Test
    public void testConexionSocketLoginValidoAdmin() {
        String ip = "localhost";
        String user = "admin";
        String password = "admin";

        MainForm mainForm = new MainForm();
        mainForm.setCodigo("A12354"); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);

        ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);


        // Nos aseguramos que la conexión se realiza correctamente y la ventana de usuario se muestra
        assertNotNull(mainForm.getSocket());

        assertEquals(user, usuarioFormPestañas.getUser());
        
        Logout.logout(usuarioFormPestañas);
    }
    
    /**
     * Prueba la conexión del socket para un inicio de sesión válido como usuario.
     */
    @Test 
    public void testConexionSocketLoginValidoUser() {
        String ip = "localhost";
        String user = "user";
        String password = "user";

        MainForm mainForm = new MainForm();
        mainForm.setCodigo("U12345");
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);

        ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);


        // Nos aseguramos que la conexión se realiza correctamente y la ventana de usuario se muestra
        assertNotNull(mainForm.getSocket());  
        assertEquals(user, usuarioFormPestañas.getUser());   

        Logout.logout(usuarioFormPestañas);        
    }

    /**
     * Prueba la conexión del socket para un inicio de sesión fallido como administrador.
     */
    @Test
    public void testConexionSocketLoginAdminErroneo() {
        String ip = "localhost";
        String user = "admin";
        String password = "admin456";
        String codigo = "A12395";
        
        MainForm mainForm = new MainForm();     
        mainForm.setCodigo(codigo); 
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(codigo,user);

        ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);
        
        // Nos aseguramos de que la conexión falla y no se establece el usuario
        assertNull(usuarioFormPestañas.getPasswordCambioPass()); // Asegurarse de que el usuario en la forma sea vacío
        
        Logout.logout(usuarioFormPestañas);
    }
    
    /**
     * Prueba la conexión del socket para un inicio de sesión fallido como usuario.
     */
    @Test
    public void testConexionSocketLoginErroneo() {
        String ip = "localhost";
        String user = "user";
        String password = "user456";
        String codigo = "U12395";
        
        MainForm mainForm = new MainForm();     
        mainForm.setCodigo(codigo); 
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(codigo,user);

        ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);
        
        // Nos aseguramos de que la conexión falla y no se establece el usuario
        assertEquals(null, usuarioFormPestañas.getPasswordCambioPass()); // Asegurarse de que el usuario en la forma sea vacío
        
        Logout.logout(usuarioFormPestañas);
    }
    
     /**
     * Prueba la conexión del socket para un inicio de sesión repetido como administrador.
     */
    @Test
    public void testConexionSocketLoginAdminRepetido() {
        String ip = "localhost";
        String user = "admin";
        String password = "admin";

        MainForm mainForm = new MainForm();
        mainForm.setCodigo("A12354"); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);

        ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);
        assertNotNull(mainForm.getSocket());

        assertEquals(user, usuarioFormPestañas.getUser());
        
        ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);
        // Nos aseguramos que la conexión se realiza correctamente y 
        //que el codigo de usuario no contiene una 'A' en su primera posicion
        assertNotEquals('A', usuarioFormPestañas.getjUserCode1().getText().charAt(0));
        
        Logout.logout(usuarioFormPestañas);
    }
}
