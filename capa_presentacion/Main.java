package capa_presentacion;

import capa_negocio.Usuario;
import capa_negocio.TipoSolicitud;
import capa_negocio.Solicitud;
import capa_negocio.UsuarioComandService;
import capa_negocio.UsuarioQueryServise;
import capa_negocio.SolicitudComandServise;
import capa_negocio.SolicitudQueryServise;
import capa_negocio.TipoSolicitudComandServise;
import capa_negocio.TipoSolicitudQueryServise;

import java.util.Scanner;

import capa_acceso_datos.UsuarioRepositorio;
import capa_acceso_datos.UsuarioRepositoryPotgrets;
import capa_acceso_datos.TipoSolicitudRepository;
import capa_acceso_datos.TipoSolicitudRepositoryPostgrees;
import capa_acceso_datos.SolicitudRepository;
import capa_acceso_datos.SolicitudRepositoryPostgrees;

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
                SolicitudQueryServise solicitudQueryServise = new SolicitudQueryServise(solicitudRepository);
                boolean usuarioLogueado = false;

                int opcion;
                System.out.println("\n=== Bienvenido al sistema de gestión de solicitudes ===");
                System.out.print("Digite su id:  ");
                int idValidacion = leerEntero(scanner);

                Usuario usuarioValidacion = usuarioQueryServise.buscarPorIdUsuario(idValidacion);
                while (usuarioValidacion == null) {
                        System.out.println("Usuario no encontrado");
                        registrar(scanner, usuarioComandService, usuarioLogueado);
                        System.out.print("Digite su id:  ");
                        idValidacion = leerEntero(scanner);
                        scanner.nextLine();
                        usuarioValidacion = usuarioQueryServise.buscarPorIdUsuario(idValidacion);

                }
                if (usuarioValidacion.getRol().equals("SOLICITANTE")) {
                        do {
                                System.out.println("\n========== MENÚ PRINCIPAL ==========");
                                System.out.println("1. Registrar usuario");
                                System.out.println("2. Crear Solicitud");
                                System.out.println("0. Salir");
                                System.out.print("Seleccione una opción: ");
                                opcion = leerEntero(scanner);
                                switch (opcion) {

                                        case 1:
                                                usuarioLogueado = true;
                                                registrar(scanner, usuarioComandService, usuarioLogueado);
                                                break;

                                        case 2:
                                                crearSolicitud(scanner, solicitudComandServise, usuarioQueryServise,
                                                                tipoSolicitudQueryServise);
                                                break;
                                }
                        } while (opcion != 0);

                } else if (usuarioValidacion.getRol().equals("FUNCIONARIO")) {
                        do {
                                System.out.println("\n========== MENÚ PRINCIPAL ==========");
                                System.out.println("1. Registrar Usuario");
                                System.out.println("2. Crear Tipo de Solicitud");
                                System.out.println("3. Crear Solicitud");
                                System.out.println("4. Cambiar Estado de Solicitud");
                                System.out.println("5. Buscar Solicitud por Estado");
                                System.out.println("6. Reporte por Tipo de Solicitud");
                                System.out.println("0. Salir");
                                System.out.print("Seleccione una opción: ");
                                opcion = leerEntero(scanner);

                                switch (opcion) {
                                        case 1:
                                                registrar(scanner, usuarioComandService, usuarioLogueado);
                                                break;

                                        case 2:
                                                System.out.println("\n=== Crear Tipo de Solicitud ===");
                                                System.out.print("Nombre del tipo: ");
                                                String tipoNombre = leerTexto(scanner);

                                                System.out.print("Descripción: ");
                                                String tipoDescripcion = leerTexto(scanner);

                                                System.out.print("Tiempo estimado (días): ");
                                                int tiempoEstimado = leerEntero(scanner);

                                                TipoSolicitud tipoSolicitud = new TipoSolicitud.Builder()
                                                                .nombre(tipoNombre.toUpperCase())
                                                                .descripcion(tipoDescripcion)
                                                                .tiempoEstimadoDias(tiempoEstimado)
                                                                .build();
                                                tipoSolicitudComandServise.crearTipoSolicitud(tipoSolicitud);
                                                break;

                                        case 3:
                                                crearSolicitud(scanner, solicitudComandServise, usuarioQueryServise,
                                                                tipoSolicitudQueryServise);
                                                break;

                                        case 4:
                                                System.out.println("\n=== Cambiar Estado de Solicitud ===");
                                                System.out.print("ID de la solicitud: ");
                                                int idSolicitud = leerEntero(scanner);

                                                System.out.print(
                                                                "Nuevo estado (CREADA / EN_REVISION / APROBADA / RECHAZADA / RESUELTA): ");
                                                String nuevoEstado = leerTexto(scanner);

                                                solicitudComandServise.cambiarEstado(idSolicitud,
                                                                nuevoEstado.toUpperCase());
                                                break;

                                        case 5:
                                                System.out.println("\n=== Buscar Solicitud por Estado ===");
                                                System.out.print("Estado: ");
                                                String estadoBuscado = leerTexto(scanner);

                                                solicitudQueryServise.buscarPorEstado(estadoBuscado.toUpperCase());
                                                break;

                                        case 6:
                                                System.out.println("\n=== Reporte por Tipo de Solicitud ===");
                                                System.out.print("ID del tipo de solicitud: ");
                                                int idTipo2 = leerEntero(scanner);

                                                solicitudQueryServise.reportePorTipo(idTipo2);
                                                break;

                                        case 0:
                                                System.out.println("Saliendo del sistema. ¡Hasta luego!");
                                                break;

                                        default:
                                                System.out.println("Opción no válida. Intente de nuevo.");
                                }

                        } while (opcion != 0);

                        scanner.close();

                }
        }

        public static void registrar(Scanner scanner, UsuarioComandService usuarioComandService,
                        boolean usuarioLogueado) {
                String rol;
                System.out.println("\n=== Registrar Usuario ===");
                System.out.print("Nombre: ");
                String nombre = leerTexto(scanner);

                System.out.print("ID: ");
                int id = leerEntero(scanner);

                System.out.print("Correo: ");
                String correo = scanner.nextLine();
                if (usuarioLogueado) {
                        rol = "SOLICITANTE";
                } else {
                        System.out.print("Rol (SOLICITANTE / FUNCIONARIO): ");
                        rol = leerTexto(scanner);
                }

                Usuario usuario = new Usuario.Builder()
                                .id(id)
                                .nombre(nombre)
                                .correo(correo)
                                .rol(rol.toUpperCase())
                                .build();

                usuarioComandService.Registrar(usuario);

        }

        public static void crearSolicitud(Scanner scanner, SolicitudComandServise solicitudComandServise,
                        UsuarioQueryServise usuarioQueryServise, TipoSolicitudQueryServise tipoSolicitudQueryServise) {
                System.out.println("\n=== Crear Solicitud ===");
                System.out.print("ID del usuario: ");
                int idUsuario = leerEntero(scanner);

                System.out.print("ID del tipo de solicitud: ");
                int idTipo = leerEntero(scanner);

                System.out.print("Descripción de la solicitud: ");
                String solicitudDescripcion = leerTexto(scanner);

                System.out.print("Fecha de creación (DD-MM-YYYY): ");
                String fecha = scanner.nextLine();

                Usuario usuarioBuscado = usuarioQueryServise.buscarPorIdUsuario(idUsuario);
                TipoSolicitud tipoSolicitudBuscado = tipoSolicitudQueryServise.buscarPorIdTipoSolicitud(idTipo);

                Solicitud solicitud = new Solicitud.Builder()
                                .usuario(usuarioBuscado)
                                .tipoSolicitud(tipoSolicitudBuscado)
                                .descripcion(solicitudDescripcion)
                                .fechaCreacion(fecha)
                                .build();

                solicitudComandServise.crearSolicitud(solicitud);
        }

        private static int leerEntero(Scanner scanner) {
                while (true) {
                        try {
                                return Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                                System.out.print("Dato inválido. Por favor, digite el tipo correcto (número entero): ");
                        }

                }
        }

        private static String leerTexto(Scanner scanner) {
                while (true) {
                        String texto = scanner.nextLine();
                        if (texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                                return texto;
                        } else {
                                System.out.print("Dato inválido. Por favor, ingrese solo letras: ");
                        }
                }
        }
}
