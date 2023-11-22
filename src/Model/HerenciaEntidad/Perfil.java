/**
 * Clase Perfil que guardara los datos del archivo .json
 *
 * @author Bruce Munizaga
 */

package Model.HerenciaEntidad;

import Model.Entidad;
import Model.Mensaje;
import edu.princeton.cs.stdlib.StdIn;
import edu.princeton.cs.stdlib.StdOut;

import java.util.LinkedList;

public class Perfil implements Entidad {

    //nombre del usuario
    private String nombreUsuario;

    //email del usuario
    private String email;

    //contrasenia del usuario
    private String contrasenia;

    //tipo de perfil del usuario (persona o empresa)
    private String tipoPerfil;

    //ubicacion geologica del usuario
    private String ubicacion;

    //amigos registrados en el perfil
    private LinkedList<Perfil> amigos = new LinkedList<>();

    //lista de solicitudes pendientes
    private LinkedList<Perfil> listaSolicitudes = new LinkedList<>();

    //lista de mensajes recibidos al usuario
    private LinkedList<Mensaje> mensajesRecibidos = new LinkedList<>();

    //lista de mensajes enviados del usuario
    private LinkedList<Mensaje> mensajesEnviados = new LinkedList<>();

    /**
     * The constructor
     * @param nombreUsuario a inicializar
     * @param email a inicializar
     * @param contrasenia a inicializar
     * @param tipoPerfil a inicializar
     */
    public Perfil(String nombreUsuario, String email, String contrasenia, String tipoPerfil, String ubicacion) {

        //validacion del largo del nombre que ocupara el usuario
        if (nombreUsuario.length() < 2){
            throw new IllegalArgumentException("El nombre de usuario debe tener un minimo de 2 caractares.");
        }
        this.nombreUsuario = nombreUsuario;

        this.email = email;

        //validacion del largo de la contrasenia que ocupara el usuario
        if (contrasenia.length() < 8){
            throw new IllegalArgumentException("La contraseña del usuario debe tener un minimo de 8 caractares.");
        }
        this.contrasenia = contrasenia;

        this.tipoPerfil = tipoPerfil;

        //en caso de que la ubicacion sea nulo, se setea con una frase
        if (ubicacion == null){
            this.ubicacion = "Ubicacion no ingresada";

            //en caso contrario se setea normal
        }else{
            this.ubicacion = ubicacion;
        }

    }

