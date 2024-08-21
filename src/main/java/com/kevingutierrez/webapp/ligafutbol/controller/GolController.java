package com.kevingutierrez.webapp.ligafutbol.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kevingutierrez.webapp.ligafutbol.model.Gol;
import com.kevingutierrez.webapp.ligafutbol.service.GolService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RestController
@RequestMapping("")
public class GolController {

    @Autowired
    GolService golService;

    @GetMapping("/goles")
    public ResponseEntity<?> listarGoles() {
        Map<String, String> response = new HashMap<>();
        try {
            List<Gol> goles = golService.listarGoles();
            if (!goles.isEmpty()) { 
                return ResponseEntity.ok(goles);
            }else{
                response.put("message", "Error");
                response.put("err", "No se pudo listar el gol");
                return ResponseEntity.badRequest().body(response);
            } 
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Ocurrió un error al listar los goles");
            return ResponseEntity.badRequest().body(response); 
        }
    }
    
    @GetMapping("/gol")
    public ResponseEntity<?> buscarGolPorId(@RequestParam Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Gol gol = golService.buscarGolPorId(id);
            if (gol != null) {
                return ResponseEntity.ok(gol);
            }else{
                response.put("message", "Error");
                response.put("err", "No se pudo encontrar el Gol con el ID proporcionado");
                return ResponseEntity.badRequest().body(response);
            } 
        }catch (Exception e){
            response.put("message", "Error");
            response.put("err", "No se encontro el gol que busco");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/gol")
    public ResponseEntity<Map<String,String>> agregarGol(@RequestBody Gol gol) {
        Map<String, String > response = new HashMap<>();
        try {
            golService.guardarGol(gol);
            response.put("message", "Gol agregado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al agregar el gol");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/gol")
    public ResponseEntity<Map<String, String>> editarGol(@RequestParam Long  id, @RequestBody Gol golNuevo) {
        Map<String, String> response = new HashMap<>();
        try {
            Gol gol = golService.buscarGolPorId(id);
            gol.setMinutoAnotacion(golNuevo.getMinutoAnotacion());
            gol.setDescripcion(golNuevo.getDescripcion());
            golService.guardarGol(gol);
            response.put("message", "Gol modificado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("messasge", "Error");
            response.put("err", "Hubo un error al modificar el gol");
            return ResponseEntity.badRequest().body(response);
        }
        
    }

    @DeleteMapping("/gol")
    public ResponseEntity<Map<String, String>> eliminarGol(@RequestParam Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Gol gol = golService.buscarGolPorId(id);
            golService.eliminarGol(gol);
            response.put("message", "Gol eliminado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al eliminar el gol");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    
    
    
}
