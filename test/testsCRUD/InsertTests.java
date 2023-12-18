package testsCRUD;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.*;
import java.net.Socket;
import javax.swing.JTextArea;

import CRUD.Insert;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import logs.ConexionSocket;
import logs.Logout;
import vistas.FormVentanasUsuario;
import vistas.MainForm;

/**
 *Clase para comprobar operaciones tipo insert
 * 
 * @author Antonio Company Rodriguez
 */

public class InsertTests {
    
    private BufferedWriter escriptor;
    private Socket socket;
    private JTextArea jTextAreaInsert;
    private ObjectInputStream perEnt;
    
    @Test
    public void testOperacionesConInsertEmpresas() throws IOException, ClassNotFoundException {
        // Configuramos el entorno para las pruebas
        String[] insertEmpresas = {"A34567","1","2","nom","Toyota","address","ElPrat","telephon","34933568956","0"};
        String palabra = "A34567,1,2,nom,Toyota,address,ElPrat,telephon,34933568956,0";            
        String ip = "localhost";
        String user = "admin";
        String password = "admin";
        String codigo = "A12354";

        MainForm mainForm = new MainForm();
        mainForm.setCodigo(codigo); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);
        jTextAreaInsert = usuarioFormPestañas.getjTextAreaInsert();
        
        try{
            ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);    
            socket = mainForm.getSocket();
            escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));

            //Método a testear
            Insert.operacionesConInsertEmpresas(insertEmpresas, palabra, escriptor, perEnt, socket, jTextAreaInsert);

            //Verificacion
            String resultadoEsperado = "Empresa creada correctamente"; 
            assertTrue(jTextAreaInsert.getText().contains(resultadoEsperado));
        }catch(IOException | ClassNotFoundException ex){
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Logout.logout(usuarioFormPestañas);
        }
    }

    @Test
    public void testOperacionesConInsertUsuarios() throws IOException, ClassNotFoundException {
        // Configuramos el entorno para las pruebas
        String[] insertUsuarios = {"A34567","1","1","login","usuario","pass","1234","numtipe","1","dni","67123456X","0"};
        String palabra = "A34567,1,1,login,usuario,pass,1234,numtipe,1,dni,67123456X,0";     
        String ip = "localhost";
        String user = "admin";
        String password = "admin";
        String codigo = "A12354";
        
        MainForm mainForm = new MainForm();
        mainForm.setCodigo(codigo); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);
        jTextAreaInsert = usuarioFormPestañas.getjTextAreaInsert();
        
        try{
            ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);      
            socket = mainForm.getSocket();
            escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));

            //Método a testear
            Insert.operacionesConInsertUsuarios(insertUsuarios, palabra, escriptor, perEnt, socket, jTextAreaInsert);

            // Verificacion
            String resultadoEsperado = "Usuario creado correctamente"; 
            assertTrue(jTextAreaInsert.getText().contains(resultadoEsperado));
        }catch(IOException | ClassNotFoundException ex){
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Logout.logout(usuarioFormPestañas);
        }
    }

    @Test
    public void testOperacionesConInsertEmpleados() throws IOException, ClassNotFoundException {
        // Configuramos el entorno para las pruebas
        String[] insertEmpresas = {"A34567","1","0","nom","Toyota","address","ElPrat","telephon","34933568956","0"};
        String palabra = "A34567,1,0,nom,Toyota,address,ElPrat,telephon,34933568956,0";            
        String ip = "localhost";
        String user = "admin";
        String password = "admin";
        String codigo = "A12354";

        MainForm mainForm = new MainForm();
        mainForm.setCodigo(codigo); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);
        jTextAreaInsert = usuarioFormPestañas.getjTextAreaInsert();

        try{
            ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);    
            socket = mainForm.getSocket();
            escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));

            //Método a testear
            Insert.operacionesConInsertEmpleado19(insertEmpresas, palabra, escriptor, perEnt, socket, jTextAreaInsert);

            //Verificacion
            String resultadoEsperado = "Empleado creado correctamente"; 
            assertTrue(jTextAreaInsert.getText().contains(resultadoEsperado));
        }catch(IOException | ClassNotFoundException ex){
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Logout.logout(usuarioFormPestañas);
        }
    }
    
    @Test
    public void testOperacionesConInsertJornada() throws IOException, ClassNotFoundException {
        // Configuramos el entorno para las pruebas
        String palabra = "A34567,1,3,dni,53313513L,0";            
        String ip = "localhost";
        String user = "admin";
        String password = "admin";
        String codigo = "A12354";

        MainForm mainForm = new MainForm();
        mainForm.setCodigo(codigo); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);
        jTextAreaInsert = usuarioFormPestañas.getjTextAreaInsert();

        try{
            ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);    
            socket = mainForm.getSocket();
            escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));

            //Método a testear
            Insert.operacionesConInsertJornada(palabra, codigo, palabra, codigo, escriptor, perEnt, socket, jTextAreaInsert);

            //Verificacion
            String resultadoEsperado = "Jornada creada correctamente"; 
            assertTrue(jTextAreaInsert.getText().contains(resultadoEsperado));
        }catch(IOException | ClassNotFoundException ex){
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Logout.logout(usuarioFormPestañas);
        }
    }
}
