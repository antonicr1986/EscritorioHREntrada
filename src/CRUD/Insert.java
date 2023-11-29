package CRUD;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import modelo.Empleados;
import modelo.Empresa;
import modelo.Jornada;
import modelo.Users;

/**
 *
 * @author Antonio Company Rodriguez
 */
public class Insert {
    
    /**
    * Método que gestiona y muestra por pantalla cuando la acción que ejecutamos 
    * és añadir una empresa.
    *
    * @param insertEmpresas array de string que contiene todos los valores para el insert
    * @param palabra string que se envia al server para el insert
    * @param escriptor BufferedWriter de contacto con el server
    * @param perEnt tipo de objeto ObjectInputStream recibido
    * @param socket Objeto tipo Socket para la conexión
    * @param jTextAreaInsert textArea en el que mostraremos los datos al usuario por la aplicación gráfica
    * 
    * @throws java.io.IOException
    * @throws java.lang.ClassNotFoundException
    * 
    */
    
    public static void operacionesConInsertEmpresas( String []insertEmpresas, String palabra, BufferedWriter escriptor, ObjectInputStream perEnt,Socket socket, JTextArea jTextAreaInsert)throws IOException, ClassNotFoundException{
        String codigoUserRecibido = insertEmpresas[0]; //el codigo recibido tiene que ser el mismo que le hemos asignado
        String crud = insertEmpresas[1];
        String nombreTabla = insertEmpresas[2]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
        String nom = insertEmpresas[3]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
        String datoNom = insertEmpresas[4];
        String address = insertEmpresas[5];
        String datoAddress = insertEmpresas[6];
        String telephon = insertEmpresas[7]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
        String datoTelephon = insertEmpresas[8];
        String orden = insertEmpresas[9];// si es el caso el orden, si no hay ponemos 0

        jTextAreaInsert.append("____________________________________________________________________" + "\n"
            +"codigoUserRecibido: " + codigoUserRecibido + "\n"
            +"crud: " + crud + "\n"
            +"nombreTabla: " + nombreTabla + "\n"
            +"nom: " + nom + "\n"
            +"datoNom: " + datoNom + "\n"
            +"address: " + address + "\n"
            +"datoApellido: " + datoAddress + "\n"
            +"telephon: " + telephon + "\n"
            +"datoTelephon: " + datoTelephon + "\n"
            +"orden: " + orden + "\n"
            +"____________________________________________________________________" + "\n");

        palabra = codigoUserRecibido + "," + crud + "," + nombreTabla + "," + nom + "," + datoNom + "," + address
                + "," + datoAddress + "," + telephon + "," + datoTelephon + "," + orden;

        if (codigoUserRecibido.equals("")) {
            codigoUserRecibido = "0";
        }

        if (crud.equals("1")) { //crud Insert
            if (nombreTabla.equals("2")) { //nombreTabla empresas

                escriptor.write(palabra);
                escriptor.newLine();
                escriptor.flush();

                perEnt = new ObjectInputStream(socket.getInputStream());
                Object receivedData = perEnt.readObject();

                if (receivedData instanceof List) {

                    jTextAreaInsert.append(("Empresa creada correctamente, sus datos son: \n"));
                    jTextAreaInsert.append("\nNombre: " + datoNom + "\n" 
                            + "Adrress: " + datoAddress + "\n" 
                            + "Telefono: " + datoTelephon + "\n");
                    jTextAreaInsert.append(
                            "____________________________________________________________________\n");

                    perEnt.getObjectInputFilter();
                } else if (receivedData instanceof String) {
                    String errorMessage = (String) receivedData;
                    jTextAreaInsert.append(errorMessage+ "\n");
                } else {
                    jTextAreaInsert.append("Datos inesperados recibidos del servidor");
                }
            }
        }
    }
    
    /**
    * Método que gestiona y muestra por pantalla cuando la acción que ejecutamos 
    * és añadir un usuario
    *
    * @param insertUsuarios array de string que contiene todos los valores para el insert
    * @param palabra string que se envia al server para el insert
    * @param escriptor BufferedWriter de contacto con el server
    * @param perEnt tipo de objeto ObjectInputStream recibido
    * @param socket Objeto tipo Socket para la conexión
    * @param jTextAreaInsert textArea en el que mostraremos los datos al usuario por la aplicación gráfica
    * 
    * @throws java.io.IOException
    * @throws java.lang.ClassNotFoundException
    * 
    */
    
