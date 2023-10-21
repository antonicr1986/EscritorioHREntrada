package modelo;

import java.io.Serializable;

/**
 *
 * @author antonio minero
 */
public class Empleados implements Serializable{
    private String dni;
    private String nom;
    private String apellido;
    private String nomempresa;
    private String departament;
    private int codicard;
    private String mail;
    private int telephon;

    public Empleados(String dni, String nom, String apellido, String nomempresa, String departament, int codicard, String mail, int telephone) {
        this.dni = dni;
        this.nom = nom;
        this.apellido = apellido;
        this.nomempresa = nomempresa;
        this.departament = departament;
        this.codicard = codicard;
        this.mail = mail;
        this.telephon = telephon;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNomempresa() {
        return nomempresa;
    }

    public void setNomEmpresa(String nomempresa) {
        this.nomempresa = nomempresa;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public int getCodiCard() {
        return codicard;
    }

    public void setCodiCard(int codicard) {
        this.codicard = codicard;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getTelephone() {
        return telephon;
    }

    public void setTelephone(int telephon) {
        this.telephon = telephon;
    }
    
    
}
