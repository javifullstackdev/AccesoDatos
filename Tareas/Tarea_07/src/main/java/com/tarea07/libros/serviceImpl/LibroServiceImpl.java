package com.tarea07.libros.serviceImpl;

import com.tarea07.libros.model.Libro;
import com.tarea07.libros.repository.LibroRepository;
import com.tarea07.libros.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    @Override
    public Optional<Libro> obtenerPorId(String id) {
        return libroRepository.findById(id);
    }

    @Override
    public Libro crear(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Optional<Libro> actualizar(String id, Libro libroNuevo) {
        return libroRepository.findById(id).map(libroExistente -> {
            libroExistente.setTitulo(libroNuevo.getTitulo());
            libroExistente.setAutor(libroNuevo.getAutor());
            libroExistente.setGenero(libroNuevo.getGenero());
            libroExistente.setPrecio(libroNuevo.getPrecio());
            libroExistente.setDisponible(libroNuevo.isDisponible());
            libroExistente.setPaginas(libroNuevo.getPaginas());
            return libroRepository.save(libroExistente);
        });
    }

    @Override
    public boolean eliminar(String id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Libro> obtenerPorGenero(String genero) {
        return libroRepository.findByGeneroContainingIgnoreCase(genero);
    }

    @Override
    public List<Libro> obtenerPorAutor(String autor) {
        return libroRepository.findByAutorContainingIgnoreCase(autor);
    }

    @Override
    public List<Libro> obtenerPorDisponibilidad(boolean disponible) {
        return libroRepository.findByDisponible(disponible);
    }

    @Override
    public List<Libro> obtenerPorRangoPrecio(double min, double max) {
        return libroRepository.findByPrecioBetween(min, max);
    }

    @Override
    public List<Libro> obtenerPorRangoPaginas(int min, int max) {
        return libroRepository.findByPaginasBetween(min, max);
    }

    @Override
    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }
}
