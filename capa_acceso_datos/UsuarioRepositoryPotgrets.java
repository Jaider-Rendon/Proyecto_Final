package capa_acceso_datos;

import capa_negocio.Usuario;
import capa_negocio.NotificacionesComandServise;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UsuarioRepositoryPotgrets implements UsuarioRepositorio {

    @Override
    public void registrarUsuario(Usuario usuario) {

        String sql = "INSERT INTO usuario(id,nombre, correo, rol) VALUES (?, ?, ?, ?)";

        try (Connection connetConnection = ConexionSQL.obtenerConexion();
                PreparedStatement statement = connetConnection.prepareStatement(sql)) {
            if (buscarPorIdUsuario(usuario.getId()) != null) {
                throw new IllegalArgumentException("El usuario ya existe");
            }
            statement.setInt(1, usuario.getId());
            statement.setString(2, usuario.getNombre());
            statement.setString(3, usuario.getCorreo());
            statement.setString(4, usuario.getRol());
            statement.executeUpdate();
            NotificionesRepository notificionesRepository = new NotificacionRepositoryPostgrees();
            NotificacionesComandServise notificacionesComandServise = new NotificacionesComandServise(
                    notificionesRepository);
            notificacionesComandServise.enviarNotificacion("USUARIO");

        } catch (Exception e) {
            System.out.println("Error al guardar usuario: " + e.getMessage());
        }

    }

    @Override
    public Usuario buscarPorIdUsuario(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";

        try (Connection connetConnection = ConexionSQL.obtenerConexion();
                PreparedStatement statement = connetConnection.prepareStatement(sql)) {
            statement.setInt(1, id);
            java.sql.ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                return new Usuario.Builder()
                        .id(resultado.getInt("id"))
                        .nombre(resultado.getString("nombre"))
                        .correo(resultado.getString("correo"))
                        .rol(resultado.getString("rol"))
                        .build();
            }
        } catch (Exception e) {
            System.out.println("Error al buscar usuario: " + e.getMessage());
        }
        return null;
    }

}
