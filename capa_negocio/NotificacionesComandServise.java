package capa_negocio;

import capa_acceso_datos.NotificionesRepository;

public class NotificacionesComandServise {

    private final NotificionesRepository notificionesRepository;

    public NotificacionesComandServise(NotificionesRepository notificionesRepository) {
        this.notificionesRepository = notificionesRepository;
    }

    public void enviarNotificacion(String tipo, int id) {
        notificionesRepository.enviarNotificacion(tipo, id);
    }

    public void enviarNotificacion2(int idSolicitud, String tipo, String estado) {
        notificionesRepository.enviarNotificacion2(idSolicitud, tipo, estado);
    }
}