    @Override
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    @Override
    public void setNombreUsuario(String nombreUsuario) {

        //validacion del largo del nombre que ocupara el usuario
        if (nombreUsuario.length() < 2){
            StdOut.println("Nombre no guardado. El nombre de usuario debe tener un minimo de 2 caractares.");
        }else{
            this.nombreUsuario = nombreUsuario;
        }
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getContrasenia() {
        return this.contrasenia;
    }

    @Override
    public void setContrasenia(String contrasenia) {

        boolean validador = false;

        //validacion del largo de la contrasenia que ocupara el usuario
        if (contrasenia.length() < 8){
            StdOut.println("Contraseña no guardada. La contraseña del usuario debe tener un minimo de 8 caractares.");

            validador = true;
        }

        //validacion si la contrasenia nueva es la misma que la actual
        if (this.contrasenia.equalsIgnoreCase(contrasenia)){
            StdOut.println("Contraseña no guardada. La contraseña nueva debe ser distinta a la actual.");
            validador = true;
        }

        //si no encontro ningun error la contrasenia se cambia
        if (!validador){
            this.contrasenia = contrasenia;
        }
    }

    @Override
    public String getTipoPerfil() {
        return this.tipoPerfil;
    }

    @Override
    public void setTipoPerfil(String tipoPerfil) {
        this.tipoPerfil = tipoPerfil;

        StdOut.println("Perfil actualizado");
    }

    @Override
    public String getUbicacion() {
        return this.ubicacion;
    }

    @Override
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public void verSolicitudes(){

        //variable que alojara la cantidad actual de solicitudes
        int cantSolicitudes = 0;

        //en caso de que no tenga solicitudes se notifica
        if (this.listaSolicitudes.size() == 0){
            StdOut.println("No tienes solicitudes pendientes");

            //en caso de que si tenga se sigue con la ejecucion
        }else{

            //se recorre la lista de solicitudes
            for (int i = 0; i < this.listaSolicitudes.size(); i++) {
                cantSolicitudes++; //se va seteando la cantidad actual de solicitudes
            }
            StdOut.println("Tienes "+cantSolicitudes+" solicitudes:");

            //se despliega una a una en pantalla
            StdOut.println("***********************");
            for (int i = 0; i < this.listaSolicitudes.size(); i++) {
                Perfil usuario = this.listaSolicitudes.get(i);
                String nombre = usuario.getNombreUsuario();

                StdOut.println("Solicitud N°"+(i+1)+":");
                StdOut.println("Nombre: "+nombre);
                StdOut.println("***********************");
            }
        }
    }

    @Override
    public void gestionarSolicitudes(Perfil[] perfiles, Perfil usuario){
        verSolicitudes();

        //cantidad actual de solicitudes
        int cantidadLista = 0;

        //se recorre el arreglo
        for (int i = 0; i < this.listaSolicitudes.size(); i++) {
            cantidadLista++; //se setea la cantidad actual
        }

        //mientras que el arreglo tenga contenido
        if (!(this.listaSolicitudes.size() == 0)){

            //variables generales
            String numeroSolicitud;
            int numeroSolicitudInt = 0;
            boolean validador;

            //bucle que valida el rango de la lista
            do {

                //se trata de convertir el dato solicitado al usuario
                try {
                    StdOut.print("Ingrese el numero de la solicitud que desea gestionar: ");
                    numeroSolicitud = StdIn.readString();
                    numeroSolicitudInt = Integer.parseInt(numeroSolicitud);
                    validador = true;

                    //en caso de que no se pudo se notifica
                } catch (NumberFormatException e) {
                    StdOut.println("Valor no valido");
                    validador = false;
                }

                //en caso de que si se pudo se verifica que no exceda el tamanio maximo de la lista
                if (numeroSolicitudInt > cantidadLista){
                    StdOut.println("Numero de solicitud no existente");
                }

            }while (numeroSolicitudInt <= 0 || numeroSolicitudInt > cantidadLista || !validador);
            numeroSolicitudInt = numeroSolicitudInt - 1;//se setea el numero de la solicitud adaptada al arreglo

            //se recibe el perfil de la solicitud
            Perfil solicitud = this.listaSolicitudes.get(numeroSolicitudInt);
            solicitud.verPerfil(); //se despliega


            //opcion a validar la desicion
            String opcion = "";

            //se le pregunta al usuario sobre la desicion a tomar con la solicitud
            while (opcion.equalsIgnoreCase("")){
                StdOut.println("""
                    ¿Desea agregarlo a su lista de amigos?
                    
                    [1] Si.
                    [2] No.
                    [3] Volver atras.
                    """);
                StdOut.print("Ingrese su opcion aqui: ");
                opcion = StdIn.readString();

                switch (opcion){
                    case "1"-> agregarAmigo(solicitud, perfiles, usuario);
                    case "2"-> eliminarSolicitud(solicitud);
                    case "3"-> StdOut.println("Volviendo al menu anterior...");
                    default-> StdOut.println("Opcion no valida");
                }
            }
        }

    }

    @Override
    public void agregarAmigoIndirectamente(Perfil solicitud, Perfil usuario){

        //se crea un nuevo perfil con los datos minimos del mismo para evitar un error de tipo StackOverflow a
        // la hora de guardarlos en el .json
        Perfil nuevoAmigo = new Perfil(usuario.getNombreUsuario(),usuario.getEmail(),usuario.getContrasenia(),
                usuario.getTipoPerfil(),usuario.getUbicacion());

        //se agrega el perfil a la lista de amigos del usuario
        solicitud.amigos.add(nuevoAmigo);
    }

    @Override
    public void agregarAmigo(Perfil solicitud, Perfil[] perfiles, Perfil usuario) {

        //se crea un nuevo perfil con los datos minimos del mismo para evitar un error de tipo StackOverflow a
        // la hora de guardarlos en el .json
        Perfil nuevoAmigo = new Perfil(solicitud.getNombreUsuario(),solicitud.getEmail(),
                solicitud.getContrasenia(),solicitud.getTipoPerfil(),solicitud.getUbicacion());

        //se agrega el perfil a la lista de amigos del usuario
        this.amigos.add(nuevoAmigo);

        for (int i = 0; i < perfiles.length; i++) {
            if (perfiles[i] == solicitud){
                perfiles[i].agregarAmigoIndirectamente(solicitud, usuario);
                break;
            }
        }
        eliminarSolicitud(solicitud);
    }

    @Override
    public boolean eliminarAmigo(String amigoEliminar){

        //se recorre la lista de amigos del usuario
        for (int i = 0; i < this.amigos.size(); i++) {

            //si lo encontro
            if (this.amigos.get(i).getNombreUsuario().equalsIgnoreCase(amigoEliminar)){
                this.amigos.remove(i); //se elimina al amigo
                return true;
            }
        }

        StdOut.println("El usuario no se encuentra en tu lista de amigos");
        return false;
    }

    @Override
    public void eliminarSolicitud(Perfil solicitud) {
        this.listaSolicitudes.remove(solicitud);
    }

    @Override
    public void agregarSolicitud(Perfil solcitud){

        //booleano para saber si el perfil ya se encuentra alojado en este usuario
        boolean encontrado = false;

        //lo busca en la lista de amigos
        for (int i = 0; i < this.amigos.size(); i++) {

            //si lo encontro lo informa
            if (this.amigos.get(i) == solcitud){

                //booleano en true porque lo encontro
                encontrado = true;
                StdOut.println("Ya son amigos.");
                break;
            }
        }

        //lo busca en la lista de solicitudes pendientes
        for (int i = 0; i < this.listaSolicitudes.size(); i++) {

            //si lo encontro lo informa
            if (this.listaSolicitudes.get(i) == solcitud){

                //booleano en true porque lo encontro
                encontrado = true;
                StdOut.println("Ya le haz enviado una solicitud de amistad.");
                break;
            }
        }

        //si no lo encontro en las dos listas, se agrega en la lista de solicitudes pendientes
        if (!encontrado){
            listaSolicitudes.add(solcitud);
            StdOut.println("Solicitud enviada");
        }
    }

    @Override
    public void verListaDeAmigos(){

        //variable que contiene la cantidad actual de amigos del usuario
        int cantAmigos = 0;

        //si no tiene amigos se notifica
        if (this.amigos.size() == 0){
            StdOut.println("No tienes amigos guardados");

            //en caso contrario
        }else{

            //se recorre el arreglo para saber su cantidad actual
            for (int i = 0; i < this.amigos.size(); i++) {
                cantAmigos++;
            }

            //se notifica la cantidad de amigos
            StdOut.println("Tienes "+cantAmigos+" amigos:");


            //se despliega el contenido de la lista de amigos del usuario
            for (int i = 0; i < this.amigos.size(); i++) {
                Perfil amigo = this.amigos.get(i);
                StdOut.println("Amigo guardado N°"+(i+1)+": ");
                amigo.verPerfil();
            }
        }
    }

    @Override
    public void verListaDeMensajesEnviados(){

        //variable que tendra la cantidad actual de mensajes enviados por el usuario
        int cantMensajes = 0;

        //si no ha enviado mensajes el usuario se notifica
        if (this.mensajesEnviados.size() == 0){
            StdOut.println("No tienes mensajes enviados");

            //en caso contrario
        }else{

            //se recorre el arreglo para saber su cantidad actual
            for (int i = 0; i < this.mensajesEnviados.size(); i++) {
                cantMensajes++;
            }

            //se notifica la cantidad actual de mensajes enviados
            StdOut.println("Tienes "+cantMensajes+" mensajes enviados:");


            //se despliegan todos los mensajes enviados por el usuario
            for (int i = 0; i < this.mensajesEnviados.size(); i++) {
                Mensaje mensaje = this.mensajesEnviados.get(i);
                StdOut.println("Mensaje enviado N°"+(i+1)+": ");
                mensaje.desplegarMensajeEnviado();
            }
        }
    }

    @Override
    public void agregarMensajeEnviado(Mensaje mensaje){
        this.mensajesEnviados.add(mensaje);
    }

    @Override
    public void verListaDeMensajesRecibidos(){

        //variable que tendra la cantidad actual de mensajes recibidos
        int cantMensajes = 0;

        //en caso de que no tenga mensajes recibidos se notifica
        if (this.mensajesRecibidos.size() == 0){
            StdOut.println("No tienes mensajes recibidos");

            //en caso contrario
        }else{

            //se recorre el arreglo para saber la cantidad actual de mensajes recibidos
            for (int i = 0; i < this.mensajesRecibidos.size(); i++) {
                cantMensajes++;
            }

            //se notifica la cantidad actual de emnsajes recibidos
            StdOut.println("Tienes "+cantMensajes+" mensajes recibidos:");


            //se despliegan todos los mensajes recibidos
            for (int i = 0; i < this.mensajesRecibidos.size(); i++) {
                Mensaje mensaje = this.mensajesRecibidos.get(i);
                StdOut.println("Mensaje recibido N°"+(i+1)+": ");
                mensaje.desplegarMensajeRecibido();
            }
        }
    }

    @Override
    public void agregarMensajeRecibido(Mensaje mensaje){
        this.mensajesRecibidos.add(mensaje);
    }

    @Override
    public void verPerfil(){


        //se despliega el contenido del perfil del usuario exceptuando la contrasenia por seguridad
        StdOut.println("*************************************");
        StdOut.println("Nombre: "+this.nombreUsuario);
        StdOut.println("Correo: "+this.email);
        StdOut.println("Tipo de perfil: "+this.tipoPerfil);
        StdOut.println("Ubicacion: "+this.ubicacion);
        StdOut.println("*************************************");
    }

    @Override
    public boolean buscarAmigo(String amigoBuscado){

        //en caso de que no tenga amigos
        if (this.amigos.size() == 0){
            return false;

            //en caso de que si tenga
        }else{

            //se recorre la lista de amigos
            for (int i = 0; i < this.amigos.size(); i++) {

                //si lo encontro lo notifica
                if (this.amigos.get(i).getNombreUsuario().equalsIgnoreCase(amigoBuscado)){
                    StdOut.println("El usuario ya esta en la lista de amigos");
                    return true;
                }
            }
        }

        //en caso de que no lo encontro en la lista de amigos, lo busca en las solicitudes pendientes

        //si no tiene solicitudes
        if (this.listaSolicitudes.size() == 0){
            return false;

            //en caso contrario
        }else{

            //se recorre el arreglo de las solicitudes
            for (int i = 0; i < this.listaSolicitudes.size(); i++) {

                //si lo encontro lo notifica
                if (this.listaSolicitudes.get(i).getNombreUsuario().equalsIgnoreCase(amigoBuscado)){
                    StdOut.println("El usuario ya esta en la lista de solicitudes pendientes");
                    return true;
                }
            }
        }

        //si no lo encontro en ninguna de las dos listas, retorna false
        return false;
    }
}
