package cr.ac.una.resource;

import cr.ac.una.entity.*;
import cr.ac.una.repository.LogRepository;
import cr.ac.una.service.MocionService;
import cr.ac.una.service.PersonaMocionService;
import cr.ac.una.service.PersonaService;
import cr.ac.una.service.TipoMocionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class MocionesResource {

    private final MocionService mocionService;
    private final PersonaService personaService;
    private final PersonaMocionService personaMocionService;
    private final TipoMocionService tipoMocionService;

    private final LogRepository LR;



    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    private String strDate= formatter.format(date);



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
        return ResponseEntity.ok().body(personaMocionService.listarPorPersona(id));
    }

    @GetMapping("/persona_mocion/mocion/{id}")
    public ResponseEntity<Collection<PersonaMocion>> listarPersonasPorMocion(@PathVariable Long id) {
        return ResponseEntity.ok().body(personaMocionService.listarPorMocion(id));
    }

    @PostMapping("/persona_mocion/{idpersona}/{idmocion}")
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

    @DeleteMapping("/persona_mocion/{id}")
    public ResponseEntity<PersonaMocion> eliminarPersonaMocion(@PathVariable Long id) {
        return ResponseEntity.ok(personaMocionService.eliminar(id));
    }

    @GetMapping("/persona_mocion")
    public ResponseEntity<Collection<PersonaMocion>> listarPersonasMociones(){
        return ResponseEntity.ok().body(personaMocionService.listar());
    }

    //                                      MOCION

    @GetMapping("/mocion")
    public ResponseEntity<Collection<Mocion>> listarMociones(){
        return ResponseEntity.ok().body(mocionService.listar());
    }

    @PostMapping("/mocion/add/{idTipo}")
    public ResponseEntity<Mocion> guardarMocion(@RequestBody Mocion mocion, @PathVariable Long idTipo) {
        mocion.setFecha(new java.sql.Date(System.currentTimeMillis()));
        return ResponseEntity.ok(mocionService.guardar(mocion, idTipo));
    }

    @DeleteMapping("/mocion/{id}")
    public ResponseEntity<Mocion> eliminarMocion(@PathVariable Long id) {
        return ResponseEntity.ok(mocionService.eliminar(id));
    }

    @GetMapping("/mocion/{id}")
    public ResponseEntity<Mocion> buscarMocionPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mocionService.BuscarPorId(id));
    }

    @PutMapping("/mocion/{id}")
    public ResponseEntity<Mocion> actualizarMocion(@RequestBody Mocion mocion, @PathVariable Long id) {
        return ResponseEntity.ok(mocionService.actualizarMocion(mocion, id));
    }

    @PutMapping("/mociontipo/{idMocion}/{idTipo}")
    public ResponseEntity<Mocion> actualizarMocionTipo(@PathVariable Long idMocion, @PathVariable Long idTipo) {
        return ResponseEntity.ok(mocionService.actualizarTipo(idMocion, idTipo));
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
    @PostMapping("/tipo_mocion/{id}")
    public ResponseEntity<TipoMocion> actualizarTipo(@PathVariable Long id, @RequestBody TipoMocion tipoMocion){
        return ResponseEntity.ok(tipoMocionService.actualizar(id, tipoMocion));
    }

    @DeleteMapping("/tipo_mocion/{id}")
    public ResponseEntity<TipoMocion> eliminarTipoMocion(@PathVariable Long id) {
        return ResponseEntity.ok(tipoMocionService.eliminar(id));
    }

    @GetMapping("/tipo_mocion/{id}")
    public ResponseEntity<TipoMocion> buscarTipoMocionPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tipoMocionService.BuscarPorId(id));
    }

    @GetMapping("/Log")
    public ResponseEntity<Collection<Log>> listarLogs(){
        Collection<Log> logs = new ArrayList<>();
        LR.findAll().forEach(logs::add);
        return ResponseEntity.ok().body(logs);
    }
}
