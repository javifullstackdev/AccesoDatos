package es.ejercicio.videojuegos_api.controller;

import es.ejercicio.videojuegos_api.model.Videojuego;
import es.ejercicio.videojuegos_api.service.VideojuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    @Autowired
    private VideojuegoService service;

    // 1. CREAR un videojuego
    @PostMapping
    public ResponseEntity<Videojuego> crear(@Valid @RequestBody Videojuego v) { // <--- Añadimos @Valid
        return new ResponseEntity<>(service.create(v), HttpStatus.CREATED);
    }

    // 2. LISTAR todos
    @GetMapping
    public List<Videojuego> listar() {
        return service.findAll();
    }

    // 3. BUSCAR por id
    @GetMapping("/{id}")
    public ResponseEntity<Videojuego> buscarPorId(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. ACTUALIZAR por id
    @PutMapping("/{id}")
    public ResponseEntity<Videojuego> actualizar(@PathVariable Long id, @RequestBody Videojuego v) {
        try {
            return ResponseEntity.ok(service.update(id, v));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. ELIMINAR por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 6. FILTRAR por plataforma
    @GetMapping("/plataforma/{plataforma}")
    public List<Videojuego> filtrarPorPlataforma(@PathVariable String plataforma) {
        return service.findByPlataforma(plataforma);
    }

    // 7. FILTRAR por puntuación mínima
    @GetMapping("/puntuacion/{min}")
    public List<Videojuego> filtrarPorPuntuacion(@PathVariable double min) {
        return service.findByPuntuacionMinima(min);
    }
}
