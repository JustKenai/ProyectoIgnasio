package dsy1103.gimnasio.repository;

import dsy1103.gimnasio.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {

    @Query("SELECT p FROM Pago p WHERE p.socio.id = :socioId")
    List<Pago> findBySocio(@Param("socioId")Long socio);

}

