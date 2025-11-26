
/* Ejercicio 1. Crear un programa en Java 
 * que genere un fichero JSON llamado peliculas.json con la información de varias películas.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class EscribirJSON {

    public static void main(String[] args) {

        // Nota: Meto el .jar en Referenced Libraries (en mi JAVA PROYECTS)
        
        // Creamos una lista de películas para escribir en el JSON
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        peliculas.add(new Pelicula("El Primer Vengador", "Joe Johnston", 2011));
        peliculas.add(new Pelicula("Black Widow", "Cate Shortland", 2021));
        peliculas.add(new Pelicula("Avengers: Endgame", "Anthony Russo y Joe Russo", 2019));

        // Usamos un JSONArray para escribir varias películas
        JSONArray arrayPeliculas = new JSONArray();

        // Convertimos cada objeto Pelicula en un JSONObject para meterlo en el JSON
        for (Pelicula p : peliculas) {
            JSONObject obj = new JSONObject();
            obj.put("titulo", p.getTitulo());
            obj.put("director", p.getDirector());
            obj.put("anio", p.getAnio());

            arrayPeliculas.put(obj);
        }

        // Ahora, escribimos el array entero en peliculas.json
        try (FileWriter file = new FileWriter("peliculas.json")) {
            file.write(arrayPeliculas.toString(4));
            System.out.println("Archivo peliculas.json generado");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo peliculas.json: " + e.getMessage());
        }

        /*
         * Nota: Tras ejecutarlo, 
         * he visto que podría haber hecho con un LinkedHashMap para que en el archivo peliculas.json, 
         * las claves salieran en orden: película, director, anio
         * pero en principio, esto no debería afectar al programa
        */ 
    }
    
}
