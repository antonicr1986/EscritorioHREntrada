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
 *Clase para comprobar tests jUnit del m�todo conexionSocket
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
        mainForm.setCodigo("A12354"); // C�digo de usuario v�lido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPesta�as = new FormVentanasUsuario(user,password);

        ConexionSocket.conexionSocket(mainForm, usuarioFormPesta�as, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);


        // Nos aseguramos que la conexi�n se realiza correctamente y la ventana de usuario se muestra
        assertNotNull(mainForm.getSocket());

        assertEquals(user, usuarioFormPesta�as.getUser());           
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
        FormVentanasUsuario usuarioFormPesta�as = new FormVentanasUsuario(user,password);

        ConexionSocket.conexionSocket(mainForm, usuarioFormPesta�as, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);


        // Nos aseguramos que la conexi�n se realiza correctamente y la ventana de usuario se muestra
        assertNotNull(mainForm.getSocket());  
        assertEquals(user, usuarioFormPesta�as.getUser());           
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
        FormVentanasUsuario usuarioFormPesta�as = new FormVentanasUsuario(user,password);

        ConexionSocket.conexionSocket(mainForm, usuarioFormPesta�as, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);

        
        // Nos aseguramos de que la conexi�n falla y no se establece el socket
        assertNull(mainForm.getSocket());

    }
}
