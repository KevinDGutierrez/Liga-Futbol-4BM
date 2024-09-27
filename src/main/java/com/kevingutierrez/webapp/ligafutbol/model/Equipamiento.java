package com.kevingutierrez.webapp.ligafutbol.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
<<<<<<< HEAD
import jakarta.persistence.Lob;
=======
>>>>>>> luisCuxun-2023518
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Equipamientos")
public class Equipamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String color;
    @Column(columnDefinition = "TEXT")
    private String escudo;
    @ManyToOne
    private Patrocinador patrocinador;
    @ManyToOne
    private Equipo equipo;

}
