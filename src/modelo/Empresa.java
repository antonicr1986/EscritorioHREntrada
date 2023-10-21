package modelo;

import java.io.Serializable;

/**
 *
 * @author Antonio Company Rodríguez
 */
public class Empresa implements Serializable{
    private String nom;
    private String address;
    private int telephon;

    public Empresa(String nom, String address, int telephon) {
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

    public int getTelephon() {
        return telephon;
    }

    public void setTelephon(int telephon) {
        this.telephon = telephon;
    }
    
    
}
