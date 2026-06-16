
package capa_acceso_datos;

public interface NotificionesRepository {

    void enviarNotificacion(String tipo, int id);

    void enviarNotificacion2(int idSolicitud, String tipo, String estado);
}
