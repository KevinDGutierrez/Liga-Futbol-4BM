package com.kevingutierrez.webapp.ligafutbol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevingutierrez.webapp.ligafutbol.model.Partido;
import com.kevingutierrez.webapp.ligafutbol.repository.PartidoRepository;

@Service
public class PartidoService implements IPartidoService {

    @Autowired
    PartidoRepository partidoRepository;

    @Override
    public List<Partido> listarPartidos() {
       return partidoRepository.findAll();
    }

    @Override
    public Partido buscarPartidoPorId(Long id) {
       return partidoRepository.findById(id).orElse(null);
    }

    @Override
    public Partido guardarPartido(Partido partido) {
      if (partido.getEquipos() == null || partido.getEquipos().size() != 2) {
         return null; 
   }
      if (partido.getEquipos().get(0).equals(partido.getEquipos().get(1))) {
         return null; 
   }
         return partidoRepository.save(partido);
   }
    }

    @Override
    public void eliminarPartido(Partido partido) {
      partidoRepository.delete(partido);  
    }
}
