/**
 * Interfaz del sistema implementado por la clase SistemaImpl
 *
 * @author Bruce Munizaga
 */

package Services;

import Model.HerenciaEntidad.Perfil;

import java.io.IOException;

public interface Sistema {

    /**
     * metodo que permitira cargar la base de datos destinada para este programa
     */
    void cargarInformacion() throws IOException;
    /**
     * metodo que tendra la funcion de cargar la lista de ciudades
     */
    void ejecucionGeneral();

    /**
     * metodo que facilitara el inicio de sesion del usuario
     */
    void iniciarSesion(SistemaImpl sistema) throws IOException;

    /**
     * metodo que facilitara el registro de un nuevo usuario
     */
    void registroNuevo();

    /**
     * metodo que permitira enviar solicitudes entre usuarios
     */
    void enviarSolicitud();

    /**
     * metodo que permitira enviar mensajes entre usuarios
     */
    void enviarMensaje() throws IOException;

    /**
     * metodo que permitira buscar usuarios en la base de datos
     */
    Perfil buscarUsuario(String nombreUsuario, Perfil[] perfiles) throws IOException;

    /**
     * metodo que permitira actualizar la base de datos del programa
     */
    void actualizarInformacion() throws IOException;

    /**
     * metodo que permitira interactuar con el programa una vez ya iniciada sesion
     * @param sistema a utilizar
     */
    void menuSesionIniciada(SistemaImpl sistema) throws IOException;

    /**
     * metodo que permitira interactuar con el perfil una vez ya iniciada sesion
     * @param sistema a utilizar
     */
    void menuPerfil(SistemaImpl sistema);

    /**
     * metodo que permitira interactuar con las solicitudes de un perfil
     * @param sistema a utilizar
     */
    void menuSolicitud(SistemaImpl sistema);

    /**
     * metodo que permitira interactuar con los mensajes de un perfil
     * @param sistema a utilizar
     */
    void menuMensaje(SistemaImpl sistema) throws IOException;

    /**
     * metodo que permitira al usuario eliminar un perfil de su lista de amigos
     */
    void eliminarAmigo();

    /**
     * metodo que permitira al usuario enviar una solicitud de amistad a otro usuario
     */
    void agregarAmigo();

    /**
     * metodo que permitira ver la lista de mensajes enviados del usuario
     */
    void verMensajesEnviados();

    /**
     * metodo que permitira ver la lista de mensajes recibidos del usuario
     */
    void verMensajesRecibidos();

    /**
     * metodo que despliega un menu con parametros de busqueda de perfiles
     */
    void buscarPerfil();

    /**
     * metodo que facilita la busqueda de perfiles mediante la coincidencia de su ubicacion
     */
    void buscarPerfilUbicacion();

    /**
     * metodo que facilita la busqueda de perfiles mediante la coincidencia de su tipo
     */
    void buscarPerfilTipo();

    /**
     * metodo que facilita la busqueda de perfiles mediante la coincidencia de su nombre
     */
    void buscarPerfilNombre();

    /**
     * metodo que despliega un menu con opciones para editar el perfil
     */
    void editarTipoPerfil();

    /**
     * metodo que facilita la edicion de la ubicacion del usuario
     */
    void editarUbicacion();

    /**
     * metodo que facilita la edicion del correo del usuario
     */
    void editarCorreo();

    /**
     * metodo que facilita la edicion de la contrasenia del usuario
     */
    void editarContrasenia();

    /**
     * metodo que facilita la edicion del  nombre del usuario
     */
    void editarNombre();
}
