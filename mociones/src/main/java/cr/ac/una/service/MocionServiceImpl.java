package cr.ac.una.service;

import cr.ac.una.entity.Mocion;
import cr.ac.una.repository.MocionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Service
public class MocionServiceImpl implements MocionService {

    private final MocionRepository mocionRepository;

    @Override
    public Collection<Mocion> listar() {
        log.info("recuperando mociones de la base de datos");
        return mocionRepository.findAll();
    }

    @Override
    public Mocion BuscarPorId(Long id) {
        log.info("recuperando mocion de la base de datos, id: {}", id);
        return mocionRepository.findById(id).orElse(new Mocion());
    }

    @Override
    public Mocion guardar(Mocion mocion) {
        log.info("guardando mocion en la base de datos, mocion: {}", mocion);
        return mocionRepository.save(mocion);
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




}
