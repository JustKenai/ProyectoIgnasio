package dsy1103.gimnasio.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorarioRequestDTO {

    @NotBlank(message = "Ingrese un nombre para el horario")
    private String nombreHorario;

    @NotNull(message = "Debe ingresar una hora de inicio para conveniencia del cliente")
    private LocalDate horaInicio;

    @NotNull(message = "Debe ingresar una hora de fin para conveniencia del cliente")
    private LocalDate horaFin;
}
