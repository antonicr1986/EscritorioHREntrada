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

    @Test
    public void testConexionSocketLoginAdminErroneo() {
        String ip = "localhost";
        String user = "admin";
        String password = "admin456";
        
        MainForm mainForm = new MainForm();     
        mainForm.setCodigo("A12395"); 
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);

        ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);

        
        // Nos aseguramos de que la conexión falla y no se establece el usuario
        assertEquals(null, usuarioFormPestañas.getPasswordCambioPass()); // Asegurarse de que el usuario en la forma sea vacío
        
        Logout.logout(usuarioFormPestañas);
    }
    
    @Test
    public void testConexionSocketLoginErroneo() {
        String ip = "localhost";
        String user = "user";
        String password = "user456";
        
        MainForm mainForm = new MainForm();     
        mainForm.setCodigo("U12395"); 
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);

        ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);

        
        // Nos aseguramos de que la conexión falla y no se establece el usuario
        assertEquals(null, usuarioFormPestañas.getPasswordCambioPass()); // Asegurarse de que el usuario en la forma sea vacío
        
        Logout.logout(usuarioFormPestañas);
    }
    
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
        // Nos aseguramos que la conexión se realiza correctamente y la ventana de usuario se muestra
        assertNotEquals('A', usuarioFormPestañas.getjUserCode1().getText().charAt(0));
        
        Logout.logout(usuarioFormPestañas);
    }
}
