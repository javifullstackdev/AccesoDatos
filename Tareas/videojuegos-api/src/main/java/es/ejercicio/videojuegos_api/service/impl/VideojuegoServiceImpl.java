package es.ejercicio.videojuegos_api.service.impl;

import es.ejercicio.videojuegos_api.model.Videojuego;
import es.ejercicio.videojuegos_api.repository.VideojuegoRepository;
import es.ejercicio.videojuegos_api.service.VideojuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideojuegoServiceImpl implements VideojuegoService {

    @Autowired
    private VideojuegoRepository repo;

    @Override
    public List<Videojuego> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Videojuego> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Videojuego create(Videojuego v) {
        v.setId(null);
        return repo.save(v);
    }

    @Override
    public Videojuego update(Long id, Videojuego v) {
        if (repo.existsById(id)) {
            v.setId(id);
            return repo.save(v);
        }
        throw new RuntimeException("No existe el videojuego con ID: " + id);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Videojuego> findByPlataforma(String plataforma) {
        return repo.findByPlataforma(plataforma);
    }

    @Override
    public List<Videojuego> findByPuntuacionMinima(double puntuacion) {
        return repo.findByPuntuacionGreaterThanEqual(puntuacion);
    }
}