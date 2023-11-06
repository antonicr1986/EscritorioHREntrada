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
 * @author antonio minero
 */
public class Logout {
    String palabra ="";
    BufferedReader lector;
    BufferedWriter escriptor;
    ObjectInputStream perEnt;
    String codigoUserRecibido;
    
    String codigo;
    String crud;
    String nombreTabla;
    String columna;
    String orden;
    

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
    
    private void logout(FormVentanasUsuario formUsuario){
        try {
            //IMPLEMENTA
            Socket socket = MainForm.socket;

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
