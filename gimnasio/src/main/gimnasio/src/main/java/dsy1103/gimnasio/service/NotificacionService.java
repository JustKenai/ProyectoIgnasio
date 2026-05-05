package dsy1103.gimnasio.service;

import dsy1103.gimnasio.dto.response.NotificacionResponseDTO;
import dsy1103.gimnasio.model.Clase;
import dsy1103.gimnasio.model.Notificacion;
import dsy1103.gimnasio.repository.NotificacionRepository;
import dsy1103.gimnasio.repository.SocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;
    private final SocioRepository socioRepository;

    private NotificacionResponseDTO mapToDTO(Notificacion n) {
        return new NotificacionResponseDTO(
                n.getId(),
                n.getMensaje()
        );
    }


}
