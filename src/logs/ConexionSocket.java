package logs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import vistas.FormVentanasUsuario;
import vistas.MainForm;

/**
 *Clase que gestionara métodos de conexion via socket
 * 
 * @author Antonio Javier Company Rodriguez
 */
public class ConexionSocket {
    
    /**
    * Método que comprueba que el login és correcto o no y si lo es comprueba que tipo de usuario
    * se ha logeado para crear un título o otro al crear un objeto del tipo FormVentanasUsuario
    *
    * @param mainForm objeto del tipo MainForm
    * @param usuarioFormPestañas objeto del tipo FormVentanasUsuario que usaremos para
    * crear la ventana del menú principal de la aplicación una vez iniciada sesión 
    * @param jTextFieldIPServidor campo de texto para introducir la ip del server
    * @param jTextFieldUsuario campo de texto para introducir usario
    * @param jPasswordField campo de texto para introducir la contraseña
    */
    
    public static void conexionSocket(MainForm mainForm,FormVentanasUsuario usuarioFormPestañas, JTextField jTextFieldIPServidor, JTextField jTextFieldUsuario, JPasswordField jPasswordField){
        boolean salir = false;

        try {
            //IMPLEMENTA
            MainForm.setSocket(new Socket(jTextFieldIPServidor.getText(), 8888));

            BufferedReader lector = new BufferedReader(new InputStreamReader( MainForm.getSocket().getInputStream()));//flujo lectura del server
            BufferedWriter escriptor = new BufferedWriter(new OutputStreamWriter( MainForm.getSocket().getOutputStream()));//flujo envio al server
            
            ObjectInputStream perEnt;
            
            String codigo = "0";
            
            ///Llegeix del servidor el mensaje de bienvenida, y la pregunta que nos hace ///           
            String mensajeServer = lector.readLine();
            
            //ahora escribimos en servidor , enviandole la palabra a buscar 
            String palabra = jTextFieldUsuario.getText();
            String pass = jPasswordField.getText();
            String number = null;

            String login = palabra + "," + pass;

            escriptor.write(login);
            escriptor.newLine();
            escriptor.flush();

            //leemos la respuesta
            mensajeServer = lector.readLine();

            if (mensajeServer.equalsIgnoreCase("-1")) {
                JOptionPane.showMessageDialog(null, "El login es erroneo");//vemos el código
                salir = true;
                lector.close();
                escriptor.close();
                
                //MainForm.setSocket(null);
                MainForm.getSocket().close();
            } else if (mensajeServer.equalsIgnoreCase("-2")) {
                JOptionPane.showMessageDialog(null,".El usuario ya esta conectado");//vemos el código
                salir = true;
                lector.close();
                escriptor.close();
                
                //MainForm.setSocket(null);
                MainForm.getSocket().close();
            } else {
               codigo = mensajeServer;

                usuarioFormPestañas = new FormVentanasUsuario(codigo, jTextFieldUsuario.getText());
                usuarioFormPestañas.setjLabel1(codigo);
                usuarioFormPestañas.setjUserCode1(codigo);
                usuarioFormPestañas.setjUserCode2(codigo);
                usuarioFormPestañas.setjUserCode3(codigo);
                // Comprueba si la primera letra es una "u" o una "a"
                if (mensajeServer.charAt(0) == 'U'){                  
                    usuarioFormPestañas.setTitle("Logeado como usuario: "+jTextFieldUsuario.getText());                
                }else if(mensajeServer.charAt(0) == 'A'){
                    usuarioFormPestañas.setTitle("Logeado como administrador: "+jTextFieldUsuario.getText());                            
                } 
                usuarioFormPestañas.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                usuarioFormPestañas.setVisible(true);
                
                mainForm.setVisible(false);
                

            }
        }catch (ConnectException e) {
           Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, e);
           MainForm.setSocket(null);
        } catch (UnknownHostException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            MainForm.setSocket(null);
        } catch (IOException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            MainForm.setSocket(null);
        }catch (Exception e) {   
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, e);
            MainForm.setSocket(null);
        }
    }
}
