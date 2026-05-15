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
import java.util.Optional;
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
                socio.getPlanSuscripcion().getNombre(),
                socio.getNotificacion().getMensaje()

        );
    }

    public List<SocioResponseDTO> buscarPorNombres(String nombres) {
        return socioRepository.findByNombres(nombres)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public SocioResponseDTO guardar(SocioRequestDTO dto){
        PlanSuscripcion planSuscripcion = planSuscripcionRepository
                .findById(dto.getPlanSuscripcionId()).orElseThrow(()-> new RuntimeException(
                        "Plan no encontrado con el id: " +dto.getPlanSuscripcionId()));

        Notificacion notificacion = notificacionRepository
                .findById(dto.getNotificacionId()).orElseThrow(()-> new RuntimeException(
                        "Notificacion no encontrada con el id: " +dto.getNotificacionId()));

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

    public void eliminar(Long id){
        socioRepository.deleteById(id);
    }


    public Optional<SocioResponseDTO> actualizar(Long id, SocioRequestDTO dto) {
        return socioRepository.findById(id).map(existente -> {
            PlanSuscripcion planSuscripcion = planSuscripcionRepository
                    .findById(dto.getPlanSuscripcionId()).orElseThrow(()-> new RuntimeException(
                            "Plan no encontrado con el id: " +dto.getPlanSuscripcionId()));

            Notificacion notificacion = notificacionRepository
                    .findById(dto.getNotificacionId()).orElseThrow(()-> new RuntimeException(
                            "Notificacion no encontrada con el id: " +dto.getNotificacionId()));
            existente.setNombres(dto.getNombres());
            existente.setApellidos(dto.getApellidos());
            existente.setRun(dto.getRun());
            existente.setTelefono(dto.getTelefono());
            existente.setFechaNacimiento(dto.getFechaNacimiento());
            existente.setPlanSuscripcion(planSuscripcion);
            existente.setNotificacion(notificacion);
            return mapToDTO(socioRepository.save(existente));
        });
    }

    public Optional<SocioResponseDTO> buscarPorId(Long id){
        return socioRepository.findById(id).map(this::mapToDTO);
    }

    public List<SocioResponseDTO> buscarTodo(){
        return socioRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}
