package dsy1103.gimnasio.controller;

import dsy1103.gimnasio.service.PlanSuscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gimnasio/plan")
@RequiredArgsConstructor
public class PlanSuscripcionController {

    private final PlanSuscripcionService planSuscripcionService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorIdPlan(@PathVariable Long id){
        return ResponseEntity.ok(planSuscripcionService.buscarPorIdPlan(id));
    }

}
