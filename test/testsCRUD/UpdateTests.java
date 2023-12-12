package testsCRUD;

import CRUD.Select;
import CRUD.Update;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.Socket;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import logs.ConexionSocket;
import logs.Logout;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import vistas.FormVentanasUsuario;
import vistas.MainForm;

/**
 *Clase para comprobar operaciones tipo Update
 * 
 * @author Antonio Company Rodriguez
 */

public class UpdateTests {
    private StringWriter stringWriter;
    private BufferedReader lector;
    private BufferedWriter escriptor;
    private JTextArea textArea;
    private Socket socket;
    private ObjectInputStream perEnt;
    
    @Test
    public void testActualizarTablaEmpresa() throws IOException, ClassNotFoundException {
        String ip = "localhost";
        String user = "admin";
        String password = "admin";
        String palabra = "";
        String NomApellido[]={"",""};

        MainForm mainForm = new MainForm();
        mainForm.setCodigo("A12354"); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);

        ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);
        
        socket = mainForm.getSocket();
        lector = new BufferedReader(new InputStreamReader( MainForm.getSocket().getInputStream()));
        escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));

        Update.updateEmpresa(NomApellido, palabra, escriptor, perEnt, socket, textArea);
        
        // Verifica que el resultado esperado se encuentra en el textArea
        assertTrue(textArea.getText().contains("Dni: 12345678A"));
        
        Logout.logout(usuarioFormPestañas);
    }
    
    @Test
    public void testActualizarTablaEmpleados() throws IOException, ClassNotFoundException {
        String ip = "localhost";
        String user = "admin";
        String password = "admin";
        String updateEmpleado[]={"",""};

        MainForm mainForm = new MainForm();
        mainForm.setCodigo("A12354"); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);

        ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);
        
        socket = mainForm.getSocket();
        lector = new BufferedReader(new InputStreamReader( MainForm.getSocket().getInputStream()));
        escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));
        
        Update.updateEmpleado(updateEmpleado, password, escriptor, perEnt, socket, textArea);

        // Verifica que el resultado esperado se encuentra en el textArea
        assertTrue(textArea.getText().contains("Dni: 12345678A"));
        
        Logout.logout(usuarioFormPestañas);
    }
    
    @Test
    public void testActualizarTablaUsers() throws IOException, ClassNotFoundException {
        String ip = "localhost";
        String user = "admin";
        String password = "admin";
        String insertEmpresas []={"",""};

        MainForm mainForm = new MainForm();
        mainForm.setCodigo("A12354"); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);

        ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);
        
        socket = mainForm.getSocket();
        lector = new BufferedReader(new InputStreamReader( MainForm.getSocket().getInputStream()));
        escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));
        
        Update.updateUser(insertEmpresas, password, escriptor, perEnt, socket, textArea);

        // Verifica que el resultado esperado se encuentra en el textArea
        assertTrue(textArea.getText().contains("Dni: 12345678A"));
        
        Logout.logout(usuarioFormPestañas);
    }
    
    @Test
    public void testActualizarTablaJornada() throws IOException, ClassNotFoundException {
        String ip = "localhost";
        String user = "admin";
        String password = "admin";
        String frase []={"",""};

        MainForm mainForm = new MainForm();
        mainForm.setCodigo("A12354"); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);

        ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);
        
        socket = mainForm.getSocket();
        lector = new BufferedReader(new InputStreamReader( MainForm.getSocket().getInputStream()));
        escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));
        
       Update.updateJornada(frase, password, escriptor, perEnt, socket, textArea);

        // Verifica que el resultado esperado se encuentra en el textArea
        assertTrue(textArea.getText().contains("Dni: 12345678A"));
        
        Logout.logout(usuarioFormPestañas);
    }
}
