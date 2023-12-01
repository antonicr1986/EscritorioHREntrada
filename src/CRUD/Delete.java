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
    * @throws IOException Gestión de excepciones de entrada/salida. 
    * @throws ClassNotFoundException para gestionar error de clase no incluida en el classpath, problemas con el nombre
    * de la clase o con la versión de java.
    * 
    */
    
    public static void deleteEmpleados( String []frase, String palabra, BufferedWriter escriptor, ObjectInputStream perEnt,Socket socket, JTextArea jTextAreaDelete)throws IOException, ClassNotFoundException{
        String codigoUserRecibido = frase[0];
        String crud = frase[1];
        String nombreTabla = frase[2];
        String dni = frase[3];
        String datoDni = frase[4];
        String orden = frase[5];

        jTextAreaDelete.append("\ncodigoUserRecibido: " + codigoUserRecibido);
        jTextAreaDelete.append("\ncrud: " + crud);
        jTextAreaDelete.append("\nnombreTabla: " + nombreTabla);
        jTextAreaDelete.append("\ndni: " + dni);
        jTextAreaDelete.append("\ndatoDni: " + datoDni);
        jTextAreaDelete.append("\norden: " + orden);
        jTextAreaDelete.append(
                "\n____________________________________________________________________");

        palabra = codigoUserRecibido + "," + crud + "," + nombreTabla + "," + dni + "," + datoDni + "," + orden;

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
            List<Empleados> deleteEmpleados = (List<Empleados>) receivedData;
            jTextAreaDelete.append("\nEmpleado borrado correctamente:");
            Empleados empleado = deleteEmpleados.get(0);
            jTextAreaDelete.append("\nDni: " + datoDni);
            jTextAreaDelete.append("\nNom: " + empleado.getNom());
            jTextAreaDelete.append("\nApellido: " + empleado.getApellido());
            jTextAreaDelete.append("\nNom empresa: " + empleado.getNomempresa());
            jTextAreaDelete.append("\nDepartament: " + empleado.getDepartament());
            jTextAreaDelete.append("\nCodicard: " + empleado.getCodicard());
            jTextAreaDelete.append("\nMail: " + empleado.getMail());
            jTextAreaDelete.append("\nTelefono: " + empleado.getTelephon());
            jTextAreaDelete.append("\n____________________________________________________________________");
            
            perEnt.getObjectInputFilter();
        } else if (receivedData instanceof String) {
            String errorMessage = (String) receivedData;
            jTextAreaDelete.append(errorMessage+ "\n____________________________________________________________________\n");
        } else {
            jTextAreaDelete.append("\nDatos no esperados recibidos del servidor");
        }
    }
    
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
    * @throws IOException Gestión de excepciones de entrada/salida. 
    * @throws ClassNotFoundException para gestionar error de clase no incluida en el classpath, problemas con el nombre
    * de la clase o con la versión de java.
    * 
    */
    
    public static void deleteUsers( String []frase, String palabra, BufferedWriter escriptor, ObjectInputStream perEnt,Socket socket, JTextArea jTextAreaDelete)throws IOException, ClassNotFoundException{
        String codigoUserRecibido = frase[0];
        String crud = frase[1];
        String nombreTabla = frase[2];
        String dni = frase[3];
        String datoDni = frase[4];
        String orden = frase[5];

        jTextAreaDelete.append("\ncodigoUserRecibido: " + codigoUserRecibido);
        jTextAreaDelete.append("\ncrud: " + crud);
        jTextAreaDelete.append("\nnombreTabla: " + nombreTabla);
        jTextAreaDelete.append("\ndni: " + dni);
        jTextAreaDelete.append("\ndatoDni: " + datoDni);
        jTextAreaDelete.append("\norden: " + orden);
        jTextAreaDelete.append(
                "\n____________________________________________________________________");

        palabra = codigoUserRecibido + "," + crud + "," + nombreTabla + "," + dni + "," + datoDni + "," + orden;

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
            List<Users> deleteUsers = (List<Users>) receivedData;
            jTextAreaDelete.append("\nUser borrado correctamente:");
            Users user = deleteUsers.get(0);
            jTextAreaDelete.append("\nDni: " + datoDni);
            jTextAreaDelete.append("\nLogin: " + user.getLogin());
            jTextAreaDelete.append("\nNumTipe: " + user.getNumtipe());
            jTextAreaDelete.append("\n____________________________________________________________________");
            
            perEnt.getObjectInputFilter();
        } else if (receivedData instanceof String) {
            String errorMessage = (String) receivedData;
            jTextAreaDelete.append(errorMessage+ "\n____________________________________________________________________\n");
        } else {
            jTextAreaDelete.append("\nDatos no esperados recibidos del servidor");
        }
    }
    
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
    * @throws IOException Gestión de excepciones de entrada/salida. 
    * @throws ClassNotFoundException para gestionar error de clase no incluida en el classpath, problemas con el nombre
    * de la clase o con la versión de java.
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
        jTextAreaDelete.append("\ncrud: " + crud);
        jTextAreaDelete.append("\nnombreTabla: " + nombreTabla);
        jTextAreaDelete.append("\nnom: " + nom);
        jTextAreaDelete.append("\ndatoNom: " + datoNom);
        jTextAreaDelete.append("\norden: " + orden);
        jTextAreaDelete.append(
                "\n____________________________________________________________________");

        palabra = codigoUserRecibido + "," + crud + "," + nombreTabla + "," + nom + "," + datoNom + "," + orden;

        if (codigoUserRecibido.equals("")) {
            codigoUserRecibido = "0";
        }

        escriptor.write(palabra);
        escriptor.newLine();
        escriptor.flush();
        jTextAreaDelete.append("\nEl usuario con codigo: " + codigoUserRecibido
                + "\nenvia los datos siguientes: \n" + palabra +"\n");

        perEnt = new ObjectInputStream(socket.getInputStream());
        Object receivedData = perEnt.readObject();

        if (receivedData instanceof List) {
            List<Empresa> deleteEmpresa = (List<Empresa>) receivedData;
            jTextAreaDelete.append("\nEmpresa borrada correctamente:");
            Empresa empresa = deleteEmpresa.get(0);
            jTextAreaDelete.append("\nNombre: " + datoNom);
            jTextAreaDelete.append("\nDireccion: " + empresa.getAddress());
            jTextAreaDelete.append("\nTelefono: " + empresa.getTelephon());
            jTextAreaDelete.append("\n____________________________________________________________________");
            perEnt.getObjectInputFilter();
        } else if (receivedData instanceof String) {
            String errorMessage = (String) receivedData;
            jTextAreaDelete.append(errorMessage+ "\n____________________________________________________________________\n");
        } else {
            jTextAreaDelete.append("\nDatos no esperados recibidos del servidor");
        }
    }  
    
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
    * @throws IOException Gestión de excepciones de entrada/salida. 
    * @throws ClassNotFoundException para gestionar error de clase no incluida en el classpath, problemas con el nombre
    * de la clase o con la versión de java.
    * 
    */
    
    public static void deleteJornada( String []frase, String palabra, BufferedWriter escriptor, ObjectInputStream perEnt,Socket socket, JTextArea jTextAreaDelete)throws IOException, ClassNotFoundException{
        String codigoUserRecibido = frase[0];
        String crud = frase[1];
        String nombreTabla = frase[2];
        String dni = frase[3];
        String datoDni = frase[4];
        String fecha = frase [5];
        String datoFecha = frase [6];
        String orden = frase[7];

        jTextAreaDelete.append("\ncodigoUserRecibido: " + codigoUserRecibido);
        jTextAreaDelete.append("\ncrud: " + crud);
        jTextAreaDelete.append("\nnombreTabla: " + nombreTabla);
        jTextAreaDelete.append("\ndni: " + dni);
        jTextAreaDelete.append("\ndatoDni: " + datoDni);
        jTextAreaDelete.append("\nfecha: " + fecha);
        jTextAreaDelete.append("\ndatoFecha: " + datoFecha);
        jTextAreaDelete.append("\norden: " + orden);
        jTextAreaDelete.append(
                "\n____________________________________________________________________");

        palabra = codigoUserRecibido + "," + crud + "," + nombreTabla + "," + dni + "," + datoDni + "," + fecha + "," + datoFecha + "," + orden;

        if (codigoUserRecibido.equals("")) {
            codigoUserRecibido = "0";
        }

        escriptor.write(palabra);
        escriptor.newLine();
        escriptor.flush();
        jTextAreaDelete.append("\nEl usuario con codigo: " + codigoUserRecibido
                + "\nenvia los datos siguientes: " + palabra + "\n");

        perEnt = new ObjectInputStream(socket.getInputStream());
        Object receivedData = perEnt.readObject();

        if (receivedData instanceof List) {
            List<Jornada> deleteJornada = (List<Jornada>) receivedData;
            jTextAreaDelete.append("\nJornada borrada correctamente:");
            Jornada jornada = deleteJornada.get(0);
            jTextAreaDelete.append("\nDni: " + datoDni);
            jTextAreaDelete.append("\nNom: " + jornada.getNom());
            jTextAreaDelete.append("\nApellido: " + jornada.getApellido());
            jTextAreaDelete.append("\nCodicard: " + jornada.getCodicard());
            jTextAreaDelete.append("\nHoraEntrada: " + jornada.getHoraentrada());
            jTextAreaDelete.append("\nHoraSalida: " + jornada.getHorasalida());
            jTextAreaDelete.append("\nTotal: " + jornada.getTotal());
            jTextAreaDelete.append("\nFecha: " + jornada.getFecha());
            jTextAreaDelete.append("\n____________________________________________________________________\n");
            
            perEnt.getObjectInputFilter();
        } else if (receivedData instanceof String) {
            String errorMessage = (String) receivedData;
            jTextAreaDelete.append(errorMessage+ "\n____________________________________________________________________\n");
        } else {
            jTextAreaDelete.append("\nDatos no esperados recibidos del servidor");
        }
    }
}
