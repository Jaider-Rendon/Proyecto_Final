import java.sql.Connection;
import java.sql.PreparedStatement;

public class SolicitudRepositoryPostgrees implements SolicitudRepository {

    @Override
    public void crearSolicitud(Solicitud solicitud) {
        String sql = "INSERT INTO solicitud(idUsuario, idTipoSolicitud, descripcion, fechaCreacion, estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection connetConnection = ConexionSQL.obtenerConexion();
                PreparedStatement statement = connetConnection.prepareStatement(sql)) {

            statement.setInt(2, solicitud.getUsuario().getId());
            statement.setInt(3, solicitud.getTipoSolicitud().getIdTipoSolicitud());
            statement.setString(4, solicitud.getDescripcion());
            statement.setString(5, solicitud.getFechaCreacion());
            statement.setString(6, solicitud.getEstado());
            statement.executeUpdate();
            System.out.println("Solicitud creada exitosamente");

        } catch (Exception e) {
            System.out.println("Error al crear solicitud: " + e.getMessage());
        }
    }
}
