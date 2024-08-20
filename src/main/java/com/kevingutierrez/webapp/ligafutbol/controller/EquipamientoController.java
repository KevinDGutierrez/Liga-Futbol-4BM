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
import org.springframework.web.multipart.MultipartFile;

import com.kevingutierrez.webapp.ligafutbol.model.Equipamiento;
import com.kevingutierrez.webapp.ligafutbol.service.EquipamientoService;

@Controller
@RestController
@RequestMapping("")
public class EquipamientoController {

    @Autowired
    EquipamientoService equipamientoService;

    @GetMapping("/equipamientos")
    public ResponseEntity<List<Equipamiento>> listarEquipamientos(){
        try {
            return ResponseEntity.ok(equipamientoService.listarEquipamientos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/equipamiento")
    public ResponseEntity<Equipamiento> buscarEquipamientoPorId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(equipamientoService.buscarEquipamientoPorId(id));
        } catch (Exception e) {
           return ResponseEntity.badRequest().body(null);
        }

    }

    @PostMapping("/equipamiento")
    public ResponseEntity<Map<String, String>> agregarEquipamiento(@RequestParam(value = "escudo", required = false) MultipartFile escudo, @RequestBody Equipamiento equipamiento){
        Map<String, String> response = new HashMap<>();
        try {
            if (escudo != null && !escudo.isEmpty()) {
                equipamiento.setEscudo(escudo.getBytes()); 
            }
            equipamientoService.guardarEquipamiento(equipamiento);
            response.put("message", "Equipamiento creado!!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al crear el Equipamiento");
            return ResponseEntity.badRequest().body(response);
        }

    }

    @PutMapping("/equipamiento")
    public ResponseEntity<Map<String, String>> editarEquipamiento(@RequestParam(value = "escudo", required = false) MultipartFile escudo, @RequestParam Long id, @RequestBody Equipamiento equipamientoNuevo){
        Map<String, String> response = new HashMap<>();
        try {
            Equipamiento equipamiento = equipamientoService.buscarEquipamientoPorId(id);
            if (escudo != null && !escudo.isEmpty()) {
                equipamiento.setEscudo(escudo.getBytes());
            }
            equipamiento.setColor(equipamientoNuevo.getColor());
            equipamiento.setPatrocinador(equipamientoNuevo.getPatrocinador());
            equipamientoService.guardarEquipamiento(equipamiento);
            response.put("message", "Equipamiento modicado !!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err:", "Hubo un error al modificar el equipamiento");
            return ResponseEntity.badRequest().body(response);
        }

    }

    @DeleteMapping("/equipamiento")
    public ResponseEntity<Map<String, String>> eliminarEquipamiento(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Equipamiento equipamiento = equipamientoService.buscarEquipamientoPorId(id);
            equipamientoService.eliminarEquipamiento(equipamiento);
            response.put("message", "Equipamiento eliminado");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("Message", "Error");
            response.put("err", "Hubo un problema al eliminar el Equipamiento");
            return ResponseEntity.badRequest().body(response);
        }

    }

}
