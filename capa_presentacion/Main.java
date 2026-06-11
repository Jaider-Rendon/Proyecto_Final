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

                System.out.println("=== Registrar Usuario ===");
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("cedula: ");
                int cedula = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Correo: ");
                String correo = scanner.nextLine();

                System.out.print("Rol (SOLICITANTE / FUNCIONARIO): ");
                String rol = scanner.nextLine();
                Usuario usuario = new Usuario.Builder()
                                .id(cedula)
                                .nombre(nombre)
                                .correo(correo)
                                .rol(rol.toUpperCase())
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

                Usuario usuarioBuscado = usuarioQueryServise.buscarPorIdUsuario(idUsuario);
                TipoSolicitud tipoSolicitudBuscado = tipoSolicitudQueryServise.buscarPorIdTipoSolicitud(idTipo);
                Solicitud solicitud = new Solicitud.Builder()
                                .usuario(usuarioBuscado)
                                .tipoSolicitud(tipoSolicitudBuscado)
                                .descripcion(solicitudDescripcion)
                                .fechaCreacion(fecha)
                                .build();

                solicitudComandServise.crearSolicitud(solicitud);

                System.out.println("\n=== Cambiar Estado de Solicitud ===");
                System.out.print("ID de la solicitud: ");
                int idSolicitud = Integer.parseInt(scanner.nextLine());
                System.out.print("Nuevo estado(CREADA / EN_REVISION / APROBADA / RECHAZADA / RESUELTA): ");
                String nuevoEstado = scanner.nextLine();
                solicitudComandServise.cambiarEstado(idSolicitud, nuevoEstado.toUpperCase());

                System.out.println("\n=== Buscar Solicitud por Estado ===");
                System.out.print("Estado: ");
                String estadoBuscado = scanner.nextLine();
                SolicitudQueryServise solicitudQueryServise = new SolicitudQueryServise(solicitudRepository);
                solicitudQueryServise.buscarPorEstado(estadoBuscado.toUpperCase());

                System.out.println("\n=== Reporte por Tipo de Solicitud ===");
                System.out.print("ID del tipo de solicitud: ");
                int idTipo2 = Integer.parseInt(scanner.nextLine());
                solicitudQueryServise.reportePorTipo(idTipo2);

                scanner.close();

        }
}
