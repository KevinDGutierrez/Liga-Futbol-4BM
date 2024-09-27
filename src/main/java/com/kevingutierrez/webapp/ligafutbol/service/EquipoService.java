package com.kevingutierrez.webapp.ligafutbol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevingutierrez.webapp.ligafutbol.model.Equipo;
import com.kevingutierrez.webapp.ligafutbol.repository.EquipoRepository;

@Service
public class EquipoService implements IEquipoService{
    @Autowired
    EquipoRepository equipoRepository ;

    @Override
    public List<Equipo> listarEquipos() {
        return equipoRepository.findAll();
    }

    @Override
    public Equipo guardarEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    @Override
    public Equipo buscarEquipoPorId(Long id) {
        return equipoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarEquipo(Equipo equipo) {
        equipoRepository.delete(equipo);
    }

    @Override
    public Boolean verficarAforo(Equipo equipo) {
        Boolean flag = Boolean.FALSE;
        Long aforo = equipo.getAforo();
        try {
            if (aforo >= 2000 && aforo <= 100000) {
                flag = Boolean.TRUE;
            }
        } catch (Exception e) {
            flag = Boolean.FALSE;
        }   
        return flag;     
        
    }

    

}
