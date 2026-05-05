package dsy1103.gimnasio.repository;

import dsy1103.gimnasio.model.Entrenador;
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

public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {


    @Query("SELECT e FROM Entrenador e WHERE e.especialidadEntrenador = :especialidadEntrenador")
    List<Entrenador> findByEspecialidad(@Param("especialidadEntrenador")String especialidadEntrenador);

}
