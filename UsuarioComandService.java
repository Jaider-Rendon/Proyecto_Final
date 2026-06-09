public class UsuarioComandService {

    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioComandService(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public void Registrar(String nombre, String correo, String rol) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacio");
        }
        Usuario usuario = new Usuario.Builder().nombre(nombre).correo(correo).rol(rol).build();
        usuarioRepositorio.registrarUsuario(usuario);
    }
}
