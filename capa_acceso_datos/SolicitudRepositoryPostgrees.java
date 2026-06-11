package capa_acceso_datos;

import capa_negocio.Solicitud;
import capa_negocio.NotificacionesComandServise;
import capa_negocio.TipoSolicitud;
import capa_negocio.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SolicitudRepositoryPostgrees implements SolicitudRepository {

    @Override
    public void crearSolicitud(Solicitud solicitud) {
        String sql = "INSERT INTO solicitud(idUsuario, idTipoSolicitud, descripcion, fechaCreacion, estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection connetConnection = ConexionSQL.obtenerConexion();
                PreparedStatement statement = connetConnection.prepareStatement(sql,
                        java.sql.Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, solicitud.getUsuario().getId());
            statement.setInt(2, solicitud.getTipoSolicitud().getIdTipoSolicitud());
            statement.setString(3, solicitud.getDescripcion());
            statement.setString(4, solicitud.getFechaCreacion());
            statement.setString(5, solicitud.getEstado());
            statement.executeUpdate();

            int idGenerado = 0;
            try (java.sql.ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idGenerado = generatedKeys.getInt(1);
                }
            }

            if (idGenerado > 0) {
                NotificionesRepository notificacionesRepository = new NotificacionRepositoryPostgrees();
                NotificacionesComandServise notificacionesComandServise = new NotificacionesComandServise(
                        notificacionesRepository);
                notificacionesComandServise.enviarNotificacion2(idGenerado, "S1", solicitud.getEstado());
            }

        } catch (Exception e) {
            System.out.println("Error al crear solicitud: " + e.getMessage());
        }
    }

    @Override
    public void cambiarEstado(int idSolicitud, String estado) {
        String sql = "UPDATE solicitud SET estado = ? WHERE idsolicitud = ?";

        try (Connection connetConnection = ConexionSQL.obtenerConexion();
                PreparedStatement statement = connetConnection.prepareStatement(sql)) {

            statement.setString(1, estado);
            statement.setInt(2, idSolicitud);
            statement.executeUpdate();
            NotificionesRepository notificacionesRepository = new NotificacionRepositoryPostgrees();
            NotificacionesComandServise notificacionesComandServise = new NotificacionesComandServise(
                    notificacionesRepository);
            notificacionesComandServise.enviarNotificacion2(idSolicitud, "S2", estado);

        } catch (Exception e) {
            System.out.println("Error al cambiar el estado de la solicitud: " + e.getMessage());
        }
    }

    @Override
    public void buscarPorEstado(String estado) {
        String sql = "SELECT * FROM solicitud WHERE estado = ?";

        try (Connection connetConnection = ConexionSQL.obtenerConexion();
                PreparedStatement statement = connetConnection.prepareStatement(sql)) {
            statement.setString(1, estado);
            java.sql.ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {
                System.out.println((new Solicitud.Builder()
                        .idSolicitud(resultado.getInt("idSolicitud"))
                        .usuario(new Usuario.Builder()
                                .id(resultado.getInt("idUsuario"))
                                .build()))
                        .tipoSolicitud(new TipoSolicitud.Builder()
                                .idTipoSolicitud(resultado.getInt("idTipoSolicitud"))
                                .build())
                        .descripcion(resultado.getString("descripcion"))
                        .fechaCreacion(resultado.getString("fechaCreacion"))
                        .estado(resultado.getString("estado"))
                        .build());
            }
        } catch (Exception e) {
            System.out.println("Error al buscar solicitud: " + e.getMessage());
        }
    }

    @Override
    public void reportePorTipo(int idTipoSolicitud) {
        String sql = "select s.idsolicitud,u.nombre,t.nombre,t.descripcion,s.fechacreacion,s.estado \n" + //
                "from solicitud s inner join usuario u on s.idusuario=u.id\n" + //
                "inner join tiposolicitud t on s.idtiposolicitud=t.idtiposolicitud\n" + //
                "where s.idtiposolicitud= ?";

        try (Connection connetConnection = ConexionSQL.obtenerConexion();
                PreparedStatement statement = connetConnection.prepareStatement(sql)) {
            statement.setInt(1, idTipoSolicitud);
            java.sql.ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {
                System.out.println("idSolicitud: " + resultado.getInt("idSolicitud") + "\n" +
                        "usuario: " + resultado.getString("nombre") + "\n" +
                        "tipoSolicitud: " + resultado.getString("nombre") + "\n" +
                        "descripcion: " + resultado.getString("descripcion") + "\n" +
                        "fechaCreacion: " + resultado.getString("fechaCreacion") + "\n" +
                        "estado: " + resultado.getString("estado") + "\n");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar solicitud: " + e.getMessage());
        }
    }

}
