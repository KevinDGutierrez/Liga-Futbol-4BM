package com.kevingutierrez.webapp.ligafutbol.controller;

import java.util.HashMap;
import java.util.List;
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
            List<Partido> partido = partidoService.listarPartidos();
            if(!partido.isEmpty()) {
            return ResponseEntity.ok(partidoService.listarPartidos()); 
            }else {
                response.put("message", "Error");
                response.put("err", "No se encontro una lista de Partidos");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "No se encontro una lista de Partidos");
            return ResponseEntity.badRequest().body(response);
        } 
    }


        @GetMapping("/partido")
        public ResponseEntity<?> buscarPartidoPorId(@RequestParam Long id) {
            Map<String, String> response = new HashMap<>();
            try {
                Partido partido = partidoService.buscarPartidoPorId(id);
                if(partido != null) {
                    return ResponseEntity.ok(partido);
                } else{
                    response.put("message", "Error");
                    response.put("err", "No se encontro el partido buscado ");
                    return ResponseEntity.badRequest().body(response);
                }
            } catch (Exception e) {
                response.put("message", "Error");
                response.put("err", "No se encontro el partido buscado");
                return ResponseEntity.badRequest().body(response);
            }
            
        }

        @PostMapping("/partido")
        public ResponseEntity<Map<String,String>> agregarPartido(@RequestBody Partido partido) {
        Map<String, String> response = new HashMap<>();
        try {
            Partido partidoGuardado = partidoService.guardarPartido(partido);
            if (partidoGuardado == null) {
                response.put("message", "Error");
                response.put("err", "El partido debe tener exactamente dos equipos diferentes.");
                return ResponseEntity.badRequest().body(response);
            }
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
        Partido partidoExistente = partidoService.buscarPartidoPorId(id);
        if (partidoExistente == null) {
            response.put("message", "Error");
            response.put("err", "No se encontró el partido con el ID especificado.");
            return ResponseEntity.badRequest().body(response);
        }
        partidoExistente.setGolVisitante(partidoNuevo.getGolVisitante());
        partidoExistente.setGolLocales(partidoNuevo.getGolLocales());
        partidoExistente.setFecha(partidoNuevo.getFecha());
        partidoExistente.setEquipos(partidoNuevo.getEquipos());
        Partido partidoGuardado = partidoService.guardarPartido(partidoExistente);
        if (partidoGuardado == null) {
            response.put("message", "Error");
            response.put("err", "El partido debe tener exactamente dos equipos diferentes.");
            return ResponseEntity.badRequest().body(response);
        }
        response.put("message", "Partido modificado con éxito");
        return ResponseEntity.ok(response);

    } catch (Exception e) {
        response.put("message", "Error");
        response.put("err", "Hubo un error al modificar el partido");
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
