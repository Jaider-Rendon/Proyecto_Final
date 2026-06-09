import java.sql.Connection;
import java.sql.PreparedStatement;

public class UsuarioRepositoryPotgrets implements UsuarioRepositorio {

    @Override
    public void registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario(nombre, correo, rol) VALUES (?, ?, ?)";

        try (Connection connetConnection = ConexionSQL.obtenerConexion();
                PreparedStatement statement = connetConnection.prepareStatement(sql)) {

            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getCorreo());
            statement.setString(3, usuario.getRol());
            statement.executeUpdate();
            System.out.println("Usuario registrado exitosamente");

        } catch (Exception e) {
            System.out.println("Error al guardar usuario: " + e.getMessage());
        }
    }

}
