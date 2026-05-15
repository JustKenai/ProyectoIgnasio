package dsy1103.gimnasio.repository;

import dsy1103.gimnasio.model.Clase;
import dsy1103.gimnasio.model.Horario;
import dsy1103.gimnasio.model.PlanSuscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanSuscripcionRepository extends JpaRepository<PlanSuscripcion, Long> {


    @Query("SELECT ps FROM PlanSuscripcion ps WHERE ps.nombre = :nombre")
    List<PlanSuscripcion> findByNombre(@Param("nombre")String nombre);

}