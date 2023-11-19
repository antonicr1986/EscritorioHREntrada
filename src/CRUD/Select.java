package CRUD;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import modelo.Empleados;
import modelo.Empresa;
import modelo.Jornada;
import modelo.Users;
import vistas.FormVentanasUsuario;

/**
 *
 * @author Antonio Company Rodriguez
 */
public class Select {
    
    /**
    * Método que gestiona y muestra por pantalla cuando la acción que ejecutamos 
    * és buscar
    *
    * @param columna string contiene el valor de la columna en la cual buscaremos
    * @param palabra string cotiene el valor de la palabra a buscar
    * @param palabraAbuscar string contiene los valores separados por comas de la operacion a realizar
    * 
    * @throws IOException Descripción de la excepción lanzada. 
    */
    
    public static void operacionesConSelect ( String columna, String palabra, String palabraAbuscar, String nombreTabla,
            BufferedWriter escriptor, String codigoUserRecibido, Socket socket, JTextArea jTextAreaSelect ) throws IOException, ClassNotFoundException{
        ObjectInputStream perEnt = null;
        
        if (nombreTabla.equals("0")&& !columna.equals("0")){
            JOptionPane.showMessageDialog(null, 
                             "if tabla empleados 1 filtro");
             mostrarTablaEmpleados1Filtro(nombreTabla, columna, codigoUserRecibido, palabra, 
                    palabraAbuscar, escriptor, jTextAreaSelect, perEnt,socket);
            
        }else if (nombreTabla.equals("1") && !columna.equals("0")){
            JOptionPane.showMessageDialog(null, 
                             "if tabla users 1 filtro");
             mostrarTablaUsers1Filtro(nombreTabla, columna, codigoUserRecibido, palabra, 
                    palabraAbuscar, escriptor, jTextAreaSelect, perEnt,socket);
            
        }else if (nombreTabla.equals("2")&& !columna.equals("0")){
            JOptionPane.showMessageDialog(null, 
                             "if tabla empresa 1 filtro");
            mostrarTablaEmpresa1Filtro(nombreTabla, columna, codigoUserRecibido, palabra, 
                    palabraAbuscar, escriptor, jTextAreaSelect, perEnt,socket);
            
        }else if (nombreTabla.equals("3")&& !columna.equals("0")){
            JOptionPane.showMessageDialog(null, 
                             "if tabla jornada 1 filtro");
            mostrarTablaJornada1Filtro(nombreTabla, columna, codigoUserRecibido, palabra, 
                    palabraAbuscar, escriptor, jTextAreaSelect, perEnt,socket);
        
        } else if (!nombreTabla.equals(null) && columna.equals("0")) {
            JOptionPane.showMessageDialog(null, 
                             "if mostrar tabla sin filtros");
            mostrarTablaSinFiltro(columna, palabra, palabraAbuscar, nombreTabla, escriptor
                    ,codigoUserRecibido,socket,jTextAreaSelect);
        }
    }
    
    /**
    * Método que gestiona las búsquedas de la tabla jornada cuando esta sea
    * una búsqueda hecha con un solo filtro de columna.
    * 
    */
    
