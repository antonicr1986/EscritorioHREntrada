package modelo;

import java.io.Serializable;

/**
 *
 * @author Antonio Company Rodriguez
 * 
 * Clase para crear objetos de tipo Users con todos sus atributos
 */

public class Users implements Serializable{
    private static final long serialVersionUID = 6529685098267757690L;
    
    private String login;
    private String pass;
    private int numtipe;
    private String dni;
    private int codigo;

    /**
     * Constructor para objetos de la clase users
     *
     * @param login Login del usuario
     * @param pass Contraseña del usuario
     * @param numtipe Tipo de usuario 0 (admin) 1 (normal)
     * @param dni Dni del empleado
     * @param codigo Codigo de autentiación del usuario
     
     */
    
    public Users(String login, String pass, int numtipe, String dni, int codigo) {
        this.login = login;
        this.pass = pass;
        this.numtipe = numtipe;
        this.dni = dni;
        this.codigo = codigo;
    }
    
    /**
     * Constructor para objetos de la clase empleado
     *
     * @param dni Dni del empleado
     * @param login Login del usuario
     * @param pass Contraseña del usuario
     * @param numtipe Tipo de usuario 0 (admin) 1 (normal)
     */
    public Users(String login, String pass, int numtipe, String dni) {
        this.login = login;
        this.pass = pass;
        this.numtipe = numtipe;
        this.dni = dni;
    }

    public Users() {
        
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getNumtipe() {
        return numtipe;
    }

    public void setNumtipe(int numtipe) {
        this.numtipe = numtipe;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    } 
}
