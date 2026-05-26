package com.tarea07.libros.controller;

import com.tarea07.libros.model.Libro;
import com.tarea07.libros.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador del catálogo de libros:
@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    // Devolvemos todos los libros:
    @GetMapping
    public ResponseEntity<List<Libro>> obtenerTodos() {
        List<Libro> libros = libroService.obtenerTodos();
        return ResponseEntity.ok(libros);
    }

    // Devuelve un libro por su id:
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerPorId(@PathVariable String id) {
        return libroService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crea un libro:
    @PostMapping
    public ResponseEntity<Libro> crear(@RequestBody Libro libro) {
        Libro libroGuardado = libroService.crear(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(libroGuardado);
    }

    // Actualiza un libro por id:
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizar(
            @PathVariable String id,
            @RequestBody Libro libroNuevo) {
        return libroService.actualizar(id, libroNuevo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Elimina un libro por id:
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        if (libroService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // FILTROS

    // Filtramos por género:
    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Libro>> obtenerPorGenero(@PathVariable String genero) {
        return ResponseEntity.ok(libroService.obtenerPorGenero(genero));
    }

    // Filtramos por autor:
    @GetMapping("/autor/{autor}")
    public ResponseEntity<List<Libro>> obtenerPorAutor(@PathVariable String autor) {
        return ResponseEntity.ok(libroService.obtenerPorAutor(autor));
    }

    // Filtramos por disponibilidad:
    @GetMapping("/disponibles")
    public ResponseEntity<List<Libro>> obtenerPorDisponibilidad(
            @RequestParam(defaultValue = "true") boolean disponible) {
        return ResponseEntity.ok(libroService.obtenerPorDisponibilidad(disponible));
    }

    // Filtramos por precio:
    @GetMapping("/precio")
    public ResponseEntity<List<Libro>> obtenerPorRangoPrecio(
            @RequestParam double min,
            @RequestParam double max) {
        return ResponseEntity.ok(libroService.obtenerPorRangoPrecio(min, max));
    }

    // Filtramos por páginas:
    @GetMapping("/paginas")
    public ResponseEntity<List<Libro>> obtenerPorRangoPaginas(
            @RequestParam int min,
            @RequestParam int max) {
        return ResponseEntity.ok(libroService.obtenerPorRangoPaginas(min, max));
    }

    // Filtramos por título:
    @GetMapping("/buscar")
    public ResponseEntity<List<Libro>> buscarPorTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(libroService.buscarPorTitulo(titulo));
    }
}
