package cr.ac.una.service;

import cr.ac.una.entity.Mocion;
import cr.ac.una.entity.TipoMocion;
import cr.ac.una.repository.MocionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j // Para el log
@RequiredArgsConstructor // Para el constructor
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
		Mocion mocion = mocionRepository.findById(id).orElse(null);
		if (mocion == null) {
			return null;
		}
		return mocion;
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

	@Override
	public Mocion agregarTipoMocion(Long idMocion, Long idTipoMocion) {
		log.info("agregando tipo de mocion a la mocion, idMocion: {}, idTipoMocion: {}", idMocion, idTipoMocion);
		Mocion mocion = BuscarPorId(idMocion);
		TipoMocion tipo = tipoMocionService.BuscarPorId(idTipoMocion);
		if (mocion == null || tipo == null) {
			return null;
		}
		mocion.setTipoMocion(tipo);
		mocionRepository.save(mocion);
		log.info("tipo de mocion agregado a la mocion, mocion: {}", mocion);
		return mocion;
	}

}
