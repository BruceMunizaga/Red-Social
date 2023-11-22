/**
 * Interfaz utilizada en la clase Perfil
 *
 * @author Bruce munizaga
 */
package Model;

import Model.HerenciaEntidad.Perfil;

public interface Entidad {

    /**
     * Funcion que retorna el nombre del usuario
     * @return el nombre
     */
    String getNombreUsuario();

    /**
     * metodo que setea el nombre del usuario
     * @param nombreUsuario a cambiar
     */
    void setNombreUsuario(String nombreUsuario);

    /**
     * funncion que retorna el email del usuario
     * @return el email
     */
    String getEmail();

    /**
     * metodo que setea el email
     * @param email a cambiar
     */
    void setEmail(String email);

    /**
     * funcion que retorna la contrasenia del usuario
     * @return la contrasenia
     */
    String getContrasenia();

    /**
     * metodo que setea la contrasenia del usuario
     * @param contrasenia a setear;
     */
    void setContrasenia(String contrasenia);

    /**
     * funcion que retorna el tipo de perfil del usuario (persona o empresa)
     * @return el tipo de perfil
     */
    String getTipoPerfil();

    /**
     * metodo que setea el tipo de perfil del usuario
     * @param tipoPerfil a cambiar
     */
    void setTipoPerfil(String tipoPerfil);

    /**
     * funcion que retorna la ubicacion del usuario
     * @return la ubicacion
     */
    String getUbicacion();

    /**
     * metodo que setea la ubicacion del usuario
     * @param ubicacion a cambiar
     */
    void setUbicacion(String ubicacion);

    /**
     * metodo que despliega la lista de solicitudes que posee el usuario
     */
    void verSolicitudes();

    /**
     * metodo que gestiona las solicitudes del usuario
     * @param perfiles para saber quien envio la solicitud
     * @param usuario para usarlo mas adelante
     */
    void gestionarSolicitudes(Perfil[] perfiles, Perfil usuario);

    /**
     * metodo que agrega un usuario al aceptar la solicitud de este mismo
     * @param solicitud se le agrega a amigo
     * @param usuario se le agrega a amigo
     */
    void agregarAmigoIndirectamente(Perfil solicitud, Perfil usuario);

    /**
     * metodo que agrega un amigo a la lista
     * @param solicitud a agregar a la lista de amigos
     * @param perfiles para saber su perfil
     * @param usuario se le agregara un amigo
     */
    void agregarAmigo(Perfil solicitud, Perfil[] perfiles, Perfil usuario);

    /**
     * funcion que elimina a un amigo de la lista
     * @param amigoEliminar a eliminar
     * @return true si lo elimino
     */
    boolean eliminarAmigo(String amigoEliminar);

    /**
     * metodo que elimina una solicitud en la lista de solicitudes pendientes
     * @param solicitud a eliminar
     */
    void eliminarSolicitud(Perfil solicitud);

    /**
     * metodo que agrega una solicitud a la lista de solicitudes
     * @param solcitud a agregar
     */
    void agregarSolicitud(Perfil solcitud);

    /**
     * metodo que despliega la lista de amigos que tiene el usuario
     */
    void verListaDeAmigos();

    /**
     * metodo que despliega la lista de mensajes enviados del usuario
     */
    void verListaDeMensajesEnviados();

    /**
     * metodo que agrega un mensaje a la lista de mensajes enviados
     * @param mensaje a agregar a la lista
     */
    void agregarMensajeEnviado(Mensaje mensaje);

    /**
     * metodo que despliega la lista de mensajes recibidos
     */
    void verListaDeMensajesRecibidos();

    /**
     * metodo que agrega un mensaje a la lista de mensajes recibidos
     * @param mensaje a agregar a la lista
     */
    void agregarMensajeRecibido(Mensaje mensaje);

    /**
     * metodo que despliega el perfil
     */
    void verPerfil();

    /**
     * funcion que busca un amigo en la lista de amigos
     * @param amigoBuscado a buscar
     * @return true si lo encontro
     */
    boolean buscarAmigo(String amigoBuscado);
}
