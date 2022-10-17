package cr.ac.una.service;

import cr.ac.una.entity.Persona;

import java.util.Collection;

public interface PersonaService {

    public Collection<Persona> listar();
    public Persona BuscarPorId(Long id);
    public Persona guardar(Persona persona);
    public Persona eliminar(Long id);
}
