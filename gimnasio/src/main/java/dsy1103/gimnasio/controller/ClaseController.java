package dsy1103.gimnasio.controller;

import dsy1103.gimnasio.dto.response.ClaseResponseDTO;
import dsy1103.gimnasio.model.Clase;
import dsy1103.gimnasio.service.ClaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ignasio")
@RequiredArgsConstructor
public class ClaseController {
    private final ClaseService claseService;

    @GetMapping
    public ResponseEntity<?> buscarPorIDEntrenador(Long id){
        return ResponseEntity.ok(claseService.buscarPorEntrenador(id));
    }

}
