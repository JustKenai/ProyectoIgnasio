package dsy1103.gimnasio.service;

import dsy1103.gimnasio.dto.request.EntrenadorRequestDTO;
import dsy1103.gimnasio.dto.response.EntrenadorResponseDTO;
import dsy1103.gimnasio.model.Clase;
import dsy1103.gimnasio.model.Entrenador;
import dsy1103.gimnasio.model.Horario;
import dsy1103.gimnasio.repository.EntrenadorRepository;
import dsy1103.gimnasio.repository.HorarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;
    private final HorarioRepository horarioRepository;

    public EntrenadorResponseDTO guardar(EntrenadorRequestDTO dto){
        Horario horario = horarioRepository
                .findById(dto.getHorarioId())
                .orElseThrow(() -> new RuntimeException(
                        "Horario no encontrado con id: "+ dto.getHorarioId()));
        Entrenador entrenador = new Entrenador(
                null,
                dto.getNombres(),
                dto.getApellidos(),
                dto.getRun(),
                dto.getEspecialidadEntrenador(),
                dto.getTelefono(),
                horario
        );
        return mapToDTO(entrenadorRepository.save(entrenador));
    }

    private EntrenadorResponseDTO mapToDTO(Entrenador entrenador){
        return new EntrenadorResponseDTO(
                entrenador.getId(),
                entrenador.getNombres(),
                entrenador.getApellidos(),
                entrenador.getRun(),
                entrenador.getEspecialidadEntrenador(),
                entrenador.getTelefono(),
                entrenador.getTurnoEntrenador().getNombre()

        );
    }

    public List<EntrenadorResponseDTO> obtenerEntrenadores(){
        return entrenadorRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<EntrenadorResponseDTO> buscarPorEspecialidad(String especialidadEntrenador) {
        return entrenadorRepository.findByEspecialidad(especialidadEntrenador)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }


}
