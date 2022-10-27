package cr.ac.una.resource;

import cr.ac.una.entity.TipoMocion;
import cr.ac.una.service.TipoMocionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/tipo_mocion")
public class TipoMocionResource {

    private final TipoMocionService tipoMocionService;

    @GetMapping
    public ResponseEntity<Collection<TipoMocion>> listarTipoMocion() {
        return ResponseEntity.ok().body(tipoMocionService.listar());
    }

    @PostMapping
    public ResponseEntity<TipoMocion> guardarTipoMocion(@RequestBody TipoMocion tipoMocion) {
        return ResponseEntity.ok(tipoMocionService.guardar(tipoMocion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TipoMocion> eliminarTipoMocion(@PathVariable Long id) {
        TipoMocion tipoMocion = tipoMocionService.eliminar(id);
        if (tipoMocion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tipoMocion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoMocion> buscarTipoMocionPorId(@PathVariable Long id) {
        TipoMocion tipoMocion = tipoMocionService.BuscarPorId(id);
        if (tipoMocion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tipoMocion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoMocion> actualizarTipoMocion(@PathVariable Long id, @RequestBody TipoMocion tipoMocion) {
        TipoMocion tipoMocionActual = tipoMocionService.BuscarPorId(id);
        if (tipoMocionActual == null) {
            return ResponseEntity.notFound().build();
        }
        tipoMocionActual.setDescripcion(tipoMocion.getDescripcion());
        return ResponseEntity.ok(tipoMocionService.guardar(tipoMocionActual));
    }
}
