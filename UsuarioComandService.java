public class UsuarioComandService {

    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioComandService(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public void Registrar(Usuario usuario) {
        usuarioRepositorio.registrarUsuario(usuario);
    }

}
