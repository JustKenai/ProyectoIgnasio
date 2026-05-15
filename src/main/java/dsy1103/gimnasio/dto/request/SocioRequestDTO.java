package dsy1103.gimnasio.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocioRequestDTO {
    @NotBlank(message = "Debe ingresar un nombre")
    private String nombres;

    @NotBlank(message = "Debe ingresar un apellido")
    private String apellidos;

    @NotNull(message = "Debe ingresar un run")
    private String run;

    @NotNull(message = "Ingrese un numero de telefono")
    private Integer telefono;

    @NotNull(message = "Ingrese una fecha de nacimiento")
    private LocalDate fechaNacimiento;

    @NotNull(message = "El socio debe tener un plan activo")
    private Long planSuscripcionId;

    private Long notificacionId;
}
