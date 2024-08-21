package com.kevingutierrez.webapp.ligafutbol.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kevingutierrez.webapp.ligafutbol.model.Partido;
import com.kevingutierrez.webapp.ligafutbol.service.PartidoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RestController
@RequestMapping("")
public class PartidoController {

    @Autowired
    PartidoService partidoService;

    @GetMapping("/partidos")
    public ResponseEntity<?> listarPartidos() {
        Map<String,String> response = new HashMap<>();
    try {
            return ResponseEntity.ok(partidoService.listarPartidos());
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "No se encontro una lista de Partidos");
            return ResponseEntity.badRequest().body(response);
        } 
    }


        @GetMapping("/partido")
        public ResponseEntity<Partido> buscarPartidoPorId(@RequestParam Long id) {
            try {
                Partido partido = partidoService.buscarPartidoPorId(id);
                return ResponseEntity.ok(partido);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
            
        }

        @PostMapping("/partido")
        public ResponseEntity<Map<String,String>> agregarPartido(@RequestBody Partido partido) {
        Map<String, String> response = new HashMap<>();
        try {
            partidoService.guardarPartido(partido);
            response.put("message", "Partido agregado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al agregar el partido");
            return ResponseEntity.badRequest().body(response);
        }
            
    }

    @PutMapping("/partido")
    public ResponseEntity<Map<String, String>> editarPartido(@RequestParam Long id, @RequestBody Partido partidoNuevo) {
        Map<String, String> response = new HashMap<>();
        try {
            Partido partido = partidoService.buscarPartidoPorId(id);
            partido.setGolVisitante(partidoNuevo.getGolVisitante());
            partido.setGolLocales(partidoNuevo.getGolLocales());
            partido.setFecha(partidoNuevo.getFecha());
            partido.setEquipos(partidoNuevo.getEquipos());
            partidoService.guardarPartido(partido);
            response.put("message", "Partido modificiado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al modificar al partido");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/partido")
    public ResponseEntity<Map<String, String>> eliminarPartido(@RequestParam Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Partido partido = partidoService.buscarPartidoPorId(id);
            partidoService.eliminarPartido(partido);
            response.put("message", "Partido eliminado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al eliminar el partido");
            return ResponseEntity.badRequest().body(response);
        }
    }
        
        
    
}
