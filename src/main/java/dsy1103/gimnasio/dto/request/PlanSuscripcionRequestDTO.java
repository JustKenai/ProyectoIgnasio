package dsy1103.gimnasio.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanSuscripcionRequestDTO {

    @NotBlank(message = "El plan debe tener un nombre")
    private String nombre;

    @NotBlank(message = "Ingrese una descricion para el plan")
    private String descripcion;

    @Positive(message = "El plan debe tener un precio mayor a 0")
    private Integer precio;

    @NotNull(message = "Debe ingresar la cantidad de dias que dura el plan")
    private Integer duracionDias;

}
