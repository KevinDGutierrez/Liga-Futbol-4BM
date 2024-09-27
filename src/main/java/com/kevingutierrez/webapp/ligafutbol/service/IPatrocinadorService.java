package com.kevingutierrez.webapp.ligafutbol.service;

import java.util.List;

import com.kevingutierrez.webapp.ligafutbol.model.Patrocinador;

public interface IPatrocinadorService {

    public List<Patrocinador> listarPatrocinadores();

    public Patrocinador buscarPatrocinadorPorId(Long id);

    public Patrocinador guardarPatrocinador(Patrocinador patrocinador);

    public void eliminarPatrocinador(Patrocinador patrocinador);

    public Boolean verificarDatoDuplicado(Patrocinador patrocinador);

}
