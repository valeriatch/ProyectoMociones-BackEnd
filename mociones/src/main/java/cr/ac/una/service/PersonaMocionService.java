package cr.ac.una.service;

import cr.ac.una.entity.Mocion;
import cr.ac.una.entity.Persona;
import cr.ac.una.entity.PersonaMocion;

import java.util.Collection;

public interface PersonaMocionService {

    public PersonaMocion guardar(Long idPersona, Long idMocion);
    public PersonaMocion eliminar(Long idPersonaMocion);
    public Collection<PersonaMocion> listarMocionesPorPersona(Long idPersona);
    public Collection<PersonaMocion> listarPersonasPorMocion(Long idMocion);
}
