package com.tarea07.libros.service;

import com.tarea07.libros.model.Libro;

import java.util.List;
import java.util.Optional;

public interface LibroService {

    List<Libro> obtenerTodos();
    Optional<Libro> obtenerPorId(String id);
    Libro crear(Libro libro);
    Optional<Libro> actualizar(String id, Libro libro);
    boolean eliminar(String id);

    // Consultas:
    List<Libro> obtenerPorGenero(String genero);
    List<Libro> obtenerPorAutor(String autor);
    List<Libro> obtenerPorDisponibilidad(boolean disponible);
    List<Libro> obtenerPorRangoPrecio(double min, double max);
    List<Libro> obtenerPorRangoPaginas(int min, int max);
    List<Libro> buscarPorTitulo(String titulo);
}
