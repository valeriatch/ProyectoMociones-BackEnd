package cr.ac.una;

import cr.ac.una.service.MocionService;
import cr.ac.una.service.PersonaMocionService;
import cr.ac.una.service.PersonaService;
import cr.ac.una.service.TipoMocionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MocionesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MocionesApplication.class, args);
    }


    @Bean
    CommandLineRunner run(MocionService MS, PersonaService PS, PersonaMocionService PMS, TipoMocionService TMS) {
        return args ->{
			            System.out.println("Hola mundo");
        };
    }
}
