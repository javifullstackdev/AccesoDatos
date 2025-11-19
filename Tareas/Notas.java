

/*
 * Ejercicio 2: Ficheros de texto
 * Crea un nuevo archivo Java llamado Notas.java
 * Pide al usuario que introduzca 3 frases desde teclado (puedes usar Scanner)
 * Escribe esas 3 frases em un fichero llamado frases.txt (una por línea)
 * Luego, vuelve a abrir ese mismo fichero y lee su contenido línea a línea
 * Muestra cada línea por pantalla con un número delante
 */

import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.io.BufferedReader;

public class Notas {

    public static void main(String[] args) {
        
        // Creamos el Scanner para poder pedir al usuario las 3 frases
        Scanner scanner = new Scanner(System.in);

        // Pedimos al usuario que introduzca las 3 frases
        String[] frases = new String[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("Introduce la frase número " + (i + 1) + ": " );
            frases[i] = scanner.nextLine();
        }

        // Escribimos las 3 frases en frases.txt
        Path rutaFichero = Paths.get("frases.txt");
        try (BufferedWriter bw = Files.newBufferedWriter(rutaFichero, StandardCharsets.UTF_8)) {
            for (String linea : frases) {
                bw.write(linea);
                bw.newLine();
            }
            System.out.println("Se han escrito las frases en: " + rutaFichero.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al escribir el fichero: " + e.getMessage());
            scanner.close();
            return; // si ha fallado la escritura, no seguimos
        }

        // Abrimos el fichero, leemos línea a línea y mostramos cada línea con un número delante:
        try (BufferedReader br = Files.newBufferedReader(rutaFichero, StandardCharsets.UTF_8)) {
            String linea;
            int numero = 1;
            System.out.println("\nContenido del fichero:");
            // Utilizamos un bucle while para ir escribiendo cada línea:
            while ((linea = br.readLine()) != null) {
                System.out.println(numero + ". " + linea);
                numero++;
            }
        }  catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
            // Cerramos el scanner
            scanner.close();    
    }
}
