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

                int opcion;



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
                        opcion = Integer.parseInt(scanner.nextLine());

                        switch (opcion) {

                                case 1:
                                        System.out.println("\n=== Registrar Usuario ===");
                                        System.out.print("Nombre: ");
                                        String nombre = scanner.nextLine();

                                        System.out.print("ID: ");
                                        int id = Integer.parseInt(scanner.nextLine());

                                        System.out.print("Correo: ");
                                        String correo = scanner.nextLine();

                                        System.out.print("Rol (SOLICITANTE / FUNCIONARIO): ");
                                        String rol = scanner.nextLine();


                                        Usuario usuario = new Usuario.Builder()
                                                        .id(id)
                                                        .nombre(nombre)
                                                        .correo(correo)
                                                        .rol(rol.toUpperCase())
                                                        .build();


                                        usuarioComandService.Registrar(usuario);
                                        break;

                                case 2:
                                        System.out.println("\n=== Crear Tipo de Solicitud ===");
                                        System.out.print("Nombre del tipo: ");
                                        String tipoNombre = scanner.nextLine();

                                        System.out.print("Descripción: ");
                                        String tipoDescripcion = scanner.nextLine();


                                        System.out.print("Tiempo estimado (días): ");
                                        int tiempoEstimado = Integer.parseInt(scanner.nextLine());

                                        TipoSolicitud tipoSolicitud = new TipoSolicitud.Builder()
                                                        .nombre(tipoNombre)
                                                        .descripcion(tipoDescripcion)
                                                        .tiempoEstimadoDias(tiempoEstimado)
                                                        .build();
                                        tipoSolicitudComandServise.crearTipoSolicitud(tipoSolicitud);
                                        break;

                                case 3:
                                        System.out.println("\n=== Crear Solicitud ===");
                                        System.out.print("ID del usuario: ");
                                        int idUsuario = Integer.parseInt(scanner.nextLine());


                                        System.out.print("ID del tipo de solicitud: ");
                                        int idTipo = Integer.parseInt(scanner.nextLine());

                                        System.out.print("Descripción de la solicitud: ");
                                        String solicitudDescripcion = scanner.nextLine();


                                        System.out.print("Fecha de creación (DD-MM-YYYY): ");
                                        String fecha = scanner.nextLine();

                                        System.out.print(
                                                        "Estado (CREADA / EN_REVISION / APROBADA / RECHAZADA / RESUELTA): ");
                                        String estado = scanner.nextLine();

                                        Usuario usuarioBuscado = usuarioQueryServise.buscarPorIdUsuario(idUsuario);
                                        TipoSolicitud tipoSolicitudBuscado = tipoSolicitudQueryServise
                                                        .buscarPorIdTipoSolicitud(idTipo);


                                        Solicitud solicitud = new Solicitud.Builder()
                                                        .usuario(usuarioBuscado)
                                                        .tipoSolicitud(tipoSolicitudBuscado)
                                                        .descripcion(solicitudDescripcion)
                                                        .fechaCreacion(fecha)
                                                        .estado(estado.toUpperCase())
                                                        .build();

                                        solicitudComandServise.crearSolicitud(solicitud);
                                        break;

                                case 4:
                                        System.out.println("\n=== Cambiar Estado de Solicitud ===");
                                        System.out.print("ID de la solicitud: ");
                                        int idSolicitud = Integer.parseInt(scanner.nextLine());

                                        System.out.print(
                                                        "Nuevo estado (CREADA / EN_REVISION / APROBADA / RECHAZADA / RESUELTA): ");
                                        String nuevoEstado = scanner.nextLine();

                                        solicitudComandServise.cambiarEstado(idSolicitud, nuevoEstado.toUpperCase());
                                        break;

                                case 5:
                                        System.out.println("\n=== Buscar Solicitud por Estado ===");
                                        System.out.print("Estado: ");
                                        String estadoBuscado = scanner.nextLine();

                                        solicitudQueryServise.buscarPorEstado(estadoBuscado.toUpperCase());
                                        break;

                                case 6:
                                        System.out.println("\n=== Reporte por Tipo de Solicitud ===");
                                        System.out.print("ID del tipo de solicitud: ");
                                        int idTipo2 = Integer.parseInt(scanner.nextLine());

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