     public static void mostrarTablaEmpleados1Filtro(String nombreTabla, String columna, String codigoUserRecibido, String palabra, 
            String palabraAbuscar, BufferedWriter escriptor, JTextArea jTextAreaSelect, ObjectInputStream perEnt,Socket socket) throws IOException, ClassNotFoundException{
        if (nombreTabla.equals("0") && columna.equals("dni")) { //tabla: empleados      filtrar por: dni
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            perEnt = new ObjectInputStream(socket.getInputStream());
            Object receivedData = perEnt.readObject();

            if (receivedData instanceof List) {
                List<Empleados> listaPersonasdni = (List<Empleados>) receivedData;
                for (Empleados empleado : listaPersonasdni) {
                    jTextAreaSelect.append("DNI: " + empleado.getDni() + "\n" 
                            + "Nombre: " + empleado.getNom() + "\n" 
                            + "Apellido: " + empleado.getApellido() + "\n" 
                            + "Nombre empresa: " + empleado.getNomempresa() + "\n" 
                            + "Departamento: " + empleado.getDepartament() + "\n" 
                            + "Codigo tarjeta: "  + empleado.getCodicard() + "\n" 
                            + "Mail: " + empleado.getMail() + "\n" 
                            + "Telefono: " + empleado.getTelephon() + "\n");
                   jTextAreaSelect.append(
                            "____________________________________________________________________");
                }
                perEnt.getObjectInputFilter();
            } else if (receivedData instanceof String) {
                String errorMessage = (String) receivedData;
                jTextAreaSelect.append(errorMessage);
            } else {
                jTextAreaSelect.append("Datos inesperados recibidos del servidor");
            }
        }else if (nombreTabla.equals("0") && columna.equals("nom")) {//tabla: empleados      filtrar por: nom
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            perEnt = new ObjectInputStream(socket.getInputStream());
            Object receivedData = perEnt.readObject();

            if (receivedData instanceof List) {
                List<Empleados> listaTotalEmpleadosNom = (List<Empleados>) receivedData;

                for (Empleados empleado : listaTotalEmpleadosNom) {
                    jTextAreaSelect.append("\nDni: " + empleado.getDni() + "\n" + "Nombre: "
                            + empleado.getNom() + "\n" + "Apellido: "
                            + empleado.getApellido() + "\n" + "Nombre empresa: "
                            + empleado.getNomempresa() + "\n" + "Departamento: "
                            + empleado.getDepartament() + "\n" + "Codigo tarjeta: "
                            + empleado.getCodicard() + "\n" + "Mail: " + empleado.getMail()
                            + "\n" + "Telefono: " + empleado.getTelephon() + "\n");
                    jTextAreaSelect.append(
                            "____________________________________________________________________");
                }
                perEnt.getObjectInputFilter();
            } else if (receivedData instanceof String) {
                String errorMessage = (String) receivedData;
                jTextAreaSelect.append(errorMessage);
            } else {
                jTextAreaSelect.append("Datos inesperados recibidos del servidor");
            }

        } else if (nombreTabla.equals("0") && columna.equals("apellido")) {//tabla: empleados      filtrar por: apelido
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            perEnt = new ObjectInputStream(socket.getInputStream());
            Object receivedData = perEnt.readObject();

            if (receivedData instanceof List) {
                List<Empleados> listaTotalEmpleadosApellido = (List<Empleados>) receivedData;

                for (Empleados empleado : listaTotalEmpleadosApellido) {
                    jTextAreaSelect.append("\nDni: " + empleado.getDni() + "\n" + "Nombre: "
                            + empleado.getNom() + "\n" + "Apellido: "
                            + empleado.getApellido() + "\n" + "Nombre empresa: "
                            + empleado.getNomempresa() + "\n" + "Departamento: "
                            + empleado.getDepartament() + "\n" + "Codigo tarjeta: "
                            + empleado.getCodicard() + "\n" + "Mail: " + empleado.getMail()
                            + "\n" + "Telefono: " + empleado.getTelephon() + "\n");
                    jTextAreaSelect.append(
                            "____________________________________________________________________");
                }
                perEnt.getObjectInputFilter();
            } else if (receivedData instanceof String) {
                String errorMessage = (String) receivedData;
                jTextAreaSelect.append(errorMessage);
            } else {
                jTextAreaSelect.append("Datos inesperados recibidos del servidor");
            }

        }else if (nombreTabla.equals("0") && columna.equals("nomempresa")) { //tabla: empleados      filtrar por: nomempresa
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            perEnt = new ObjectInputStream(socket.getInputStream());
            Object receivedData = perEnt.readObject();

            if (receivedData instanceof List) {
                List<Empleados> listaTotalEmpleadosNomEmpresa = (List<Empleados>) receivedData;

                for (Empleados empleado : listaTotalEmpleadosNomEmpresa) {
                    jTextAreaSelect.append("Dni: " + empleado.getDni() + "\n" + "Nombre: "
                            + empleado.getNom() + "\n" + "Apellido: "
                            + empleado.getApellido() + "\n" + "Nombre empresa: "
                            + empleado.getNomempresa() + "\n" + "Departamento: "
                            + empleado.getDepartament() + "\n" + "Codigo tarjeta: "
                            + empleado.getCodicard() + "\n" + "Mail: " + empleado.getMail()
                            + "\n" + "Telefono: " + empleado.getTelephon() + "\n");
                    jTextAreaSelect.append("____________________________________________________________________");
                }
                perEnt.getObjectInputFilter();
            } else if (receivedData instanceof String) {
                String errorMessage = (String) receivedData;
                jTextAreaSelect.append(errorMessage);
            } else {
                jTextAreaSelect.append("Datos inesperados recibidos del servidor");
            }
            
        } else if (nombreTabla.equals("0") && columna.equals("departament")) { //tabla: empleados      filtrar por: departament
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            perEnt = new ObjectInputStream(socket.getInputStream());
            Object receivedData = perEnt.readObject();

            if (receivedData instanceof List) {
                List<Empleados> listaTotalEmpleadosDepart = (List<Empleados>) receivedData;

                for (Empleados empleado : listaTotalEmpleadosDepart) {
                     jTextAreaSelect.append("Dni: " + empleado.getDni() + "\n" + "Nombre: "
                            + empleado.getNom() + "\n" + "Apellido: "
                            + empleado.getApellido() + "\n" + "Nombre empresa: "
                            + empleado.getNomempresa() + "\n" + "Departamento: "
                            + empleado.getDepartament() + "\n" + "Codigo tarjeta: "
                            + empleado.getCodicard() + "\n" + "Mail: " + empleado.getMail()
                            + "\n" + "Telefono: " + empleado.getTelephon() + "\n");
                     jTextAreaSelect.append("____________________________________________________________________");
                }
                perEnt.getObjectInputFilter();
            } else if (receivedData instanceof String) {
                String errorMessage = (String) receivedData;
                 jTextAreaSelect.append(errorMessage);
            } else {
                 jTextAreaSelect.append("Datos inesperados recibidos del servidor");
            }
        } else if (nombreTabla.equals("0") && columna.equals("codicard")) { //tabla: empleados      filtrar por: codicard
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            List<Empleados> listaTotalEmpleadosCodiCard = new ArrayList<>();

            perEnt = new ObjectInputStream(socket.getInputStream());
            listaTotalEmpleadosCodiCard = (ArrayList) perEnt.readObject();

            for (int i = 0; i < listaTotalEmpleadosCodiCard.size(); i++) {
                String codicard = String.valueOf(listaTotalEmpleadosCodiCard.get(i).getCodicard());
                if (columna.equals("codicard") && palabraAbuscar.equals(codicard)) {
                    jTextAreaSelect.append("____________________________________________________________________"+ "\n");
                    jTextAreaSelect.append("Dni: " + listaTotalEmpleadosCodiCard.get(i).getDni() + "\n"
                            + "Nombre: " + listaTotalEmpleadosCodiCard.get(i).getNom() + "\n"
                            + "Apellido: " + listaTotalEmpleadosCodiCard.get(i).getApellido() + "\n"
                            + "Nombre empresa: " + listaTotalEmpleadosCodiCard.get(i).getNomempresa() + "\n"
                            + "Departamento: " + listaTotalEmpleadosCodiCard.get(i).getDepartament() + "\n"
                            + "Codigo tarjeta: " + listaTotalEmpleadosCodiCard.get(i).getCodicard() + "\n"
                            + "Mail: " + listaTotalEmpleadosCodiCard.get(i).getMail() + "\n"
                            + "Teléfono: " + listaTotalEmpleadosCodiCard.get(i).getTelephon() + "\n"
                            +"____________________________________________________________________" + "\n");
                }
            }
            perEnt.getObjectInputFilter();
            
        } else if (nombreTabla.equals("0") && columna.equals("mail")) { //tabla: empleados      filtrar por: mail
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

             perEnt = new ObjectInputStream(socket.getInputStream());
            Object receivedData = perEnt.readObject();

            if (receivedData instanceof List) {
                List<Empleados> listaTotalEmpleadosMail = (List<Empleados>) receivedData;

                for (Empleados empleado : listaTotalEmpleadosMail) {
                    jTextAreaSelect.append("Dni: " + empleado.getDni() + "\n" + "Nombre: "
                            + empleado.getNom() + "\n" + "Apellido: "
                            + empleado.getApellido() + "\n" + "Nombre empresa: "
                            + empleado.getNomempresa() + "\n" + "Departamento: "
                            + empleado.getDepartament() + "\n" + "Codigo tarjeta: "
                            + empleado.getCodicard() + "\n" + "Mail: " + empleado.getMail()
                            + "\n" + "Telefono: " + empleado.getTelephon() + "\n");
                    jTextAreaSelect.append(
                            "____________________________________________________________________");
                }
                perEnt.getObjectInputFilter();
            } else if (receivedData instanceof String) {
                String errorMessage = (String) receivedData;
                jTextAreaSelect.append(errorMessage);
            } else {
                jTextAreaSelect.append("Datos inesperados recibidos del servidor");
            }
        } else if (nombreTabla.equals("0") && columna.equals("telephon")) { //tabla: empleados      filtrar por: telephon
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            List<Empleados> listaTotalEmpleadosTelf = new ArrayList<>();

            perEnt = new ObjectInputStream(socket.getInputStream());
            listaTotalEmpleadosTelf = (ArrayList) perEnt.readObject();

            for (int i = 0; i < listaTotalEmpleadosTelf.size(); i++) {
                String telephon = String.valueOf(listaTotalEmpleadosTelf.get(i).getTelephon());
                if (columna.equals("telephon") && palabraAbuscar.equals(telephon)) {
                    jTextAreaSelect.append("____________________________________________________________________"+ "\n");
                    jTextAreaSelect.append("Dni: " + listaTotalEmpleadosTelf.get(i).getDni() + "\n"
                            + "Nombre: " + listaTotalEmpleadosTelf.get(i).getNom() + "\n"
                            + "Apellido: " + listaTotalEmpleadosTelf.get(i).getApellido() + "\n"
                            + "Nombre empresa: " + listaTotalEmpleadosTelf.get(i).getNomempresa() + "\n"
                            + "Departamento: " + listaTotalEmpleadosTelf.get(i).getDepartament() + "\n"
                            + "Codigo tarjeta: " + listaTotalEmpleadosTelf.get(i).getCodicard() + "\n"
                            + "Mail: " + listaTotalEmpleadosTelf.get(i).getMail() + "\n"
                            + "Teléfono: " + listaTotalEmpleadosTelf.get(i).getTelephon() + "\n"
                            +"____________________________________________________________________" + "\n");
                }
            }
            perEnt.getObjectInputFilter();
        }
     }
     
