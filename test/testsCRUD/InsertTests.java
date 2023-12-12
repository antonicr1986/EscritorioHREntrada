package testsCRUD;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.*;
import java.net.Socket;
import javax.swing.JTextArea;

import CRUD.Insert;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import logs.ConexionSocket;
import vistas.FormVentanasUsuario;
import vistas.MainForm;

/**
 *Clase para comprobar operaciones tipo insert
 * 
 * @author Antonio Company Rodriguez
 */

public class InsertTests {
    
    private StringWriter stringWriter;
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

        MainForm mainForm = new MainForm();
        mainForm.setCodigo("A12354"); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);

        ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);
        
        socket = mainForm.getSocket();
        escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));
        
        //Método a testear
        Insert.operacionesConInsertEmpresas(insertEmpresas, palabra, escriptor, perEnt, socket, jTextAreaInsert);

        //Verificaion
        String resultadoEsperado = "Me falta poner resultado esperado aqui"; 
        assertTrue(stringWriter.toString().contains(resultadoEsperado));
    }

    @Test
    public void testOperacionesConInsertUsuarios() throws IOException, ClassNotFoundException {
        // Configuramos el entorno para las pruebas
        String[] insertUsuarios = {"A34567","1","2","nom","Seat","address","ElPrat","telephon","34930000956","0"};
        String palabra = "A34567,1,2,nom,Seat,address,ElPrat,telephon,34930000956,0";
        StringWriter stringWriter = new StringWriter();
        BufferedWriter escriptor = new BufferedWriter(stringWriter);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("mockedData".getBytes());
        ObjectInputStream perEnt = new ObjectInputStream(byteArrayInputStream);
        Socket socket = new Socket(); 
        JTextArea jTextAreaInsert = new JTextArea();

        //Método a testear
        Insert.operacionesConInsertUsuarios(insertUsuarios, palabra, escriptor, perEnt, socket, jTextAreaInsert);

        // Verificacion
        String resultadoEsperado = "Me falta poner resultado esperado aqui"; 
        assertTrue(stringWriter.toString().contains(resultadoEsperado));

    }

}
