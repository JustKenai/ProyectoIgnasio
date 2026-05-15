package dsy1103.gimnasio.dto.request;

import dsy1103.gimnasio.model.Horario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntrenadorRequestDTO {

    @NotBlank(message = "Por seguridad el entrenador debe tener su nombre completo")
    private String nombres;

    @NotBlank(message = "Por seguridad el entrenador debe tener su nombre completo")
    private String apellidos;

    @NotBlank(message = "Para registrar un entrenador debe ingresar su rut")
    private String run;

    @NotBlank(message = "Todo entrenador debe tener una especialidad")
    private String especialidadEntrenador;

    @NotBlank(message = "")
    private String telefono;

    @NotNull(message = "El turno no puede estar vacio")
    private Long horarioId;
}