    public static void  operacionesConInsertUsuarios( String [] insertUsuarios, String palabra, BufferedWriter escriptor, ObjectInputStream perEnt,Socket socket, JTextArea jTextAreaInsert)throws IOException, ClassNotFoundException{
        String codigoUserRecibido = insertUsuarios[0]; //el codigo recibido tiene que ser el mismo que le hemos asignado
        String crud = insertUsuarios[1];
        String nombreTabla = insertUsuarios[2]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
        String login = insertUsuarios[3]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
        String datoLogin = insertUsuarios[4];
        String pass = insertUsuarios[5];
        String datoPass = insertUsuarios[6];
        String numTipe = insertUsuarios[7]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
        String datoNumTipe = insertUsuarios[8];
        String dni = insertUsuarios[9]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
        String datoDni = insertUsuarios[10];
        String orden = insertUsuarios[11];// si es el caso el orden, si no hay ponemos 0

        jTextAreaInsert.append("____________________________________________________________________" + "\n"
            +"codigoUserRecibido: " + codigoUserRecibido + "\n"
            +"crud: " + crud + "\n"
            +"nombreTabla: " + nombreTabla + "\n"
            +"login: " + login + "\n"
            +"datoLogin: " + datoLogin + "\n"
            +"pass: " + pass + "\n"
            +"datoPass: " + datoPass + "\n"
            +"numTipe: " + numTipe + "\n"
            +"datoNumTipe: " + datoNumTipe + "\n"
            +"dni: " + dni + "\n"
            +"datoDni: " + datoDni + "\n"
            +"orden: " + orden + "\n"
            +"____________________________________________________________________\n");

        palabra = codigoUserRecibido + "," + crud + "," + nombreTabla + "," + login + "," + datoLogin + "," + pass
                + "," + datoPass + "," + numTipe + "," + datoNumTipe + "," + dni + "," + datoDni + "," + orden;

        if (codigoUserRecibido.equals("")) {
            codigoUserRecibido = "0";
        }

        if (crud.equals("1")) {
            if (nombreTabla.equals("1")) {

                escriptor.write(palabra);
                escriptor.newLine();
                escriptor.flush();
                jTextAreaInsert.append("El usuario con codigo: " + codigoUserRecibido
                        + "\nenvia los datos siguientes: \n" + palabra + "\n");

                perEnt = new ObjectInputStream(socket.getInputStream());
                Object receivedData = perEnt.readObject();

                if (receivedData instanceof List) {
                    jTextAreaInsert.append(("\nUsuario creado correctamente, sus datos son: \n"));
                    jTextAreaInsert.append("Login: " + datoLogin + "\n" 
                            + "Pass: " + datoPass + "\n"
                            + "Num Tipe: " + datoNumTipe + "\n" 
                            + "Dni: " + datoDni + "\n");
                    jTextAreaInsert.append(
                            "____________________________________________________________________\n");
                    perEnt.getObjectInputFilter();
                } else if (receivedData instanceof String) {
                    String errorMessage = (String) receivedData;
                    jTextAreaInsert.append(errorMessage + "\n\n");

                } else {
                    jTextAreaInsert.append("Datos inesperados recibidos del servidor");
                }
            }
        }
    }
    
    /**
    * Método que gestiona y muestra por pantalla cuando la acción que ejecutamos 
    * és añadir un empleado con 16 valores
    *
    * @param insertEmpleadoMailTelf array de string que contiene todos los valores para el insert
    * @param palabra string que se envia al server para el insert
    * @param escriptor BufferedWriter de contacto con el server
    * @param perEnt tipo de objeto ObjectInputStream recibido
    * @param socket Objeto tipo Socket para la conexión
    * @param jTextAreaInsert textArea en el que mostraremos los datos al usuario por la aplicación gráfica
    * 
    * @throws java.io.IOException
    * @throws java.lang.ClassNotFoundException
    * 
    */
    
