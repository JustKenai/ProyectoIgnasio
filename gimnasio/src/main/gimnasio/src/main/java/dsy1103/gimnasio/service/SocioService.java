package dsy1103.gimnasio.service;
import dsy1103.gimnasio.dto.request.SocioRequestDTO;
import dsy1103.gimnasio.dto.response.SocioResponseDTO;
import dsy1103.gimnasio.model.*;
import dsy1103.gimnasio.repository.NotificacionRepository;
import dsy1103.gimnasio.repository.PlanSuscripcionRepository;
import dsy1103.gimnasio.repository.SocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SocioService {

    private final SocioRepository socioRepository;
    private final PlanSuscripcionRepository planSuscripcionRepository;
    private final NotificacionRepository notificacionRepository;

    private SocioResponseDTO mapToDTO(Socio socio) {

        return new SocioResponseDTO(
                socio.getId(),
                socio.getNombres(),
                socio.getApellidos(),
                socio.getRun(),
                socio.getTelefono(),
                socio.getFechaNacimiento(),
                socio.getPlanSuscripcion().getDescripcion(),
                socio.getNotificacion().getMensaje()

        );
    }

    public List<SocioResponseDTO> buscarPorNombres(String nombres) {
        return socioRepository.findByNombre(nombres)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public SocioResponseDTO guardar(SocioRequestDTO dto){
        PlanSuscripcion planSuscripcion = planSuscripcionRepository
                .findById(dto.getPlanSuscripcion_ID()).orElseThrow(()-> new RuntimeException(
                        "Plan no encontrado con el id: " +dto.getPlanSuscripcion_ID()));

        Notificacion notificacion = notificacionRepository
                .findById(dto.getNotificacion()).orElseThrow(()-> new RuntimeException(
                        "Notificacion no encontrada con el id: " +dto.getNotificacion()));

        Socio socio = new Socio(
                null,
                dto.getNombres(),
                dto.getApellidos(),
                dto.getRun(),
                dto.getTelefono(),
                dto.getFechaNacimiento(),
                planSuscripcion,
                notificacion

        );
        return mapToDTO(socioRepository.save(socio));
    }

}
