package com.kevingutierrez.webapp.ligafutbol.service;

import java.util.List;

import com.kevingutierrez.webapp.ligafutbol.model.Jugador;

public interface IJugadorService {

    public List<Jugador> listarJugadores();

    public Jugador buscarJugadorPorId(Long id);

    public Jugador guardarJugador(Jugador jugador);

    public void eliminarJugador(Jugador jugador);

}
