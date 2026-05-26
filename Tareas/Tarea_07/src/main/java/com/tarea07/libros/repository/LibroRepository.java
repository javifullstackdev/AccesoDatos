package com.tarea07.libros.repository;

import com.tarea07.libros.model.Libro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends MongoRepository<Libro, String> {

    // Filtramos por género:
    List<Libro> findByGeneroContainingIgnoreCase(String genero);

    // Filtramos por autor:
    List<Libro> findByAutorContainingIgnoreCase(String autor);

    // Filtramos por disponibilidad:
    List<Libro> findByDisponible(boolean disponible);

    // Filtramos por precio:
    List<Libro> findByPrecioBetween(double min, double max);

    // Filtramos por páginas:
    List<Libro> findByPaginasBetween(int min, int max);

    // Filtramos por título:
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
}
