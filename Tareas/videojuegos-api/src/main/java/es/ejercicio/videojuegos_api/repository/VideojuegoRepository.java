package es.ejercicio.videojuegos_api.repository;

import es.ejercicio.videojuegos_api.model.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {
    
    // Método para filtrar por plataforma
    List<Videojuego> findByPlataforma(String plataforma);
    
    // Método para filtrar por puntuación mínima
    List<Videojuego> findByPuntuacionGreaterThanEqual(double puntuacion);
}