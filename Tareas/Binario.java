/*
Ejercicio 1. Escritura y lectura de datos binarios. 
Guarda varios tipos de datos (número, texto y decimales) en un archivo binario para en el siguiente paso recuperarlos. 
    - Pasos: 
        1. Crea una clase llamada Binario.java 
        2. Usa un DataOutputStream para escribir en el fichero los siguientes datos: 
            - Un número entero (int) 
            - Una cadena de texto (String) 
            - Un número decimal (double) 
        3. Usa DataInputStream para leer esos mismos datos en el mismo orden. 
        4. Muestra el resultado por pantalla.
*/

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Binario {

    public static void main(String[] args) {
        
        // Declaro el archivo binario donde voy a guardar los datos
        String fichero = "datos.bin";

        // Datos que voy a escribir en el archivo
        int numero = 42;
        String texto = "Números binarios";
        double decimal = 1.21;

        // Uso DataOutputStream para escribir los datos en el archivo
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fichero))) {

            // Escribimos los datos en el mismo orden en el que los vamos a leer
            dos.writeInt(numero);
            dos.writeUTF(texto);
            dos.writeDouble(decimal);

            System.out.println("Datos escritos correctamente en " + fichero);

        } catch (IOException e) {
            System.out.println("Error al escribir el archivo");
        }

        // Ahora, uso DataInputStream para leer los datos en el mismo orden en el que los hemos escrito
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fichero))) {

            int numLeido = dis.readInt();
            String textoLeido = dis.readUTF();
            double decimalLeido = dis.readDouble();

            // Mostramos los datos leídos para comprobar
            System.out.println("\nDatos leídos del archivo");
            System.out.println("- Entero: " + numLeido);
            System.out.println("- Texto: " + textoLeido);
            System.out.println("- Decimal: " + decimalLeido);

        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }

    }

}
