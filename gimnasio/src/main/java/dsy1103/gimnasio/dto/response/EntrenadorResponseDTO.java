package dsy1103.gimnasio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntrenadorResponseDTO {
    private Long id;
    private String nombres;
    private String apellidos;
    private String run;
    private String especialidadEntrenador;
    private String telefono;

    private String horarioEntrenador;

}
