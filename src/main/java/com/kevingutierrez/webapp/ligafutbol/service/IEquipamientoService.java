package com.kevingutierrez.webapp.ligafutbol.service;

import java.util.List;

import com.kevingutierrez.webapp.ligafutbol.model.Equipamiento;

public interface IEquipamientoService {

    public List<Equipamiento> listarEquipamientos();

    public Equipamiento buscarEquipamientoPorId(Long id);

    public Equipamiento guardarEquipamiento(Equipamiento equipamiento);

    public void eliminarEquipamiento(Equipamiento equipamiento);

    public Boolean verificarEquipamientosPorEquipo(Equipamiento equipamiento);

}
