/**
 * The Main
 *
 * @author Bruce Munizaga
 */

import Services.SistemaImpl;
import edu.princeton.cs.stdlib.StdIn;
import edu.princeton.cs.stdlib.StdOut;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //se instancia el sistema del programa
        SistemaImpl sistema = new SistemaImpl();
        sistema.cargarInformacion();
        //menu general del programa
        menuGeneral(sistema);
    }

    /**
     * menu general del programa
     * @param sistema a utilizar
     * @throws IOException en caso de error de compilacion
     */
    public static void menuGeneral(SistemaImpl sistema) throws IOException {

        //se carga la lista de ciudades
        sistema.ejecucionGeneral();

        //opcion a validar en el menu principal
        String opcion = "";

        //funcion del menu principal
        while (!opcion.equalsIgnoreCase("3")){
            StdOut.println("""
                    [+][+][+][+] BIENVENIDO/A [+][+][+][+]
                    
                    [1] Iniciar sesion.
                    [2] Nuevo usuario.
                    [3] Cerrar programa.
                    """);
            StdOut.print("Ingrese su opcion aqui: ");
            opcion = StdIn.readString();

            switch (opcion){
                case "1"-> sistema.iniciarSesion(sistema);
                case "2"-> sistema.registroNuevo();
                case "3"-> sistema.actualizarInformacion();
                default-> StdOut.println("Opcion no valida");
            }
        }
        StdOut.println("Hasta pronto!");
    }
}