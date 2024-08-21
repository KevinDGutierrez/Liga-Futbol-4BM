package com.kevingutierrez.webapp.ligafutbol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevingutierrez.webapp.ligafutbol.model.Jugador;
import com.kevingutierrez.webapp.ligafutbol.repository.JugadorRepository;

@Service
public class JugadorService implements IJugadorService{

    @Autowired
    JugadorRepository jugadorRepository;

    @Override
    public List<Jugador> listarJugadores() {
        return jugadorRepository.findAll();
    }

    @Override
    public Jugador buscarJugadorPorId(Long id) {
        return jugadorRepository.findById(id).orElse(null);
    }

    @Override
    public Jugador guardarJugador(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    @Override
    public void eliminarJugador(Jugador jugador) {
        jugadorRepository.delete(jugador);
    }

    @Override
    public Boolean verificarJugadoresDeMasEnEquipos(Jugador jugador) {
        Boolean flag = Boolean.FALSE;
        List<Jugador> jugadores = listarJugadores();
        int contador = 0;
        for (Jugador j : jugadores) {
            if (j.getEquipo().getId().equals(jugador.getEquipo().getId()) && !j.getId().equals(jugador.getId())) {
                contador++;
            }
            if (contador >= 16) {
                flag = Boolean.TRUE;
            }
        }
        return flag;
    }

}
