package com.kevingutierrez.webapp.ligafutbol.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Goles")
public class Gol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String minutoAnotacion;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    @ManyToOne
    private Jugador jugador;
}
