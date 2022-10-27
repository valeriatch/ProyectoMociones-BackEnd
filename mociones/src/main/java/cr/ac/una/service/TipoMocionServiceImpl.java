package cr.ac.una.service;

import cr.ac.una.entity.TipoMocion;
import cr.ac.una.repository.TipoMocionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Service
public class TipoMocionServiceImpl implements TipoMocionService{

    private final TipoMocionRepository tipoMocionRepository;

    @Override
    public Collection<TipoMocion> listar() {
        log.info("recuperando tipos de mociones de la base de datos");
        return (Collection<TipoMocion>) tipoMocionRepository.findAll();
    }

    @Override
    public TipoMocion BuscarPorId(Long id) {
        log.info("recuperando tipo de mocion de la base de datos, id: {}", id);
        TipoMocion tipo= tipoMocionRepository.findById(id).orElse(null);

		if(tipo == null){
			return null;
		}
        return tipo;
    }

    @Override
    public TipoMocion guardar(TipoMocion tipoMocion) {
        log.info("guardando tipo de mocion en la base de datos, tipoMocion: {}", tipoMocion);
        return tipoMocionRepository.save(tipoMocion);
    }

    @Override
    public TipoMocion eliminar(Long id) {
        log.info("eliminando tipo de mocion de la base de datos, id: {}", id);
        TipoMocion tipoMocion = tipoMocionRepository.findById(id).orElse(null);
        if (tipoMocion == null) {
            return null;
        }
        tipoMocionRepository.delete(tipoMocion);
        log.info("tipo de mocion eliminada de la base de datos, tipoMocion: {}", tipoMocion);
        return tipoMocion;
    }
    @Override
    public TipoMocion actualizar(Long id, TipoMocion tipoMocion) {
        log.info("actualizando tipo de mocion de la base de datos, id: {}", id);
        TipoMocion tipoMocion1 = tipoMocionRepository.findById(id).orElse(null);
        if (tipoMocion1 != null) {
            tipoMocionRepository.save(tipoMocion);
        }
        log.info("tipo de mocion actualizada de la base de datos, tipoMocion: {}", tipoMocion);
        return tipoMocion;
    }

}