    public static void operacionesConInsertEmpleadoMailTelf( String [] insertEmpleadoMailTelf, String palabra, BufferedWriter escriptor, ObjectInputStream perEnt,Socket socket, JTextArea jTextAreaInsert)throws IOException, ClassNotFoundException{
        String codigoUserRecibido = insertEmpleadoMailTelf[0]; //el codigo recibido tiene que ser el mismo que le hemos asignado
        String crud = insertEmpleadoMailTelf[1];
        String nombreTabla = insertEmpleadoMailTelf[2]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
        String dni = insertEmpleadoMailTelf[3]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
        String datoDni = insertEmpleadoMailTelf[4];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
        String nom = insertEmpleadoMailTelf[5]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
        String datoNom = insertEmpleadoMailTelf[6];
        String apellido = insertEmpleadoMailTelf[7];// si es el caso el orden, si no hay ponemos 0
        String datoApellido = insertEmpleadoMailTelf[8]; //el codigo recibido tiene que ser el mismo que le hemos asignado
        String nomempresa = insertEmpleadoMailTelf[9];
        String datoNomempresa = insertEmpleadoMailTelf[10]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
        String departament = insertEmpleadoMailTelf[11]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
        String datoDepartament = insertEmpleadoMailTelf[12];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
        String codicard = insertEmpleadoMailTelf[13]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
        String datoCodicard = insertEmpleadoMailTelf[14];
        String orden = insertEmpleadoMailTelf[15];// si es el caso el orden, si no hay ponemos 0

        jTextAreaInsert.append("____________________________________________________________________"+ "\n" 
                +"codigoUserRecibido: " + codigoUserRecibido + "\n" 
                + "crud: " + crud + "\n" 
                +"nombreTabla: " + nombreTabla + "\n" 
                +"dni: " + dni + "\n" 
                + "datoDni: " + datoDni +  "\n" 
                +"nom: " + nom + "\n" 
                + "datoNom: " + datoNom + "\n" 
                + "apellido: " + apellido + "\n" 
                + "datoApellido: " + datoApellido + "\n" 
                + "nomempresa: " + nomempresa + "\n" 
                +"datoNomempresa: " + datoNomempresa + "\n" 
                + "departament: " + departament + "\n" 
                +"datoDepartament: " + datoDepartament + "\n" 
                + "codicard: " + codicard + "\n" 
                +"datoCodicar: " + datoCodicard + "\n" 
                + "orden: " + orden + "\n" 
                + "____________________________________________________________________\n");

        palabra = codigoUserRecibido + "," + crud + "," + nombreTabla + "," + dni + "," + datoDni + "," + nom + "," + datoNom + "," + apellido
                + "," + datoApellido + "," + nomempresa + "," + datoNomempresa + "," + departament + "," + datoDepartament + "," + codicard + "," + datoCodicard
                + "," + orden;

        if (codigoUserRecibido.equals("")) {
            codigoUserRecibido = "0";
        }

        if (crud.equals("1")) {
            if (nombreTabla.equals("0")) {

                escriptor.write(palabra);
                escriptor.newLine();
                escriptor.flush();
                jTextAreaInsert.append("El usuario con codigo: " + codigoUserRecibido
                        + "\nenvia los datos siguientes: \n" + palabra + "\n");

                List<Empleados> insertEmpleadosMailTelf = new ArrayList<>();

                perEnt = new ObjectInputStream(socket.getInputStream());
                insertEmpleadosMailTelf = (ArrayList) perEnt.readObject();
                jTextAreaInsert.append(("Empleado creado correctamente, sus datos son: \n"));
                jTextAreaInsert.append("Dni: " + datoDni + "\n"
                        + "Nombre: " + datoNom + "\n"
                        + "Apellido: " + datoApellido + "\n"
                        + "Nombre empresa: " + datoNomempresa + "\n"
                        + "Departamento: " + datoDepartament + "\n"
                        + "Codigo tarjeta: " + datoCodicard + "\n"
                        +"____________________________________________________________________\n");
                perEnt.getObjectInputFilter();
            }
        }
    }
    
