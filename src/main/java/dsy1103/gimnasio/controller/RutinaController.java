package dsy1103.gimnasio.controller;

import dsy1103.gimnasio.dto.request.ClaseRequestDTO;
import dsy1103.gimnasio.dto.request.RutinaRequestDTO;
import dsy1103.gimnasio.dto.response.ClaseResponseDTO;
import dsy1103.gimnasio.dto.response.RutinaResponseDTO;
import dsy1103.gimnasio.service.ClaseService;
import dsy1103.gimnasio.service.RutinaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gimnasio/rutina")
@RequiredArgsConstructor
public class RutinaController {
    private final RutinaService rutinaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id){
        return ResponseEntity.ok(rutinaService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<?> buscarTodo(){
        return ResponseEntity.ok(rutinaService.buscarTodo());
    }

    @PostMapping
    public ResponseEntity<?> agregar(@Valid @RequestBody RutinaRequestDTO dto) {
        return ResponseEntity.status(201).body(rutinaService.guardar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (rutinaService.buscarPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        rutinaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RutinaResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody RutinaRequestDTO dto) {
        return rutinaService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
