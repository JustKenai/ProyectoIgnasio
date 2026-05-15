package dsy1103.gimnasio.dto.response;

import dsy1103.gimnasio.model.Entrenador;
import dsy1103.gimnasio.model.Socio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RutinaResponseDTO {

    private Long id;
    private String socio;
    private String entrenador;
    private String descripcion;

}
