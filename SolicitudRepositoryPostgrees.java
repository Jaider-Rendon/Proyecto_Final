import java.sql.Connection;
import java.sql.PreparedStatement;

public class SolicitudRepositoryPostgrees implements SolicitudRepository {

    @Override
    public void crearSolicitud(Solicitud solicitud) {
        String sql = "INSERT INTO solicitud(idUsuario, idTipoSolicitud, descripcion, fechaCreacion, estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection connetConnection = ConexionSQL.obtenerConexion();
                PreparedStatement statement = connetConnection.prepareStatement(sql)) {

            statement.setInt(1, solicitud.getUsuario().getId());
            statement.setInt(2, solicitud.getTipoSolicitud().getIdTipoSolicitud());
            statement.setString(3, solicitud.getDescripcion());
            statement.setString(4, solicitud.getFechaCreacion());
            statement.setString(5, solicitud.getEstado());
            statement.executeUpdate();
            System.out.println("Solicitud creada exitosamente");

        } catch (Exception e) {
            System.out.println("Error al crear solicitud: " + e.getMessage());
        }
    }
}
