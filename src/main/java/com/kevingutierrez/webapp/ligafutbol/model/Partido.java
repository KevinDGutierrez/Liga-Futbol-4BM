package com.kevingutierrez.webapp.ligafutbol.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Partidos")
public class Partido {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String golVisitante;
    private String golLocales;
    private Date fecha;
    @ManyToMany
    @JoinTable(name = "equipos_partidos",
    joinColumns = @JoinColumn(name = "equipo_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "partido_id", referencedColumnName = "id"))
    private List<Equipo> equipos;

}   
