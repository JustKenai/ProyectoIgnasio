package dsy1103.gimnasio.service;

import dsy1103.gimnasio.dto.request.HorarioRequestDTO;
import dsy1103.gimnasio.dto.response.HorarioResponseDTO;
import dsy1103.gimnasio.model.Horario;
import dsy1103.gimnasio.repository.HorarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public void eliminar(Long id){
        horarioRepository.deleteById(id);
    }

    public List<HorarioResponseDTO> buscarTodo(){
        return horarioRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<HorarioResponseDTO> buscarPorId(Long id){
        return horarioRepository.findById(id).map(this::mapToDTO);
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

    public Optional<HorarioResponseDTO> actualizar(Long id, HorarioRequestDTO dto) {
        return horarioRepository.findById(id).map(existente -> {

            existente.setNombre(dto.getNombreHorario());
            existente.setHoraInicio(dto.getHoraInicio());
            existente.setHoraFin(dto.getHoraFin());
            return mapToDTO(horarioRepository.save(existente));
        });
    }
}
