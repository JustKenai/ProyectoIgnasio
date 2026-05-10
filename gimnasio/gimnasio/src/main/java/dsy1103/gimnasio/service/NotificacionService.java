package dsy1103.gimnasio.service;

import dsy1103.gimnasio.dto.request.ClaseRequestDTO;
import dsy1103.gimnasio.dto.request.HorarioRequestDTO;
import dsy1103.gimnasio.dto.request.NotificacionRequestDTO;
import dsy1103.gimnasio.dto.response.ClaseResponseDTO;
import dsy1103.gimnasio.dto.response.HorarioResponseDTO;
import dsy1103.gimnasio.dto.response.NotificacionResponseDTO;
import dsy1103.gimnasio.model.*;
import dsy1103.gimnasio.repository.NotificacionRepository;
import dsy1103.gimnasio.repository.SocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    private NotificacionResponseDTO mapToDTO(Notificacion n) {
        return new NotificacionResponseDTO(
                n.getId(),
                n.getMensaje()
        );
    }

    public Optional<NotificacionResponseDTO> buscarPorId(Long id){
        return notificacionRepository.findById(id).map(this::mapToDTO);

    }

    public void eliminar(Long id){
        notificacionRepository.deleteById(id);
    }

    public NotificacionResponseDTO guardar(NotificacionRequestDTO dto){
        Notificacion notificacion = new Notificacion(
                null,
                dto.getMensaje()
        );
        return mapToDTO(notificacionRepository.save(notificacion));
    }

    public List<NotificacionResponseDTO> buscarTodo(){
        return notificacionRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<NotificacionResponseDTO> actualizar(Long id, NotificacionRequestDTO dto) {
        return notificacionRepository.findById(id).map(existente -> {
            existente.setMensaje(dto.getMensaje());
            return mapToDTO(notificacionRepository.save(existente));
        });
    }

}