    /**
    * Método que gestiona las búsquedas de la tabla jornada cuando esta sea
    * una búsqueda hecha con un solo filtro de columna.
    * 
    */
    
    public static void mostrarTablaUsers1Filtro(String nombreTabla, String columna, String codigoUserRecibido, String palabra, 
            String palabraAbuscar, BufferedWriter escriptor, JTextArea jTextAreaSelect, ObjectInputStream perEnt,Socket socket) throws IOException, ClassNotFoundException{
        if (nombreTabla.equals("1") && columna.equals("dni")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            perEnt = new ObjectInputStream(socket.getInputStream());
            Object receivedData = perEnt.readObject();

            if (receivedData instanceof List) {
                List<Users> listaToUsersDni = (List<Users>) receivedData;
                for (Users user : listaToUsersDni) {
                    jTextAreaSelect.append("Login: " + user.getLogin() + "\n" + "Password: "
                            + user.getPass() + "\n" + "Tipo de user: " + user.getNumtipe()
                            + "\n" + "DNI: " + user.getDni());
                    jTextAreaSelect.append("____________________________________________________________________");
                }
                perEnt.getObjectInputFilter();
            } else if (receivedData instanceof String) {
                String errorMessage = (String) receivedData;
                jTextAreaSelect.append(errorMessage);
            } else {
                jTextAreaSelect.append("Datos inesperados recibidos del servidor");
            }
            
        } else if (nombreTabla.equals("1") && columna.equals("login")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            perEnt = new ObjectInputStream(socket.getInputStream());
            Object receivedData = perEnt.readObject();

            if (receivedData instanceof List) {
                List<Users> listaTotalUsersLogin = (List<Users>) receivedData;

                for (Users user : listaTotalUsersLogin) {
                    jTextAreaSelect.append("Login: " + user.getLogin() + "\n" + "Password: "
                            + user.getPass() + "\n" + "Tipo de user: " + user.getNumtipe()
                            + "\n" + "DNI: " + user.getDni());
                    jTextAreaSelect.append("____________________________________________________________________");
                }
                perEnt.getObjectInputFilter();
            } else if (receivedData instanceof String) {
                String errorMessage = (String) receivedData;
                jTextAreaSelect.append(errorMessage);
            } else {
                jTextAreaSelect.append("Datos inesperados recibidos del servidor");
            }
        } else if (nombreTabla.equals("1") && columna.equals("numtipe")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            perEnt = new ObjectInputStream(socket.getInputStream());
            Object receivedData = perEnt.readObject();

            if (receivedData instanceof List) {
                List<Users> listaTotalUsersTipe = (List<Users>) receivedData;

                for (Users user : listaTotalUsersTipe) {
                     jTextAreaSelect.append("Login: " + user.getLogin() + "\n" + "Password: "
                            + user.getPass() + "\n" + "Tipo de user: " + user.getNumtipe()
                            + "\n" + "DNI: " + user.getDni());
                     jTextAreaSelect.append(
                            "____________________________________________________________________");
                }
                perEnt.getObjectInputFilter();
            } else if (receivedData instanceof String) {
                String errorMessage = (String) receivedData;
                 jTextAreaSelect.append(errorMessage);
            } else {
                 jTextAreaSelect.append("Datos inesperados recibidos del servidor");
            }
        }
    }
    
