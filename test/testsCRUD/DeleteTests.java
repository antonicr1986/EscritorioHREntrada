package testsCRUD;

import CRUD.Delete;
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
*Clase para comprobar operaciones tipo Delete
 * 
 * @author Antonio Company Rodriguez
 */

public class DeleteTests {
    private StringWriter stringWriter;
    private BufferedReader lector;
    private BufferedWriter escriptor;
    private JTextArea textArea;
    private Socket socket;
    private ObjectInputStream perEnt;
    
    @Test
    public void testBorrarTablaEmpresa() throws IOException, ClassNotFoundException {
        String ip = "localhost";
        String user = "admin";
        String password = "admin";
        String codigo = "A12354";
        String datoDni = "12345678X";
        String palabra = codigo+",3,2,dni,"+datoDni+",0";
        String NomApellido[]={codigo,"3","2","dni",datoDni,"0"};

        MainForm mainForm = new MainForm();
        mainForm.setCodigo(codigo); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);
        textArea = usuarioFormPestañas.getjTextAreaDelete();
        
        ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);
        
        socket = mainForm.getSocket();
        lector = new BufferedReader(new InputStreamReader( MainForm.getSocket().getInputStream()));
        escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));

        try {
            Delete.deleteEmpresas(NomApellido, palabra, escriptor, perEnt, socket, textArea);
            // Verifica que el resultado esperado se encuentra en el textArea
        assertTrue(textArea.getText().contains("Dni: 12345678A"));
        
        }catch(IOException | ClassNotFoundException ex){
            
        }finally{
            Logout.logout(usuarioFormPestañas);
        }      
    }
    
    @Test
    public void testBorrarTablaEmpleados() throws IOException, ClassNotFoundException {
        String ip = "localhost";
        String user = "admin";
        String password = "admin";
        String codigo = "A12354";
        String datoDni = "12345678X";
        String palabra = codigo+",3,0,dni,"+datoDni+",0";
        String NomApellido[]={codigo,"3","0","dni",datoDni,"0"};

        MainForm mainForm = new MainForm();
        mainForm.setCodigo(codigo); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);
        textArea = usuarioFormPestañas.getjTextAreaDelete();
        
        ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);
        
        socket = MainForm.getSocket();
        lector = new BufferedReader(new InputStreamReader( MainForm.getSocket().getInputStream()));
        escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));

        Delete.deleteEmpleados(NomApellido, palabra, escriptor, perEnt, socket, textArea);
        
        // Verifica que el resultado esperado se encuentra en el textArea
        assertTrue(textArea.getText().contains("Dni: 12345678A"));
        
        Logout.logout(usuarioFormPestañas);
    }
    
    @Test
    public void testBorrarTablaUsers() throws IOException, ClassNotFoundException {
        String ip = "localhost";
        String user = "admin";
        String password = "admin";
        String codigo = "A12354";
        String datoDni = "12345678X";
        String palabra = codigo+",3,1,dni,"+datoDni+",0";
        String NomApellido[]={codigo,"3","1","dni",datoDni,"0"};

        MainForm mainForm = new MainForm();
        mainForm.setCodigo(codigo); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);
        textArea = usuarioFormPestañas.getjTextAreaDelete();
        
        ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);
        
        socket = MainForm.getSocket();
        lector = new BufferedReader(new InputStreamReader( MainForm.getSocket().getInputStream()));
        escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));

        Delete.deleteUsers(NomApellido, palabra, escriptor, perEnt, socket, textArea);
        
        // Verifica que el resultado esperado se encuentra en el textArea
        assertTrue(textArea.getText().contains("Dni: 12345678A"));
        
        Logout.logout(usuarioFormPestañas);
    }
    
    @Test
    public void testBorrarTablaJornada() throws IOException, ClassNotFoundException {
        String ip = "localhost";
        String user = "admin";
        String password = "admin";
        String codigo = "A12354";
        String datoDni = "12345678X";
        String datoFecha = "13/12/2023";
        String palabra = codigo+",3,3,dni,"+datoDni+",fecha,"+datoFecha+",0";
        String NomApellido[]={codigo,"3","3","dni",datoDni,"fecha",datoFecha,"0"};

        MainForm mainForm = new MainForm();
        mainForm.setCodigo(codigo); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);
        textArea = usuarioFormPestañas.getjTextAreaDelete();
        
        ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);
        
        socket = mainForm.getSocket();
        lector = new BufferedReader(new InputStreamReader( MainForm.getSocket().getInputStream()));
        escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));

        Delete.deleteJornada(NomApellido, palabra, escriptor, perEnt, socket, textArea);
        
        // Verifica que el resultado esperado se encuentra en el textArea
        assertTrue(textArea.getText().contains("Dni: 12345678A"));
        
        Logout.logout(usuarioFormPestañas);
    }
}
