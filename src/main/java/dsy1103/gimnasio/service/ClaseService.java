package dsy1103.gimnasio.service;

import dsy1103.gimnasio.dto.request.ClaseRequestDTO;
import dsy1103.gimnasio.dto.response.ClaseResponseDTO;
import dsy1103.gimnasio.model.Clase;
import dsy1103.gimnasio.model.Entrenador;
import dsy1103.gimnasio.model.Horario;

import dsy1103.gimnasio.repository.ClaseRepository;
import dsy1103.gimnasio.repository.EntrenadorRepository;
import dsy1103.gimnasio.repository.HorarioRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClaseService {

    private final ClaseRepository claseRepository;
    private final HorarioRepository horarioRepository;
    private final EntrenadorRepository entrenadorRepository;

    private ClaseResponseDTO mapToDTO(Clase clase){
        return new ClaseResponseDTO(
                clase.getId(),
                clase.getNombre(),
                clase.getHorario().getNombre(),
                clase.getEntrenador().getNombres() + clase.getEntrenador().getApellidos()
        );

    }

    public void eliminar(Long id){
        claseRepository.deleteById(id);
    }


    public List<ClaseResponseDTO> buscarTodo(){
        return claseRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<ClaseResponseDTO> buscarPorId(Long id){
        return claseRepository.findById(id).map(this::mapToDTO);
    }

    public ClaseResponseDTO guardar(ClaseRequestDTO dto){
        Horario horario = horarioRepository
                .findById(dto.getHorarioId()).orElseThrow(()-> new RuntimeException(
                "Horario no encontrado con el id: " +dto.getHorarioId()));

        Entrenador entrenador = entrenadorRepository
                .findById(dto.getEntrenadorId()).orElseThrow(()-> new RuntimeException(
                        "Entrenador no encontrado con el id: " +dto.getEntrenadorId()));

        Clase clase = new Clase(
                null,
                dto.getNombreClase(),
                horario,
                entrenador
        );
        return mapToDTO(claseRepository.save(clase));
    }

    public Optional<ClaseResponseDTO> actualizar(Long id, ClaseRequestDTO dto) {
        return claseRepository.findById(id).map(existente -> {
            Horario horario = horarioRepository
                    .findById(dto.getHorarioId())
                    .orElseThrow(() -> new RuntimeException(
                            "Horario no encontrado con id: " + dto.getHorarioId()));
            Entrenador entrenador = entrenadorRepository
                    .findById(dto.getEntrenadorId())
                    .orElseThrow(() -> new RuntimeException(
                            "Entrenador no encontrado con id: " + dto.getEntrenadorId()));
            existente.setNombre(dto.getNombreClase());
            existente.setHorario(horario);
            existente.setEntrenador(entrenador);
            return mapToDTO(claseRepository.save(existente));
        });
    }

    public List<Clase> buscarPorEntrenador(Long id){
        return claseRepository.findByEntrenadorId(id);
    }

    public List<Clase> buscarPorHorario(Long id){
        return claseRepository.findByHorarioId(id);
    }
}
