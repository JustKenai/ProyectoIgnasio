package dsy1103.gimnasio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name= "socio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(unique = true, length = 13, nullable = false)
    private String run;

    @Column(nullable = false)
    private Integer telefono;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "plansuscripcion_id")
    private PlanSuscripcion planSuscripcion;

    @ManyToOne
    @JoinColumn(name = "notificacion_id")
    private Notificacion notificacion;


}
