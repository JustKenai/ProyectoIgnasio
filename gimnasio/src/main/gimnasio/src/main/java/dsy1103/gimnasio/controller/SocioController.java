package dsy1103.gimnasio.controller;

import dsy1103.gimnasio.service.SocioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ginmnasio/socio")
@RequiredArgsConstructor
public class SocioController {

    private final SocioService socioService;

    @GetMapping("/{nombreSocio}")
    public ResponseEntity<?> buscarPorSocio(@PathVariable String nombres){
        return ResponseEntity.ok(socioService.buscarPorNombres(nombres));
    }

}
