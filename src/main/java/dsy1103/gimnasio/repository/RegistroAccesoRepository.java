package dsy1103.gimnasio.repository;

import dsy1103.gimnasio.model.RegistroAcceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistroAccesoRepository extends JpaRepository<RegistroAcceso, Long> {

    @Query("SELECT r FROM RegistroAcceso r WHERE r.socio.id = :socioId")
    List<RegistroAcceso> findBySocio(@Param("socioId")Long socioId);
}
