package com.kevingutierrez.webapp.ligafutbol.service;

import java.util.List;

import com.kevingutierrez.webapp.ligafutbol.model.Equipo;

public interface IEquipoService {
    public List<Equipo> listarEquipos();

    public Equipo guardarEquipo(Equipo equipo);

    public Equipo buscarEquipoPorId(Long id);

    public void eliminarEquipo(Equipo equipo);
}
