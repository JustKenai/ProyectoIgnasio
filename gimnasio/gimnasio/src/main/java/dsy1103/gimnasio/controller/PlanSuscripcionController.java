package dsy1103.gimnasio.controller;

import dsy1103.gimnasio.dto.request.HorarioRequestDTO;
import dsy1103.gimnasio.dto.request.PlanSuscripcionRequestDTO;
import dsy1103.gimnasio.dto.response.HorarioResponseDTO;
import dsy1103.gimnasio.dto.response.PlanSuscripcionResponseDTO;
import dsy1103.gimnasio.service.PlanSuscripcionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gimnasio/plan")
@RequiredArgsConstructor
public class PlanSuscripcionController {

    private final PlanSuscripcionService planSuscripcionService;
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id){
        return ResponseEntity.ok(planSuscripcionService.buscarPorIdPlan(id));
    }

    @GetMapping
    public ResponseEntity<?> buscarTodo(){
        return ResponseEntity.ok(planSuscripcionService.buscarTodo());
    }

    @PostMapping
    public ResponseEntity<?> agregar(@Valid @RequestBody PlanSuscripcionRequestDTO dto) {
        return ResponseEntity.status(201).body(planSuscripcionService.guardar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (planSuscripcionService.buscarPorIdPlan(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        planSuscripcionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanSuscripcionResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody PlanSuscripcionRequestDTO dto) {
        return planSuscripcionService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
