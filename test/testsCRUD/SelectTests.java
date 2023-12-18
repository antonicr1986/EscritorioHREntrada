package testsCRUD;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;
import CRUD.Select;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
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
    private BufferedReader lector;
    private BufferedWriter escriptor;
    private JTextArea textArea;
    private Socket socket;
    
    /**
     * Prueba para mostrar la tabla de empleados sin filtro.
     *
     * @throws IOException Excepcion de tipo entrada salida.
     * @throws ClassNotFoundException Lanzada cuando la clase especificada no ha sido encontrada.
     */
    @Test
    public void testMostrarTablaEmpleadosSinFiltro() throws IOException, ClassNotFoundException {
        String ip = "localhost";
        String user = "admin";
        String password = "admin";
        String codigo = "A12354";
        String columna = "dni";
        String palabra = codigo+",0,0,"+columna+",0,0";
        String palabraAbuscar = "12345678A";
        String nombreTabla = "0";//tabla empleados

        
        MainForm mainForm = new MainForm();
        mainForm.setCodigo(codigo); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);
        
        try{
            ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);

            socket = mainForm.getSocket();
            lector = new BufferedReader(new InputStreamReader( MainForm.getSocket().getInputStream()));
            escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));

            Select.mostrarTablaSinFiltro(columna, palabra, palabraAbuscar,nombreTabla, escriptor, codigo, socket, textArea);

            // Verifica que el resultado esperado se encuentra en el textArea
            assertTrue(textArea.getText().contains("Dni: 12345678A"));//Dni: 12345678A
        }catch(IOException | ClassNotFoundException ex){
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Logout.logout(usuarioFormPestañas);
        }      
    }
    
    
    /**
     * Prueba para mostrar la tabla de empresas sin filtro.
     *
     * @throws IOException Excepction de tipo entrada salida
     * @throws ClassNotFoundException Lanzada cuando la clase especificada no ha sido encontrada.
     */
    @Test
    public void testMostrarTablaEmpresaSinFiltro() throws IOException, ClassNotFoundException {
        String ip = "localhost";
        String user = "admin";
        String password = "admin";
        String codigo = "A12354";
        String columna = "dni";
        String palabra = codigo+",0,2,"+columna+",0,0";
        String palabraAbuscar = "12345678A";
        String nombreTabla = "2";//tabla empresa

        
        MainForm mainForm = new MainForm();
        mainForm.setCodigo(codigo); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);
        
        try{
            ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);

            socket = mainForm.getSocket();
            lector = new BufferedReader(new InputStreamReader( MainForm.getSocket().getInputStream()));
            escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));

            Select.mostrarTablaSinFiltro(columna, palabra, palabraAbuscar,nombreTabla, escriptor, codigo, socket, textArea);

            // Verifica que el resultado esperado se encuentra en el textArea
            assertTrue(textArea.getText().contains("Nombre empresa: HREntrada"));
        }catch(IOException | ClassNotFoundException ex){
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Logout.logout(usuarioFormPestañas);
        }      
    }
    
    /**
     * Prueba para mostrar la tabla de usuarios sin filtro.
     *
     * @throws IOException Excepcion de tipo entrada salida.
     * @throws ClassNotFoundException Lanzada cuando la clase especificada no ha sido encontrada.
     */
   @Test
    public void testMostrarTablaUsersSinFiltro() throws IOException, ClassNotFoundException {
        String ip = "localhost";
        String user = "admin";
        String password = "admin";
        String codigo = "A12354";
        String columna = "dni";
        String palabra = codigo+",0,1,"+columna+",0,0";
        String palabraAbuscar = "12345678A";
        String nombreTabla = "1";//tabla users

        
        MainForm mainForm = new MainForm();
        mainForm.setCodigo(codigo); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);
        
        try{
            ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);

            socket = mainForm.getSocket();
            lector = new BufferedReader(new InputStreamReader( MainForm.getSocket().getInputStream()));
            escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));

            Select.mostrarTablaSinFiltro(columna, palabra, palabraAbuscar,nombreTabla, escriptor, codigo, socket, textArea);

            // Verifica que el resultado esperado se encuentra en el textArea
            assertTrue(textArea.getText().contains("DNI: 12345678A"));
        }catch(IOException | ClassNotFoundException ex){
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Logout.logout(usuarioFormPestañas);
        }      
    }

    /**
     * Prueba para mostrar la tabla de jornada sin filtro.
     *
     * @throws IOException Excepcion de tipo entrada salida.
     * @throws ClassNotFoundException Lanzada cuando la clase especificada no ha sido encontrada.
     */
    @Test
    public void testMostrarTablaJornadaSinFiltro() throws IOException, ClassNotFoundException {
        String ip = "localhost";
        String user = "admin";
        String password = "admin";
        String codigo = "A12354";
        String columna = "dni";
        
        String palabraAbuscar = "12345678A";
        String nombreTabla = "3";//tabla jornada
        String palabra = codigo+",0,"+nombreTabla+","+columna+",0,0";

        
        MainForm mainForm = new MainForm();
        mainForm.setCodigo(codigo); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);
        
        try{
            ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);

            socket = mainForm.getSocket();
            lector = new BufferedReader(new InputStreamReader( MainForm.getSocket().getInputStream()));
            escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));

            Select.mostrarTablaSinFiltro(columna, palabra, palabraAbuscar,nombreTabla, escriptor, codigo, socket, textArea);

            // Verifica que el resultado esperado se encuentra en el textArea
            assertTrue(textArea.getText().contains("Dni: 12345679B"));
        }catch(IOException | ClassNotFoundException ex){
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Logout.logout(usuarioFormPestañas);
        }      
    }
    
    /**
     * Prueba para mostrar la tabla de usuarios con un filtro.
     *
     * @throws IOException Excepcion de tipo entrada salida.
     * @throws ClassNotFoundException Lanzada cuando la clase especificada no ha sido encontrada.
     */
    @Test
    public void testMostrarTablaUsers1Filtro() throws IOException, ClassNotFoundException {
        String ip = "localhost";
        String user = "admin";
        String password = "admin";
        String nombreTabla = "1";//tabla users
        String columna = "dni";
        String codigoUser = "A12354";
        String palabraAbuscar = "12345678A";
        String palabra = codigoUser+",0,"+nombreTabla+","+columna+","+palabraAbuscar+",0";     

        MainForm mainForm = new MainForm();
        mainForm.setCodigo(codigoUser); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);

        try{
            ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);

            socket = mainForm.getSocket();
            escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));

            Select.mostrarTablaUsers1Filtro(nombreTabla, columna, codigoUser, palabra, palabraAbuscar, escriptor, textArea, null, socket);

            // Verificamos que el resultado esperado se encuentra en el textArea
            assertTrue(textArea.getText().contains(palabraAbuscar));
        }catch(IOException | ClassNotFoundException ex){
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Logout.logout(usuarioFormPestañas);
        }  
    }
    
    /**
     * Prueba para mostrar la tabla de empleados con un filtro.
     *
     * @throws IOException Excepcion de tipo entrada salida.
     * @throws ClassNotFoundException Lanzada cuando la clase especificada no ha sido encontrada.
     */
    @Test
    public void testMostrarTablaEmpleados1Filtro() throws IOException, ClassNotFoundException {
        String ip = "localhost";
        String user = "admin";
        String password = "admin";
        String nombreTabla = "0";//tabla empleados
        String columna = "dni";
        String codigoUser = "A12354";
        String palabraAbuscar = "12345678A";
        String palabra = codigoUser+",0,"+nombreTabla+","+columna+","+palabraAbuscar+",0";
        

        MainForm mainForm = new MainForm();
        mainForm.setCodigo(codigoUser); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);

        try{
            ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);

            socket = mainForm.getSocket();
            escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));

            Select.mostrarTablaEmpleados1Filtro(nombreTabla, columna, codigoUser, palabra, palabraAbuscar, escriptor, textArea, null, socket);

            // Verificamos que el resultado esperado se encuentra en el textArea
            assertTrue(textArea.getText().contains(palabraAbuscar));
            
        }catch(IOException | ClassNotFoundException ex){
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Logout.logout(usuarioFormPestañas);
        }  
    }
    
    /**
     * Prueba para mostrar la tabla de empresas con un filtro.
     *
     * @throws IOException Excepcion de tipo entrada salida.
     * @throws ClassNotFoundException Lanzada cuando la clase especificada no ha sido encontrada.
     */
    @Test
    public void testMostrarTablaEmpresa1Filtro() throws IOException, ClassNotFoundException {
        String ip = "localhost";
        String user = "admin";
        String password = "admin";
        String nombreTabla = "2";//tabla empresa
        String columna = "nom";
        String codigo = "A12354";
        String palabraAbuscar = "HREntrada";
        String palabra = codigo+",0,"+nombreTabla+","+columna+","+palabraAbuscar+",0";
        

        MainForm mainForm = new MainForm();
        mainForm.setCodigo(codigo); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);

        try{
            ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);

            socket = mainForm.getSocket();
            escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));

            Select.mostrarTablaEmpresa1Filtro(nombreTabla, columna, codigo, palabra, palabraAbuscar, escriptor, textArea, null, socket);

            // Verificamos que el resultado esperado se encuentra en el textArea
            assertTrue(textArea.getText().contains(palabraAbuscar));
        
        }catch(IOException | ClassNotFoundException ex){
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Logout.logout(usuarioFormPestañas);
        }  
    }
    
    /**
     * Prueba para mostrar la tabla de jornada con un filtro.
     *
     * @throws IOException Excepcion de tipo entrada salida.
     * @throws ClassNotFoundException Lanzada cuando la clase especificada no ha sido encontrada.
     */
    @Test
    public void testMostrarTablaJornada1Filtro() throws IOException, ClassNotFoundException {
        String ip = "localhost";
        String user = "admin";
        String password = "admin";
        String nombreTabla = "3";//tabla jornada
        String columna = "dni";
        String codigo = "A12354";
        String palabraAbuscar = "12345679B";
        String palabra = codigo+",0,"+nombreTabla+","+columna+","+palabraAbuscar+",0";     

        MainForm mainForm = new MainForm();
        mainForm.setCodigo(codigo); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);

        try{
            ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);

            socket = mainForm.getSocket();
            escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));

            Select.mostrarTablaJornada1Filtro(nombreTabla, columna, codigo, palabra, palabraAbuscar, escriptor, textArea, null, socket);

            // Verificamos que el resultado esperado se encuentra en el textArea
            assertTrue(textArea.getText().contains(palabraAbuscar));
        
        }catch(IOException | ClassNotFoundException ex){
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Logout.logout(usuarioFormPestañas);
        } 
    }
    
    /**
     * Prueba para mostrar la tabla de jornada con un filtro y texto erróneo.
     *
     * @throws IOException Excepcion de tipo entrada salida.
     * @throws ClassNotFoundException Lanzada cuando la clase especificada no ha sido encontrada.
     */
    @Test
    public void testMostrarTablaJornada1FiltroTextoErroneo() throws IOException, ClassNotFoundException {
        String ip = "localhost";
        String user = "admin";
        String password = "admin";
        String nombreTabla = "3";//tabla jornada
        String columna = "dni";
        String codigo = "A12354";
        String palabraAbuscar = "12345679B";
        String palabra = codigo+",0,"+nombreTabla+","+columna+","+palabraAbuscar+",0";     

        MainForm mainForm = new MainForm();
        mainForm.setCodigo(codigo); // Código de usuario válido para la prueba
        JTextField jTextFieldIPServidor = new JTextField(ip);
        JTextField jTextFieldUsuario = new JTextField(user);
        JPasswordField jPasswordField = new JPasswordField(password);
        FormVentanasUsuario usuarioFormPestañas = new FormVentanasUsuario(user,password);

        try{
            ConexionSocket.conexionSocket(mainForm, usuarioFormPestañas, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);

            socket = mainForm.getSocket();
            escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));

            Select.mostrarTablaJornada1Filtro(nombreTabla, columna, codigo, palabra, palabraAbuscar, escriptor, textArea, null, socket);

            // Verificamos que el resultado esperado se encuentra en el textArea
            assertTrue(!textArea.getText().contains(palabraAbuscar));
        
        }catch(IOException | ClassNotFoundException ex){
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Logout.logout(usuarioFormPestañas);
        } 
    }
}
