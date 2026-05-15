package dsy1103.gimnasio.service;

import dsy1103.gimnasio.dto.request.PlanSuscripcionRequestDTO;
import dsy1103.gimnasio.dto.response.PlanSuscripcionResponseDTO;
import dsy1103.gimnasio.model.PlanSuscripcion;
import dsy1103.gimnasio.repository.PlanSuscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanSuscripcionService {

    private final PlanSuscripcionRepository planSuscripcionRepository;

    private PlanSuscripcionResponseDTO mapToDTO(PlanSuscripcion dto){
        return new PlanSuscripcionResponseDTO(
                dto.getId(),
                dto.getNombre(),
                dto.getDescripcion(),
                dto.getPrecio(),
                dto.getDuracionDias()
        );
    }

    public List<PlanSuscripcionResponseDTO> buscarPorPlan(String nombre){
        return planSuscripcionRepository.findByNombre(nombre)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<PlanSuscripcionResponseDTO> buscarPorIdPlan(Long id){
        return planSuscripcionRepository.findById(id).map(this::mapToDTO);
    }

    public void eliminar(Long id){
        planSuscripcionRepository.deleteById(id);
    }

    public PlanSuscripcionResponseDTO guardar(PlanSuscripcionRequestDTO dto){
        PlanSuscripcion planSuscripcion = new PlanSuscripcion(
                null,
                dto.getNombre(),
                dto.getDescripcion(),
                dto.getPrecio(),
                dto.getDuracionDias()
        );
        return mapToDTO(planSuscripcionRepository.save(planSuscripcion));
    }

    public List<PlanSuscripcionResponseDTO> buscarTodo(){
        return planSuscripcionRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<PlanSuscripcionResponseDTO> actualizar(Long id, PlanSuscripcionRequestDTO dto) {
        return planSuscripcionRepository.findById(id).map(existente -> {

            existente.setNombre(dto.getNombre());
            existente.setDescripcion(dto.getDescripcion());
            existente.setPrecio(dto.getPrecio());
            existente.setDuracionDias(dto.getDuracionDias());
            return mapToDTO(planSuscripcionRepository.save(existente));
        });
    }
}
