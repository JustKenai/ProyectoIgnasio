package dsy1103.gimnasio.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroAccesoRequestDTO {

    @NotNull(message = "Debe ingresar el id de un socio.")
    private Long socioId;

    @NotNull(message = "Ingrese la hora de entrada del socio. ")
    private LocalTime HoraEntrada;

    @NotNull(message = "Ingrese la hora de salida del socio.")
    private LocalTime    HoraSalida;
}
