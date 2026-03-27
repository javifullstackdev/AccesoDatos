package es.ejercicio.videojuegos_api.service;

import es.ejercicio.videojuegos_api.model.Videojuego;
import java.util.List;
import java.util.Optional;

public interface VideojuegoService {
    List<Videojuego> findAll();
    Optional<Videojuego> findById(Long id);
    Videojuego create(Videojuego v);
    Videojuego update(Long id, Videojuego v);
    void delete(Long id);
    List<Videojuego> findByPlataforma(String plataforma);
    List<Videojuego> findByPuntuacionMinima(double puntuacion);
}