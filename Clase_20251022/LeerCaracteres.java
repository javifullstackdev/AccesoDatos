
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class LeerCaracteres {
    public static void main(String[] args) {
        
        String ruta = "texto.txt";  // Fichero de texto en la carpeta
        File f = new File(ruta);  // Creamos el objeto File
        try {
            FileReader fr = new FileReader(f);  // Creamos el FileReader
            int caracter;
            // Leemos carácter a carácter hasta el final del fichero
            while ((caracter = fr.read()) != -1) {
                System.out.print((char) caracter);  // Convertimos el entero a carácter y lo mostramos
            }
            fr.close();  // Cerramos el FileReader
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}
