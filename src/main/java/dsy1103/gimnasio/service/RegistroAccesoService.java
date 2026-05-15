package dsy1103.gimnasio.service;

import dsy1103.gimnasio.dto.request.RegistroAccesoRequestDTO;
import dsy1103.gimnasio.dto.response.RegistroAccesoResponseDTO;
import dsy1103.gimnasio.model.*;
import dsy1103.gimnasio.repository.RegistroAccesoRepository;
import dsy1103.gimnasio.repository.SocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistroAccesoService {
    private final RegistroAccesoRepository registroAccesoRepository;
    private final SocioRepository socioRepository;

    private RegistroAccesoResponseDTO mapToDTO(RegistroAcceso dto){
        return new RegistroAccesoResponseDTO(
                dto.getId(),
                dto.getSocio().getNombres() + " " + dto.getSocio().getApellidos(),
                dto.getFechaHoraEntrada(),
                dto.getFechaHoraSalida()
        );
    }

    public void eliminar(Long id){
        registroAccesoRepository.deleteById(id);
    }

    public List<RegistroAccesoResponseDTO> buscarTodo(){
        return registroAccesoRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<RegistroAccesoResponseDTO> buscarPorId(Long id){
        return registroAccesoRepository.findById(id).map(this::mapToDTO);
    }

    public RegistroAccesoResponseDTO guardar(RegistroAccesoRequestDTO dto){
        Socio socio  = socioRepository
                .findById(dto.getSocioId()).orElseThrow(()-> new RuntimeException(
                        "Socio no encontrado con el id: " +dto.getSocioId()));

        RegistroAcceso registroAcceso = new RegistroAcceso(
                null,
                socio,
                dto.getHoraEntrada(),
                dto.getHoraSalida()
        );
        return mapToDTO(registroAccesoRepository.save(registroAcceso));
    }

    public Optional<RegistroAccesoResponseDTO> actualizar(Long id, RegistroAccesoRequestDTO dto) {
        return registroAccesoRepository.findById(id).map(existente -> {
            Socio socio = socioRepository
                    .findById(dto.getSocioId())
                    .orElseThrow(() -> new RuntimeException(
                            "Horario no encontrado con id: " + dto.getSocioId()));
            existente.setSocio(socio);
            existente.setFechaHoraEntrada(dto.getHoraEntrada());
            existente.setFechaHoraSalida(dto.getHoraSalida());
            return mapToDTO(registroAccesoRepository.save(existente));
        });
    }

    public List<RegistroAcceso> buscarPorSocio(Long id){
        return registroAccesoRepository.findBySocio(id);
    }
}
