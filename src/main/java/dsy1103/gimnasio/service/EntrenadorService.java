package dsy1103.gimnasio.service;

import dsy1103.gimnasio.dto.request.EntrenadorRequestDTO;
import dsy1103.gimnasio.dto.response.EntrenadorResponseDTO;
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

    public List<EntrenadorResponseDTO> buscarTodo(){
        return entrenadorRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

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

    public List<EntrenadorResponseDTO> buscarPorEspecialidad(String especialidadEntrenador) {
        return entrenadorRepository.findByEspecialidad(especialidadEntrenador)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public void eliminar(Long id){
        entrenadorRepository.deleteById(id);
    }

    public Optional<EntrenadorResponseDTO> buscarPorId(Long id){
        return entrenadorRepository.findById(id).map(this::mapToDTO);
    }

    public Optional<EntrenadorResponseDTO> actualizar(Long id, EntrenadorRequestDTO dto){
        return entrenadorRepository.findById(id).map(existente -> {
            Horario horario = horarioRepository
                    .findById(dto.getHorarioId())
                    .orElseThrow(() -> new RuntimeException(
                            "Horario no encontrado con id: " + dto.getHorarioId()));

            existente.setNombres(dto.getNombres());
            existente.setApellidos(dto.getApellidos());
            existente.setRun(dto.getRun());
            existente.setEspecialidadEntrenador(dto.getEspecialidadEntrenador());
            existente.setTelefono(dto.getTelefono());
            existente.setTurnoEntrenador(horario);
            return mapToDTO(entrenadorRepository.save(existente));
        });
    }
}
