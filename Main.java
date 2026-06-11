public class Main {
        public static void main(String[] args) {

                Usuario usuario = new Usuario.Builder()
                                .nombre("jaider")
                                .correo("jaider12@gamil.com")
                                .rol("usuario").build();

                UsuarioRepositorio usuarioRepositorio = new UsuarioRepositoryPotgrets();
                UsuarioComandService usuarioComandService = new UsuarioComandService(usuarioRepositorio);
                usuarioComandService.Registrar(usuario);

                TipoSolicitudRepository tipoSolicitudRepository = new TipoSolicitudRepositoryPostgrees();

                TipoSolicitudComandServise tipoSolicitudComandServise = new TipoSolicitudComandServise(
                                tipoSolicitudRepository);

                TipoSolicitud tipoSolicitud2 = new TipoSolicitud.Builder()
                                .nombre("Solicitud de cambio de correo")
                                .descripcion("Solicitud de cambio de correo")
                                .tiempoEstimadoDias(1)
                                .build();

                tipoSolicitudComandServise.crearTipoSolicitud(tipoSolicitud2);

                SolicitudRepository solicitudRepository = new SolicitudRepositoryPostgrees();
                SolicitudComandServise solicitudComandServise = new SolicitudComandServise(solicitudRepository);
                UsuarioQueryServise usuarioQueryServise = new UsuarioQueryServise(usuarioRepositorio);
                TipoSolicitudQueryServise tipoSolicitudQueryServise = new TipoSolicitudQueryServise(
                                tipoSolicitudRepository);

                Usuario usuario1 = usuarioQueryServise.buscarPorIdUsuario(1);
                TipoSolicitud tipoSolicitud = tipoSolicitudQueryServise.buscarPorIdTipoSolicitud(1);
                Solicitud solicitud = new Solicitud.Builder()
                                .usuario(usuario1)
                                .tipoSolicitud(tipoSolicitud)
                                .descripcion("Solicitud de cambio de contraseña")
                                .fechaCreacion("2022-01-01")
                                .estado("Pendiente")
                                .build();

                solicitudComandServise.crearSolicitud(solicitud);
        }
}
