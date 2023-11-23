# Información importante sobre el Taller N°2
## Sobre el contexto del Taller
El taller N°2 Impuesto por el Profesor Tomas Reimann en el ramo de Programación Avanzada trata sobre la simulación de una red social donde se podrá interactuar como usuario (persona o empresa) donde este puede venir ya registrado en la base de datos, como también registrarse como uno nuevo. Ademas de enviar o recibir mensajes (como tambien poder verlos en un futuro). Por otro lado, también se pueden agregar entre usuarios (o eliminarse como amigos), como también visualizar el perfil a gusto del usuario (todos los perfiles son publicos). Sin dejar de lado que también cada usuario podrá editar el contenido de su perfil. Por último, toda la información será guardada para que en una proxima ejecución del codigo esta información esté guardada.
> Si elimina el archivo perfiles.json toda la información será perdida y a la hora de volver a ejecutar el codigo, este se comportará como si fuese la primera vez que lo ejecuta.
## Sobre las librerias necesarias para la correcta ejecución
**Para la correcta ejecución del codigo se necesitan de dos librerias las cuales fueron enviadas al correo del ayudante del ramo**
- gson-2.10.1
- stdlib

## Sobre la utilización de metodos relacionados al archivos .json
En este taller se utilizaron metodos para la lectura y actualización de datos mediante un archivo .json ya que representa una forma efectiva de hacerlo. Metodos empleados:
- Gson GSON = new GsonBuilder().setPrettyPrinting().create();
> Linea 24 de la clase SistemaImpl
- cargarInformacion()
> Linea 35 de la clase SistemaImpl
- actualizarInformacion()
> Linea 228 de la clase SistemaImpl

## Sobre los usuarios predeterminados del codigo
Si es tu primera vez ejecutando el codigo, por defecto se cargaran 7 usuarios para que puedas interactuar con un inicio de sesion rapido.
1. `Nombre de usuario:` tahia.    `Contraseña:` 12345678.
2. `Nombre de usuario:` bruce.    `Contraseña:` 123456789.
3. `Nombre de usuario:` Tony.     `Contraseña:` IronMan123.
4. `Nombre de usuario:` plats.    `Contraseña:` plats101.
5. `Nombre de usuario:` Clarck.   `Contraseña:` Superman123.
6. `Nombre de usuario:` Hal.      `Contraseña:` GreenLantern456.
7. `Nombre de usuario:` Emma.     `Contraseña:` SimpleCode456.
> Para obtener mayor información de cada perfil puede ir a la clase SistemaImpl de la linea 40 hasta la 46. Como también, ejecutar el metodo verPerfil().

## Sobre el autor del Taller N°2.
El Taller N°2 fue exclusiva y unicamente realizado por Bruce Munizaga. 

## Sobre el uso de LinkedList y ArrayList
Se decidió optar por usar LinkedList y ArrayList por la eficiencia y efectividad  a la hora de consumir recursos. 

## Sobre el sistema de guardado
El codigo usa un sistema de guardado ramificado que consiste en almacenar los nodos en un archivo .json (perfiles.json). Además el codigo es capaz de guardar datos cuando su ejecución es interrumpida de forma inesperada.

> El archivo perfiles.json será el protagonista del guardado de datos, este se creará en su directorio una vez terminada la primera ejecución (los datos que el usuario haya manipulado durante la primera ejecución también serán guardados en el archivo).
