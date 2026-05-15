package dsy1103.gimnasio.controller;

import dsy1103.gimnasio.dto.request.ClaseRequestDTO;
import dsy1103.gimnasio.dto.request.NotificacionRequestDTO;
import dsy1103.gimnasio.dto.response.ClaseResponseDTO;
import dsy1103.gimnasio.dto.response.NotificacionResponseDTO;
import dsy1103.gimnasio.service.NotificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gimnasio/notificacion")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id){
        return ResponseEntity.ok(notificacionService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<?> buscarTodo(){
        return ResponseEntity.ok(notificacionService.buscarTodo());
    }

    @PostMapping
    public ResponseEntity<?> agregar(@Valid @RequestBody NotificacionRequestDTO dto) {
        return ResponseEntity.status(201).body(notificacionService.guardar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (notificacionService.buscarPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        notificacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificacionResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody NotificacionRequestDTO dto) {
        return notificacionService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
