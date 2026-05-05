package dsy1103.gimnasio.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionRequestDTO {

    @NotNull(message = "Ingrese el id del socio que recibira la notificacion")
    private Long socioID;

    @NotBlank(message = "Ingrese algun mensaje para el socio")
    private String mensaje;

}
