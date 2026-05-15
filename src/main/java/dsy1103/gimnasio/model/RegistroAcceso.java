package dsy1103.gimnasio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name="RegistroAcceso")

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RegistroAcceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "socio_id", nullable = false)
    private Socio socio;

    @Column(nullable = false)
    private LocalTime fechaHoraEntrada;

    @Column(nullable = false)
    private LocalTime fechaHoraSalida;

}
