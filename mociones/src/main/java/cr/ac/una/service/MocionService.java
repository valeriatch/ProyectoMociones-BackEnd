package cr.ac.una.service;

import cr.ac.una.entity.Mocion;

import java.util.Collection;

public interface MocionService {

    public Collection<Mocion> listar();
    public Mocion BuscarPorId(Long id);
    public Mocion actualizarMocion(Mocion mocion, Long id);
    public Mocion guardar(Mocion mocion, Long id);
    public Mocion guardarMoc(Mocion mocion);
    public Mocion eliminar(Long id);

    public Mocion agregarTipoMocion(Long idMocion, Long idTipoMocion);



}
