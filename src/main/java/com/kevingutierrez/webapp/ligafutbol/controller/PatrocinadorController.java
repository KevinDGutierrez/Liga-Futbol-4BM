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

import com.kevingutierrez.webapp.ligafutbol.model.Patrocinador;
import com.kevingutierrez.webapp.ligafutbol.service.PatrocinadorService;

@Controller
@RestController
@RequestMapping("")
public class PatrocinadorController {

    @Autowired
    PatrocinadorService patrocinadorService;

    @GetMapping("/patrocinadores")
    public List<Patrocinador> listarPatrocinadores(){
        return patrocinadorService.listarPatrocinadores();
    }

    @GetMapping("/patrocinador")
    public ResponseEntity<Patrocinador> buscarPatrocinadorPorId(@RequestParam Long id){
        try {
            Patrocinador patrocinador = patrocinadorService.buscarPatrocinadorPorId(id);
            return ResponseEntity.ok(patrocinador);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/patrocinador")
    public ResponseEntity<Map<String, String>> agregarPatrocinador(@RequestBody Patrocinador patrocinador){
        Map<String, String> response = new HashMap<>();
        try {
            if(!patrocinadorService.verificarDatoDuplicado(patrocinador)){
                patrocinadorService.guardarPatrocinador(patrocinador);
                response.put("message", "¡¡Patrocinador creado con éxito :D!!");
                return ResponseEntity.ok(response);
            }else{
                response.put("message", "Error");
                response.put("err", "El logo o el nombre se encuentra duplicado");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al crear el patrocinador");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/patrocinador")
    public ResponseEntity<Map<String, String>> editarPatrocinador(@RequestParam Long id, @RequestBody Patrocinador patrocinadorNuevo){
        Map<String, String> response = new HashMap<>();
        try {
            Patrocinador patrocinador = patrocinadorService.buscarPatrocinadorPorId(id);
            patrocinador.setNombre(patrocinadorNuevo.getNombre());
            patrocinador.setLogo(patrocinadorNuevo.getLogo());
            patrocinador.setEquipos(patrocinadorNuevo.getEquipos());
            if(!patrocinadorService.verificarDatoDuplicado(patrocinador)){
                patrocinadorService.guardarPatrocinador(patrocinador);
                response.put("message", "¡¡Patrocinador modificado con éxito :D!!");
                return ResponseEntity.ok(response);
            }else{
                response.put("message", "Error");
                response.put("err", "El logo o el nombre se encuentra duplicado");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al modificar el patrocinador");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/patrocinador")
    public ResponseEntity<Map<String, String>> eliminarPatrocinador(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Patrocinador patrocinador = patrocinadorService.buscarPatrocinadorPorId(id);
            patrocinadorService.eliminarPatrocinador(patrocinador);
            response.put("message", "¡¡El patrocinador se ha eliminado con éxito :D!!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al eliminar el patrocinador");
            return ResponseEntity.badRequest().body(response);
        }
    }

}
