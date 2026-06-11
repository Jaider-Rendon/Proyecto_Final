package capa_acceso_datos;

import capa_negocio.Solicitud;

public interface SolicitudRepository {

    void crearSolicitud(Solicitud solicitud);

    void cambiarEstado(int idSolicitud, String estado);

    void buscarPorEstado(String estado);

    void reportePorTipo(int idUsuario);

    Solicitud buscarPorIdSolicitud(int id);

}
