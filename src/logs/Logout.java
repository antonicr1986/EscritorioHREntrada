package logs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import vistas.MainForm;
import vistas.FormVentanasUsuario;

/**
 *
 * @author Antonio Company Rodriguez
 * 
 * Clase que de momento no utilizamos pero que quiero utilizar cuando deje de usar su
 * m�todo logout() de la clase MainForm.java
 * 
 */

public class Logout {
    private static String palabra ="";
    private BufferedReader lector;
    private BufferedWriter escriptor;
    private ObjectInputStream perEnt;
    private String codigoUserRecibido;
    
    private String codigo;
    private String crud;
    private String nombreTabla;
    private String columna;
    private String orden;
    

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
    
    /**
    * M�todo que obtiene el socket de Mainform y lo asigna a una variable tipo socket
    * y luego entabla un dialogo con el servidor para hacer logout de la sesi�n, 
    * cierra la ventana abierta actualmente y abre una nueva tipo MainForm.
    *
    * @param formUsuario objeto del tipo FormVentanasUsuario que usaremos para
    * cerrar la ventana que tengamos abierta de este tipo de Forms 
    * 
    */  
    public static void logout(FormVentanasUsuario formUsuario){
        try {        
            Socket socket = MainForm.getSocket();   
            SSLSocket sslSocket = MainForm.getSslSocket(); // Aseg�rate de tener un m�todo similar para obtener el socket SSL.          
            BufferedReader lector =null;
            BufferedWriter escriptor = null;
            if (socket != null && socket.isConnected()) {
                 //JOptionPane.showMessageDialog(null, 
                   //          "if socket normal");
                // Obtener flujos de entrada y salida.
                lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //JOptionPane.showMessageDialog(null, 
                  //           "despues asignar lector");
                escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            }else if (sslSocket != null && sslSocket.isConnected()) {
                // Obtener flujos de entrada y salida.
                lector = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
                escriptor = new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
            }else{
                 JOptionPane.showMessageDialog(null,"Problemas con la conexi�n al socket.");  
                 return;
            }
                // Aqu� enviamos la se�al de "logout" al servidor.
                String logoutSignal = "exit";
                escriptor.write(logoutSignal);
                escriptor.newLine();
                escriptor.flush();

                
                // Resto de la l�gica de cierre de sesi�n.
                lector.close();
                escriptor.close();
                socket.close();
                
                MainForm.setSocket(null);//***Testing

                palabra = "exit";

                //Cerramos ventana actual y abrimos la principal
                formUsuario.dispose();

                MainForm mainForm = new MainForm();
                mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainForm.setLocation(mainForm.getX(),mainForm.getY()); 
                mainForm.setVisible(true);     
                mainForm.setPalabra(palabra);

                //JOptionPane.showMessageDialog(null,"Palabra: "+ palabra+"\nPalabra: "+mainForm.getPalabra());               
         } catch (IOException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