    /**
    * Método que gestiona las búsquedas de la tabla jornada cuando esta sea
    * una búsqueda hecha con un solo filtro de columna.
    * 
    */
    
    public static void mostrarTablaEmpresa1Filtro(String nombreTabla, String columna, String codigoUserRecibido, String palabra, 
            String palabraAbuscar, BufferedWriter escriptor, JTextArea jTextAreaSelect, ObjectInputStream perEnt,Socket socket) throws IOException, ClassNotFoundException{
        if (nombreTabla.equals("2") && columna.equals("nom")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            
            perEnt = new ObjectInputStream(socket.getInputStream());
            Object receivedData = perEnt.readObject();

            if (receivedData instanceof List) {
                List<Empresa> listaEmpresasNom = (List<Empresa>) receivedData;
                for (Empresa empresa : listaEmpresasNom) {
                    jTextAreaSelect.append(
                            "____________________________________________________________________");
                    jTextAreaSelect.append("Nombre empresa: " + empresa.getNom() + "\n"
                            + "Direccion: " + empresa.getAddress() + "\n" + "Telefono: "
                            + empresa.getTelephon());
                    jTextAreaSelect.append(
                            "____________________________________________________________________");
                }
                perEnt.getObjectInputFilter();
            } else if (receivedData instanceof String) {
                String errorMessage = (String) receivedData;
                jTextAreaSelect.append(errorMessage);
            } else {
                jTextAreaSelect.append("Datos inesperados recibidos del servidor");
            }
            
        } else if (nombreTabla.equals("2") && columna.equals("address")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            
            perEnt = new ObjectInputStream(socket.getInputStream());
            Object receivedData = perEnt.readObject();

            if (receivedData instanceof List) {
                List<Empresa> listaEmpresasAddress = (List<Empresa>) receivedData;
                for (Empresa empresa : listaEmpresasAddress) {
                    jTextAreaSelect.append(
                            "____________________________________________________________________");
                    jTextAreaSelect.append("Nombre empresa: " + empresa.getNom() + "\n"
                            + "Direcciï¿½n: " + empresa.getAddress() + "\n" + "Telefono: "
                            + empresa.getTelephon());
                    jTextAreaSelect.append(
                            "____________________________________________________________________");
                }
                perEnt.getObjectInputFilter();
            } else if (receivedData instanceof String) {
                String errorMessage = (String) receivedData;
                jTextAreaSelect.append(errorMessage);
            } else {
                jTextAreaSelect.append("Datos inesperados recibidos del servidor");
            }
            
        } else if (nombreTabla.equals("2") && columna.equals("telephon")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
           
            List<Empresa> listaEmpresasTelepho = new ArrayList<>();

            perEnt = new ObjectInputStream(socket.getInputStream());
            listaEmpresasTelepho = (ArrayList) perEnt.readObject();

            for (int i = 0; i < listaEmpresasTelepho.size(); i++) {
                String telephon = String.valueOf(listaEmpresasTelepho.get(i).getTelephon());
                if (columna.equals("telephon") && palabraAbuscar.equals(telephon)) {
                    jTextAreaSelect.append("____________________________________________________________________" + "\n"
                        +"Nombre empresa: " + listaEmpresasTelepho.get(i).getNom() + "\n"
                        + "Dirección: " + listaEmpresasTelepho.get(i).getAddress() + "\n"
                        + "Teléfono: " + listaEmpresasTelepho.get(i).getTelephon() + "\n"
                        +"____________________________________________________________________" + "\n");
                }
            }
            perEnt.getObjectInputFilter();
        }
    }
    
