

/*
 * Ejercicio 1: Sistema de ficheros
 * Crea un nuevo fichero Java llamado ExploraRuta.java en tu carpeta de la asignatura.
 * Pide al usuario (por argumento) una ruta de carpeta o fichero
 * Si no existe, que muestre el mensaje: "La ruta no existe"
 * Si es un fichero, que muestre:
 *      - Su nombre
 *      - Su tamaño en bytes
 *      - Si se puede leer y escribir
 * Si es un directorio, que muestre:
 *      - Cuantos elementos tiene
 *      - El nombre de cada uno
*/

import java.io.File;

public class ExploraRuta {

    public static void main(String[] args) {
        
        // Comprobamos que el usuario ha pasado un argumento, y si no hay:
        if (args.length == 0) {
            System.out.println("Uso: java ExploraRuta <ruta a fichero o directorio>");
            return;
        }

        //Si ha pasado un argumento, creamos un objeto File a partir de la ruta recibida:
        String ruta = args[0];
        File f = new File(ruta);

        // Comprobamos que existe:
        if (!f.exists()) {
            System.out.println("La ruta no existe");
            return;
        }

        // Si es un fichero, mostramos los datos que nos piden:
        if (f.isFile()) {
            System.out.println("Información del fichero:");
            System.out.println("Nombre: " + f.getName());
            System.out.println("Tamaño (bytes): " + f.length());
            System.out.println("¿Se puede leer?: " + (f.canRead() ? "Sí" : "No"));
            System.out.println("¿Se puede escribir?: " + (f.canWrite() ? "Sí" : "No"));
        }

        // Si es un directorio:
        else if (f.isDirectory()){
            System.out.println("Información del directorio:");
            
            // Comprobamos que tiene elementos:
            
            File[] elementos = f.listFiles();
            // Primero comprobamos que podemos acceder al directorio:
            if (elementos == null) {
                System.out.println("No tienes permiso para acceder a los elementos");
                return;
            } else {
                System.out.println("Número de elementos: " + elementos.length);
                // Si tenemos permisos, comprobamos si el directorio está vacío, o tiene elementos:
                if (elementos.length == 0) {
                    System.out.println("El directorio está vacío");
                }
                // Si no está vacío, mostramos el nombre de cada uno:
                System.out.println("Nombre de los elementos del directorio: ");
                for (File e : elementos) {
                    System.out.println("- " + e.getName());
                }
            }
        } else {
            System.out.println("La ruta existe, pero no es ni un fichero ni un directorio");
        }
    }
}
