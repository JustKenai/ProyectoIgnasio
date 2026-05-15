package dsy1103.gimnasio.config;

import dsy1103.gimnasio.model.*;
import dsy1103.gimnasio.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.*;


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
    private final PagoRepository pagoRepository;
    private final InventarioRepository inventarioRepository;
    private final RegistroAccesoRepository registroAccesoRepository;
    private final RutinaRepository rutinaRepository;


    //hora
    Clock clock = Clock.systemDefaultZone();
    Instant now = clock.instant();
    LocalDateTime dateTime = LocalDateTime.now(clock);


    @Override
    public void run(String... args) {

        if (claseRepository.count() > 0) {
            log.info(">>> DataInitializer: la BD ya tiene datos, se omite la carga inicial.");
            return;
        }
        log.info(">>> DataInitializer: BD vacía detectada, insertando datos de prueba...");


        //Planes
        PlanSuscripcion planBasico = planSuscripcionRepository.save(
                    new PlanSuscripcion(null, "Plan Básico", "✅ Uso de sala de máquinas ✅ Peso libre ✅ Clases grupales básicas.", 140000, 30));
        PlanSuscripcion planBlack = planSuscripcionRepository.save(
                new PlanSuscripcion(null, "Plan Black", "✅ Posibilidad de llevar a un amigo ✅ Acceso a áreas de relax.✅ Incluye todo lo del Plan Básico", 17450,30));
        PlanSuscripcion planCorporativo = planSuscripcionRepository.save(
                new PlanSuscripcion(null, "Plan Corporativo", "✅ Se ofrece como beneficio laboral a empleados de empresas asociadas. ✅ Más todo lo del Plan Básico", 26900,30));
        log.info(">>> DataInitializer: {} Planes insertados correctamente.",
                horarioRepository.count());


        //Notificaciones
        Notificacion notificacionVencer = notificacionRepository.save(
                new Notificacion(null,"🔔 Plan a punto de vencer. Realizar pago PRONTO")
        );
        Notificacion notificacionNavidad = notificacionRepository.save(
                new Notificacion(null,"🔔 Descuento especial por navidad. 80% de descuento")
        );
        //Horarios
        Horario horario1 = horarioRepository.save(
                new Horario(null, "Horario 1", LocalDate.now(Clock.systemUTC()), LocalDate.now(Clock.systemDefaultZone())));
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
                new Entrenador(null, "Joao ", "Gaete", "24.234.221-K","Brazo","934873487", horario2));
        Entrenador entrenador3 = entrenadorRepository.save(
                new Entrenador(null, "Benjamin", "Ortega", "21.979.554-0", "Fuerza y Acondicionamiento", "935385387", horario3));
        log.info(">>> DataInitializer: {} entrenadores insertados correctamente.",
                entrenadorRepository.count());

        //Clases
        Clase claseYoga = claseRepository.save(
                new Clase(null,"Clase de Yoga",horario1,entrenador1));
        Clase claseBrazo = claseRepository.save(
                new Clase(null,"Clase de Brazo",horario2,entrenador2));
        Clase claseFuerza = claseRepository.save(
                new Clase(null, "Clase de Fuerza y Acondicionamiento", horario3,entrenador3));
        log.info(">>> DataInitializer: {} clases insertada correctamente.",
                claseRepository.count());

        //Socios
        Socio socio1 = socioRepository.save(
                new Socio(null,"Marcelo Ignacio","Antiguay Araya","22.185.379-2",974064520, LocalDate.of(2006,8,10),planBasico,notificacionVencer));
        Socio socio2 = socioRepository.save(
                new Socio(null,"Joaquin Antonio","Canales Sepulveda","19.542.230-k",998237634, LocalDate.of(2003,2,3),planBlack,notificacionNavidad));
        Socio socio3 = socioRepository.save(
                new Socio(null,"Manu","Sarmiento Castro","21.235.123-2",947272781, LocalDate.of(2001,1,24),planCorporativo,notificacionNavidad));
                log.info(">>> DataInitializer: {} socios insertados correctamente.",
                        socioRepository.count());
        //Pagos
        Pago pago1 = pagoRepository.save(
                new Pago(null, socio1, 14000, LocalDate.of(2001,1,24)));
        Pago pago2 = pagoRepository.save(
                new Pago(null, socio2, 17450, LocalDate.of(2002,2,22)));
        Pago pago3 = pagoRepository.save(
                new Pago(null, socio3, 26900, LocalDate.of(2003,3,3)));
        log.info(">>> DataInitializer: {} pagos insertados correctamente.",
                pagoRepository.count());

        //RegistroAcceso
        RegistroAcceso registroAcceso1 = registroAccesoRepository.save(
                new RegistroAcceso(null, socio1,LocalTime.of(13, 30), LocalTime.of(15,30)));
        RegistroAcceso registroAcceso2 = registroAccesoRepository.save(
                new RegistroAcceso(null, socio2,LocalTime.of(15, 30), LocalTime.of(18,30)));
        RegistroAcceso registroAcceso3 = registroAccesoRepository.save(
                new RegistroAcceso(null, socio3,LocalTime.of(18, 30), LocalTime.of(22,30)));
        log.info(">>> DataInitializer: {} registros insertados correctamente.",
                registroAccesoRepository.count());

        //Inventario
        Inventario inventario1 = inventarioRepository.save(
                new Inventario(null, "Maquina de Pilates", "Yoga", 2, LocalDate.of(2025, 3, 21)));
        Inventario inventario2 = inventarioRepository.save(
                new Inventario(null, "Maquina de Curl de Biceps", "Brazo", 5, LocalDate.of(2026, 6, 17)));
        Inventario inventario3 = inventarioRepository.save(
                new Inventario(null, "Maquina Smith", "Fuerza", 7, LocalDate.of(2024, 1, 15)));
        log.info(">>> DataInitializer: {} Inventarios insertados correctamente.",
                inventarioRepository.count());

        //Rutina
        Rutina rutina1 = rutinaRepository.save(
                new Rutina(null, socio1, entrenador1, "Clase de Pilates 2hrs"));
        Rutina rutina2 = rutinaRepository.save(
                new Rutina(null, socio2, entrenador2, "Clase de Brazo 2hrs"));
        Rutina rutina3 = rutinaRepository.save(
                new Rutina(null, socio3, entrenador3, "Clase de Fuerza 1:30hrs"));
        log.info(">>> DataInitializer: {} Rutinas insertadas correctamente.",
                rutinaRepository.count());

    }
}

