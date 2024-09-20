package com.kevingutierrez.webapp.ligafutbol.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Patrocinadores")
public class Patrocinador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String logo;
    @ManyToMany
    @JoinTable(name = "patrocinadores_equipos", 
    joinColumns = @JoinColumn(name = "patrocinador_id", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "equipos_id", referencedColumnName = "id"))
    private List<Equipo> equipo;

}
