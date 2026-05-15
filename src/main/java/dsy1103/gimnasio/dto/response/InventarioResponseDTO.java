package dsy1103.gimnasio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class InventarioResponseDTO {

    private Long id;
    private String maquina;
    private String categoria;
    private Integer cantidad;
    private LocalDate fechaUltimoMantenimiento;

}
