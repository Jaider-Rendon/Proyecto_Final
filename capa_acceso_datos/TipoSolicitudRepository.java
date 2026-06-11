package capa_acceso_datos;

import capa_negocio.TipoSolicitud;

public interface TipoSolicitudRepository {

    TipoSolicitud buscarPorIdTipoSolicitud(int id);

    void crearTipoSolicitud(TipoSolicitud tipoSolicitud);

}
