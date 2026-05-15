package dsy1103.gimnasio.controller;

import dsy1103.gimnasio.dto.request.ClaseRequestDTO;
import dsy1103.gimnasio.dto.request.EntrenadorRequestDTO;
import dsy1103.gimnasio.dto.response.ClaseResponseDTO;
import dsy1103.gimnasio.dto.response.EntrenadorResponseDTO;
import dsy1103.gimnasio.service.EntrenadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gimnasio/entrenador")
@RequiredArgsConstructor
public class EntrenadorController {
    private  final EntrenadorService entrenadorService;

    @GetMapping
    public ResponseEntity<?> buscarTodo(){
        return ResponseEntity.ok(entrenadorService.buscarTodo());
    }

    @PostMapping
    public ResponseEntity<?> agregar(@Valid @RequestBody EntrenadorRequestDTO dto){
        return ResponseEntity.status(201).body(entrenadorService.guardar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        if (entrenadorService.buscarPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        entrenadorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable Long id){
        return ResponseEntity.ok(entrenadorService.buscarPorId(id));
    }

    @GetMapping("/buscar/{especialidad}")
    public ResponseEntity<?> buscarPorEspecialidad(@PathVariable String especialidad){
        return ResponseEntity.ok(entrenadorService.buscarPorEspecialidad(especialidad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntrenadorResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody EntrenadorRequestDTO dto) {
        return entrenadorService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
