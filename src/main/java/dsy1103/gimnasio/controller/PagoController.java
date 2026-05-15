package dsy1103.gimnasio.controller;

import dsy1103.gimnasio.dto.request.PagoRequestDTO;
import dsy1103.gimnasio.dto.response.PagoResponseDTO;
import dsy1103.gimnasio.service.PagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gimnasio/pago")
@RequiredArgsConstructor
public class PagoController {
    private final PagoService pagoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id){
        return ResponseEntity.ok(pagoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<?> buscarTodo(){
        return ResponseEntity.ok(pagoService.buscarTodo());
    }

    @PostMapping
    public ResponseEntity<?> agregar(@Valid @RequestBody PagoRequestDTO dto) {
        return ResponseEntity.status(201).body(pagoService.guardar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (pagoService.buscarPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody PagoRequestDTO dto) {
        return pagoService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar/socio/{id}")
    public ResponseEntity<?> buscarPorSocio(@PathVariable Long id){
        return ResponseEntity.ok(pagoService.buscarPorSocio(id));
    }
}
