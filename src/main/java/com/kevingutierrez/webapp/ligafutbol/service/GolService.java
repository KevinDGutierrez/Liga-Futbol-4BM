package com.kevingutierrez.webapp.ligafutbol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevingutierrez.webapp.ligafutbol.model.Gol;
import com.kevingutierrez.webapp.ligafutbol.repository.GolRepository;

@Service
public class GolService implements IGolService {
    @Autowired
    GolRepository golRepository;

    @Override
    public List<Gol> listarGoles() {
       return golRepository.findAll();
    }

    @Override
    public Gol buscarGolPorId(Long id) {
      return golRepository.findById(id).orElse(null);
    }  

    @Override
    public Gol guardarGol(Gol gol) {
      return golRepository.save(gol);
    }

    @Override
    public void eliminarGol(Gol gol) {
        golRepository.delete(gol);
    }


}
 