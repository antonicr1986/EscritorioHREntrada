package testsCRUD;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;
import CRUD.Select;
import java.net.Socket;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import logs.ConexionSocket;
import logs.Logout;
import vistas.FormVentanasUsuario;
import vistas.MainForm;

/**
 *Clase para comprobar operaciones tipo Select
 * 
 * @author Antonio Company Rodriguez
 */

public class SelectTests {
    private Select select;
    private StringWriter stringWriter;
    private BufferedWriter bufferedWriter;
    private JTextArea textArea;
    private Socket socket;
    
    @Test
    public void testOperacionesConSelect() throws IOException, ClassNotFoundException {
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
        
        Select.operacionesConSelect("dni", "123", "operacion", "0", bufferedWriter, "usuario", socket, textArea);

        // Verifica que el resultado esperado se encuentra en el textArea
        assertTrue(textArea.getText().contains("Dni: 123"));
        
        Logout.logout(usuarioFormPestañas);
    }
    
    /*@Test
    public void testMostrarTablaEmpleados1Filtro() throws IOException, ClassNotFoundException {
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
        
        Select.mostrarTablaEmpleados1Filtro("0", "dni", "usuario", "123", "operacion", bufferedWriter, textArea, null, socket);

        // Verificamos que el resultado esperado se encuentra en el textArea
        assertTrue(textArea.getText().contains("Dni: 123"));
        
        Logout.logout(usuarioFormPestañas);
    }*/

}
