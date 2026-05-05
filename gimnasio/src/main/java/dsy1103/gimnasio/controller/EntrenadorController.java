package dsy1103.gimnasio.controller;

import dsy1103.gimnasio.service.EntrenadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("entrenador/clase")
@RequiredArgsConstructor
public class EntrenadorController {
    private  final EntrenadorService entrenadorService;

    @GetMapping
    public ResponseEntity<?> buscarPorEspecialidad(String especialidad){
        return ResponseEntity.ok(entrenadorService.buscarPorEspecialidad(especialidad));
    }


}
