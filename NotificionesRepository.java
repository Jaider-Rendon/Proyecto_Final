public interface NotificionesRepository {

    void enviarNotificacion(String tipo);

    void enviarNotificacion2(int idSolicitud, String tipo, String estado);
}
