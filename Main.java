public class Main {
    public static void main(String[] args) {
        UsuarioRepositorio usuarioRepositorio = new UsuarioRepositoryPotgrets();
        UsuarioComandService usuarioComandService = new UsuarioComandService(usuarioRepositorio);
        usuarioComandService.Registrar("Juan", "juan@gmial.com", "ADMIN");
        
    }
}
