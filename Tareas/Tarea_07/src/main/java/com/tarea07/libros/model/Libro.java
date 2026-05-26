package com.tarea07.libros.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Clase que representa un libro:
@Document(collection = "libros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {

    @Id
    private String id;

    private String titulo;    
    private String autor;     
    private String genero;    
    private double precio;    
    private boolean disponible; 
    private int paginas;
}
