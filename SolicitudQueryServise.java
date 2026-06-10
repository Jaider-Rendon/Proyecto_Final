public class SolicitudQueryServise {

    private final SolicitudRepository solicitudRepository;

    public SolicitudQueryServise(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    public void buscarPorEstado(String estado) {
        solicitudRepository.buscarPorEstado(estado);
    }

    public void reportePorTipo(int idTipoSolicitud) {
        solicitudRepository.reportePorTipo(idTipoSolicitud);
    }
}
