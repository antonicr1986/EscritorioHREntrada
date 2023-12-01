package CRUD;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import modelo.Empresa;
import modelo.Jornada;

/**
 *
 * @author Antonio Company Rodriguez
 */

public class Update {
    
    /**
    * Método que gestiona el update de una empresa
    *
    * @param NomApellido array de string que contiene todos los valores para el update
    * @param palabra string que se envia al server para el insert
    * @param escriptor BufferedWriter de contacto con el server
    * @param perEnt tipo de objeto ObjectInputStream recibido
    * @param socket Objeto tipo Socket para la conexión
    * @param jTextAreaUpdate Area de texto donde mostraremos informacion al usuario al hacer o intentar un update
    * 
    * @throws java.lang.ClassNotFoundException
    *
    */       
    
    public static void updateEmpresa(String NomApellido[],String palabra, BufferedWriter escriptor, ObjectInputStream perEnt,Socket socket, JTextArea jTextAreaUpdate) throws ClassNotFoundException{
        try {
            String codigoUserRecibido = NomApellido[0];
            String crud = NomApellido[1];
            String nombreTabla = NomApellido[2];
            String nomNuevo = NomApellido[3];
            String datoNomnuevo = NomApellido[4];
            String addressNuevo = NomApellido[5];
            String datoAddressNuevo = NomApellido[6];
            String telephonNuevo = NomApellido[7];
            String datoTelephonNuevo = NomApellido[8];
            String nom = NomApellido[9];
            String datoNom = NomApellido[10];
            String orden = NomApellido[11];
            jTextAreaUpdate.append("\ncodigoUserRecibido: " + codigoUserRecibido +"\n");
            jTextAreaUpdate.append("crud: " + crud +"\n");
            jTextAreaUpdate.append("nombreTabla: " + nombreTabla +"\n");
            jTextAreaUpdate.append("nomNuevo: " + nomNuevo +"\n");
            jTextAreaUpdate.append("datoNomnuevo: " + datoNomnuevo +"\n");
            jTextAreaUpdate.append("address: " + addressNuevo +"\n");
            jTextAreaUpdate.append("datoAddress: " + datoAddressNuevo +"\n");
            jTextAreaUpdate.append("telephon: " + telephonNuevo +"\n");
            jTextAreaUpdate.append("datoTelephon: " + datoTelephonNuevo +"\n");
            jTextAreaUpdate.append("nom: " + nom +"\n");
            jTextAreaUpdate.append("datoNom: " + datoNom +"\n");
            jTextAreaUpdate.append("orden: " + orden +"\n");
            jTextAreaUpdate.append(
                    "____________________________________________________________________\n");
            
            palabra = codigoUserRecibido + "," + crud + "," + nombreTabla + "," + nomNuevo + ","
                                        + datoNomnuevo + "," + addressNuevo + "," + datoAddressNuevo + "," + telephonNuevo + "," + datoTelephonNuevo + "," + nom + "," + datoNom + "," + orden;

            if (codigoUserRecibido.equals("")) {
                codigoUserRecibido = "0";
            }

            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            jTextAreaUpdate.append("\nEl usuario con codigo: " + codigoUserRecibido
                    + "\nenvia los datos siguiente: \n" + palabra);

            perEnt = new ObjectInputStream(socket.getInputStream());
            Object receivedData = perEnt.readObject();

            if (receivedData instanceof List) {
                List<Empresa> updateEmpresa = (List<Empresa>) receivedData;
                jTextAreaUpdate.append("\nNombre de empresa modificado correctamente:");
                jTextAreaUpdate.append("\nNombre empresa: " + datoNomnuevo + "\n"
                        + "Direccion: " + datoAddressNuevo + "\n"
                        + "Telefono: " + datoTelephonNuevo+ "\n");
                jTextAreaUpdate.append("____________________________________________________________________\n");
                perEnt.getObjectInputFilter();
            } else if (receivedData instanceof String) {
                String errorMessage = (String) receivedData;
                jTextAreaUpdate.append(errorMessage+ "\n");
            } else {
                jTextAreaUpdate.append("\nDatos inesperados recibidos del servidor");
            }
        } catch (IOException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
    * Método que gestiona el update de un user.
    *
    * @param insertEmpresas array de string que contiene todos los valores para el update
    * @param palabra string que se envia al server para el insert
    * @param escriptor BufferedWriter de contacto con el server
    * @param perEnt tipo de objeto ObjectInputStream recibido
    * @param socket Objeto tipo Socket para la conexión
    * @param jTextAreaUpdate textArea en el que mostraremos los datos al usuario por la aplicación gráfica
    * 
    * @throws java.lang.ClassNotFoundException
    */       
    
    public static void updateUser(String []insertEmpresas,String palabra, BufferedWriter escriptor, ObjectInputStream perEnt,Socket socket, JTextArea jTextAreaUpdate) throws ClassNotFoundException{
        try {
            String codigoUserRecibido = insertEmpresas[0];
            String crud = insertEmpresas[1];
            String nombreTabla = insertEmpresas[2];
            String passNuevo = insertEmpresas[3];
            String datoPassNuevo = insertEmpresas[4];
            String numtipeNuevo = insertEmpresas[5];
            String datoNumtipeNuevo = insertEmpresas[6];
            String login = insertEmpresas[7];
            String datoLogin = insertEmpresas[8];
            String orden = insertEmpresas[9];
            jTextAreaUpdate.append("\ncodigoUserRecibido: " + codigoUserRecibido +"\n");
            jTextAreaUpdate.append("crud: " + crud +"\n");
            jTextAreaUpdate.append("nombreTabla: " + nombreTabla +"\n");
            jTextAreaUpdate.append("passNuevo: " + passNuevo +"\n");
            jTextAreaUpdate.append("datoPassNuevo: " + datoPassNuevo +"\n");
            jTextAreaUpdate.append("numtipeNuev: " + numtipeNuevo +"\n");
            jTextAreaUpdate.append("datoNumtipeNuevo: " + datoNumtipeNuevo +"\n");
            jTextAreaUpdate.append("login: " + login +"\n");
            jTextAreaUpdate.append("datoLogin: " + datoLogin +"\n");
            jTextAreaUpdate.append("orden: " + orden +"\n");
            jTextAreaUpdate.append(
                    "____________________________________________________________________\n");
            
            palabra = codigoUserRecibido + "," + crud + "," + nombreTabla + ","
                    + passNuevo + "," + datoPassNuevo + ","
                    + numtipeNuevo + "," + datoNumtipeNuevo + ","
                    + login + "," + datoLogin + "," + orden;
            
            if (codigoUserRecibido.equals("")) {
                codigoUserRecibido = "0";
            }
            
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            jTextAreaUpdate.append("\nEl usuario con codigo: " + codigoUserRecibido
                    + "\nenvia los datos siguientes: " + palabra + "\n");
            
            perEnt = new ObjectInputStream(socket.getInputStream());
            Object receivedData = perEnt.readObject();
            
            if (receivedData instanceof List) {
                jTextAreaUpdate.append("\nUsuario modificado correctamente:");
                jTextAreaUpdate.append("\nLogin: " + datoLogin);
                jTextAreaUpdate.append("\nPass: " + datoPassNuevo);
                jTextAreaUpdate.append("\nNumTipe: " + datoNumtipeNuevo);
                jTextAreaUpdate.append("\n____________________________________________________________________\n");
                perEnt.getObjectInputFilter();
            } else if (receivedData instanceof String) {
                String errorMessage = (String) receivedData;
                jTextAreaUpdate.append(errorMessage+ "\n");
            } else {
               jTextAreaUpdate.append("\nDatos inesperados recibidos del servidor");
            }
        } catch (IOException ex) {
            jTextAreaUpdate.append("\n____________________________________________________________________\n"
                    + "Problema con entrada y salida de sockets.\n");
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
    * Método que gestiona el update de una empresa
    *
    * @param updateEmpleado array de string que contiene todos los valores para el update
    * @param palabra string que se envia al server para el insert
    * @param escriptor BufferedWriter de contacto con el server
    * @param perEnt tipo de objeto ObjectInputStream recibido
    * @param socket Objeto tipo Socket para la conexión
    * @param jTextAreaUpdate textArea en el que mostraremos los datos al usuario por la aplicación gráfica
    * 
    * @throws java.lang.ClassNotFoundException
    */       
    
    public static void updateEmpleado(String updateEmpleado[],String palabra, BufferedWriter escriptor, ObjectInputStream perEnt,Socket socket, JTextArea jTextAreaUpdate) throws ClassNotFoundException{
        try {
            String codigoUserRecibido = updateEmpleado[0];
            String crud = updateEmpleado[1];
            String nombreTabla = updateEmpleado[2];
            String dniNuevo = updateEmpleado[3];
            String datoDniNuevo = updateEmpleado[4];
            String nomNuevo = updateEmpleado[5];
            String datoNomNuevo = updateEmpleado[6];
            String apellidoNuevo = updateEmpleado[7];
            String datoApellidoNuevo = updateEmpleado[8];
            String nomempresaNuevo = updateEmpleado[9];
            String datoNomempresaNuevo = updateEmpleado[10];
            String departamentNuevo = updateEmpleado[11];
            String datoDepartamentNuevo = updateEmpleado[12];
            String codicardNuevo = updateEmpleado[13];
            String datoCodicardNuevo = updateEmpleado[14];
            String mailNuevo = updateEmpleado[15];
            String datoMailNuevo = updateEmpleado[16];
            String telephonNuevo = updateEmpleado[17];
            String datoTelephonNuevo = updateEmpleado[18];
            String dni = updateEmpleado[19];
            String datoDni = updateEmpleado[20];
            String orden = updateEmpleado[21];
            
            jTextAreaUpdate.append("\ncodigoUserRecibido: " + codigoUserRecibido +"\n");
            jTextAreaUpdate.append("crud: " + crud +"\n");
            jTextAreaUpdate.append("nombreTabla: " + nombreTabla +"\n");
            jTextAreaUpdate.append("dniNuevo: " + dniNuevo +"\n");
            jTextAreaUpdate.append("datoDniNuevo: " + datoDniNuevo +"\n");
            jTextAreaUpdate.append("nomNuevo: " + nomNuevo +"\n");
            jTextAreaUpdate.append("datoNomNuevo: " + datoNomNuevo +"\n");
            jTextAreaUpdate.append("apellidoNuevo: " + apellidoNuevo +"\n");
            jTextAreaUpdate.append("datoApellidoNuevo: " + datoApellidoNuevo +"\n");
            jTextAreaUpdate.append("nomempresaNuevo: " + nomempresaNuevo +"\n");
            jTextAreaUpdate.append("datoNomempresaNuevo: " + datoNomempresaNuevo +"\n");
            jTextAreaUpdate.append("departamentNuevo: " + departamentNuevo +"\n");
            jTextAreaUpdate.append("datoDepartament: " + datoDepartamentNuevo +"\n");
            jTextAreaUpdate.append("codicardNuevo: " + codicardNuevo +"\n");
            jTextAreaUpdate.append("datoCodicardNuevo: " + datoCodicardNuevo +"\n");
            jTextAreaUpdate.append("mailNuevo: " + mailNuevo +"\n");
            jTextAreaUpdate.append("datoMailNuevo: " + datoMailNuevo +"\n");
            jTextAreaUpdate.append("telephon: " + telephonNuevo +"\n");
            jTextAreaUpdate.append("datoTelephon: " + datoTelephonNuevo +"\n");
            jTextAreaUpdate.append("dni: " + dni +"\n");
            jTextAreaUpdate.append("datoDni: " + datoDni +"\n");
            jTextAreaUpdate.append("orden: " + orden +"\n");
            jTextAreaUpdate.append(
                    "____________________________________________________________________\n");
            
            palabra = codigoUserRecibido + "," + crud + "," + nombreTabla + ","
                                        + dniNuevo + "," + datoDniNuevo + ","
                                        + nomNuevo + "," + datoNomNuevo + ","
                                        + apellidoNuevo + "," + datoApellidoNuevo + ","
                                        + nomempresaNuevo + "," + datoNomempresaNuevo + ","
                                        + departamentNuevo + "," + datoDepartamentNuevo + ","
                                        + codicardNuevo + "," + datoCodicardNuevo + ","
                                        + mailNuevo + "," + datoMailNuevo + ","
                                        + telephonNuevo + "," + datoTelephonNuevo + ","
                                        + dni + "," + datoDni + "," + orden;

            if (codigoUserRecibido.equals("")) {
                codigoUserRecibido = "0";
            }

            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            System.out.println("\nEl usuario con codigo: " + codigoUserRecibido
                    + "\nenvia los datos siguiente: \n" + palabra);

            perEnt = new ObjectInputStream(socket.getInputStream());
            Object receivedData = perEnt.readObject();

            if (receivedData instanceof List) {
                List<Empresa> updateEmpresa = (List<Empresa>) receivedData;
                jTextAreaUpdate.append("\nEmpleado modificado correctamente:");
                jTextAreaUpdate.append("\nDni: " + datoDniNuevo +"\n");
                jTextAreaUpdate.append("Nombre: " + datoNomNuevo +"\n");
                jTextAreaUpdate.append("Apellido: " + datoApellidoNuevo +"\n");
                jTextAreaUpdate.append("Nombre empresa: " + datoNomempresaNuevo +"\n");
                jTextAreaUpdate.append("Departament: " + datoDepartamentNuevo +"\n");
                jTextAreaUpdate.append("Codigo tarjeta: " + datoCodicardNuevo +"\n");
                jTextAreaUpdate.append("Mail: " + datoMailNuevo +"\n");
                jTextAreaUpdate.append("Telefono: " + datoTelephonNuevo +"\n");
                jTextAreaUpdate.append("____________________________________________________________________\n");
                perEnt.getObjectInputFilter();
            } else if (receivedData instanceof String) {
                String errorMessage = (String) receivedData;
                jTextAreaUpdate.append(errorMessage+ "\n");
            } else {
                jTextAreaUpdate.append("\nDatos inesperados recibidos del servidor");
            }
        } catch (IOException ex) {
            jTextAreaUpdate.append("\n_________________________________________________________\n");
            jTextAreaUpdate.append("Problema con entrada y salida de sockets.\n");
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
    * Método que gestiona el update de una empresa
    *
    * @param frase array de string que contiene todos los valores para el update
    * @param palabra string que se envia al server para el insert
    * @param escriptor BufferedWriter de contacto con el server
    * @param perEnt tipo de objeto ObjectInputStream recibido
    * @param socket Objeto tipo Socket para la conexión
    * @param jTextAreaUpdate textArea en el que mostraremos los datos al usuario por la aplicación gráfica
    * 
    * @throws java.lang.ClassNotFoundException
    */       
    
    public static void updateJornada(String[] frase,String palabra, BufferedWriter escriptor, ObjectInputStream perEnt,Socket socket, JTextArea jTextAreaUpdate) throws ClassNotFoundException{
        try {
            String codigoUserRecibido = frase[0];
            String crud = frase[1];
            String nombreTabla = frase[2];
            String dni = frase[3];
            String datoDni = frase[4];
            String orden = frase[5];
            jTextAreaUpdate.append("\ncodigoUserRecibido: " + codigoUserRecibido);
            jTextAreaUpdate.append("\ncrud: " + crud);
            jTextAreaUpdate.append("\nnombreTabla: " + nombreTabla);
            jTextAreaUpdate.append("\ndni: " + dni);
            jTextAreaUpdate.append("\ndatoDni: " + datoDni);
            jTextAreaUpdate.append("\norden: " + orden);
            jTextAreaUpdate.append(
                    "\n____________________________________________________________________");

            palabra = codigoUserRecibido + "," + crud + "," + nombreTabla + "," + dni + "," + datoDni + "," + orden;

            if (codigoUserRecibido.equals("")) {
                codigoUserRecibido = "0";
            }

            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            jTextAreaUpdate.append("\nEl usuario con codigo: " + codigoUserRecibido
                    + "\nenvia los datos siguientes: " + palabra +"\n");

            perEnt = new ObjectInputStream(socket.getInputStream());
            Object receivedData = perEnt.readObject();

            if (receivedData instanceof List) {
                List<Jornada> updateJornada = (List<Jornada>) receivedData;
                System.out.println("\nJornada modificada correctamente:");
                Jornada jornada = updateJornada.get(0);
                jTextAreaUpdate.append("\nDni: " + datoDni);
                jTextAreaUpdate.append("\nNombre: " + jornada.getNom());
                jTextAreaUpdate.append("\nApellido: " + jornada.getApellido());
                jTextAreaUpdate.append("\nHora entrada: " + jornada.getHoraentrada());
                jTextAreaUpdate.append("\nHora salida: " + jornada.getHorasalida());
                jTextAreaUpdate.append("\nTotal: " + jornada.getTotal());
                jTextAreaUpdate.append("\nFecha: " + jornada.getFecha());
                jTextAreaUpdate.append("\nCodigo tarjeta: " + jornada.getCodicard());
                jTextAreaUpdate.append("\n____________________________________________________________________\n");
                perEnt.getObjectInputFilter();
            } else if (receivedData instanceof String) {
                String errorMessage = (String) receivedData;
                jTextAreaUpdate.append(errorMessage+ "\n");
            } else {
                jTextAreaUpdate.append("\nDatos inesperados recibidos del servidor");
            }
        } catch (IOException ex) {
            jTextAreaUpdate.append("\n_________________________________________________________\n"
                    + "Problema con entrada y salida de sockets.\n");
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
