package dsy1103.gimnasio.controller;

import dsy1103.gimnasio.dto.request.ClaseRequestDTO;
import dsy1103.gimnasio.dto.request.HorarioRequestDTO;
import dsy1103.gimnasio.dto.response.ClaseResponseDTO;
import dsy1103.gimnasio.dto.response.HorarioResponseDTO;
import dsy1103.gimnasio.service.ClaseService;
import dsy1103.gimnasio.service.HorarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gimnasio/horario")
@RequiredArgsConstructor
public class HorarioController {

    private final HorarioService horarioService;

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id){
        return ResponseEntity.ok(horarioService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<?> buscarTodo(){
        return ResponseEntity.ok(horarioService.buscarTodo());
    }

    @PostMapping
    public ResponseEntity<?> agregar(@Valid @RequestBody HorarioRequestDTO dto) {
        return ResponseEntity.status(201).body(horarioService.guardar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (horarioService.buscarPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        horarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody HorarioRequestDTO dto) {
        return horarioService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
