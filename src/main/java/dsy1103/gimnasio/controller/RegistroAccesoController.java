package dsy1103.gimnasio.controller;

import dsy1103.gimnasio.dto.request.ClaseRequestDTO;
import dsy1103.gimnasio.dto.request.RegistroAccesoRequestDTO;
import dsy1103.gimnasio.dto.response.ClaseResponseDTO;
import dsy1103.gimnasio.dto.response.RegistroAccesoResponseDTO;
import dsy1103.gimnasio.service.ClaseService;
import dsy1103.gimnasio.service.RegistroAccesoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gimnasio/registro")
@RequiredArgsConstructor
public class RegistroAccesoController {
    private final RegistroAccesoService registroAccesoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id){
        return ResponseEntity.ok(registroAccesoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<?> buscarTodo(){
        return ResponseEntity.ok(registroAccesoService.buscarTodo());
    }

    @PostMapping
    public ResponseEntity<?> agregar(@Valid @RequestBody RegistroAccesoRequestDTO dto) {
        return ResponseEntity.status(201).body(registroAccesoService.guardar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (registroAccesoService.buscarPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        registroAccesoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroAccesoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody RegistroAccesoRequestDTO dto) {
        return registroAccesoService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar/socio/{id}")
    public ResponseEntity<?> buscarPorSocio(@PathVariable Long id) {
        return ResponseEntity.ok(registroAccesoService.buscarPorSocio(id));
    }
}