    /**
    * Método que gestiona y muestra por pantalla cuando la acción que ejecutamos 
    * és añadir un empleado con 18 valores
    *
    * @param insertEmpleadoMT array de string que contiene todos los valores para el insert
    * @param palabra string que se envia al server para el insert
    * @param escriptor BufferedWriter de contacto con el server
    * @param perEnt tipo de objeto ObjectInputStream recibido
    * @param socket Objeto tipo Socket para la conexión
    * @param jTextAreaInsert textArea en el que mostraremos los datos al usuario por la aplicación gráfica
    * 
    * @throws java.io.IOException
    * @throws java.lang.ClassNotFoundException
    * 
    */
    
    public static void operacionsConInsertEmpleadoMT17y15(String []insertEmpleadoMT, String palabra, BufferedWriter escriptor, ObjectInputStream perEnt,Socket socket, JTextArea jTextAreaInsert )throws IOException, ClassNotFoundException{
        if (insertEmpleadoMT[17].equals("0") && insertEmpleadoMT[15].equals("mail")
                    || insertEmpleadoMT[17].equals("1") && insertEmpleadoMT[15].equals("mail")) {

            String codigoUserRecibido = insertEmpleadoMT[0]; //el codigo recibido tiene que ser el mismo que le hemos asignado
            String crud = insertEmpleadoMT[1];
            String nombreTabla = insertEmpleadoMT[2]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
            String dni = insertEmpleadoMT[3]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
            String datoDni = insertEmpleadoMT[4];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
            String nom = insertEmpleadoMT[5]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
            String datoNom = insertEmpleadoMT[6];
            String apellido = insertEmpleadoMT[7];// si es el caso el orden, si no hay ponemos 0
            String datoApellido = insertEmpleadoMT[8]; //el codigo recibido tiene que ser el mismo que le hemos asignado
            String nomempresa = insertEmpleadoMT[9];
            String datoNomempresa = insertEmpleadoMT[10]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
            String departament = insertEmpleadoMT[11]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
            String datoDepartament = insertEmpleadoMT[12];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
            String codicard = insertEmpleadoMT[13]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
            String datoCodicard = insertEmpleadoMT[14];
            String mail = insertEmpleadoMT[15];// si es el caso el orden, si no hay ponemos 0
            String datoMail = insertEmpleadoMT[16];
            String orden = insertEmpleadoMT[17];// si es el caso el orden, si no hay ponemos 0

            jTextAreaInsert.append("____________________________________________________________________"+ "\n" 
                +"codigoUserRecibido: " + codigoUserRecibido + "\n" 
                + "crud: " + crud + "\n" 
                +"nombreTabla: " + nombreTabla + "\n" 
                +"dni: " + dni + "\n" 
                + "datoDni: " + datoDni +  "\n" 
                +"nom: " + nom + "\n" 
                + "datoNom: " + datoNom + "\n" 
                + "apellido: " + apellido + "\n" 
                + "datoApellido: " + datoApellido + "\n" 
                + "nomempresa: " + nomempresa + "\n" 
                +"datoNomempresa: " + datoNomempresa + "\n" 
                + "departament: " + departament + "\n" 
                +"datoDepartament: " + datoDepartament + "\n" 
                + "codicard: " + codicard + "\n" 
                +"datoCodicar: " + datoCodicard + "\n" 
                + "orden: " + orden + "\n" 
                + "____________________________________________________________________\n");

            palabra = codigoUserRecibido + "," + crud + "," + nombreTabla + "," + dni + "," + datoDni + "," + nom + "," + datoNom + "," + apellido
                    + "," + datoApellido + "," + nomempresa + "," + datoNomempresa + "," + departament + "," + datoDepartament + "," + codicard + "," + datoCodicard
                    + "," + mail + "," + datoMail + "," + orden;

            if (codigoUserRecibido.equals("")) {
                codigoUserRecibido = "0";
            }

            if (crud.equals("1")) {
                if (nombreTabla.equals("0")) {

                    escriptor.write(palabra);
                    escriptor.newLine();
                    escriptor.flush();
                     jTextAreaInsert.append("El usuario con codigo: " + codigoUserRecibido
                            + "\nenvia los datos siguientes: \n" + palabra + "\n");

                    List<Empleados> insertEmpleadosMail = new ArrayList<>();

                    perEnt = new ObjectInputStream(socket.getInputStream());
                    insertEmpleadosMail = (ArrayList) perEnt.readObject();
                     jTextAreaInsert.append(("Empleado creado correctamente, sus datos son: \n"));
                     jTextAreaInsert.append("Dni: " + datoDni + "\n"
                            + "Nombre: " + datoNom + "\n"
                            + "Apellido: " + datoApellido + "\n"
                            + "Nombre empresa: " + datoNomempresa + "\n"
                            + "Departamento: " + datoDepartament + "\n"
                            + "Codigo tarjeta: " + datoCodicard + "\n"
                            + "Mail: " + datoMail + "\n"
                            +"____________________________________________________________________\n");
                    perEnt.getObjectInputFilter();
                }
            }
        } else if (insertEmpleadoMT[17].equals("0") && insertEmpleadoMT[15].equals("telephon")
                || insertEmpleadoMT[17].equals("1") && insertEmpleadoMT[15].equals("telephon")) {

            String codigoUserRecibido = insertEmpleadoMT[0]; //el codigo recibido tiene que ser el mismo que le hemos asignado
            String crud = insertEmpleadoMT[1];
            String nombreTabla = insertEmpleadoMT[2]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
            String dni = insertEmpleadoMT[3]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
            String datoDni = insertEmpleadoMT[4];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
            String nom = insertEmpleadoMT[5]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
            String datoNom = insertEmpleadoMT[6];
            String apellido = insertEmpleadoMT[7];// si es el caso el orden, si no hay ponemos 0
            String datoApellido = insertEmpleadoMT[8]; //el codigo recibido tiene que ser el mismo que le hemos asignado
            String nomempresa = insertEmpleadoMT[9];
            String datoNomempresa = insertEmpleadoMT[10]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
            String departament = insertEmpleadoMT[11]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
            String datoDepartament = insertEmpleadoMT[12];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
            String codicard = insertEmpleadoMT[13]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
            String datoCodicard = insertEmpleadoMT[14];
            String telephon = insertEmpleadoMT[15];// si es el caso el orden, si no hay ponemos 0
            String datoTelephon = insertEmpleadoMT[16];
            String orden = insertEmpleadoMT[17];// si es el caso el orden, si no hay ponemos 0

            jTextAreaInsert.append("____________________________________________________________________"+ "\n" 
                +"codigoUserRecibido: " + codigoUserRecibido + "\n" 
                + "crud: " + crud + "\n" 
                +"nombreTabla: " + nombreTabla + "\n" 
                +"dni: " + dni + "\n" 
                + "datoDni: " + datoDni +  "\n" 
                +"nom: " + nom + "\n" 
                + "datoNom: " + datoNom + "\n" 
                + "apellido: " + apellido + "\n" 
                + "datoApellido: " + datoApellido + "\n" 
                + "nomempresa: " + nomempresa + "\n" 
                +"datoNomempresa: " + datoNomempresa + "\n" 
                + "departament: " + departament + "\n" 
                +"datoDepartament: " + datoDepartament + "\n" 
                + "codicard: " + codicard + "\n" 
                +"datoCodicar: " + datoCodicard + "\n" 
                + "orden: " + orden + "\n" 
                + "____________________________________________________________________\n");

            palabra = codigoUserRecibido + "," + crud + "," + nombreTabla + "," + dni + "," + datoDni + "," + nom + "," + datoNom + "," + apellido
                    + "," + datoApellido + "," + nomempresa + "," + datoNomempresa + "," + departament + "," + datoDepartament + "," + codicard + "," + datoCodicard
                    + "," + telephon + "," + datoTelephon + "," + orden;

            if (codigoUserRecibido.equals("")) {
                codigoUserRecibido = "0";
            }

            if (crud.equals("1")) {
                if (nombreTabla.equals("0")) {

                    escriptor.write(palabra);
                    escriptor.newLine();
                    escriptor.flush();
                    jTextAreaInsert.append("El usuario con codigo: " + codigoUserRecibido
                            + "\nenvia los datos siguientes: \n" + palabra + "\n");

                    List<Empleados> insertEmpleadosTelf = new ArrayList<>();

                    perEnt = new ObjectInputStream(socket.getInputStream());
                    insertEmpleadosTelf = (ArrayList) perEnt.readObject();
                    jTextAreaInsert.append(("\n____________________________________________________________________\n+"
                            + "Empleado creado correctamente, sus datos son: \n"));
                    jTextAreaInsert.append("Dni: " + datoDni + "\n"
                            + "Nombre: " + datoNom + "\n"
                            + "Apellido: " + datoApellido + "\n"
                            + "Nombre empresa: " + datoNomempresa + "\n"
                            + "Departamento: " + datoDepartament + "\n"
                            + "Codigo tarjeta: " + datoCodicard + "\n"
                            + "Telephon: " + datoTelephon + "\n"
                            +"____________________________________________________________________\n");
                    perEnt.getObjectInputFilter();
                }
            }
        }
    }
    
