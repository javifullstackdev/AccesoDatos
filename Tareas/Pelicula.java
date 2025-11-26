
/*
 * El objetivo de esta tarea será trabajar con ficheros JSON 
 * para guardar y leer informarión estructurada desde Java.
 * Tu modelo será una clase 'Pelicula', que represente información básica de una película: 
 * título, director y año de estreno.
 */

public class Pelicula {


    private String titulo;
    private String director;
    private int anio;

    // Constructor

    public Pelicula(String titulo, String director, int anio) {
        this.titulo = titulo;
        this.director = director;
        this.anio = anio;
    }

    // Getters

    public String getTitulo() {
        return titulo;
    }

    public String getDirector() {
        return director;
    }

    public int getAnio() {
        return anio;
    }
    
}
