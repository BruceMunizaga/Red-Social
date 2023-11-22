/**
 * Clase que tiene la ejecucion general del programa
 *
 * @author Bruce Munizaga
 */

package Services;

import Model.Mensaje;
import Model.HerenciaEntidad.Perfil;
import Utils.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.princeton.cs.stdlib.StdIn;
import edu.princeton.cs.stdlib.StdOut;

import java.io.*;
import java.util.LinkedList;

public class SistemaImpl implements Sistema{

    //procesador de JSON
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    //lista de perfiles guardados en el .json
    private Perfil[] perfiles = new Perfil[0];

    //lista de ciudades
    private LinkedList<String> ciudades = new LinkedList<>();

    //usuario logeado
    private Perfil usuario = null;
    @Override
    public void cargarInformacion() throws IOException {
        try{
            // trato de leer los socios y los libros desde el archivo.
            this.perfiles = GSON.fromJson(new FileReader("perfiles.json"), Perfil[].class);
        }catch (FileNotFoundException ex){
            this.perfiles = Utils.append(this.perfiles, new Perfil("tahia","tahia@gmail.com","12345678","Persona","Tocopilla"));
            this.perfiles = Utils.append(this.perfiles, new Perfil("bruce","bruce@gmail.com","123456789","Persona","Santiago"));
            this.perfiles = Utils.append(this.perfiles, new Perfil("Tony","tony@gmail.com","IronMan123","Persona",null));
            this.perfiles = Utils.append(this.perfiles, new Perfil("plats","plats@gmail.com","plats101","Empresa","Antofagasta"));
            this.perfiles = Utils.append(this.perfiles, new Perfil("Clark","clark@gmail.com","Superman123","Persona",null));
            this.perfiles = Utils.append(this.perfiles, new Perfil("Hal","hal@gmail.com","GreenLantern456","Empresa","Coquimbo"));
            this.perfiles = Utils.append(this.perfiles, new Perfil("Emma","emma@gmail.com","SimpleCode456","Empresa","Antofagasta"));
        }
    }

    @Override
    public void ejecucionGeneral() {

        // Agrega las ciudades de Chile a la lista
        ciudades.add("Arica");
        ciudades.add("Iquique");
        ciudades.add("Antofagasta");
        ciudades.add("Calama");
        ciudades.add("Tocopilla");
        ciudades.add("La Serena");
        ciudades.add("Coquimbo");
        ciudades.add("Ovalle");
        ciudades.add("Valparaíso");
        ciudades.add("Viña del Mar");
        ciudades.add("Quilpué");
        ciudades.add("Concón");
        ciudades.add("Rancagua");
        ciudades.add("San Fernando");
        ciudades.add("Talca");
        ciudades.add("Curicó");
        ciudades.add("Chillán");
        ciudades.add("Concepción");
        ciudades.add("Los Ángeles");
        ciudades.add("Temuco");
        ciudades.add("Valdivia");
        ciudades.add("Puerto Montt");
        ciudades.add("Osorno");
        ciudades.add("Puerto Varas");
        ciudades.add("Coyhaique");
        ciudades.add("Punta Arenas");
    }

    @Override
    public void iniciarSesion(SistemaImpl sistema) throws IOException {

        //variables generales que se usaran en la ejecucion
        this.usuario = null;
        boolean encontrado = false;

        //se solicitan los datos
        StdOut.print("Ingrese el nombre de usuario: ");
        String nombreUsuario = StdIn.readString();
        StdOut.print("Ingrese la contraseña: ");
        String contrasenia = StdIn.readString();

        //se busca la coincidencia en la base de datos
        for (int i = 0; i < this.perfiles.length; i++) {
            this.usuario = this.perfiles[i];

            //entra al if si lo encontró
            if (this.usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario) &&
                    this.usuario.getContrasenia().equals(contrasenia)){
                encontrado = true; //booleano en true para no entrar al if de abajo

                //llamamos al metodo del menu con la sesion iniciada
                menuSesionIniciada(sistema);
                break;
            }
        }

