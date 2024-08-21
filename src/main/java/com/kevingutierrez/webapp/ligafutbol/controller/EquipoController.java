package com.kevingutierrez.webapp.ligafutbol.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kevingutierrez.webapp.ligafutbol.model.Equipo;
import com.kevingutierrez.webapp.ligafutbol.service.EquipoService;

@Controller
@RestController
@RequestMapping("")
public class EquipoController {
    @Autowired
    EquipoService equipoService;

    @GetMapping("/equipos")
    public List<Equipo> listaEquipos(){
        return equipoService.listarEquipos();
    }

    @GetMapping("/equipo")
    public ResponseEntity<Equipo> buscarEquipoPorId(@RequestParam Long id){
        try {
            Equipo equipo = equipoService.buscarEquipoPorId(id);
            return ResponseEntity.ok(equipo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/equipo")
    public ResponseEntity<Map<String, String>> agregarEquipo(@RequestBody Equipo equipo){
        Map<String, String> response = new HashMap<>();
        try {
            equipoService.guardarEquipo(equipo);
            response.put("message", "Equipo creada con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al crear la Equipo");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/equipo")
    public ResponseEntity<Map<String, String>> editarEquipo(@RequestParam Long id, @RequestBody Equipo equipoNuevo){
        Map<String, String> response = new HashMap<>();
        try{
            Equipo equipo = equipoService.buscarEquipoPorId(id);
            equipo.setNombre(equipoNuevo.getNombre());
            equipo.setEstadio(equipoNuevo.getEstadio());
            equipo.setCiudad(equipoNuevo.getCiudad());
            equipo.setAforo(equipoNuevo.getAforo());
            equipoService.guardarEquipo(equipo);
            response.put("massage", "El equipo se ha modificado con exito");
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("message", "Error");
            response.put("err", "Hubo un error al modificar el equipo");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/equipo")
    public ResponseEntity<Map<String, String>> eliminarEquipo(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try{
            Equipo equipo = equipoService.buscarEquipoPorId(id);
            equipoService.eliminarEquipo(equipo);
            response.put("message", "El equipo se ha eliminado");
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("message", "Error");
            response.put("err", "Hubo un error al eliminar el equipo");
            return ResponseEntity.badRequest().body(response);

        } 
    }
}
