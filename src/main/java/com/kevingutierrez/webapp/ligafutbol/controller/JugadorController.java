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

import com.kevingutierrez.webapp.ligafutbol.model.Jugador;
import com.kevingutierrez.webapp.ligafutbol.service.JugadorService;

@Controller
@RestController
@RequestMapping("")
public class JugadorController {

    @Autowired
    JugadorService jugadorService;

    @GetMapping("/jugadores")
    public ResponseEntity<?> listarJugadores() {
        Map<String, String> response = new HashMap<>();
        try {
            List<Jugador> jugador = jugadorService.listarJugadores();
            if (!jugador.isEmpty()){
                return ResponseEntity.ok(jugadorService.listarJugadores());
            }else{
                response.put("message", "Error");
                response.put("err", "No se encontro una lista jugadores");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "No se encontro una lista jugadores");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/jugador")
    public ResponseEntity<?> buscarJugadorPorId(@RequestParam Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Jugador jugador = jugadorService.buscarJugadorPorId(id);
            if(jugador != null){
                return ResponseEntity.ok(jugador);
            }else{
                response.put("message", "Error");
                response.put("err", "No se encontro al jugador buscado");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "No se encontro al jugador buscado");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/jugador")
    public ResponseEntity<Map<String, String>> agregarJugador(@RequestBody Jugador jugador) {
        Map<String, String> response = new HashMap<>();
        try {
            if(!jugadorService.verificarJugadoresDeMasEnEquipos(jugador)){
                jugadorService.guardarJugador(jugador);
                response.put("message", "¡¡Jugador creado con éxito :D!!");
                return ResponseEntity.ok(response);
            }else{
                response.put("message", "Error");
                response.put("err", "El equipo ya alcanzo el maximo de jugadores");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al crear el jugador");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/jugador")
    public ResponseEntity<Map<String, String>> editarJugador(@RequestParam Long id, @RequestBody Jugador jugadorNuevo) {
        Map<String, String> response = new HashMap<>();
        try {
            Jugador jugador = jugadorService.buscarJugadorPorId(id);
            jugador.setNombre(jugadorNuevo.getNombre());
            jugador.setApellido(jugadorNuevo.getApellido());
            jugador.setDorsal(jugadorNuevo.getDorsal());
            jugador.setEquipo(jugadorNuevo.getEquipo());
            if(!jugadorService.verificarJugadoresDeMasEnEquipos(jugador)){
                jugadorService.guardarJugador(jugador);
                response.put("message", "¡¡Jugador modificado con éxito :D!!");
                return ResponseEntity.ok(response);
            }else{
                response.put("message", "Error");
                response.put("err", "El equipo ya alcanzo el maximo de jugadores");
                return ResponseEntity.badRequest().body(response);    
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al modificar el Jugador");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/jugador")
    public ResponseEntity <Map<String, String>> eliminarJugador(@RequestParam Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Jugador jugador = jugadorService.buscarJugadorPorId(id);
            jugadorService.eliminarJugador(jugador);
            response.put("message", "¡¡El jugador se ha eliminado con éxito :D!!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al eliminar el jugador");
            return ResponseEntity.badRequest().body(response);
        }
    }

}
