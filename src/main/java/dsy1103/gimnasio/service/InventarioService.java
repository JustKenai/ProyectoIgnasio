package dsy1103.gimnasio.service;

import dsy1103.gimnasio.dto.request.InventarioRequestDTO;
import dsy1103.gimnasio.dto.response.InventarioResponseDTO;
import dsy1103.gimnasio.model.Inventario;
import dsy1103.gimnasio.repository.InventarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class InventarioService {

    private final InventarioRepository inventarioRepository;

    private InventarioResponseDTO mapToDTO(Inventario inventario){
        return new InventarioResponseDTO(
                inventario.getId(),
                inventario.getMaquina(),
                inventario.getCategoria(),
                inventario.getCantidad(),
                inventario.getFechaUltimoMantenimiento()

        );
    }

    public void eliminar(Long id){
        inventarioRepository.deleteById(id);
    }

    public List<InventarioResponseDTO> buscarTodo(){
        return inventarioRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public InventarioResponseDTO guardar(InventarioRequestDTO dto){
        Inventario inventario = new Inventario(
                null,
                dto.getMaquina(),
                dto.getCategoria(),
                dto.getCantidad(),
                dto.getFechaUltimoMantenimiento()
        );
        return mapToDTO(inventarioRepository.save(inventario));
    }

    public Optional<InventarioResponseDTO> buscarPorId(Long id){
        return inventarioRepository.findById(id).map(this::mapToDTO);
    }

    public Optional<InventarioResponseDTO> actualizar(Long id, InventarioRequestDTO dto) {
        return inventarioRepository.findById(id).map(existente -> {

            existente.setMaquina(dto.getMaquina());
            existente.setCategoria(dto.getCategoria());
            existente.setCantidad(dto.getCantidad());
            existente.setFechaUltimoMantenimiento(dto.getFechaUltimoMantenimiento());
            return mapToDTO(inventarioRepository.save(existente));
        });
    }
}
