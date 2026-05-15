package dsy1103.gimnasio.repository;

import dsy1103.gimnasio.model.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/*
 * JpaRepository<Entidad, TipoPK> hereda:
 * save()        → INSERT o UPDATE automático
 * findById()    → SELECT WHERE id = ?
 * findAll()     → SELECT * FROM categorias
 * deleteById()  → DELETE WHERE id = ?
 * count()       → SELECT COUNT(*)
 * existsById()  → comprueba si existe un id
 * */

public interface ClaseRepository extends JpaRepository<Clase, Long> {

    @Query("SELECT c FROM Clase c WHERE c.horario.id = :horarioId")
    List<Clase> findByHorarioId(@Param("horarioId")Long horarioId);

    //buscar por id entrenador
    @Query("SELECT c FROM Clase c WHERE c.entrenador.id = :entrenadorId")
    List<Clase> findByEntrenadorId(@Param("entrenadorId")Long entrenadorId);




}
