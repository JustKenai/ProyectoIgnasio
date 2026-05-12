package dsy1103.gimnasio.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PagoRequestDTO {

    @NotNull(message = "Ingrese la id del socio que ha realizado el pago")
    private Long socio_id;

    @NotNull(message = "El pago no puede estar vacio")
    @Positive(message = "La cantidad debe ser mayor a 0")
    private Integer cantidadPago;

    @NotNull(message = "Por control el pago debe tener fecha")
    private LocalDate fechaPago;
}
