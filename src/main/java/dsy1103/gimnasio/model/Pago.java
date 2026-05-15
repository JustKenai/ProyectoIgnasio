package dsy1103.gimnasio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name= "Pago")

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "socio_id",nullable = false)
    private Socio socio;

    @Column(nullable = false)
    private Integer cantidadPago;

    @Column(nullable = false)
    private LocalDate fechaPago;

}
