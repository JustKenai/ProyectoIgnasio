package dsy1103.gimnasio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroAccesoResponseDTO {

    private Long id;
    private String socio;
    private LocalTime HoraEntrada;
    private LocalTime HoraSalida;
}
