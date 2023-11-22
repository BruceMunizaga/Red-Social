/**
 * Clase que construira un mensaje
 *
 * @author Bruce munizaga
 */

package Model;

import edu.princeton.cs.stdlib.StdOut;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Mensaje {

    //remitente del mensaje a enviar
    private final String remitente;

    //destinatario del mensaje
    private final String destinatario;

    //contenido del mensaje
    private final String contenido;

    //hora de envio del mensaje
    private final String horaDeEnvio;

    //fecha de envio del mensaje
    private final String fechaDeEnvio;

    /**
     * The constructor
     *
     * @param remitente a guardar
     * @param destinatario a guardar
     * @param contenido a guardar
     */
    public Mensaje(String remitente, String destinatario, String contenido) {

        //se valida que el remitente o el destinatario siempre tenga contenido
        if (remitente == null || destinatario == null){
            throw new IllegalArgumentException("Remitente o Destinatario nulo");
        }
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.contenido = contenido;

        //se captura la fecha actual
        LocalDate fecha = LocalDate.now();
        this.fechaDeEnvio = fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        //se captura la hora actual
        LocalTime hora = LocalTime.now();
        this.horaDeEnvio = hora.format(DateTimeFormatter.ofPattern("HH:mm"));

    }

    /**
     * metodo que desplegara el contenido de un mensaje enviado
     */
    public void desplegarMensajeEnviado(){

        //se despliega el mensaje enviado
        StdOut.println("********************************");
        StdOut.println("Destinatario: "+this.destinatario);
        StdOut.println("Contenido: "+this.contenido);
        StdOut.println("Fecha de envio: "+this.fechaDeEnvio);
        StdOut.println("Hora de envio: "+this.horaDeEnvio);
        StdOut.println("********************************");
    }

    /**
     * metodo que desplegara el contenido de un mensaje recibido
     */
    public void desplegarMensajeRecibido(){

        //se despliega el mensaje recibido
        StdOut.println("********************************");
        StdOut.println("Remitente: "+this.remitente);
        StdOut.println("Contenido: "+this.contenido);
        StdOut.println("Fecha de recibo: "+this.fechaDeEnvio);
        StdOut.println("Hora de recibo: "+this.horaDeEnvio);
        StdOut.println("********************************");
    }
}
