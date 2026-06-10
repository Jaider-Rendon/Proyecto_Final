public class Solicitud {
    private int idSolicitud;
    private Usuario usuario;
    private TipoSolicitud tipoSolicitud;
    private String descripcion;
    private String fechaCreacion;
    private String estado;

    private Solicitud() {
    }

    public static class Builder {
        private Solicitud solicitud;

        public Builder() {
            solicitud = new Solicitud();
        }

        public Builder idSolicitud(int idSolicitud) {
            solicitud.idSolicitud = idSolicitud;
            return this;
        }

        public Builder usuario(Usuario usuario) {
            if (usuario == null) {
                throw new IllegalArgumentException("El usuario no puede ser nulo");
            }
            solicitud.usuario = usuario;
            return this;
        }

        public Builder tipoSolicitud(TipoSolicitud tipoSolicitud) {
            if (tipoSolicitud == null) {
                throw new IllegalArgumentException("El tipo de solicitud no puede ser nulo");
            }
            solicitud.tipoSolicitud = tipoSolicitud;
            return this;
        }

        public Builder descripcion(String descripcion) {
            solicitud.descripcion = descripcion;
            return this;
        }

        public Builder fechaCreacion(String fechaCreacion) {
            solicitud.fechaCreacion = fechaCreacion;
            return this;
        }

        public Builder estado(String estado) {
            solicitud.estado = estado;
            return this;
        }

        public Solicitud build() {
            return solicitud;
        }

    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public TipoSolicitud getTipoSolicitud() {
        return tipoSolicitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

}
