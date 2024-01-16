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
    /**
     * Prueba la conexi�n del socket para un inicio de sesi�n v�lido como administrador.
     */
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
        
        Logout.logout(usuarioFormPesta�as);
    }
    
    /**
     * Prueba la conexi�n del socket para un inicio de sesi�n v�lido como usuario.
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
        FormVentanasUsuario usuarioFormPesta�as = new FormVentanasUsuario(user,password);

        ConexionSocket.conexionSocket(mainForm, usuarioFormPesta�as, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);


        // Nos aseguramos que la conexi�n se realiza correctamente y la ventana de usuario se muestra
        assertNotNull(mainForm.getSocket());  
        assertEquals(user, usuarioFormPesta�as.getUser());   

        Logout.logout(usuarioFormPesta�as);        
    }

    /**
     * Prueba la conexi�n del socket para un inicio de sesi�n fallido como administrador.
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
        FormVentanasUsuario usuarioFormPesta�as = new FormVentanasUsuario(codigo,user);

        ConexionSocket.conexionSocket(mainForm, usuarioFormPesta�as, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);
        
        // Nos aseguramos de que la conexi�n falla y no se establece el usuario
        assertNull(usuarioFormPesta�as.getPasswordCambioPass()); // Asegurarse de que el usuario en la forma sea vac�o
        
        Logout.logout(usuarioFormPesta�as);
    }
    
    /**
     * Prueba la conexi�n del socket para un inicio de sesi�n fallido como usuario.
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
        FormVentanasUsuario usuarioFormPesta�as = new FormVentanasUsuario(codigo,user);

        ConexionSocket.conexionSocket(mainForm, usuarioFormPesta�as, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);
        
        // Nos aseguramos de que la conexi�n falla y no se establece el usuario
        assertEquals(null, usuarioFormPesta�as.getPasswordCambioPass()); // Asegurarse de que el usuario en la forma sea vac�o
        
        Logout.logout(usuarioFormPesta�as);
    }
    
     /**
     * Prueba la conexi�n del socket para un inicio de sesi�n repetido como administrador.
     */
    @Test
    public void testConexionSocketLoginAdminRepetido() {
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
        assertNotNull(mainForm.getSocket());

        assertEquals(user, usuarioFormPesta�as.getUser());
        
        ConexionSocket.conexionSocket(mainForm, usuarioFormPesta�as, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);
        // Nos aseguramos que la conexi�n se realiza correctamente y 
        //que el codigo de usuario no contiene una 'A' en su primera posicion
        assertNotEquals('A', usuarioFormPesta�as.getjUserCode1().getText().charAt(0));
        
        Logout.logout(usuarioFormPesta�as);
    }
}
