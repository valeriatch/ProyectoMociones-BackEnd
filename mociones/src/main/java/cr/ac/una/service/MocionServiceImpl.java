package cr.ac.una.service;

import cr.ac.una.entity.Mocion;
import cr.ac.una.entity.TipoMocion;
import cr.ac.una.repository.MocionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j //Para el log
@RequiredArgsConstructor //Para el constructor
@Service
public class MocionServiceImpl implements MocionService {

    private final MocionRepository mocionRepository;
    private final TipoMocionService tipoMocionService;

    @Override
    public Collection<Mocion> listar() {
        log.info("recuperando mociones de la base de datos");
        return (Collection<Mocion>) mocionRepository.findAll();
    }

    @Override
    public Mocion BuscarPorId(Long id) {
        log.info("recuperando mocion de la base de datos, id: {}", id);
        return mocionRepository.findById(id).orElse(new Mocion());
    }

    @Override
    public Mocion guardar(Mocion mocion, Long id) {
        log.info("guardando mocion en la base de datos, mocion: {}", mocion);
        TipoMocion tipoMocion = tipoMocionService.BuscarPorId(id);
        mocion.setTipoMocion(tipoMocion);
        return mocionRepository.save(mocion);
    }

    @Override
    public Mocion actualizarMocion(Mocion mocion, Long id) {
        log.info("actualizando la mocion en la base de datos, id: {},", id);
        //Mocion mocion1 = mocionRepository.findById(id).orElse(null);
        TipoMocion tipoMocion = tipoMocionService.BuscarPorId(id);
        if (mocion != null) {
            mocion.setTipoMocion(tipoMocion);
            mocionRepository.save(mocion);
            return mocion;
        }
        log.info("tipo de mocion actualizado de la mocion, mocion: {}", mocion);
        return null;
    }

    @Override
    public Mocion eliminar(Long id) {
        log.info("eliminando mocion de la base de datos, id: {}", id);
        Mocion mocion = mocionRepository.findById(id).orElse(null);
        if (mocion != null) {
            mocionRepository.delete(mocion);
        }
        log.info("mocion eliminada de la base de datos, mocion: {}", mocion);
        return mocion;
    }

    @Override
    public Mocion agregarTipoMocion(Long idMocion, Long idTipoMocion) {
        log.info("agregando tipo de mocion a la mocion, idMocion: {}, idTipoMocion: {}", idMocion, idTipoMocion);
        Mocion mocion = mocionRepository.findById(idMocion).orElse(null);
        TipoMocion tipo = tipoMocionService.BuscarPorId(idTipoMocion);
        if (mocion != null && tipo != null) {
            mocion.setTipoMocion(tipo);
            mocionRepository.save(mocion);
        }
        log.info("tipo de mocion agregado a la mocion, mocion: {}", mocion);
        return mocion;
    }

    @Override
    public Mocion actualizarTipo(Long idMocion, Long idTipo) {
        log.info("actualizando tipo de mocion de la mocion, idMocion: {}, idTipo: {}", idMocion, idTipo);
        Mocion mocion = mocionRepository.findById(idMocion).orElse(null);
        TipoMocion tipo = tipoMocionService.BuscarPorId(idTipo);
        if (mocion != null && tipo != null) {
            mocion.setTipoMocion(tipo);
            mocionRepository.save(mocion);
        }
        log.info("tipo de mocion actualizado de la mocion, mocion: {}", mocion);
        return mocion;
    }


}
