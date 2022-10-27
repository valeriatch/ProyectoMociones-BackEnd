package cr.ac.una.service;

import cr.ac.una.entity.TipoMocion;

import java.util.Collection;

public interface TipoMocionService {

    public Collection<TipoMocion> listar();
    public TipoMocion BuscarPorId(Long id);
    public TipoMocion guardar(TipoMocion tipoMocion);
    public TipoMocion eliminar(Long id);
    public TipoMocion actualizar(Long id, TipoMocion tipoMocion);


}
