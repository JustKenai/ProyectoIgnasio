package dsy1103.gimnasio.controller;

import dsy1103.gimnasio.service.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gimnasio/notificacion")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;


}
