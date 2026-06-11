package capa_negocio;

import capa_acceso_datos.TipoSolicitudRepository;

public class TipoSolicitudQueryServise {

    private final TipoSolicitudRepository tipoSolicitudRepository;

    public TipoSolicitudQueryServise(TipoSolicitudRepository tipoSolicitudRepository) {
        this.tipoSolicitudRepository = tipoSolicitudRepository;
    }

    public TipoSolicitud buscarPorIdTipoSolicitud(int id) {
        return tipoSolicitudRepository.buscarPorIdTipoSolicitud(id);
    }
}
