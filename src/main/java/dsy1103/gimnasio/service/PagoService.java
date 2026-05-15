package dsy1103.gimnasio.service;
import dsy1103.gimnasio.dto.request.PagoRequestDTO;
import dsy1103.gimnasio.dto.response.PagoResponseDTO;
import dsy1103.gimnasio.model.*;
import dsy1103.gimnasio.repository.PagoRepository;
import dsy1103.gimnasio.repository.SocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;
    private final SocioRepository socioRepository;

    private PagoResponseDTO mapToDTO(Pago pago){
        return new PagoResponseDTO(
             pago.getId(),
                pago.getSocio().getNombres() +" "+ pago.getSocio().getApellidos(),
                pago.getCantidadPago(),
                pago.getFechaPago()

        );
    }

    public void eliminar(Long id){
        pagoRepository.deleteById(id);
    }

    public List<PagoResponseDTO> buscarTodo(){
        return pagoRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public PagoResponseDTO guardar(PagoRequestDTO dto){
        Socio socio  = socioRepository
                .findById(dto.getSocioId()).orElseThrow(()-> new RuntimeException(
                        "Socio no encontrado con el id: " +dto.getSocioId()));

        Pago pago = new Pago(
                null,
                socio,
                dto.getCantidadPago(),
                dto.getFechaPago()
        );
        return mapToDTO(pagoRepository.save(pago));
    }

    public Optional<PagoResponseDTO> actualizar(Long id, PagoRequestDTO dto) {
        return pagoRepository.findById(id).map(existente -> {
            Socio socio = socioRepository
                    .findById(dto.getSocioId())
                    .orElseThrow(() -> new RuntimeException(
                            "Socio no encontrado con id: " + dto.getSocioId()));

            existente.setSocio(socio);
            existente.setCantidadPago(dto.getCantidadPago());
            existente.setFechaPago(dto.getFechaPago());
            return mapToDTO(pagoRepository.save(existente));
        });
    }

    public Optional<PagoResponseDTO> buscarPorId(Long id){
        return pagoRepository.findById(id).map(this::mapToDTO);
    }
    public List<Pago> buscarPorSocio(Long id){
        return pagoRepository.findBySocio(id);
    }
}
