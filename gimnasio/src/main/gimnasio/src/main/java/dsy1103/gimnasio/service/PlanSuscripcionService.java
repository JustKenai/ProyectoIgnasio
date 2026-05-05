package dsy1103.gimnasio.service;

import dsy1103.gimnasio.dto.response.ClaseResponseDTO;
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

    private PlanSuscripcionResponseDTO mapToDTO(PlanSuscripcion ps){
        return new PlanSuscripcionResponseDTO(
                ps.getId(),
                ps.getNombre(),
                ps.getDescripcion(),
                ps.getPrecio(),
                ps.getDuracionDias()
        );
    }

    public List<PlanSuscripcionResponseDTO> buscarPorPlan(String nombre){
        return planSuscripcionRepository.findBySuscripcion(nombre)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<PlanSuscripcionResponseDTO> buscarPorIdPlan(Long id){
        return planSuscripcionRepository.findById(id).map(this::mapToDTO);
    }

}
