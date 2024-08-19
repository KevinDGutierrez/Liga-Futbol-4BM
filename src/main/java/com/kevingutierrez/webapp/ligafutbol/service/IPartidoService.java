package com.kevingutierrez.webapp.ligafutbol.service;

import java.util.List;

import com.kevingutierrez.webapp.ligafutbol.model.Partido;

public interface IPartidoService {

    public List<Partido> listarPartidos();
    
    public Partido buscarPartidoPorId(Long id);

    public Partido guardarPartido(Partido partido);

    public void eliminarPartido(Partido partido);
}
