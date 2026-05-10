package dsy1103.gimnasio.repository;

import dsy1103.gimnasio.model.Horario;
import dsy1103.gimnasio.model.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SocioRepository extends JpaRepository<Socio, Long> {



    @Query("SELECT s FROM Socio s WHERE s.nombres = :nombres")
    List<Socio> findByNombre(@Param("nombres")String nombres);

}
