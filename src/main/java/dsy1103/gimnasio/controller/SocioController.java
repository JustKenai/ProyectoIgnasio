package dsy1103.gimnasio.controller;

import dsy1103.gimnasio.dto.request.ClaseRequestDTO;
import dsy1103.gimnasio.dto.request.SocioRequestDTO;
import dsy1103.gimnasio.dto.response.ClaseResponseDTO;
import dsy1103.gimnasio.dto.response.SocioResponseDTO;
import dsy1103.gimnasio.service.SocioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gimnasio/socio")
@RequiredArgsConstructor
public class SocioController {

    private final SocioService socioService;

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id){
        return ResponseEntity.ok(socioService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<?> buscarTodo(){
        return ResponseEntity.ok(socioService.buscarTodo());
    }

    @PostMapping
    public ResponseEntity<?> agregar(@Valid @RequestBody SocioRequestDTO dto) {
        return ResponseEntity.status(201).body(socioService.guardar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (socioService.buscarPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        socioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SocioResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody SocioRequestDTO dto) {
        return socioService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("buscar/{nombres}")
    public ResponseEntity<?> buscarPorSocio(@PathVariable String nombres){
        return ResponseEntity.ok(socioService.buscarPorNombres(nombres));
    }
}
