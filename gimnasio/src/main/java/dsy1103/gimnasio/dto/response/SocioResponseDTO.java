package dsy1103.gimnasio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocioResponseDTO {

    private Long id;
    private String nombres;
    private String apellidos;
    private String run;
    private Integer telefono;
    private LocalDate fechaNacimiento;
    private String planSuscripcion;
    private String notificacion;


}