    /**
    * Método que gestiona y muestra por pantalla cuando la acción que ejecutamos 
    * és añadir un empleado con 20 valores
    *
    * @param insertEmpleado array de string que contiene todos los valores para el insert
    * @param palabra string que se envia al server para el insert
    * @param escriptor BufferedWriter de contacto con el server
    * @param perEnt tipo de objeto ObjectInputStream recibido
    * @param socket Objeto tipo Socket para la conexión
    * @param jTextAreaInsert textArea en el que mostraremos los datos al usuario por la aplicación gráfica
    * 
    * @throws java.io.IOException
    * @throws java.lang.ClassNotFoundException
    * 
    */
    
    public static void operacionesConInsertEmpleado19(String []insertEmpleado,  String palabra,   BufferedWriter escriptor, ObjectInputStream perEnt,Socket socket, JTextArea jTextAreaInsert)throws IOException, ClassNotFoundException{
        String codigoUserRecibido = insertEmpleado[0]; //el codigo recibido tiene que ser el mismo que le hemos asignado
        String crud = insertEmpleado[1];
        String nombreTabla = insertEmpleado[2]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
        String dni = insertEmpleado[3]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
        String datoDni = insertEmpleado[4];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
        String nom = insertEmpleado[5]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
        String datoNom = insertEmpleado[6];
        String apellido = insertEmpleado[7];// si es el caso el orden, si no hay ponemos 0
        String datoApellido = insertEmpleado[8]; //el codigo recibido tiene que ser el mismo que le hemos asignado
        String nomempresa = insertEmpleado[9];
        String datoNomempresa = insertEmpleado[10]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
        String departament = insertEmpleado[11]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
        String datoDepartament = insertEmpleado[12];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
        String codicard = insertEmpleado[13]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
        String datoCodicard = insertEmpleado[14];
        String mail = insertEmpleado[15];// si es el caso el orden, si no hay ponemos 0
        String datoMail = insertEmpleado[16];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
        String telephon = insertEmpleado[17]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
        String datoTelephon = insertEmpleado[18];
        String orden = insertEmpleado[19];// si es el caso el orden, si no hay ponemos 0

        jTextAreaInsert.append("____________________________________________________________________"+ "\n" 
                +"codigoUserRecibido: " + codigoUserRecibido + "\n" 
                + "crud: " + crud + "\n" 
                +"nombreTabla: " + nombreTabla + "\n" 
                +"dni: " + dni + "\n" 
                + "datoDni: " + datoDni +  "\n" 
                +"nom: " + nom + "\n" 
                + "datoNom: " + datoNom + "\n" 
                + "apellido: " + apellido + "\n" 
                + "datoApellido: " + datoApellido + "\n" 
                + "nomempresa: " + nomempresa + "\n" 
                +"datoNomempresa: " + datoNomempresa + "\n" 
                + "departament: " + departament + "\n" 
                +"datoDepartament: " + datoDepartament + "\n" 
                + "codicard: " + codicard + "\n" 
                +"datoCodicar: " + datoCodicard + "\n" 
                + "orden: " + orden + "\n" 
                + "____________________________________________________________________\n");

        palabra = codigoUserRecibido + "," + crud + "," + nombreTabla + "," + dni + "," + datoDni + "," + nom + "," + datoNom + "," + apellido
                + "," + datoApellido + "," + nomempresa + "," + datoNomempresa + "," + departament + "," + datoDepartament + "," + codicard + "," + datoCodicard
                + "," + mail + "," + datoMail + "," + telephon + "," + datoTelephon + "," + orden;

        if (codigoUserRecibido.equals("")) {
            codigoUserRecibido = "0";
        }

        if (crud.equals("1")) {
            if (nombreTabla.equals("0")) {

                escriptor.write(palabra);
                escriptor.newLine();
                escriptor.flush();
                jTextAreaInsert.append("El usuario con codigo: " + codigoUserRecibido
                        + "\nenvia los datos siguientes: \n" + palabra + "\n");

                List<Empleados> insertEmpleados = new ArrayList<>();

                perEnt = new ObjectInputStream(socket.getInputStream());
                insertEmpleados = (ArrayList) perEnt.readObject();
                 jTextAreaInsert.append(("\n____________________________________________________________________" 
                         + "\nEmpleado creado correctamente, sus datos son: \n"));
                 jTextAreaInsert.append("Dni: " + datoDni + "\n"
                        + "Nombre: " + datoNom + "\n"
                        + "Apellido: " + datoApellido + "\n"
                        + "Nombre empresa: " + datoNomempresa + "\n"
                        + "Departamento: " + datoDepartament + "\n"
                        + "Codigo tarjeta: " + datoCodicard + "\n"
                        + "Mail: " + datoMail + "\n"
                        + "Telefono: " + datoTelephon + "\n"
                        +"____________________________________________________________________\n");
                perEnt.getObjectInputFilter();
            }
        }
    }
    
