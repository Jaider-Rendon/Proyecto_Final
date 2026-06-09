public class TipoSolicitudComandServise {

    private final TipoSolicitudRepository tipoSolicitudRepository;

    public TipoSolicitudComandServise(TipoSolicitudRepository tipoSolicitudRepository) {
        this.tipoSolicitudRepository = tipoSolicitudRepository;
    }

    public TipoSolicitud buscarPorIdTipoSolicitud(int id) {
        return tipoSolicitudRepository.buscarPorIdTipoSolicitud(id);
    }

    public void crearTipoSolicitud(TipoSolicitud tipoSolicitud) {
        tipoSolicitudRepository.crearTipoSolicitud(tipoSolicitud);
    }
}
