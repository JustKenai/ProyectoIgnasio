package dsy1103.gimnasio.dto.response;

import dsy1103.gimnasio.model.Socio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PagoResponseDTO {

    private Long id;
    private String socioPago;
    private Integer cantidadPago;
    private LocalDate fechaPago;

}
