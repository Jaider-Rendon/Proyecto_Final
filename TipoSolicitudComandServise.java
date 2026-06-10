public class TipoSolicitudComandServise {

    private final TipoSolicitudRepository tipoSolicitudRepository;

    public TipoSolicitudComandServise(TipoSolicitudRepository tipoSolicitudRepository) {
        this.tipoSolicitudRepository = tipoSolicitudRepository;
    }

    public void crearTipoSolicitud(TipoSolicitud tipoSolicitud) {
        tipoSolicitudRepository.crearTipoSolicitud(tipoSolicitud);
    }
}
