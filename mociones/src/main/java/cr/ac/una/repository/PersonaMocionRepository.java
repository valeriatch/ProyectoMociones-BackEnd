package cr.ac.una.repository;

import cr.ac.una.entity.PersonaMocion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PersonaMocionRepository extends JpaRepository<PersonaMocion, Long> {
    public Collection<PersonaMocion> FindAllByPersonaId(Long idPersona);
    public Collection<PersonaMocion> FindAllByMocionId(Long idMocion);

}
