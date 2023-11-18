package CRUD;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
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
    public void operacionesConSelect ( String columna, String palabra, String palabraAbuscar ) throws IOException, ClassNotFoundException{
         if (nombreTabla.equals("0") && columna.equals("dni")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            System.out.println("El usuario con codigo: " + codigoUserRecibido
                    + "\nenvia los datos siguientes: \n" + palabra + "\n");

            List<Empleados> listaPersonasdni = new ArrayList<>();

            perEnt = new ObjectInputStream(socket.getInputStream());
            listaPersonasdni = (ArrayList) perEnt.readObject();

            for (int i = 0; i < listaPersonasdni.size(); i++) {
                if (columna.equals("dni") && palabraAbuscar.equals(listaPersonasdni.get(i).getDni())) {
                    jTextAreaSelect.append("____________________________________________________________________"+ "\n");
                    jTextAreaSelect.append("Dni: " + listaPersonasdni.get(i).getDni() + "\n"
                            + "Nombre: " + listaPersonasdni.get(i).getNom() + "\n"
                            + "Apellido: " + listaPersonasdni.get(i).getApellido() + "\n"
                            + "Nombre empresa: " + listaPersonasdni.get(i).getNomempresa() + "\n"
                            + "Departamento: " + listaPersonasdni.get(i).getDepartament() + "\n"
                            + "Codigo tarjeta: " + listaPersonasdni.get(i).getCodicard() + "\n"
                            + "Mail: " + listaPersonasdni.get(i).getMail() + "\n"
                            + "Teléfono: " + listaPersonasdni.get(i).getTelephon() + "\n"
                            +"____________________________________________________________________" + "\n");
                }
            }
            perEnt.getObjectInputFilter();
        } else if (nombreTabla.equals("0") && columna.equals("nomempresa")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            jTextAreaSelect.append("El usuario con codigo: " + codigoUserRecibido
                    + "\nenvia los datos siguientes: \n" + palabra + "\n");

            List<Empleados> listaTotalEmpleadosNomEmpresa = new ArrayList<>();

            perEnt = new ObjectInputStream(socket.getInputStream());
            listaTotalEmpleadosNomEmpresa = (ArrayList) perEnt.readObject();

            for (int i = 0; i < listaTotalEmpleadosNomEmpresa.size(); i++) {
                if (columna.equals("nomempresa") && palabraAbuscar.equals(listaTotalEmpleadosNomEmpresa.get(i).getNomempresa())) {
                     jTextAreaSelect.append("____________________________________________________________________"+ "\n");
                    jTextAreaSelect.append("Dni: " + listaTotalEmpleadosNomEmpresa.get(i).getDni() + "\n"
                            + "Nombre: " + listaTotalEmpleadosNomEmpresa.get(i).getNom() + "\n"
                            + "Apellido: " + listaTotalEmpleadosNomEmpresa.get(i).getApellido() + "\n"
                            + "Nombre empresa: " + listaTotalEmpleadosNomEmpresa.get(i).getNomempresa() + "\n"
                            + "Departamento: " + listaTotalEmpleadosNomEmpresa.get(i).getDepartament() + "\n"
                            + "Codigo tarjeta: " + listaTotalEmpleadosNomEmpresa.get(i).getCodicard() + "\n"
                            + "Mail: " + listaTotalEmpleadosNomEmpresa.get(i).getMail() + "\n"
                            + "Teléfono: " + listaTotalEmpleadosNomEmpresa.get(i).getTelephon() + "\n"
                            +"____________________________________________________________________" + "\n");
                }
            }
            perEnt.getObjectInputFilter();
        } else if (nombreTabla.equals("0") && columna.equals("departament")) {

            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            System.out.println("El usuario con codigo: " + codigoUserRecibido
                    + "\nenvia los datos siguientes: \n" + palabra + "\n");

            List<Empleados> listaTotalEmpleadosDepart = new ArrayList<>();

            perEnt = new ObjectInputStream(socket.getInputStream());
            listaTotalEmpleadosDepart = (ArrayList) perEnt.readObject();

            for (int i = 0; i < listaTotalEmpleadosDepart.size(); i++) {
                if (columna.equals("departament") && palabraAbuscar.equals(listaTotalEmpleadosDepart.get(i).getDepartament())) {
                     jTextAreaSelect.append("____________________________________________________________________"+ "\n");
                    jTextAreaSelect.append("Dni: " + listaTotalEmpleadosDepart.get(i).getDni() + "\n"
                            + "Nombre: " + listaTotalEmpleadosDepart.get(i).getNom() + "\n"
                            + "Apellido: " + listaTotalEmpleadosDepart.get(i).getApellido() + "\n"
                            + "Nombre empresa: " + listaTotalEmpleadosDepart.get(i).getNomempresa() + "\n"
                            + "Departamento: " + listaTotalEmpleadosDepart.get(i).getDepartament() + "\n"
                            + "Codigo tarjeta: " + listaTotalEmpleadosDepart.get(i).getCodicard() + "\n"
                            + "Mail: " + listaTotalEmpleadosDepart.get(i).getMail() + "\n"
                            + "Teléfono: " + listaTotalEmpleadosDepart.get(i).getTelephon() + "\n"
                            +"____________________________________________________________________" + "\n");
                }
            }
            perEnt.getObjectInputFilter();
        } else if (nombreTabla.equals("0") && columna.equals("codicard")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            jTextAreaSelect.append("El usuario con codigo: " + codigoUserRecibido
                    + "\nenvia los datos siguientes: \n" + palabra + "\n");

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
        } else if (nombreTabla.equals("0") && columna.equals("mail")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            jTextAreaSelect.append("El usuario con codigo: " + codigoUserRecibido
                    + "\nenvia los datos siguientes: \n" + palabra + "\n");

            List<Empleados> listaTotalEmpleadosMail = new ArrayList<>();

            perEnt = new ObjectInputStream(socket.getInputStream());
            listaTotalEmpleadosMail = (ArrayList) perEnt.readObject();

            for (int i = 0; i < listaTotalEmpleadosMail.size(); i++) {
                if (columna.equals("mail") && palabraAbuscar.equals(listaTotalEmpleadosMail.get(i).getMail())) {
                    jTextAreaSelect.append("____________________________________________________________________"+ "\n");
                    jTextAreaSelect.append("Dni: " + listaTotalEmpleadosMail.get(i).getDni() + "\n"
                            + "Nombre: " + listaTotalEmpleadosMail.get(i).getNom() + "\n"
                            + "Apellido: " + listaTotalEmpleadosMail.get(i).getApellido() + "\n"
                            + "Nombre empresa: " + listaTotalEmpleadosMail.get(i).getNomempresa() + "\n"
                            + "Departamento: " + listaTotalEmpleadosMail.get(i).getDepartament() + "\n"
                            + "Codigo tarjeta: " + listaTotalEmpleadosMail.get(i).getCodicard() + "\n"
                            + "Mail: " + listaTotalEmpleadosMail.get(i).getMail() + "\n"
                            + "Teléfono: " + listaTotalEmpleadosMail.get(i).getTelephon() + "\n"
                            +"____________________________________________________________________" + "\n");
                }
            }
            perEnt.getObjectInputFilter();
        } else if (nombreTabla.equals("0") && columna.equals("telephon")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            jTextAreaSelect.append("El usuario con codigo: " + codigoUserRecibido
                    + "\nenvia los datos siguientes: \n" + palabra + "\n");

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

        } else if (nombreTabla.equals("1") && columna.equals("dni")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            jTextAreaSelect.append("El usuario con codigo: " + codigoUserRecibido
                    + "\nenvia los datos siguientes: \n" + palabra + "\n");

            List<Users> listaToUsersDni = new ArrayList<>();

            perEnt = new ObjectInputStream(socket.getInputStream());
            listaToUsersDni = (ArrayList) perEnt.readObject();

            for (int i = 0; i < listaToUsersDni.size(); i++) {
                if (columna.equals("dni") && palabraAbuscar.equals(listaToUsersDni.get(i).getDni())) {
                    jTextAreaSelect.append("Login: " + listaToUsersDni.get(i).getLogin() + "\n"
                            + "Password: " + listaToUsersDni.get(i).getPass() + "\n"
                            + "Tipo de user: " + listaToUsersDni.get(i).getNumtipe() + "\n"
                            + "DNI: " + listaToUsersDni.get(i).getDni()+ "\n"
                            +"____________________________________________________________________" + "\n");
                }
            }
            perEnt.getObjectInputFilter();
        } else if (nombreTabla.equals("1") && columna.equals("login")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            jTextAreaSelect.append("El usuario con codigo: " + codigoUserRecibido
                    + "\nenvia los datos siguientes: \n" + palabra + "\n");

            List<Users> listaTotalUsersLogin = new ArrayList<>();

            perEnt = new ObjectInputStream(socket.getInputStream());
            listaTotalUsersLogin = (ArrayList) perEnt.readObject();

            for (int i = 0; i < listaTotalUsersLogin.size(); i++) {
                if (columna.equals("login") && palabraAbuscar.equals(listaTotalUsersLogin.get(i).getLogin())) {
                    jTextAreaSelect.append("Login: " + listaTotalUsersLogin.get(i).getLogin() + "\n"
                            + "Password: " + listaTotalUsersLogin.get(i).getPass() + "\n"
                            + "Tipo de user: " + listaTotalUsersLogin.get(i).getNumtipe() + "\n"
                            + "DNI: " + listaTotalUsersLogin.get(i).getDni()+ "\n"
                            +"____________________________________________________________________" + "\n");
                }
            }
            perEnt.getObjectInputFilter();
        } else if (nombreTabla.equals("1") && columna.equals("numtipe")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            jTextAreaSelect.append("El usuario con codigo: " + codigoUserRecibido
                    + "\nenvia los datos siguientes: \n" + palabra + "\n");

            List<Users> listaTotalUsersTipe = new ArrayList<>();

            perEnt = new ObjectInputStream(socket.getInputStream());
            listaTotalUsersTipe = (ArrayList) perEnt.readObject();

            for (int i = 0; i < listaTotalUsersTipe.size(); i++) {
                String numtipe = String.valueOf(listaTotalUsersTipe.get(i).getNumtipe());
                if (columna.equals("numtipe") && palabraAbuscar.equals(numtipe)) {
                    jTextAreaSelect.append("Login: " + listaTotalUsersTipe.get(i).getLogin() + "\n"
                            + "Password: " + listaTotalUsersTipe.get(i).getPass() + "\n"
                            + "Tipo de user: " + listaTotalUsersTipe.get(i).getNumtipe() + "\n"
                            + "DNI: " + listaTotalUsersTipe.get(i).getDni() + "\n"
                            +"____________________________________________________________________" + "\n");
                }
            }
            perEnt.getObjectInputFilter();

        } else if (nombreTabla.equals("2") && columna.equals("nom")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            jTextAreaSelect.append("El usuario con codigo: " + codigoUserRecibido
                    + "\nenvia los datos siguientes: \n" + palabra+ "\n");

            List<Empresa> listaEmpresasNom = new ArrayList<>();

            perEnt = new ObjectInputStream(socket.getInputStream());
            listaEmpresasNom = (ArrayList) perEnt.readObject();

            for (int i = 0; i < listaEmpresasNom.size(); i++) {
                if (columna.equals("nom") && palabraAbuscar.equals(listaEmpresasNom.get(i).getNom())) {
                    jTextAreaSelect.append("____________________________________________________________________" + "\n"
                        +"Nombre empresa: " + listaEmpresasNom.get(i).getNom() + "\n"
                        + "Dirección: " + listaEmpresasNom.get(i).getAddress() + "\n"
                        + "Teléfono: " + listaEmpresasNom.get(i).getTelephon() + "\n"
                        +"____________________________________________________________________" + "\n");
                }
            }
            perEnt.getObjectInputFilter();
        } else if (nombreTabla.equals("2") && columna.equals("address")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            jTextAreaSelect.append("El usuario con codigo: " + codigoUserRecibido
                    + "\nenvia los datos siguientes: \n" + palabra + "\n");

            List<Empresa> listaEmpresasAddress = new ArrayList<>();

            perEnt = new ObjectInputStream(socket.getInputStream());
            listaEmpresasAddress = (ArrayList) perEnt.readObject();

            for (int i = 0; i < listaEmpresasAddress.size(); i++) {
                if (columna.equals("address") && palabraAbuscar.equals(listaEmpresasAddress.get(i).getAddress())) {
                    jTextAreaSelect.append("____________________________________________________________________" + "\n"
                        +"Nombre empresa: " + listaEmpresasAddress.get(i).getNom() + "\n"
                        + "Dirección: " + listaEmpresasAddress.get(i).getAddress() + "\n"
                        + "Teléfono: " + listaEmpresasAddress.get(i).getTelephon() + "\n"
                        +"____________________________________________________________________" + "\n");
                }
            }
            perEnt.getObjectInputFilter();
        } else if (nombreTabla.equals("2") && columna.equals("telephon")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            jTextAreaSelect.append("El usuario con codigo: " + codigoUserRecibido
                    + "\nenvia los datos siguientes: \n" + palabra + "\n");

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

        } else if (nombreTabla.equals("3") && columna.equals("dni")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            jTextAreaSelect.append("El usuario con codigo: " + codigoUserRecibido
                    + "\nenvia los datos siguientes: \n" + palabra + "\n");

            List<Jornada> listaToJornadaDni = new ArrayList<>();

            perEnt = new ObjectInputStream(socket.getInputStream());
            listaToJornadaDni = (ArrayList) perEnt.readObject();

            for (int i = 0; i < listaToJornadaDni.size(); i++) {
                if (columna.equals("dni") && palabraAbuscar.equals(listaToJornadaDni.get(i).getDni())) {
                    jTextAreaSelect.append("\nDni: " + listaToJornadaDni.get(i).getDni() + "\n"
                            + "Nombre: " + listaToJornadaDni.get(i).getNom() + "\n"
                            + "Apellido: " + listaToJornadaDni.get(i).getApellido() + "\n"
                            + "Codigo tarjeta: " + listaToJornadaDni.get(i).getCodicard() + "\n"
                            + "Hora entrada: " + listaToJornadaDni.get(i).getHoraentrada() + "\n"
                            + "Hora salida: " + listaToJornadaDni.get(i).getHorasalida() + "\n"
                            + "Total: " + listaToJornadaDni.get(i).getTotal() + "\n"
                            + "Fecha: " + listaToJornadaDni.get(i).getFecha() + "\n"
                            +"____________________________________________________________________" + "\n");
                }
            }
            perEnt.getObjectInputFilter();
        } else if (nombreTabla.equals("3") && columna.equals("codicard")) {
            escriptor.write(palabra);
            escriptor.newLine();
            escriptor.flush();
            jTextAreaSelect.append("El usuario con codigo: " + codigoUserRecibido
                    + "\nenvia los datos siguientes: \n" + palabra + "\n");

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
            jTextAreaSelect.append("El usuario con codigo: " + codigoUserRecibido
                    + "\nenvia los datos siguientes: \n" + palabra + "\n");

            List<Jornada> listaTotalJornadaFecha = new ArrayList<>();

            perEnt = new ObjectInputStream(socket.getInputStream());
            listaTotalJornadaFecha = (ArrayList) perEnt.readObject();

            for (int i = 0; i < listaTotalJornadaFecha.size(); i++) {
                if (columna.equals("fecha") && palabraAbuscar.equals(listaTotalJornadaFecha.get(i).getFecha())) {
                    jTextAreaSelect.append("\nDni: " + listaTotalJornadaFecha.get(i).getDni() + "\n"
                            + "Nombre: " + listaTotalJornadaFecha.get(i).getNom() + "\n"
                            + "Apellido: " + listaTotalJornadaFecha.get(i).getApellido() + "\n"
                            + "Codigo tarjeta: " + listaTotalJornadaFecha.get(i).getCodicard() + "\n"
                            + "Hora entrada: " + listaTotalJornadaFecha.get(i).getHoraentrada() + "\n"
                            + "Hora salida: " + listaTotalJornadaFecha.get(i).getHorasalida() + "\n"
                            + "Total: " + listaTotalJornadaFecha.get(i).getTotal() + "\n"
                            + "Fecha: " + listaTotalJornadaFecha.get(i).getFecha() + "\n"
                            +"____________________________________________________________________" + "\n");
                }
            }
            perEnt.getObjectInputFilter();
        } else if (!nombreTabla.equals(null) && columna.equals("0")) {
            mostrarTablasColumnaEquals0();
        }
    }
    
    public void operacionesConNomYApellidos7 ( String[] NomApellido)throws IOException, ClassNotFoundException{
        String codigoUserRecibido = NomApellido[0]; //el codigo recibido tiene que ser el mismo que le hemos asignado
        crud = NomApellido[1];
        nombreTabla = NomApellido[2]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
        String nom = NomApellido[3]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
        String datoNom = NomApellido[4];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
        String apellido = NomApellido[5]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
        String datoApellido = NomApellido[6];
        orden = NomApellido[7];// si es el caso el orden, si no hay ponemos 0

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
                jTextAreaSelect.append("El usuario con codigo: " + codigoUserRecibido
                        + "\nenvia los datos siguientes: \n" + palabra + "\n");

                List<Empleados> listaEmpleadosNomApellido = new ArrayList<>();

                perEnt = new ObjectInputStream(socket.getInputStream());
                listaEmpleadosNomApellido = (ArrayList) perEnt.readObject();

                for (int i = 0; i < listaEmpleadosNomApellido.size(); i++) {
                    if (nom.equals("nom")
                            && datoNom.equals(listaEmpleadosNomApellido.get(i).getNom())
                            && apellido.equals("apellido")
                            && datoApellido.equals(listaEmpleadosNomApellido.get(i).getApellido())) {
                        jTextAreaSelect.append("____________________________________________________________________"+ "\n");
                        jTextAreaSelect.append("Dni: " + listaEmpleadosNomApellido.get(i).getDni() + "\n"
                                + "Nombre: " + listaEmpleadosNomApellido.get(i).getNom() + "\n"
                                + "Apellido: " + listaEmpleadosNomApellido.get(i).getApellido() + "\n"
                                + "Nombre empresa: " + listaEmpleadosNomApellido.get(i).getNomempresa() + "\n"
                                + "Departamento: " + listaEmpleadosNomApellido.get(i).getDepartament() + "\n"
                                + "Codigo tarjeta: " + listaEmpleadosNomApellido.get(i).getCodicard() + "\n"
                                + "Mail: " + listaEmpleadosNomApellido.get(i).getMail() + "\n"
                                + "Telefono: " + listaEmpleadosNomApellido.get(i).getTelephon() + "\n"
                                +"____________________________________________________________________" + "\n");
                    }
                }
                perEnt.getObjectInputFilter();
            } else if (nombreTabla.equals("3") && nom.equals("nom") && apellido.equals("apellido")) {
                escriptor.write(palabra);
                escriptor.newLine();
                escriptor.flush();
                jTextAreaSelect.append("El usuario con codigo: " + codigoUserRecibido
                        + "\nenvia los datos siguientes: \n" + palabra + "\n");

                List<Jornada> listaJornadaNomApellido = new ArrayList<>();

                perEnt = new ObjectInputStream(socket.getInputStream());
                listaJornadaNomApellido = (ArrayList) perEnt.readObject();
                for (int i = 0; i < listaJornadaNomApellido.size(); i++) {
                    if (nom.equals("nom")
                            && datoNom.equals(listaJornadaNomApellido.get(i).getNom())
                            && apellido.equals("apellido")
                            && datoApellido.equals(listaJornadaNomApellido.get(i).getApellido())) {
                        jTextAreaSelect.append("\nDni: " + listaJornadaNomApellido.get(i).getDni() + "\n"
                                + "Nombre: " + listaJornadaNomApellido.get(i).getNom() + "\n"
                                + "Apellido: " + listaJornadaNomApellido.get(i).getApellido() + "\n"
                                + "Codigo tarjeta: " + listaJornadaNomApellido.get(i).getCodicard() + "\n"
                                + "Hora entrada: " + listaJornadaNomApellido.get(i).getHoraentrada() + "\n"
                                + "Hora salida: " + listaJornadaNomApellido.get(i).getHorasalida() + "\n"
                                + "Total: " + listaJornadaNomApellido.get(i).getTotal() + "\n"
                                + "Fecha: " + listaJornadaNomApellido.get(i).getFecha() + "\n"
                                +"____________________________________________________________________");
                    }
                }
                perEnt.getObjectInputFilter();
            }
        }
    }*/             
}
