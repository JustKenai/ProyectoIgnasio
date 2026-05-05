package dsy1103.gimnasio.repository;

import dsy1103.gimnasio.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
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
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    List<Horario> findByHoraInicioHoraFinBetween(LocalDate min, LocalDate max);

    @Query("SELECT h FROM Horario h WHERE h.nombreHorario = :nombreHorario")
    List<Horario> findByNombreHorario(@Param("nombreHorario")String nombreHorario);


}
