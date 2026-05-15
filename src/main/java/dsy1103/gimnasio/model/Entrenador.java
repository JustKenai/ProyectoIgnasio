package dsy1103.gimnasio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Entrenador")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Entrenador {
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
    private String especialidadEntrenador;

    @Column(nullable = false)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "horario_id", nullable = false)
    private Horario turnoEntrenador;

}
