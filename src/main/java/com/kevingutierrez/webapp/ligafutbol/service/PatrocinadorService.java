package com.kevingutierrez.webapp.ligafutbol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevingutierrez.webapp.ligafutbol.model.Patrocinador;
import com.kevingutierrez.webapp.ligafutbol.repository.PatrocinadorRepository;

@Service
public class PatrocinadorService implements IPatrocinadorService {

    @Autowired
    PatrocinadorRepository patrocinadorRepository;

    @Override
    public List<Patrocinador> listarPatrocinadores() {
        return patrocinadorRepository.findAll();
    }

    @Override
    public Patrocinador buscarPatrocinadorPorId(Long id) {
        return patrocinadorRepository.findById(id).orElse(null);
    }

    @Override
    public Patrocinador guardarPatrocinador(Patrocinador patrocinador) {
        return patrocinadorRepository.save(patrocinador);
    }

    @Override
    public void eliminarPatrocinador(Patrocinador patrocinador) {
        patrocinadorRepository.delete(patrocinador);
    }

    @Override
    public Boolean verificarDatoDuplicado(Patrocinador patrocinador) {
        Boolean flag = Boolean.FALSE;
        List<Patrocinador> patrocinadores = listarPatrocinadores();
        for (Patrocinador p : patrocinadores) {
            if (p.getNombre().equals(patrocinador.getNombre()) && p.getLogo().equals(patrocinador.getLogo()) && !p.getId().equals(patrocinador.getId())) {
                flag = Boolean.TRUE;
            }
        }
        return flag;
    }

}
