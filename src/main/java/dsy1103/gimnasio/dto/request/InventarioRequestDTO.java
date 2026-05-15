package dsy1103.gimnasio.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class InventarioRequestDTO {

    @NotBlank(message = "Ingrese el nombre de la maquina")
    private String maquina;

    @NotBlank(message = "Ingrese a que categoria pertenece el equipamiento")
    private String categoria;

    @NotNull(message = "")
    @PositiveOrZero(message = "Ingrese la cantidad que existe")
    private Integer cantidad;

    @NotNull(message = "Por seguridad, el equipo debe tener fecha de mantenimiento")
    private LocalDate fechaUltimoMantenimiento;

}
