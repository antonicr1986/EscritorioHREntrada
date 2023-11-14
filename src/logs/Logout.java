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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import vistas.MainForm;
import vistas.FormVentanasUsuario;

/**
 *
 * @author Antonio Company Rodriguez
 * 
 * Clase que de momento no utilizamos pero que quiero utilizar cuando deje de usar su
 * método logout() de la clase MainForm.java
 * 
 */

public class Logout {
    private String palabra ="";
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
    * Descripción del método.
    *
    * @param formUsuario objeto del tipo FormVentanasUsuario que usaremos para
    * cerrar la ventana que tengamos abierta de este tipo de Forms 
    * 
    */
    private void logout(FormVentanasUsuario formUsuario){
        try {
            //IMPLEMENTA
            Socket socket = MainForm.getSocket();

            if (socket != null && socket.isConnected()) {
                // Obtener flujos de entrada y salida.
                BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                // Aquí enviamos la señal de "logout" al servidor.
                String logoutSignal = "exit";
                escriptor.write(logoutSignal);
                escriptor.newLine();
                escriptor.flush();

                // Resto de la lógica de cierre de sesión.
                lector.close();
                escriptor.close();
                socket.close();

                palabra = "exit";

                //Cerramos ventana actual y abrimos la principal
                formUsuario.dispose();

                MainForm mainForm = new MainForm();
                mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainForm.setLocation(mainForm.getX(),mainForm.getY()); 
                mainForm.setVisible(true);     
                mainForm.setPalabra(palabra);

                //JOptionPane.showMessageDialog(null,"Palabra: "+ palabra+"\nPalabra: "+mainForm.getPalabra());     
            }
            else{
                 JOptionPane.showMessageDialog(null,"Problemas con la conexión al socket.");     
            }
         } catch (IOException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
