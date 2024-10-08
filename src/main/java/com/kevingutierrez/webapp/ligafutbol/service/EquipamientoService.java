package com.kevingutierrez.webapp.ligafutbol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevingutierrez.webapp.ligafutbol.model.Equipamiento;
import com.kevingutierrez.webapp.ligafutbol.repository.EquipamientoRepository;

@Service
public class EquipamientoService implements IEquipamientoService {

    @Autowired
    EquipamientoRepository equipamientoRepository;
    @Override
    public List<Equipamiento> listarEquipamientos() {
        return equipamientoRepository.findAll();
    }

    @Override
    public Equipamiento buscarEquipamientoPorId(Long id) {
        return equipamientoRepository.findById(id).orElse(null);    
    }

    @Override
    public Equipamiento guardarEquipamiento(Equipamiento equipamiento) {
        return equipamientoRepository.save(equipamiento);    
    }

    @Override
    public void eliminarEquipamiento(Equipamiento equipamiento) {
        equipamientoRepository.delete(equipamiento);    
    }

    @Override
    public Boolean verificarEquipamientosPorEquipo(Equipamiento equipamiento) {
        Boolean flag = Boolean.FALSE;
        List<Equipamiento> equipamientos = listarEquipamientos();
        int contador = 0;
        for (Equipamiento e : equipamientos) {
            if (e.getEquipo().getId().equals(equipamiento.getEquipo().getId()) && !e.getId().equals(equipamiento.getId())) {
                contador++;
            }
            if (contador >= 3) {
                flag = Boolean.TRUE;
                break;
            }
        }
    
        return flag;
    }

}
