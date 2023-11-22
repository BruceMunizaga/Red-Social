package Model;
import Model.HerenciaEntidad.Perfil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Solicitud {

    //remitente de la solicitud
    private final Perfil remitente;

    //destinatario de la solicitud
    private final Perfil destinatario;

    //estado de la solicitud
    private String estado;

    //fecha de creacion de la solicitud
    private final String fechaDeCreacion;

    //ultima actualizacion de la solicitud
    private String ultimaActualizacion;

    /**
     * The constructor
     *
     * @param remitente a inicializar
     * @param destinatario a inicializar
     */
    public Solicitud(Perfil remitente, Perfil destinatario) {

        //validacion del remitente y el destinatario
        if (remitente == null || destinatario == null){
            throw new IllegalArgumentException("Remitente o Destinatario nulo");
        }
        this.remitente = remitente;
        this.destinatario = destinatario;

        //se setea la fecha actual de la solicitud
        LocalDate hoy = LocalDate.now();
        this.fechaDeCreacion = hoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        this.ultimaActualizacion = this.fechaDeCreacion;
    }

    public String getEstado() {
        //se setea la ultima actualizacion de la solicitud por consultar su estado
        setUltimaActualizacion();
        return this.estado;
    }

    public void setEstado(String estado) {
        //se setea la ultima actualizacion de la solicitud por setear su estado
        setUltimaActualizacion();
        this.estado = estado;
    }

    public String getFechaDeCreacion() {
        return this.fechaDeCreacion;
    }

    public String getUltimaActualizacion() {
        return this.ultimaActualizacion;
    }

    public void setUltimaActualizacion() {
        //se setea la ultima actualizacion de la solicitud
        LocalDate hoy = LocalDate.now();
        this.ultimaActualizacion = hoy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
