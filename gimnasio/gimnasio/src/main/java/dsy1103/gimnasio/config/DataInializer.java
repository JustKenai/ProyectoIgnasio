package dsy1103.gimnasio.config;

import dsy1103.gimnasio.model.*;
import dsy1103.gimnasio.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

/*
 * * ═══════════════════════════════════════════════════
 * DataInitializer.java
 * Inserta datos de prueba al arrancar la aplicación.
 *
 * ESTRATEGIA "insertar una sola vez":
 *   Antes de insertar, preguntamos cuántos registros
 *   hay en la BD. Si ya hay datos (count > 0), no
 *   hacemos nada. Así:
 *     - Al borrar manualmente datos en phpMyAdmin
 *       NO se vuelven a insertar (conteo > 0 si hay
 *       otros datos, o hay que reiniciar con BD vacía).
 *     - Si la BD está completamente vacía (nuevo despliegue
 *       o BD recreada), se insertan automáticamente.
 *   Esta es la forma más simple y directa para clase.
 * ═══════════════════════════════════════════════════

 * @Slf4j (Lombok): genera automáticamente un logger
 *   'log' para usar log.info(), log.warn(), etc.
 *   Sin Lombok sería: private static final Logger log = ...
 * */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataInializer implements CommandLineRunner{

    private final ClaseRepository claseRepository;
    private final EntrenadorRepository entrenadorRepository;
    private final HorarioRepository horarioRepository;
    private final PlanSuscripcionRepository planSuscripcionRepository;
    private final NotificacionRepository notificacionRepository;
    private final SocioRepository socioRepository;

    @Override
    public void run(String... args) {

        if (claseRepository.count() > 0) {
            log.info(">>> DataInitializer: la BD ya tiene datos, se omite la carga inicial.");
            return;
        }

        log.info(">>> DataInitializer: BD vacía detectada, insertando datos de prueba...");
        //Planes
        PlanSuscripcion plan1 = planSuscripcionRepository.save(
                new PlanSuscripcion(null, "Plan Premium", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", 32000.0, 29));
        log.info(">>> DataInitializer: {} Planes insertados correctamente.",
                horarioRepository.count());

        //Notificaciones
        Notificacion notificacion1 = notificacionRepository.save(
                new Notificacion(null,"Plan a punto de vencer. Realizar pago PRONTO")
        );
        Notificacion notificacion2 = notificacionRepository.save(
                new Notificacion(null,"Descuento especial por navidad. 80% de descuento")
        );


        //Horarios
        Horario horario1 = horarioRepository.save(
                new Horario(null, "Horario 1", LocalDate.now(), LocalDate.now()));
        Horario horario2 = horarioRepository.save(
                new Horario(null, "Horario 2",LocalDate.now(), LocalDate.now()));
        Horario horario3 = horarioRepository.save(
                new Horario(null, "Horario 3", LocalDate.now(), LocalDate.now()));
        log.info(">>> DataInitializer: {} horarios insertados correctamente.",
                horarioRepository.count());

        //Entrenadores
        Entrenador entrenador1 = entrenadorRepository.save(
                new Entrenador(null, "Joaquin Arturo ", "Orellana Monje", "22.010.969-0","Yoga","958076770", horario3));
        Entrenador entrenador2 = entrenadorRepository.save(
                new Entrenador(null, "Joao ", "Gaete", "2423422","Brazo","934873487", horario2));
        log.info(">>> DataInitializer: {} entrenadores insertados correctamente.",
                entrenadorRepository.count());

        //Clases
        Clase clase1 = claseRepository.save(
                new Clase(null,"Clase de Yoga",horario3,entrenador1));
        Clase clase2 = claseRepository.save(
                new Clase(null,"Clase de Brazo",horario2,entrenador2));
        log.info(">>> DataInitializer: {} clases insertada correctamente.",
                claseRepository.count());

        //Socios
        Socio socio1 = socioRepository.save(
                new Socio(null,"Marcelo Ignacio","Antiguay Araya","22.185.379-2",974064520, LocalDate.of(2006,8,10),plan1,notificacion1));
        Socio socio2 = socioRepository.save(
                new Socio(null,"Joaquin","Canales","19.542.230-k",998237634, LocalDate.of(2003,2,3),plan1,notificacion2));
                log.info(">>> DataInitializer: {} socios insertados correctamente.",
                        socioRepository.count());
    }
}