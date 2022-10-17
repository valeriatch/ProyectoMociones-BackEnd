package cr.ac.una.resource;

import cr.ac.una.entity.Mocion;
import cr.ac.una.entity.Persona;
import cr.ac.una.entity.PersonaMocion;
import cr.ac.una.entity.TipoMocion;
import cr.ac.una.service.MocionService;
import cr.ac.una.service.PersonaMocionService;
import cr.ac.una.service.PersonaService;
import cr.ac.una.service.TipoMocionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MocionesResource {

    private final MocionService mocionService;
    private final PersonaService personaService;
    private final PersonaMocionService personaMocionService;
    private final TipoMocionService tipoMocionService;

    //                                  PERSONA

    @GetMapping("/persona")
    public ResponseEntity<Collection<Persona>> listarPersonas(){
        return ResponseEntity.ok().body(personaService.listar());
    }

    @PostMapping("/persona")
    public ResponseEntity<Persona> guardarPersona(@RequestBody Persona persona) {
        return ResponseEntity.ok(personaService.guardar(persona));
    }

    @DeleteMapping("/persona/{id}")
    public ResponseEntity<Persona> eliminarPersona(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.eliminar(id));
    }

    @GetMapping("/persona/{id}")
    public ResponseEntity<Persona> buscarPersonaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.BuscarPorId(id));
    }

    //                                     PERSONA MOCION

    @GetMapping("/persona_mocion/persona/{id}")
    public ResponseEntity<Collection<PersonaMocion>> listarMocionesPorPersona(@PathVariable Long id) {
        return ResponseEntity.ok().body(personaMocionService.listarMocionesPorPersona(id));
    }

    @GetMapping("/persona_mocion/mocion/{id}")
    public ResponseEntity<Collection<PersonaMocion>> listarPersonasPorMocion(@PathVariable Long id) {
        return ResponseEntity.ok().body(personaMocionService.listarPersonasPorMocion(id));
    }

    @PostMapping("/persona_mocion")
    public ResponseEntity<PersonaMocion> guardarPersonaMocion(@RequestBody PersonaMocion personaMocion) {
        return ResponseEntity.ok(personaMocionService.guardar(personaMocion.getPersona().getId(), personaMocion.getMocion().getId()));
    }

    @DeleteMapping("/persona_mocion/{id}")
    public ResponseEntity<PersonaMocion> eliminarPersonaMocion(@PathVariable Long id) {
        return ResponseEntity.ok(personaMocionService.eliminar(id));
    }

    //                                      MOCION

    @GetMapping("/mocion")
    public ResponseEntity<Collection<Mocion>> listarMociones(){
        return ResponseEntity.ok().body(mocionService.listar());
    }

    @PostMapping("/mocion")
    public ResponseEntity<Mocion> guardarMocion(@RequestBody Mocion mocion) {
        return ResponseEntity.ok(mocionService.guardar(mocion));
    }

    @DeleteMapping("/mocion/{id}")
    public ResponseEntity<Mocion> eliminarMocion(@PathVariable Long id) {
        return ResponseEntity.ok(mocionService.eliminar(id));
    }

    @GetMapping("/mocion/{id}")
    public ResponseEntity<Mocion> buscarMocionPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mocionService.BuscarPorId(id));
    }

    //                                      TIPO MOCION

    @GetMapping("/tipo_mocion")
    public ResponseEntity<Collection<TipoMocion>> listarTipoMociones(){
        return ResponseEntity.ok().body(tipoMocionService.listar());
    }

    @PostMapping("/tipo_mocion")
    public ResponseEntity<TipoMocion> guardarTipoMocion(@RequestBody TipoMocion tipoMocion) {
        return ResponseEntity.ok(tipoMocionService.guardar(tipoMocion));
    }

    @DeleteMapping("/tipo_mocion/{id}")
    public ResponseEntity<TipoMocion> eliminarTipoMocion(@PathVariable Long id) {
        return ResponseEntity.ok(tipoMocionService.eliminar(id));
    }

    @GetMapping("/tipo_mocion/{id}")
    public ResponseEntity<TipoMocion> buscarTipoMocionPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tipoMocionService.BuscarPorId(id));
    }
}
