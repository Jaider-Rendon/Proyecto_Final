package capa_acceso_datos;

import capa_negocio.Solicitud;
import capa_negocio.NotificacionesComandServise;
import capa_negocio.TipoSolicitud;
import capa_negocio.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class SolicitudRepositoryPostgrees implements SolicitudRepository {

    @Override
    public void crearSolicitud(Solicitud solicitud) {
        String sql = "INSERT INTO solicitud(idUsuario, idTipoSolicitud, descripcion, fechaCreacion, estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection connetConnection = ConexionSQL.obtenerConexion();
                PreparedStatement statement = connetConnection.prepareStatement(sql,
                        java.sql.Statement.RETURN_GENERATED_KEYS)) {

            if (solicitud.getUsuario() == null && solicitud.getTipoSolicitud() == null) {
                throw new IllegalArgumentException("El usuario y el tipo de solicitud no existen");
            }

            if (solicitud.getUsuario() == null) {
                throw new IllegalArgumentException("El usuario no existe");
            }
            if (solicitud.getTipoSolicitud() == null) {
                throw new IllegalArgumentException("El tipo de solicitud no existe");
            }

            statement.setInt(1, solicitud.getUsuario().getId());
            statement.setInt(2, solicitud.getTipoSolicitud().getIdTipoSolicitud());
            statement.setString(3, solicitud.getDescripcion());
            statement.setString(4, solicitud.getFechaCreacion());
            statement.setString(5, "CREADA");
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
    public void cambiarEstado(int idSolicitud, String nuevoEstado) {
        String sql = "UPDATE solicitud SET estado = ? WHERE idsolicitud = ?";

        try (Connection connetConnection = ConexionSQL.obtenerConexion();
                PreparedStatement statement = connetConnection.prepareStatement(sql)) {

            Solicitud solicitud = buscarPorIdSolicitud(idSolicitud);

            if (solicitud == null) {
                throw new IllegalArgumentException("La solicitud " + idSolicitud + " no existe.");
            }

            String estadoActual = solicitud.getEstado();

            List<String> estadosInmutables = List.of("APROBADA", "RECHAZADA", "CERRADA");
            if (estadosInmutables.contains(estadoActual)) {
                throw new IllegalArgumentException(
                        "La solicitud " + idSolicitud + " es inmutable porque ya está en estado: " + estadoActual);
            }

            boolean transicionValida = (estadoActual.equals("CREADA") && nuevoEstado.equals("EN_REVISION")) ||
                    (estadoActual.equals("EN_REVISION") && nuevoEstado.equals("APROBADA")) ||
                    (estadoActual.equals("EN_REVISION") && nuevoEstado.equals("RECHAZADA"));

            if (!transicionValida) {
                throw new IllegalArgumentException(
                        "Transición inválida: No se permite pasar de " + estadoActual + " a " + nuevoEstado);
            }
            statement.setString(1, nuevoEstado);
            statement.setInt(2, idSolicitud);
            statement.executeUpdate();

            NotificionesRepository notificacionesRepository = new NotificacionRepositoryPostgrees();
            NotificacionesComandServise notificacionesComandServise = new NotificacionesComandServise(
                    notificacionesRepository);

            notificacionesComandServise.enviarNotificacion2(idSolicitud, "S2", nuevoEstado);

        } catch (Exception e) {
            System.out.println("Error al cambiar el estado de la solicitud en BD: " + e.getMessage());

        }
    }

    @Override
    public void buscarPorEstado(String estado) {
        String sql = "SELECT * FROM solicitud WHERE estado = ?";

        try (Connection connetConnection = ConexionSQL.obtenerConexion();
                PreparedStatement statement = connetConnection.prepareStatement(sql)) {
            statement.setString(1, estado);
            java.sql.ResultSet resultado = statement.executeQuery();

            boolean seEncontroAlguna = false;

            while (resultado.next()) {
                seEncontroAlguna = true;
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

            if (!seEncontroAlguna) {
                throw new IllegalArgumentException("No se encontraron solicitudes con el estado: " + estado);
            }

        } catch (Exception e) {
            System.out.println("Error al buscar solicitud: " + e.getMessage());
        }
    }

    @Override
    public void reportePorTipo(int idTipoSolicitud) {
        String sql = "select s.idsolicitud,u.nombre,t.nombretipo,t.descripcion,s.fechacreacion,s.estado \n" + //
                "from solicitud s inner join usuario u on s.idusuario=u.id\n" + //
                "inner join tiposolicitud t on s.idtiposolicitud=t.idtiposolicitud\n" + //
                "where s.idtiposolicitud= ?";

        try (Connection connetConnection = ConexionSQL.obtenerConexion();
                PreparedStatement statement = connetConnection.prepareStatement(sql)) {
            statement.setInt(1, idTipoSolicitud);
            java.sql.ResultSet resultado = statement.executeQuery();
            boolean seEncontroAlguna = false;
            while (resultado.next()) {
                seEncontroAlguna = true;
                System.out.println("idSolicitud: " + resultado.getInt("idSolicitud") + "\n" +
                        "usuario: " + resultado.getString("nombre") + "\n" +
                        "tipoSolicitud: " + resultado.getString("nombretipo") + "\n" +
                        "descripcion: " + resultado.getString("descripcion") + "\n" +
                        "fechaCreacion: " + resultado.getString("fechaCreacion") + "\n" +
                        "estado: " + resultado.getString("estado") + "\n");
            }

            if (!seEncontroAlguna) {
                throw new IllegalArgumentException("No se encontraron solicitudes con el tipo: " + idTipoSolicitud);
            }

        } catch (Exception e) {
            System.out.println("Error al buscar solicitud: " + e.getMessage());
        }
    }

    @Override
    public Solicitud buscarPorIdSolicitud(int id) {
        String sql = "SELECT * FROM solicitud WHERE idsolicitud = ?";

        try (Connection connetConnection = ConexionSQL.obtenerConexion();
                PreparedStatement statement = connetConnection.prepareStatement(sql)) {
            statement.setInt(1, id);
            java.sql.ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                return new Solicitud.Builder()
                        .idSolicitud(resultado.getInt("idSolicitud"))
                        .usuario(new Usuario.Builder()
                                .id(resultado.getInt("idUsuario"))
                                .build())
                        .tipoSolicitud(new TipoSolicitud.Builder()
                                .idTipoSolicitud(resultado.getInt("idTipoSolicitud"))
                                .build())
                        .descripcion(resultado.getString("descripcion"))
                        .fechaCreacion(resultado.getString("fechaCreacion"))
                        .estado(resultado.getString("estado"))
                        .build();
            }
        } catch (Exception e) {
            System.out.println("Error al buscar solicitud: " + e.getMessage());
        }
        return null;
    }

}