    /**
     * Descripcion: Método para gestionar la función insert en la tabla jornada
     * 
     * @param nombreTabla string con el nombre de la tabla que vamos a trabajar
     * @param columna string con la columna de la tabla jornada
     * @param palabra string que se envia al server para el insert
     * @param codigoUserRecibido string con el codigo de usuario logueado
     * @param escriptor BufferedWriter de contacto con el server
     * @param perEnt tipo de objeto ObjectInputStream recibido
     * @param socket Objeto tipo Socket para la conexión
     * @param jTextAreaInsert textArea en el que mostraremos los datos al usuario por la aplicación gráfica
     * 
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void operacionesConInsertJornada(String nombreTabla, String columna, String palabra, 
            String codigoUserRecibido, BufferedWriter escriptor, ObjectInputStream perEnt,Socket socket, JTextArea jTextAreaInsert) throws IOException, ClassNotFoundException{
        if (nombreTabla.equals("3") && columna.equals("dni")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
             jTextAreaInsert.append("\nEl usuario con codigo: " + codigoUserRecibido
                    + "\nenvia los datos siguiente: \n" + palabra + "\n");

            perEnt = new ObjectInputStream(socket.getInputStream());
            Object receivedData = perEnt.readObject();

            if (receivedData instanceof List) {
                List<?> dataList = (List<?>) receivedData;
                 jTextAreaInsert.append(("\nJornada creada correctamente.\n"));
                for (Object data : dataList) {
                    if (data instanceof Jornada) {
                        Jornada jornada = (Jornada) data;
                        jTextAreaInsert.append("Dni: " + jornada.getDni() + "\n"
                                +"Nombre: " + jornada.getNom() + "\n"
                                +"Apellido: " + jornada.getApellido() + "\n"
                                +"Codigo tarjeta: " + jornada.getCodicard() + "\n"
                                +"Hora entrada: " + jornada.getHoraentrada() + "\n"
                                +"Hora salida: " + jornada.getHorasalida() + "\n"
                                +"Total: " + jornada.getTotal() + "\n"
                                +"Fecha: " + jornada.getFecha() + "\n"
                                +"____________________________________________________________________\n");
                    } else {
                         jTextAreaInsert.append("Datos inesperados recibidos del servidor");

                    }
                    perEnt.getObjectInputFilter();
                }
            } else if (receivedData instanceof String) {
                String errorMessage = (String) receivedData;
                jTextAreaInsert.append(errorMessage+ "\n");
            } else {
                jTextAreaInsert.append("Datos inesperados recibidos del servidor\n");
            }
        }
    }
}
