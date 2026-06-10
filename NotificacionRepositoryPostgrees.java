import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

public class NotificacionRepositoryPostgrees implements NotificionesRepository {

    @Override
    public void enviarNotificacion(String tipo) {
        if (tipo.equals("USUARIO") || tipo.equals("TIPOSOLICITUD")) {
            System.out.println("Se registro correctamente tu " + tipo);
        }
    }

    @Override
    public void enviarNotificacion2(int idSolicitud, String tipo, String estado) {
        String sql = "INSERT INTO notificaciones(idsolicitud,mensaje,fecha,estadoSolicitud) VALUES (?, ?, ?, ?)";
        try (Connection connetConnection = ConexionSQL.obtenerConexion();
                PreparedStatement statement = connetConnection.prepareStatement(sql)) {
            if (tipo.equals("S1")) {
                statement.setInt(1, idSolicitud);
                statement.setString(2, "Solicitud creada exitosamente");
                statement.setString(3, LocalDateTime.now().toString());
                statement.setString(4, estado);
                statement.executeUpdate();
                System.out.println("Solicitud creada exitosamente");

            } else if (tipo.equals("S2")) {
                statement.setInt(1, idSolicitud);
                statement.setString(2, "Estado de la solicitud actualizado exitosamente");
                statement.setString(3, LocalDateTime.now().toString());
                statement.setString(4, estado);
                statement.executeUpdate();
                System.out.println("Estado de la solicitud actualizado exitosamente");
            }
        } catch (Exception e) {
            System.out.println("Error al enviar notificacion: " + e.getMessage());
        }
    }
}
