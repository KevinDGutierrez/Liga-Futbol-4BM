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
    public ResponseEntity<?> listarEquipamientos(){
        Map<String, String> response = new HashMap<>();
        try {
            List<Equipamiento> equipamiento = equipamientoService.listarEquipamientos();
            if (!equipamiento.isEmpty()) {
                return ResponseEntity.ok(equipamientoService.listarEquipamientos());
            }else{
                response.put("message", "Error");
                response.put("err", "No se encontro la lista Equipamientos");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
                response.put("message", "Error");
                response.put("err", "No se encontro la lista Equipamientos");
                return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/equipamiento")
    public ResponseEntity<?> buscarEquipamientoPorId(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Equipamiento equipamiento = equipamientoService.buscarEquipamientoPorId(id);
            if (equipamiento != null ) {
                return ResponseEntity.ok(equipamiento);
            } else {
                response.put("message", "Error");
                response.put("err", "No se encontro ningun Equipamiento");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "No se encontro ningun Equipamiento");
            return ResponseEntity.badRequest().body(response);
        }

    }

    @PostMapping("/equipamiento")
    public ResponseEntity<Map<String, String>> agregarEquipamiento(@RequestParam(value = "escudo", required = false) MultipartFile escudo, @RequestBody Equipamiento equipamiento){
        Map<String, String> response = new HashMap<>();
        try {
            if (!equipamientoService.verificarEquipamientosPorEquipo(equipamiento)) {
                equipamientoService.guardarEquipamiento(equipamiento);
                response.put("message", "Equipamiento creado!!");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Error");
                response.put("err", "Hubo un error maximo de equipamientos alcanzado");
                return ResponseEntity.badRequest().body(response);
            }
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
            equipamiento.setColor(equipamientoNuevo.getColor());
            equipamiento.setEscudo(equipamientoNuevo.getEscudo());
            equipamiento.setPatrocinador(equipamientoNuevo.getPatrocinador());
            equipamiento.setEquipo(equipamientoNuevo.getEquipo());
            if(!equipamientoService.verificarEquipamientosPorEquipo(equipamiento)){
            equipamientoService.guardarEquipamiento(equipamiento);
            response.put("message", "Equipamiento modicado !!");
            return ResponseEntity.ok(response);
            }else{
                response.put("message", "Error");
                response.put("err", "Hubo un error maximo de equipamientos alcanzado");
                return ResponseEntity.badRequest().body(response); 
            }
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
