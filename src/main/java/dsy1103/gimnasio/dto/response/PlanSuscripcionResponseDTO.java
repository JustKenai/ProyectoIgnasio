package dsy1103.gimnasio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanSuscripcionResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Integer precio;
    private Integer duracionDias;
}
