package com.kevingutierrez.webapp.ligafutbol.service;

import java.util.List;

import com.kevingutierrez.webapp.ligafutbol.model.Gol;

public interface IGolService {
    
    public List<Gol> listarGoles();

    public Gol buscarGolPorId(Long id);

    public Gol guardarGol(Gol gol);

    public void eliminarGol(Gol gol);
}
