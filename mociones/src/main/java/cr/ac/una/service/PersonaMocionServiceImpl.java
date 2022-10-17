package cr.ac.una.service;

import cr.ac.una.entity.Mocion;
import cr.ac.una.entity.Persona;
import cr.ac.una.entity.PersonaMocion;
import cr.ac.una.repository.PersonaMocionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Service
public class PersonaMocionServiceImpl implements PersonaMocionService{


    private final PersonaMocionRepository personaMocionRepository;

    private final PersonaService personaService;

    private final MocionService mocionService;
    @Override
    public PersonaMocion guardar(Long idPersona, Long idMocion) {
        log.info("guardando personaMocion en la base de datos, idPersona: {}, idMocion: {}", idPersona, idMocion);
        Persona persona = personaService.BuscarPorId(idPersona);
        Mocion mocion = mocionService.BuscarPorId(idMocion);
        return personaMocionRepository.save(new PersonaMocion(null,persona, mocion));

    }

    @Override
    public PersonaMocion eliminar(Long idPersonaMocion) {
        log.info("eliminando personaMocion de la base de datos, idPersonaMocion: {}", idPersonaMocion);
        PersonaMocion personaMocion = personaMocionRepository.findById(idPersonaMocion).orElse(null);
        if (personaMocion != null) {
            personaMocionRepository.delete(personaMocion);
        }
        log.info("personaMocion eliminada de la base de datos, personaMocion: {}", personaMocion);
        return personaMocion;
    }

    @Override
    public Collection<PersonaMocion> listarMocionesPorPersona(Long idPersona) {
        log.info("recuperando mociones de la base de datos por medio de persona, idPersona: {}", idPersona);
        return personaMocionRepository.FindAllByPersonaId(idPersona);
    }

    @Override
    public Collection<PersonaMocion> listarPersonasPorMocion(Long idMocion) {
        log.info("recuperando personas de la base de datos por medio de mocion, idMocion: {}", idMocion);
        return personaMocionRepository.FindAllByMocionId(idMocion);
    }
}
