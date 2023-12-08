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
 *Clase que gestionara m�todos de conexion via socket
 * 
 * @author Antonio Javier Company Rodriguez
 */
public class ConexionSocket {
    
    /**
    * M�todo que comprueba que el login �s correcto o no y si lo es comprueba que tipo de usuario
    * se ha logeado para crear un t�tulo o otro al crear un objeto del tipo FormVentanasUsuario
    *
    * @param mainForm objeto del tipo MainForm
    * @param usuarioFormPesta�as objeto del tipo FormVentanasUsuario que usaremos para
    * crear la ventana del men� principal de la aplicaci�n una vez iniciada sesi�n 
    * @param jTextFieldIPServidor campo de texto para introducir la ip del server
    * @param jTextFieldUsuario campo de texto para introducir usario
    * @param jPasswordField campo de texto para introducir la contrase�a
    */
    
    public static void conexionSocket(MainForm mainForm,FormVentanasUsuario usuarioFormPesta�as, JTextField jTextFieldIPServidor, JTextField jTextFieldUsuario, JPasswordField jPasswordField){
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
                JOptionPane.showMessageDialog(null, "El login es erroneo");//vemos el c�digo
                salir = true;
                lector.close();
                escriptor.close();
                
                //MainForm.setSocket(null);
                MainForm.getSocket().close();
            } else if (mensajeServer.equalsIgnoreCase("-2")) {
                JOptionPane.showMessageDialog(null,".El usuario ya esta conectado");//vemos el c�digo
                salir = true;
                lector.close();
                escriptor.close();
                
                //MainForm.setSocket(null);
                MainForm.getSocket().close();
            } else {
               codigo = mensajeServer;

                usuarioFormPesta�as = new FormVentanasUsuario(codigo, jTextFieldUsuario.getText());
                usuarioFormPesta�as.setjLabel1(codigo);
                usuarioFormPesta�as.setjUserCode1(codigo);
                usuarioFormPesta�as.setjUserCode2(codigo);
                usuarioFormPesta�as.setjUserCode3(codigo);
                // Comprueba si la primera letra es una "u" o una "a"
                if (mensajeServer.charAt(0) == 'U'){                  
                    usuarioFormPesta�as.setTitle("Logeado como usuario: "+jTextFieldUsuario.getText());                
                }else if(mensajeServer.charAt(0) == 'A'){
                    usuarioFormPesta�as.setTitle("Logeado como administrador: "+jTextFieldUsuario.getText());                            
                } 
                usuarioFormPesta�as.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                usuarioFormPesta�as.setVisible(true);
                
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
