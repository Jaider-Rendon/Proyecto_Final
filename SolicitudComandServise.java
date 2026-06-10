public class SolicitudComandServise {

    private final SolicitudRepository solicitudRepository;

    public SolicitudComandServise(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    public void crearSolicitud(Solicitud solicitud) {
        solicitudRepository.crearSolicitud(solicitud);
    }

    public void cambiarEstado(int idSolicitud, String estado) {
        solicitudRepository.cambiarEstado(idSolicitud, estado);
    }
}