        //si no encontro el nombre del usuario y la contraseña, entra al if
        if(!encontrado){
            StdOut.println("Usuario no encontrado.");
        }
    }

    @Override
    public void registroNuevo() {

        //variables generales para el registro de un nuevo usuario
        String opcion = "";
        String tipodePerfil = "";
        String nombreNuevo;
        String correoNuevo;
        String contraseniaNueva;
        while(!opcion.equalsIgnoreCase("1") && !opcion.equalsIgnoreCase("2")){
            StdOut.println("""
                ¿Que tipo de perfil es?
                [1] Persona
                [2] Empresa
                """);
            StdOut.print("Ingrese su opcion: ");
            opcion = StdIn.readString();

            switch (opcion){
                case "1" -> tipodePerfil = "Persona";
                case "2" -> tipodePerfil = "Empresa";
                default -> StdOut.println("Opcion no valida");
            }
        }
        StdOut.print("Ingrese el nombre de usuario: ");
        nombreNuevo = StdIn.readString();
        StdOut.print("Ingrese la contraseña nueva: ");
        contraseniaNueva = StdIn.readString();
        StdOut.print("Ingrese el correo de la cuenta: ");
        correoNuevo = StdIn.readString();
        Utils.validarEmail(correoNuevo);
        this.perfiles = Utils.append(this.perfiles, new Perfil(nombreNuevo,correoNuevo,contraseniaNueva,tipodePerfil, null));
        for (int i = 0; i < this.perfiles.length; i++) {
            if (this.perfiles[i].getNombreUsuario().equalsIgnoreCase(nombreNuevo)){
                this.usuario = this.perfiles[i];
                break;
            }
        }
        this.usuario.verPerfil();


    }

    @Override
    public void enviarSolicitud() {
        Perfil destinatario;
        StdOut.print("Ingrese el nombre del perfil a enviar la solicitud: ");
        String nombreDestinatario = StdIn.readString();
        destinatario = buscarUsuario(nombreDestinatario, this.perfiles);

        if (destinatario == null){
            StdOut.println("Usuario no encontrado");
        }else{

            destinatario.agregarSolicitud(this.usuario);
        }
    }

    @Override
    public void enviarMensaje() throws IOException {

        //variables generales del metodo
        String contenido;
        Perfil destinatario;

        //solicitud de datos del usuario
        StdOut.print("Ingrese el nombre del perfil a enviar un mensaje: ");
        String nombreDestinatario = StdIn.readString();

        //busco al destinatario
        destinatario = buscarUsuario(nombreDestinatario, this.perfiles);

        //si no se encontro se notifica
        if (destinatario == null){
            StdOut.println("Usuario no encontrado");

            //en caso contrario se sigue con la ejecucion
        }else{

            //en caso que el destinatario no somos nosotros mismos se sigue con la ejecucion
            if (this.usuario != destinatario){

                //se solicita el contenido del mensaje
                StdOut.print("Ingrese el contenido del mensaje: ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                contenido = reader.readLine();

                //se crea un mensaje
                Mensaje mensaje = new Mensaje(this.usuario.getNombreUsuario(),destinatario.getNombreUsuario(),contenido);

                //se guardan en las respectivas listas de los involucrados
                this.usuario.agregarMensajeEnviado(mensaje);
                destinatario.agregarMensajeRecibido(mensaje);

                //en caso que el destinatario somos nosotros mismos, se notifica y no se sigue con el metodo
            }else{
                StdOut.println("No puedes enviarte mensajes a ti mismo.");
            }
        }
    }

    @Override
    public Perfil buscarUsuario(String nombreUsuario, Perfil[] perfiles) {
        for (Perfil perfil : perfiles) {
            if (perfil.getNombreUsuario().equals(nombreUsuario)) {
                return perfil;
            }
        }
        return null;
    }

    @Override
    public void actualizarInformacion() throws IOException {

        StdOut.println("Hasta pronto!");
        //guardo los perfiles.
        try (FileWriter writer = new FileWriter("perfiles.json")) {
            GSON.toJson(this.perfiles, writer);
        }
    }

    @Override
    public void menuSesionIniciada(SistemaImpl sistema) throws IOException {
        //opcion a validar en el menu con la sesion ya iniciada
        String opcion = "";

        //funcion del menu con la sesion ya iniciada
        while (!opcion.equalsIgnoreCase("4")){
            StdOut.println("""
                    [+][+][+][+] SESION INICIADA [+][+][+][+]
                    
                    [1] Opciones de Mensajes.
                    [2] Opciones de amigos.
                    [3] Opciones de Perfil.
                    [4] Cerrar Sesion.
                    """);
            StdOut.print("Ingrese su opcion aqui: ");
            opcion = StdIn.readString();

            switch (opcion){
                case "1"-> menuMensaje(sistema);
                case "2"-> menuSolicitud(sistema);
                case "3"-> menuPerfil(sistema);
                case "4"-> StdOut.println("Regresando al menu anterior...");
                default-> StdOut.println("Opcion no valida");
            }
        }
    }

    @Override
    public void menuPerfil(SistemaImpl sistema) {
        //opcion a validar en el menu del perfil
        String opcion = "";

        //funcion del menu del perfil
        while (!opcion.equalsIgnoreCase("8")){
            StdOut.println("""
                    [+][+][+][+] MENU PERFIL [+][+][+][+]
                    
                    [1] Buscar perfil.
                    [2] Ver perfil.
                    [3] Editar nombre.
                    [4] Editar contrasenia.
                    [5] Editar correo.
                    [6] Editar ubicacion.
                    [7] Editar tipo de perfil.
                    [8] Volver al menu anterior.
                    """);
            StdOut.print("Ingrese su opcion aqui: ");
            opcion = StdIn.readString();

            switch (opcion){
                case "1"-> buscarPerfil();
                case "2"-> this.usuario.verPerfil();
                case "3"-> editarNombre();
                case "4"-> editarContrasenia();
                case "5"-> editarCorreo();
                case "6"-> editarUbicacion();
                case "7"-> editarTipoPerfil();
                case "8"-> StdOut.println("Regresando al menu anterior...");
                default-> StdOut.println("Opcion no valida");
            }
        }
    }

    @Override
    public void buscarPerfil() {

        //opcion a validar en el menu del perfil
        String opcion = "";

        //funcion del menu del perfil
        while (opcion.equalsIgnoreCase("")){
            StdOut.println("""
                    [+][+][+][+] BUSQUEDA DE PERFILES [+][+][+][+]
                    
                    [1] Buscar perfil por nombre.
                    [2] Buscar perfil por tipo.
                    [3] Buscar perfil por ubicacion.
                    [4] Volver al menu anterior.
                    """);
            StdOut.print("Ingrese su opcion aqui: ");
            opcion = StdIn.readString();

            switch (opcion){
                case "1"-> buscarPerfilNombre();
                case "2"-> buscarPerfilTipo();
                case "3"-> buscarPerfilUbicacion();
                case "4"-> StdOut.println("Regresando al menu anterior...");
                default-> StdOut.println("Opcion no valida");
            }
        }
    }

    @Override
    public void buscarPerfilUbicacion() {

        //variables generales del metodo
        boolean validador = false;
        int ubicacionNuevaInt = 0;
        int posArregloMaximo = 0;

        //se despliega la lista de ciudades
        StdOut.println("**************************************************");
        StdOut.println("Lista de ubicaciones soportadas: ");
        for (int i = 0; i < this.ciudades.size(); i++) {
            StdOut.println((i+1)+".- "+this.ciudades.get(i));

            //se guarda la cantidad maxima de la lista
            posArregloMaximo++;
        }
        StdOut.println("**************************************************");

        //se solicita el dato al usuario
        StdOut.print("Ingrese el numero de la ubicacion que desea buscar: ");
        String ubicacionNueva = StdIn.readString();

        //se trata de pasar de String a int
        try{
            ubicacionNuevaInt = Integer.parseInt(ubicacionNueva);

            //en caso de que no se pudo se notifica
        }catch (IllegalArgumentException e){
            StdOut.println("Valor no valido");
            validador = true; //valor en true
        }

        //en caso de que si se pudo pasar de String a int
        if (!validador){

            //se valida que el valor este dentro del rango permitido
            if (ubicacionNuevaInt <= 0 || ubicacionNuevaInt > posArregloMaximo){
                StdOut.println("El numero ingresado no existe en el arreglo");

                //en caso de que si este en el rango
            }else{

                //se guarda la ubicacion como String
                ubicacionNueva = this.ciudades.get(ubicacionNuevaInt-1);

                //se recorre el arreglo de perfiles
                for (int i = 0; i < this.perfiles.length; i++) {

                    //se valida que el perfil cumpla con la validacion
                    if (this.perfiles[i].getUbicacion().equalsIgnoreCase(ubicacionNueva)){

                        //se valida que la casilla no sea el usuario
                        if (this.perfiles[i] != this.usuario){

                            //se despliega
                            this.perfiles[i].verPerfil();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void buscarPerfilTipo() {

        //validador para utilizar mas adelante
        boolean validador = true;

        //opcion a validar en el menu del tipo de perfil
        String opcion = "";
        int opcionInt = 0;

        //funcion del menu del tipo de perfil
        while (opcion.equalsIgnoreCase("")){
            StdOut.println("""
                    ¿Que tipo de perfil buscas?
                    
                    [1] Empresa.
                    [2] Persona.
                    """);
            StdOut.print("Ingrese su opcion aqui: ");
            opcion = StdIn.readString();

            //se trata de convertir el String a int
            try{
                opcionInt = Integer.parseInt(opcion);

                //si no se pudo se notifica
            }catch (IllegalArgumentException e){
                StdOut.println("Caracter no valido");
                validador = false; //validador en false
            }

            //si el validador es en true, significa que si pudo cambiar de String a int
            if (validador){

                //en caso de que escogio la opcion uno
                if (opcionInt == 1){

                    //se reccore el arreglo de perfiles
                    for (int i = 0; i < this.perfiles.length; i++) {

                        //si el contenido dentro de la casilla se ajusta a la validacion
                        if (this.perfiles[i].getTipoPerfil().equalsIgnoreCase("Empresa")){

                            //se valida que no sea el mismo usuario
                            if (this.perfiles[i] != this.usuario){

                                //se despliega
                                this.perfiles[i].verPerfil();
                            }
                        }
                    }
                }

                //en caso de que escogio la segunda opcion
                if (opcionInt == 2){

                    //se recorre la lista de perfiles
                    for (int i = 0; i < this.perfiles.length; i++) {

                        //si el contenido dentro de la casilla se ajusta a la validacion
                        if (this.perfiles[i].getTipoPerfil().equalsIgnoreCase("Persona")){

                            //se valida que no sea el mismo usuario
                            if (this.perfiles[i] != this.usuario){

                                //se despliega
                                this.perfiles[i].verPerfil();
                            }
                        }
                    }
                }

                //en caso de que el valor no sea ni uno ni dos, se notifica
                if (opcionInt != 1 && opcionInt != 2){
                    StdOut.println("Valor no valido.");
                }
            }
        }
    }

    @Override
    public void buscarPerfilNombre() {

        //se solicitan los datos al usuario
        StdOut.print("Ingrese el nombre de usuario al que desea buscar");
        String nombreBuscado = StdIn.readString();

        //se busca el perfil
        Perfil perfilBuscado = buscarUsuario(nombreBuscado,this.perfiles);

        //si se encontro, se despliega
        if (perfilBuscado != null){
            perfilBuscado.verPerfil();

            //en caso contrario se notifica
        }else{
            StdOut.println("Perfil no encontrado");
        }

    }

    @Override
    public void editarTipoPerfil() {

        //opcion a validar en el menu del tipo de perfil
        String opcion = "";

        //funcion del menu del tipo de perfil
        while (opcion.equalsIgnoreCase("")){
            StdOut.println("""
                    ¿A que tipo te gustaria identificar a tu perfil?
                    
                    [1] Empresa.
                    [2] Persona.
                    [3] Volver atras.
                    """);
            StdOut.print("Ingrese su opcion aqui: ");
            opcion = StdIn.readString();

            switch (opcion){
                case "1"-> this.usuario.setTipoPerfil("Empresa");
                case "2"-> this.usuario.setTipoPerfil("Persona");
                case "3"-> StdOut.println("Regresando al menu anterior...");
                default-> StdOut.println("Opcion no valida");
            }
        }
    }

    @Override
    public void editarUbicacion() {

        //variables generales del metodo
        boolean validador = false;
        int ubicacionNuevaInt = 0;
        int posArregloMaximo = 0;

        //se despliega la lista de ciudades
        StdOut.println("**************************************************");
        StdOut.println("Lista de ubicaciones soportadas: ");
        for (int i = 0; i < this.ciudades.size(); i++) {
            StdOut.println((i+1)+".- "+this.ciudades.get(i));
            posArregloMaximo++;
        }
        StdOut.println("**************************************************");

        //se solicita el dato al usuario
        StdOut.print("Ingrese la ciudad que desea poner en su ubicacion: ");
        String ubicacionNueva = StdIn.readString();

        //se trata de cambiar de String a int
        try{
            ubicacionNuevaInt = Integer.parseInt(ubicacionNueva);

            //en caso de que no se pudo se notifica
        }catch (IllegalArgumentException e){
            StdOut.println("Valor no valido");
            validador = true; //validador en true
        }

        //en caso de que si se pudo se sigue con la ejecucion
        if (!validador){

            //se valida que el valor este dentro del rango del arreglo
            if (ubicacionNuevaInt <= 0 || ubicacionNuevaInt > posArregloMaximo){
                StdOut.println("El numero ingresado no existe en el arreglo");

                //en caso de que si este dentro del rango
            }else{
                this.usuario.setUbicacion(this.ciudades.get(ubicacionNuevaInt-1));
            }
        }
    }

    @Override
    public void editarCorreo() {

        //se solicita el dato al usuario
        StdOut.print("Ingrese el correo nuevo del perfil: ");
        String emailNuevo = StdIn.readString();

        //se valida que el email cumpla con la estructura definida
        Utils.validarEmail(emailNuevo);

        //se setea el email del usuario
        this.usuario.setEmail(emailNuevo);
    }

    @Override
    public void editarContrasenia() {

        //se solicita el dato al usuario
        StdOut.print("Ingrese la contraseña nueva del perfil: ");
        String contraseniaNueva = StdIn.readString();

        //se setea la contrasenia del usuario
        this.usuario.setContrasenia(contraseniaNueva);
    }

    @Override
    public void editarNombre() {

        //se solicita el dato al usuario
        StdOut.print("Ingrese el nombre nuevo del perfil: ");
        String nuevoNombre = StdIn.readString();

        //se setea el nombre del usuario
        this.usuario.setNombreUsuario(nuevoNombre);
    }

    @Override
    public void menuSolicitud(SistemaImpl sistema) {
        //opcion a validar en el menu de solicitudes
        String opcion = "";

        //funcion del menu de solicitudes
        while (!opcion.equalsIgnoreCase("6")){
            StdOut.println("""
                    [+][+][+][+] MENU AMIGOS [+][+][+][+]
                    
                    [1] Ver amigos.
                    [2] Ver solicitudes.
                    [3] Gestionar solicitudes.
                    [4] Agregar usuario a lista de amigos.
                    [5] Eliminar a un usuario de la lista de amigos
                    [6] Volver al menu anterior.
                    """);
            StdOut.print("Ingrese su opcion aqui: ");
            opcion = StdIn.readString();

            switch (opcion){
                case "1"-> this.usuario.verListaDeAmigos();
                case "2"-> this.usuario.verSolicitudes();
                case "3"-> this.usuario.gestionarSolicitudes(this.perfiles, this.usuario);
                case "4"-> agregarAmigo();
                case "5"-> eliminarAmigo();
                case "6"-> StdOut.println("Regresando al menu anterior...");
                default-> StdOut.println("Opcion no valida");
            }
        }
    }

    @Override
    public void eliminarAmigo() {

        //variable del nombre del perfil a eliminar
        String amigoEliminar;

        //se solicitan los datos desde pantalla
        StdOut.print("Ingrese el nombre del perfil al que desea eliminar de su lista de amigos: ");
        amigoEliminar = StdIn.readString();

        //en caso de que el usuario se ponga a si mismo se notifica
        if (this.usuario.getNombreUsuario().equalsIgnoreCase(amigoEliminar)){
            StdOut.println("No puedes eliminarte a ti mismo");

            //en caso contrario se sigue con la ejecucion
        }else{

            //se trata de eliminar al perfil
            boolean eliminado = this.usuario.eliminarAmigo(amigoEliminar);

            //si lo elimino se notifica
            if (eliminado){
                StdOut.println("Amigo eliminado.");

                //tambien se elimina de la lista del otro usuario
                Perfil otroUsuario;
                for (int i = 0; i < this.perfiles.length; i++) {
                    if (this.perfiles[i].getNombreUsuario().equalsIgnoreCase(amigoEliminar)){
                        otroUsuario = this.perfiles[i];
                        boolean eliminadoDos = otroUsuario.eliminarAmigo(this.usuario.getNombreUsuario());
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void agregarAmigo() {

        //variable donde alojara el nombre del perfil a agregar
        String nombreNuevoAmigo;

        //se solicitan los datos al usuario
        StdOut.print("Ingrese el nombre del perfil al que desea enviarle la solicitud: ");
        nombreNuevoAmigo = StdIn.readString();

        //en caso que el usuario se ponga a si mismo
        if (this.usuario.getNombreUsuario().equalsIgnoreCase(nombreNuevoAmigo)){
            StdOut.println("No puedes agregarte a ti mismo");

        }else{

            //se busca al otro perfil
            Perfil destinatario = buscarUsuario(nombreNuevoAmigo,this.perfiles);

            //si no se encontro se notifica
            if (destinatario == null){
                StdOut.println("Usuario no encontrado.");

                //en caso contrario se sigue con la ejecucion
            }else{

                //se verifica que el perfil no sea amigo del usuario
                boolean validador = this.usuario.buscarAmigo(destinatario.getNombreUsuario());

                //en caso de no ser amigo del usuario, se agrega a la lista de solicitudes pendientes del otro perfil
                if (!validador){
                    destinatario.agregarSolicitud(this.usuario);
                }
            }
        }
    }

    @Override
    public void menuMensaje(SistemaImpl sistema) throws IOException {
        //opcion a validar en el menu de mensajes con la sesion ya iniciada
        String opcion = "";

        //funcion del menu de mensajes con la sesion ya iniciada
        while (!opcion.equalsIgnoreCase("4")){
            StdOut.println("""
                    [+][+][+][+] MENU MENSAJES [+][+][+][+]
                    
                    [1] Ver Mensajes enviados.
                    [2] Ver Mensajes recibidos.
                    [3] Enviar Mensajes.
                    [4] Volver al menu anterior.
                    """);
            StdOut.print("Ingrese su opcion aqui: ");
            opcion = StdIn.readString();

            switch (opcion){
                case "1"-> verMensajesEnviados();
                case "2"-> verMensajesRecibidos();
                case "3"-> enviarMensaje();
                case "4"-> StdOut.println("Regresando al menu anterior...");
                default-> StdOut.println("Opcion no valida");
            }
        }
    }

    @Override
    public void verMensajesEnviados() {
        this.usuario.verListaDeMensajesEnviados();
    }

    @Override
    public void verMensajesRecibidos() {
        this.usuario.verListaDeMensajesRecibidos();
    }
}
