package dsy1103.gimnasio.service;

import dsy1103.gimnasio.dto.request.HorarioRequestDTO;
import dsy1103.gimnasio.dto.response.EntrenadorResponseDTO;
import dsy1103.gimnasio.dto.response.HorarioResponseDTO;
import dsy1103.gimnasio.model.Entrenador;
import dsy1103.gimnasio.model.Horario;
import dsy1103.gimnasio.repository.HorarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HorarioService {

    private final HorarioRepository horarioRepository;

    private HorarioResponseDTO mapToDTO(Horario h){
        return new HorarioResponseDTO(
                h.getId(),
                h.getNombre(),
                h.getHoraFin(),
                h.getHoraFin()
        );
    }

    public HorarioResponseDTO guardar(HorarioRequestDTO dto){
        Horario horario = new Horario(
                null,
                dto.getNombreHorario(),
                dto.getHoraInicio(),
                dto.getHoraFin()
        );
        return mapToDTO(horarioRepository.save(horario));
    }


    public List<HorarioResponseDTO> buscarPorNombreHorario(String nombreHorario) {
        return horarioRepository.findByNombreHorario(nombreHorario)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}
