package cr.ac.una.service;

import cr.ac.una.entity.Mocion;

import java.util.Collection;

public interface MocionService {

    public Collection<Mocion> listar();
    public Mocion BuscarPorId(Long id);
    public Mocion guardar(Mocion mocion);
    public Mocion eliminar(Long id);

}