    /**
    * Método que gestiona las búsquedas de la tabla jornada cuando esta sea
    * una búsqueda hecha con un solo filtro de columna.
    * 
    */
    
    public static void mostrarTablaJornada1Filtro(String nombreTabla, String columna, String codigoUserRecibido, String palabra, 
            String palabraAbuscar, BufferedWriter escriptor, JTextArea jTextAreaSelect, ObjectInputStream perEnt,Socket socket) throws IOException, ClassNotFoundException{
        if (nombreTabla.equals("3") && columna.equals("dni")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
           
            perEnt = new ObjectInputStream(socket.getInputStream());
            Object receivedData = perEnt.readObject();

            if (receivedData instanceof List) {

                List<Jornada> listaToJornadaDni = (List<Jornada>) receivedData;
                for (Jornada jornada : listaToJornadaDni) {
                     jTextAreaSelect.append("Dni: " + jornada.getDni() + "\n" + "Nombre: "
                            + jornada.getNom() + "\n" + "Apellido: " + jornada.getApellido()
                            + "\n" + "Codigo tarjeta: " + jornada.getCodicard() + "\n"
                            + "Hora entrada: " + jornada.getHoraentrada() + "\n"
                            + "Hora salida: " + jornada.getHorasalida() + "\n" + "Total: "
                            + jornada.getTotal() + "\n" + "Fecha: " + jornada.getFecha());
                     jTextAreaSelect.append(
                            "____________________________________________________________________");
                }
                perEnt.getObjectInputFilter();
            } else if (receivedData instanceof String) {
                String errorMessage = (String) receivedData;
                 jTextAreaSelect.append(errorMessage);
            } else {
                 jTextAreaSelect.append("Datos inesperados recibidos del servidor");
            }
            
        } else if (nombreTabla.equals("3") && columna.equals("nom")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();

            perEnt = new ObjectInputStream(socket.getInputStream());
            Object receivedData = perEnt.readObject();

            if (receivedData instanceof List) {

                List<Jornada> listaTotalJornadaNom = (List<Jornada>) receivedData;
                for (Jornada jornada : listaTotalJornadaNom) {
                    jTextAreaSelect.append("\nDni: " + jornada.getDni() + "\n" + "Nombre: "
                            + jornada.getNom() + "\n" + "Apellido: " + jornada.getApellido()
                            + "\n" + "Codigo tarjeta: " + jornada.getCodicard() + "\n"
                            + "Hora entrada: " + jornada.getHoraentrada() + "\n"
                            + "Hora salida: " + jornada.getHorasalida() + "\n" 
                            + "Total: " + jornada.getTotal() + "\n" 
                            + "Fecha: " + jornada.getFecha() + "\n");
                    jTextAreaSelect.append(
                            "____________________________________________________________________");
                }
                perEnt.getObjectInputFilter();
            } else if (receivedData instanceof String) {
                String errorMessage = (String) receivedData;
                jTextAreaSelect.append(errorMessage);
            } else {
                jTextAreaSelect.append("\nDatos inesperados recibidos del servidor");
            }

        } else if (nombreTabla.equals("3") && columna.equals("apellido")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            
            perEnt = new ObjectInputStream(socket.getInputStream());
            Object receivedData = perEnt.readObject();

            if (receivedData instanceof List) {

                List<Jornada> listaTotalJornadaAapellido = (List<Jornada>) receivedData;
                for (Jornada jornada : listaTotalJornadaAapellido) {
                    jTextAreaSelect.append("\nDni: " + jornada.getDni() + "\n" + "Nombre: "
                            + jornada.getNom() + "\n" + "Apellido: " + jornada.getApellido()
                            + "\n" + "Codigo tarjeta: " + jornada.getCodicard() + "\n"
                            + "Hora entrada: " + jornada.getHoraentrada() + "\n"
                            + "Hora salida: " + jornada.getHorasalida() + "\n" 
                            + "Total: " + jornada.getTotal() + "\n" 
                            + "Fecha: " + jornada.getFecha() + "\n");
                    jTextAreaSelect.append(
                            "____________________________________________________________________");
                }
                perEnt.getObjectInputFilter();
            } else if (receivedData instanceof String) {
                String errorMessage = (String) receivedData;
                jTextAreaSelect.append(errorMessage);
            } else {
                jTextAreaSelect.append("\nDatos inesperados recibidos del servidor");
            }

        }else if (nombreTabla.equals("3") && columna.equals("codicard")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
           
            List<Jornada> listaJornadaCodiCard = new ArrayList<>();

            perEnt = new ObjectInputStream(socket.getInputStream());
            listaJornadaCodiCard = (ArrayList) perEnt.readObject();

            for (int i = 0; i < listaJornadaCodiCard.size(); i++) {
                String codicard = String.valueOf(listaJornadaCodiCard.get(i).getCodicard());
                if (columna.equals("codicard") && palabraAbuscar.equals(codicard)) {
                    jTextAreaSelect.append("\nDni: " + listaJornadaCodiCard.get(i).getDni() + "\n"
                            + "Nombre: " + listaJornadaCodiCard.get(i).getNom() + "\n"
                            + "Apellido: " + listaJornadaCodiCard.get(i).getApellido() + "\n"
                            + "Codigo tarjeta: " + listaJornadaCodiCard.get(i).getCodicard() + "\n"
                            + "Hora entrada: " + listaJornadaCodiCard.get(i).getHoraentrada() + "\n"
                            + "Hora salida: " + listaJornadaCodiCard.get(i).getHorasalida() + "\n"
                            + "Total: " + listaJornadaCodiCard.get(i).getTotal() + "\n"
                            + "Fecha: " + listaJornadaCodiCard.get(i).getFecha() + "\n"
                            +"____________________________________________________________________" + "\n");
                }
            }
            perEnt.getObjectInputFilter();
        } else if (nombreTabla.equals("3") && columna.equals("fecha")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            
            perEnt = new ObjectInputStream(socket.getInputStream());
            Object receivedData = perEnt.readObject();

            if (receivedData instanceof List) {

                List<Jornada> listaTotalJornadaFecha = (List<Jornada>) receivedData;
                for (Jornada jornada : listaTotalJornadaFecha) {
                    jTextAreaSelect.append("Dni: " + jornada.getDni() + "\n" + "Nombre: "
                            + jornada.getNom() + "\n" + "Apellido: " + jornada.getApellido()
                            + "\n" + "Codigo tarjeta: " + jornada.getCodicard() + "\n"
                            + "Hora entrada: " + jornada.getHoraentrada() + "\n"
                            + "Hora salida: " + jornada.getHorasalida() + "\n" + "Total: "
                            + jornada.getTotal() + "Fecha: " + jornada.getFecha());
                    jTextAreaSelect.append(
                            "____________________________________________________________________");
                }
                perEnt.getObjectInputFilter();
            } else if (receivedData instanceof String) {
                String errorMessage = (String) receivedData;
                jTextAreaSelect.append(errorMessage);
            } else {
                jTextAreaSelect.append("Datos inesperados recibidos del servidor");
            }
        }
    }
    
