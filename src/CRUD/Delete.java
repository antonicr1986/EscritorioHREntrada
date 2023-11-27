package CRUD;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import javax.swing.JTextArea;
import modelo.Empresa;
import modelo.Empleados;
import modelo.Jornada;
import modelo.Users;

/**
 *
 * @author Antonio Company Rodriguez
 */
public class Delete {
    /**
    * Método que gestiona y muestra por pantalla cuando la acción que ejecutamos 
    * és añadir una empresa.
    *
    * @param frase array de string que contiene todos los valores para el delete
    * @param palabra string que se envia al server para el insert
    * @param escriptor BufferedWriter de contacto con el server
    * @param perEnt tipo de objeto ObjectInputStream recibido
    * @param socket Objeto tipo Socket para la conexión
    * @param jTextAreaDelete textArea en el que mostraremos los datos al usuario por la aplicación gráfica
    * 
    * @throws java.io.IOException
    * @throws java.lang.ClassNotFoundException
    * 
    */
    
    public static void deleteEmpresas( String []frase, String palabra, BufferedWriter escriptor, ObjectInputStream perEnt,Socket socket, JTextArea jTextAreaDelete)throws IOException, ClassNotFoundException{
        String codigoUserRecibido = frase[0];
        String crud = frase[1];
        String nombreTabla = frase[2];
        String nom = frase[3];
        String datoNom = frase[4];
        String orden = frase[5];

        jTextAreaDelete.append("\ncodigoUserRecibido: " + codigoUserRecibido);
        jTextAreaDelete.append("crud: " + crud);
        jTextAreaDelete.append("nombreTabla: " + nombreTabla);
        jTextAreaDelete.append("nom: " + nom);
        jTextAreaDelete.append("datoNom: " + datoNom);
        jTextAreaDelete.append("orden: " + orden);
        jTextAreaDelete.append(
                "____________________________________________________________________");

        palabra = codigoUserRecibido + "," + crud + "," + nombreTabla + "," + nom + "," + datoNom + "," + orden;

        if (codigoUserRecibido.equals("")) {
            codigoUserRecibido = "0";
        }

        escriptor.write(palabra);
        escriptor.newLine();
        escriptor.flush();
        jTextAreaDelete.append("\nEl usuario con codigo: " + codigoUserRecibido
                + "\nenvia los datos siguiente: \n" + palabra);

        perEnt = new ObjectInputStream(socket.getInputStream());
        Object receivedData = perEnt.readObject();

        if (receivedData instanceof List) {
            List<Empresa> deleteEmpresa = (List<Empresa>) receivedData;
            System.out.println("\nJornada modificada correctamente:");
            Empresa empresa = deleteEmpresa.get(0);
            jTextAreaDelete.append("\nNombre: " + datoNom);
            jTextAreaDelete.append("Direccion: " + empresa.getAddress());
            jTextAreaDelete.append("Telefonon: " + empresa.getTelephon());
            jTextAreaDelete.append("____________________________________________________________________");
            perEnt.getObjectInputFilter();
        } else if (receivedData instanceof String) {
            String errorMessage = (String) receivedData;
            jTextAreaDelete.append(errorMessage);
        } else {
            jTextAreaDelete.append("\nDatos no esperados recibidos del servidor");
        }
    }   
}
