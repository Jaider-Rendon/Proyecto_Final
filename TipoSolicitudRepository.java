public interface TipoSolicitudRepository {

    TipoSolicitud buscarPorIdTipoSolicitud(int id);

    void crearTipoSolicitud(TipoSolicitud tipoSolicitud);

}