    /**
    * Método que gestiona las búsquedas cuando el valor de columna sea igual a 0 o nulo
    * por lo que nos devuelve todos los valores de la tabla que estemos consultando
    * 
    */
    
    public static void mostrarTablaSinFiltro (String columna, String palabra, String palabraAbuscar, String nombreTabla, BufferedWriter escriptor, String codigoUserRecibido, Socket socket, JTextArea jTextAreaSelect) throws IOException, ClassNotFoundException{
        ObjectInputStream perEnt=null;
        
        switch (nombreTabla) {
            case "0": //Tabla empleados
                //ahora si enviamos al server los datos que queremos, sin errores
                escriptor.write(palabra);
                escriptor.newLine();
                escriptor.flush();
                
                List<Empleados> listaPersonas = new ArrayList<>();

                perEnt = new ObjectInputStream(socket.getInputStream());
                listaPersonas = (ArrayList) perEnt.readObject();
                jTextAreaSelect.append("____________________________________________________________________"+ "\n");
                //recibo objeto
                for (int i = 0; i < listaPersonas.size(); i++) {
                    jTextAreaSelect.append("Dni: " + listaPersonas.get(i).getDni() + "\n"
                            + "Nombre: " + listaPersonas.get(i).getNom() + "\n"
                            + "Apellido: " + listaPersonas.get(i).getApellido() + "\n"
                            + "Nombre empresa: " + listaPersonas.get(i).getNomempresa() + "\n"
                            + "Departamento: " + listaPersonas.get(i).getDepartament() + "\n"
                            + "Codigo tarjeta: " + listaPersonas.get(i).getCodicard() + "\n"
                            + "Mail: " + listaPersonas.get(i).getMail() + "\n"
                            + "Telefono: " + listaPersonas.get(i).getTelephon() + "\n"
                            +"____________________________________________________________________" + "\n");
                }
                perEnt.getObjectInputFilter();
                break;
            case "1": //Tabla users

                //ahora si enviamos al server los datos que queremos, sin errores
                escriptor.write(palabra);
                escriptor.newLine();
                escriptor.flush();

                List<Users> listaUsers = new ArrayList<>();

                perEnt = new ObjectInputStream(socket.getInputStream());
                listaUsers = (ArrayList) perEnt.readObject();

                jTextAreaSelect.append("____________________________________________________________________"+ "\n");
                //recibo objeto

                for (int i = 0; i < listaUsers.size(); i++) {
                    jTextAreaSelect.append("Login: " + listaUsers.get(i).getLogin() + "\n"
                            + "Password: " + listaUsers.get(i).getPass() + "\n"
                            + "Tipo de user: " + listaUsers.get(i).getNumtipe() + "\n"
                            + "DNI: " + listaUsers.get(i).getDni() + "\n"
                            +"____________________________________________________________________" + "\n");
                }
                perEnt.getObjectInputFilter();
                break;

            case "2": //Tabla empresa

                //ahora si enviamos al server los datos que queremos, sin errores
                escriptor.write(palabra);
                escriptor.newLine();
                escriptor.flush();
               
                List<Empresa> listaEmpresa = new ArrayList<>();
                perEnt = new ObjectInputStream(socket.getInputStream());
                listaEmpresa = (ArrayList) perEnt.readObject();
                jTextAreaSelect.append("____________________________________________________________________"+ "\n");
                //recibo objeto
                for (int i = 0; i < listaEmpresa.size(); i++) {
                    jTextAreaSelect.append("Nombre empresa: " + listaEmpresa.get(i).getNom() + "\n"
                            + "Dirección: " + listaEmpresa.get(i).getAddress() + "\n"
                            + "Telefono: " + listaEmpresa.get(i).getTelephon() + "\n"
                            +"____________________________________________________________________" + "\n");
                }
                perEnt.getObjectInputFilter();
                break;
            case "3": //Tabla jornada

                //ahora si enviamos al server los datos que queremos, sin errores
                escriptor.write(palabra);
                escriptor.newLine();
                escriptor.flush();
                
                List<Jornada> listaJorandas = new ArrayList<>();
                perEnt = new ObjectInputStream(socket.getInputStream());
                listaJorandas = (ArrayList) perEnt.readObject();

                jTextAreaSelect.append("____________________________________________________________________"+ "\n");
                //recibo objeto
                for (int i = 0; i < listaJorandas.size(); i++) {
                    jTextAreaSelect.append("\nDni: " + listaJorandas.get(i).getDni() + "\n"
                            + "Nombre: " + listaJorandas.get(i).getNom() + "\n"
                            + "Apellido: " + listaJorandas.get(i).getApellido() + "\n"
                            + "Codigo tarjeta: " + listaJorandas.get(i).getCodicard() + "\n"
                            + "Hora entrada: " + listaJorandas.get(i).getHoraentrada() + "\n"
                            + "Hora salida: " + listaJorandas.get(i).getHorasalida() + "\n"
                            + "Total: " + listaJorandas.get(i).getTotal() + "\n"
                            + "Fecha: " + listaJorandas.get(i).getFecha() + "\n"
                            + "____________________________________________________________________");
                }
                perEnt.getObjectInputFilter();
                break;
            }
        }
    
