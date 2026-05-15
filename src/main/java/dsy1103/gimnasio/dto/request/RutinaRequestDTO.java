package dsy1103.gimnasio.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RutinaRequestDTO {

    @NotNull(message = "Ingrese un id del socio asociado a esta rutina")
    private Long socioId;

    @NotNull(message = "Esta rutina debe tener almenos un entrenador")
    private Long entrenadorId;

    @NotBlank(message = "Ingrese una descripcion para la rutina")
    private String descripcion;
}
