import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);

                UsuarioRepositorio usuarioRepositorio = new UsuarioRepositoryPotgrets();
                UsuarioComandService usuarioComandService = new UsuarioComandService(usuarioRepositorio);
                UsuarioQueryServise usuarioQueryServise = new UsuarioQueryServise(usuarioRepositorio);

                TipoSolicitudRepository tipoSolicitudRepository = new TipoSolicitudRepositoryPostgrees();
                TipoSolicitudComandServise tipoSolicitudComandServise = new TipoSolicitudComandServise(
                                tipoSolicitudRepository);
                TipoSolicitudQueryServise tipoSolicitudQueryServise = new TipoSolicitudQueryServise(
                                tipoSolicitudRepository);

                SolicitudRepository solicitudRepository = new SolicitudRepositoryPostgrees();
                SolicitudComandServise solicitudComandServise = new SolicitudComandServise(solicitudRepository);

                System.out.println("=== Registrar Usuario ===");
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Correo: ");
                String correo = scanner.nextLine();

                System.out.print("Rol (SOLICITANTE / FUNCIONARIO): ");
                String rol = scanner.nextLine();

                Usuario usuario = new Usuario.Builder()
                                .nombre(nombre)
                                .correo(correo)
                                .rol(rol)
                                .build();

                usuarioComandService.Registrar(usuario);

                System.out.println("\n=== Crear Tipo de Solicitud ===");
                System.out.print("Nombre del tipo: ");
                String tipoNombre = scanner.nextLine();

                System.out.print("Descripción: ");
                String tipoDescripcion = scanner.nextLine();

                System.out.print("Tiempo estimado (días): ");
                int tiempoEstimado = Integer.parseInt(scanner.nextLine());

                TipoSolicitud tipoSolicitud2 = new TipoSolicitud.Builder()
                                .nombre(tipoNombre)
                                .descripcion(tipoDescripcion)
                                .tiempoEstimadoDias(tiempoEstimado)
                                .build();

                tipoSolicitudComandServise.crearTipoSolicitud(tipoSolicitud2);

                System.out.println("\n=== Crear Solicitud ===");
                System.out.print("ID del usuario: ");
                int idUsuario = Integer.parseInt(scanner.nextLine());

                System.out.print("ID del tipo de solicitud: ");
                int idTipo = Integer.parseInt(scanner.nextLine());

                System.out.print("Descripción de la solicitud: ");
                String solicitudDescripcion = scanner.nextLine();

                System.out.print("Fecha de creación (DD-MM-YYYY): ");
                String fecha = scanner.nextLine();

                System.out.print("Estado (CREADA / EN_REVISION / APROBADA / RECHAZADA / RESUELTA): ");
                String estado = scanner.nextLine();

                Usuario usuarioBuscado = usuarioQueryServise.buscarPorIdUsuario(idUsuario);
                TipoSolicitud tipoSolicitudBuscado = tipoSolicitudQueryServise.buscarPorIdTipoSolicitud(idTipo);

                Solicitud solicitud = new Solicitud.Builder()
                                .usuario(usuarioBuscado)
                                .tipoSolicitud(tipoSolicitudBuscado)
                                .descripcion(solicitudDescripcion)
                                .fechaCreacion(fecha)
                                .estado(estado)
                                .build();

                solicitudComandServise.crearSolicitud(solicitud);

                scanner.close();
                System.out.println("\n Solicitud creada exitosamente.");
        }
}