    /**
    * Método que gestiona las busquedas con filtro doble de campos nom y apellidos y muestra
    * por el textArea de la pestaña busqueda el resultado de la búsqueda
    * 
    * @param NomApellido array de Strings que contiene los 8 datos que necesitamos para la consulta
    */
    
    public static void operacionesConNomYApellidos7 ( String[] NomApellido, String palabra, BufferedWriter escriptor, Socket socket, JTextArea jTextAreaSelect)throws IOException, ClassNotFoundException{
        String codigoUserRecibido = NomApellido[0]; //el codigo recibido tiene que ser el mismo que le hemos asignado
        String crud = NomApellido[1];
        String nombreTabla = NomApellido[2]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
        String nom = NomApellido[3]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
        String datoNom = NomApellido[4];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
        String apellido = NomApellido[5]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
        String datoApellido = NomApellido[6];
        String orden = NomApellido[7];// si es el caso el orden, si no hay ponemos 0

        ObjectInputStream perEnt=null;
        
        jTextAreaSelect.append("____________________________________________________________________" + "\n"
            +"codigoUserRecibido: " + codigoUserRecibido + "\n"
            +"crud: " + crud + "\n"
            +"nombreTabla: " + nombreTabla + "\n"
            +"nom: " + nom + "\n"
            +"datoNom: " + datoNom + "\n"
            +"apellido: " + apellido + "\n"
            +"datoApellido: " + datoApellido + "\n"
            +"orden: " + orden + "\n"
            +"____________________________________________________________________" + "\n");

        palabra = codigoUserRecibido + "," + crud + "," + nombreTabla + "," + nom + "," + datoNom + "," + apellido + "," + datoApellido + "," + orden;

        if (codigoUserRecibido.equals("")) {
            codigoUserRecibido = "0";
        }

        if (crud.equals("0")) {
            if (nombreTabla.equals("0") && nom.equals("nom") && apellido.equals("apellido")) {
                escriptor.write(palabra);
                escriptor.newLine();
                escriptor.flush();

                perEnt = new ObjectInputStream(socket.getInputStream());
                Object receivedData = perEnt.readObject();

                if (receivedData instanceof List) {
                    List<Empleados> listaEmpleadosNomApellido = (List<Empleados>) receivedData;
                    for (Empleados empleado : listaEmpleadosNomApellido) {
                        jTextAreaSelect.append("Dni: " + empleado.getDni() + "\n" 
                                + "Nombre: " + empleado.getNom() + "\n" 
                                + "Apellido: " + empleado.getApellido() + "\n" 
                                + "Nombre empresa: " + empleado.getNomempresa() + "\n" 
                                + "Departamento: "  + empleado.getDepartament() + "\n" 
                                + "Codigo tarjeta: "  + empleado.getCodicard() + "\n" 
                                + "Mail: " + empleado.getMail() + "\n" 
                                + "Telefono: " + empleado.getTelephon() + "\n");
                        jTextAreaSelect.append(
                                "____________________________________________________________________\n");
                    }
                    perEnt.getObjectInputFilter();
                } else if (receivedData instanceof String) {
                    String errorMessage = (String) receivedData;
                    jTextAreaSelect.append(errorMessage);
                } else {
                    jTextAreaSelect.append("Datos inesperados recibidos del servidor");
                }
                
            } else if (nombreTabla.equals("3") && nom.equals("nom") && apellido.equals("apellido")) {
                escriptor.write(palabra);
                escriptor.newLine();
                escriptor.flush();

                perEnt = new ObjectInputStream(socket.getInputStream());
                Object receivedData = perEnt.readObject();

                if (receivedData instanceof List) {
                    List<Jornada> listaJornadaNomApellido = (List<Jornada>) receivedData;

                    for (Jornada jornada : listaJornadaNomApellido) {
                        jTextAreaSelect.append("Dni: " + jornada.getDni() + "\n" 
                                + "Nombre: " + jornada.getNom() + "\n" 
                                + "Apellido: " + jornada.getApellido() + "\n" 
                                + "Codigo tarjeta: " + jornada.getCodicard() + "\n"
                                + "Hora entrada: " + jornada.getHoraentrada() + "\n"
                                + "Hora salida: " + jornada.getHorasalida() + "\n" 
                                + "Total: " + jornada.getTotal() + "\n" 
                                + "Fecha: " + jornada.getFecha() + "\n");
                        jTextAreaSelect.append(
                                "____________________________________________________________________\n");
                    }
                    perEnt.getObjectInputFilter();
                } else if (receivedData instanceof String) {
                    String errorMessage = (String) receivedData;
                    jTextAreaSelect.append(errorMessage);
                } else {
                    jTextAreaSelect.append("Datos inesperados recibidos del servidor");
                }
            }
        }
    }             
}
