package cr.ac.una.repository;


import cr.ac.una.entity.Persona;
import org.springframework.data.repository.CrudRepository;

public interface PersonaRepository extends CrudRepository<Persona, Long> {


}
