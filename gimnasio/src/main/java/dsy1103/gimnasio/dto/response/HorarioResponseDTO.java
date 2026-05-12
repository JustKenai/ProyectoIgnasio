package dsy1103.gimnasio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorarioResponseDTO {

    private Long id;
    private String nombreHorario;
    private LocalDate horaInicio;
    private LocalDate horaFin;
}
