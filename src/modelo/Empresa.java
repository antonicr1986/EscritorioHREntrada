package modelo;

import java.io.Serializable;

/**
 *
 * @author Antonio Company Rodriguez
 * 
 * Clase para crear objetos de tipo Empresa con todos sus atributos
 */
public class Empresa implements Serializable{
    private static final long serialVersionUID = 6529685098267757690L;
    
    private String nom;
    private String address;
    private String telephon;

    /**
     *
     * @param nom Nombre de la empresa
     * @param address Direcci�n de la empresa
     * @param telephon Tel�fono de la empresa
     */
    
    public Empresa(String nom, String address, String telephon) {
        this.nom = nom;
        this.address = address;
        this.telephon = telephon;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephon() {
        return telephon;
    }

    public void setTelephon(String telephon) {
        this.telephon = telephon;
    }
    
    
}
