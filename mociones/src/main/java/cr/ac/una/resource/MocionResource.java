package cr.ac.una.resource;

import cr.ac.una.entity.*;
import cr.ac.una.repository.LogRepository;
import cr.ac.una.service.MocionService;
import cr.ac.una.service.TipoMocionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mocion")
public class MocionResource {

	private final MocionService mocionService;

	private final TipoMocionService tipoMocionService;

	private final LogRepository LR;

	@GetMapping
	public ResponseEntity<Collection<Mocion>> listarMociones() {
		return ResponseEntity.ok().body(mocionService.listar());
	}

	/*@PostMapping
	public ResponseEntity<Mocion> guardarMocion(@RequestBody Mocion mocion) {
		mocion.setFecha(new java.sql.Date(System.currentTimeMillis()));
		return ResponseEntity.ok(mocionService.guardar(mocion));
	}*/

	@DeleteMapping("/{id}")
	public ResponseEntity<Mocion> eliminarMocion(@PathVariable Long id) {
		Mocion mocion =mocionService.eliminar(id);
		if(mocion == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(mocion);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Mocion> buscarMocionPorId(@PathVariable Long id) {
		Mocion mocion = mocionService.BuscarPorId(id);
		if(mocion == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(mocion);
	}

	@PutMapping("/mociontipo/{idMocion}/{idTipo}")
	public ResponseEntity<Mocion> actualizarMocionTipo(@PathVariable Long idMocion, @PathVariable Long idTipo) {
		Mocion mocion = mocionService.agregarTipoMocion(idMocion, idTipo);
		if (mocion == null) {
			return ResponseEntity.badRequest().build();// ERROR 400
		}
		return ResponseEntity.ok(mocion);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Mocion> actualizarMocion(@PathVariable("id") Long id, @RequestBody Mocion mocion) {
		TipoMocion tipomocionActual = tipoMocionService.BuscarPorId(id);
		Mocion mocionActual = mocionService.BuscarPorId(mocion.getId());
		if (mocionActual == null && tipomocionActual == null) {
			return ResponseEntity.badRequest().build();// ERROR 400
		}
		return ResponseEntity.ok(mocionService.guardar(mocionActual,id));
	}

	@GetMapping("/Log")
	public ResponseEntity<Collection<Log>> listarLogs() {
		Collection<Log> logs = new ArrayList<>();
		LR.findAll().forEach(logs::add);
		return ResponseEntity.ok().body(logs);
	}

}
