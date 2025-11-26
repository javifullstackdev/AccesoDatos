/*
 * Ejercicio 2. Leer el fichero peliculas.json 
 * generado en el ejercicio anterior y mostrar su contenido por pantalla.
 */

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class LeerJSON {

    public static void main(String[] args) {
        
        try {

            // Primero, leemos todo el contendio del archivo peliculas.json a un String
            String contenido = new String(Files.readAllBytes(Paths.get("peliculas.json")));

            // Convertimos el contenido a un JSONArray porque contiene una lista de películas
            JSONArray array = new JSONArray(contenido);

            System.out.println("Contenido de peliculas.json:\n");

            // Ahora, recorremos el array y mostramos los datos de cada película
            for(int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String titulo = obj.getString("titulo");
                String director = obj.getString("director");
                int anio = obj.getInt("anio");

                System.out.println("Película " + (i + 1));
                System.out.println("- Título: " + titulo);
                System.out.println("- Director: " + director);
                System.out.println("- Año: " + anio);
                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("Error al leer peliculas.json: " + e.getMessage());
        }
    }
    
}
