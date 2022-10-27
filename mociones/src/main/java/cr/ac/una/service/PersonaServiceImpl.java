package cr.ac.una.service;

import cr.ac.una.entity.Persona;
import cr.ac.una.repository.PersonaRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
@RequiredArgsConstructor
@Slf4j
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    @Override
    public Collection<Persona> listar() {
        log.info("recuperando personas de la base de datos");
        return (Collection<Persona>) personaRepository.findAll();
    }

    @Override
    public Persona BuscarPorId(Long id) {
        log.info("recuperando persona de la base de datos, id: {}", id);
        Persona persona= personaRepository.findById(id).orElse(null);
        if(persona == null){
            return null;
        }
        return persona;
    }

    @Override
    public Persona guardar(Persona persona) {
        log.info("guardando persona en la base de datos, persona: {}", persona);
        return personaRepository.save(persona);
    }

    @Override
    public Persona eliminar(Long id) {
        log.info("eliminando persona de la base de datos, id: {}", id);
        Persona persona = personaRepository.findById(id).orElse(null);
        if (persona == null) {
            return null;
        }
        personaRepository.delete(persona);
        log.info("persona eliminada de la base de datos, persona: {}", persona);
        return persona;
    }

}

