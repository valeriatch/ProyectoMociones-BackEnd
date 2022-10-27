package cr.ac.una.resource;

import cr.ac.una.entity.Persona;
import cr.ac.una.service.PersonaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/persona")
public class PersonaResource {

    private final PersonaService personaService;

    @GetMapping
    public ResponseEntity<Collection<Persona>> listarPersonas() {
        return ResponseEntity.ok().body(personaService.listar());
    }

    @PostMapping
    public ResponseEntity<Persona> guardarPersona(@RequestBody Persona persona) {
        return ResponseEntity.ok(personaService.guardar(persona));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Persona> eliminarPersona(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.eliminar(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> buscarPersonaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.BuscarPorId(id));
    }
}
