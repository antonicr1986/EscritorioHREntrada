package modelo;
import java.io.Serializable;

/**
 *
 * @author Antonio Company Rodriguez
 * 
 * Clase para crear objetos de tipo Jornada con todos sus atributos
 */
public class Jornada implements Serializable{
    private static final long serialVersionUID = 6529685098267757690L;
    
    private String dni;
    private String nom;
    private String apellido;
    private String codicard;
    private String horaentrada;
    private String horasalida;
    private String total;
    private String fecha;

    /**
     * Constructor para objetos de la clase jornada
     *
     * @param dni Dni del empleado
     * @param nom Nombre del empleado
     * @param apellido Apellido del empleado
     * @param horaentrada Hora entrada del empleado en su jornada
     * @param horasalida Hora salida del empleado en su jornada
     * @param total Total tiempo jornada laboral
     * @param fecha Fecha de ejecución de la jornada laboral
     */
    
    public Jornada(String dni, String nom, String apellido, String horaentrada, String horasalida, String total, String fecha) {
        this.dni = dni;
        this.nom = nom;
        this.apellido = apellido;
        this.horaentrada = horaentrada;
        this.horasalida = horasalida;
        this.total = total;
        this.fecha = fecha;
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

    public String getCodicard() {
        return codicard;
    }

    public void setCodicard(String codicard) {
        this.codicard = codicard;
    }

    public String getHoraentrada() {
        return horaentrada;
    }

    public void setHoraentrada(String horaentrada) {
        this.horaentrada = horaentrada;
    }

    public String getHorasalida() {
        return horasalida;
    }

    public void setHorasalida(String horasalida) {
        this.horasalida = horasalida;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    } 
}
