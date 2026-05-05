package dsy1103.gimnasio.controller;

import dsy1103.gimnasio.service.HorarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class HorarioController {

    private final HorarioService horarioService;

    @GetMapping
    public ResponseEntity<?> buscarPorNombreHorario(String nombreHorario){
        return ResponseEntity.ok(horarioService.buscarPorNombreHorario(nombreHorario));
    }
}
