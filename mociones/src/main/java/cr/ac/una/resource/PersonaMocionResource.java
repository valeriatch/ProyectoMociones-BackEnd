package cr.ac.una.resource;

import cr.ac.una.entity.Mocion;
import cr.ac.una.entity.Persona;
import cr.ac.una.entity.PersonaMocion;
import cr.ac.una.service.MocionService;
import cr.ac.una.service.PersonaMocionService;
import cr.ac.una.service.PersonaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/persona_mocion")
public class PersonaMocionResource {

    private final PersonaMocionService personaMocionService;

    private final MocionService mocionService;

    private final PersonaService personaService;


    Date date = new Date();

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

    private String strDate = formatter.format(date);

    @GetMapping("/persona/{id}")
    public ResponseEntity<Collection<PersonaMocion>> listarMocionesPorPersona(@PathVariable Long id) {
        return ResponseEntity.ok().body(personaMocionService.listarPorPersona(id));
    }

    @GetMapping("/mocion/{id}")
    public ResponseEntity<Collection<PersonaMocion>> listarPersonasPorMocion(@PathVariable Long id) {
        return ResponseEntity.ok().body(personaMocionService.listarPorMocion(id));
    }

    @PostMapping("/{idpersona}/{idmocion}")
    public ResponseEntity<PersonaMocion> guardarPersonaMocion(@PathVariable("idpersona") Long idpersona,
                                                              @PathVariable("idmocion") Long idmocion) {

        Persona persona = personaService.BuscarPorId(idpersona);
        Mocion mocion = mocionService.BuscarPorId(idmocion);
        if (persona == null || mocion == null) {
            return ResponseEntity.badRequest().build();
        }

        Collection<PersonaMocion> personas = personaMocionService.listarPorPersona(idpersona);
        Collection<PersonaMocion> mociones = personaMocionService.listarPorMocion(idmocion);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Collection<PersonaMocion> listaFiltrada = new ArrayList<>();
        for (PersonaMocion personaMocion : personas) {
            String fechaDeLista = simpleDateFormat.format(personaMocion.getMocion().getFecha());
            String fechaMocion = simpleDateFormat.format(mocion.getFecha());
            if (fechaDeLista.equals(fechaMocion)) {
                listaFiltrada.add(personaMocion);
            }
        }
        if (mociones.size() >= 3 || listaFiltrada.size() >= 5) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(personaMocionService.guardar(idpersona, idmocion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonaMocion> eliminarPersonaMocion(@PathVariable Long id) {
        return ResponseEntity.ok(personaMocionService.eliminar(id));
    }

    @GetMapping
    public ResponseEntity<Collection<PersonaMocion>> listarPersonasMociones() {
        return ResponseEntity.ok().body(personaMocionService.listar());
    }
}
