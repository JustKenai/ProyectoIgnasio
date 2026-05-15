package dsy1103.gimnasio.service;
import dsy1103.gimnasio.dto.request.RutinaRequestDTO;
import dsy1103.gimnasio.dto.response.RutinaResponseDTO;
import dsy1103.gimnasio.model.*;
import dsy1103.gimnasio.repository.EntrenadorRepository;
import dsy1103.gimnasio.repository.RutinaRepository;
import dsy1103.gimnasio.repository.SocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class RutinaService {

    private final RutinaRepository rutinaRepository;
    private final SocioRepository socioRepository;
    private final EntrenadorRepository entrenadorRepository;

    private RutinaResponseDTO mapToDTO(Rutina rutina){
        return new RutinaResponseDTO(
                rutina.getId(),
                rutina.getSocio().getNombres() + " " + rutina.getSocio().getApellidos(),
                rutina.getEntrenador().getNombres() + " " + rutina.getEntrenador().getApellidos(),
                rutina.getDescripcion()
        );

    }

    public void eliminar(Long id){
        rutinaRepository.deleteById(id);
    }

    public List<RutinaResponseDTO> buscarTodo(){
        return rutinaRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<RutinaResponseDTO> buscarPorId(Long id){
        return rutinaRepository.findById(id).map(this::mapToDTO);
    }

    public RutinaResponseDTO guardar(RutinaRequestDTO dto){
        Socio socio = socioRepository
                .findById(dto.getSocioId()).orElseThrow(()-> new RuntimeException(
                        "Socio no encontrado con el id: " +dto.getSocioId()));

        Entrenador entrenador = entrenadorRepository
                .findById(dto.getEntrenadorId()).orElseThrow(()-> new RuntimeException(
                        "Entrenador no encontrado con el id: " +dto.getEntrenadorId()));

        Rutina rutina = new Rutina(
                null,
                socio,
                entrenador,
                dto.getDescripcion()

        );
        return mapToDTO(rutinaRepository.save(rutina));
    }

    public Optional<RutinaResponseDTO> actualizar(Long id, RutinaRequestDTO dto) {
        return rutinaRepository.findById(id).map(existente -> {
            Socio socio = socioRepository
                    .findById(dto.getSocioId())
                    .orElseThrow(() -> new RuntimeException(
                            "Socio no encontrado con id: " + dto.getSocioId()));

            Entrenador entrenador = entrenadorRepository
                    .findById(dto.getEntrenadorId())
                    .orElseThrow(() -> new RuntimeException(
                            "Entrenador no encontrado con id: " + dto.getEntrenadorId()));
            existente.setSocio(socio);
            existente.setEntrenador(entrenador);
            existente.setDescripcion(dto.getDescripcion());
            return mapToDTO(rutinaRepository.save(existente));
        });
    }

}
