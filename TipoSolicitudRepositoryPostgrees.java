import java.sql.Connection;
import java.sql.PreparedStatement;

public class TipoSolicitudRepositoryPostgrees implements TipoSolicitudRepository {

    @Override
    public TipoSolicitud buscarPorIdTipoSolicitud(int id) {
        String sql = "SELECT * FROM tipoSolicitud WHERE idTipoSolicitud = ?";

        try (Connection connetConnection = ConexionSQL.obtenerConexion();
                PreparedStatement statement = connetConnection.prepareStatement(sql)) {
            statement.setInt(1, id);
            java.sql.ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                return new TipoSolicitud.Builder()
                        .idTipoSolicitud(resultado.getInt("idTipoSolicitud"))
                        .nombre(resultado.getString("nombre"))
                        .descripcion(resultado.getString("descripcion"))
                        .tiempoEstimadoDias(resultado.getInt("tiempoEstimadoDias"))
                        .build();
            }
        } catch (Exception e) {
            System.out.println("Error al buscar tipo de solicitud: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void crearTipoSolicitud(TipoSolicitud tipoSolicitud) {
        String sql = "INSERT INTO tipoSolicitud(nombre, descripcion, tiempoEstimadoDias) VALUES (?, ?, ?)";

        try (Connection connetConnection = ConexionSQL.obtenerConexion();
                PreparedStatement statement = connetConnection.prepareStatement(sql)) {

            statement.setString(1, tipoSolicitud.getNombre());
            statement.setString(2, tipoSolicitud.getDescripcion());
            statement.setInt(3, tipoSolicitud.getTiempoEstimadoDias());
            statement.executeUpdate();
            System.out.println("Tipo de solicitud creado exitosamente");

        } catch (Exception e) {
            System.out.println("Error al crear tipo de solicitud: " + e.getMessage());
        }
    }

}
