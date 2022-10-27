package cr.ac.una;

import cr.ac.una.entity.Mocion;
import cr.ac.una.entity.Persona;
import cr.ac.una.entity.TipoMocion;
import cr.ac.una.service.MocionService;
import cr.ac.una.service.PersonaMocionService;
import cr.ac.una.service.PersonaService;
import cr.ac.una.service.TipoMocionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.sql.Date;
import java.util.Arrays;

@SpringBootApplication
public class MocionesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MocionesApplication.class, args);
    }


    @Bean
    CommandLineRunner run(MocionService MS, PersonaService PS, PersonaMocionService PMS, TipoMocionService TMS) {
        return args ->{
            TMS.guardar(new TipoMocion(null,"Mocion de orden del dia"));
            TMS.guardar(new TipoMocion(null,"Mocion de cierre de sesion"));
            TMS.guardar(new TipoMocion(null,"Mocion de apertura de sesion"));
            MS.guardar(new Mocion(null, "Descripcion 1", null, new Date(System.currentTimeMillis())), Long.parseLong("1"));
            MS.guardar(new Mocion(null, "Descripcion 2", null, new Date(System.currentTimeMillis())), Long.parseLong("2"));
            MS.guardar(new Mocion(null, "Descripcion 3", null, new Date(System.currentTimeMillis())), Long.parseLong("3"));
            MS.guardar(new Mocion(null, "Descripcion 4", null, new Date(System.currentTimeMillis())), Long.parseLong("3"));
            PS.guardar(new Persona(null, "123456", "juan" ));
            PS.guardar(new Persona(null, "123457", "pedro" ));
            PS.guardar(new Persona(null, "123458", "maria" ));
        };
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource basedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
        configuration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type", "Accept",
                "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method",
                "Access-Control-Request-Headers"));
        configuration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        basedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(basedCorsConfigurationSource);
    }
}
