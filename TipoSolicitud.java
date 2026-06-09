
public class TipoSolicitud {
    private int idTipoSolicitud;
    private String nombre;
    private String descripcion;
    private int tiempoEstimadoDias;

    private TipoSolicitud() {
    }

    public static class Builder {
        private TipoSolicitud tipoSolicitud;

        public Builder() {
            tipoSolicitud = new TipoSolicitud();
        }

        public Builder idTipoSolicitud(int idTipoSolicitud) {
            tipoSolicitud.idTipoSolicitud = idTipoSolicitud;
            return this;
        }

        public Builder nombre(String nombre) {
            if (nombre == null || nombre.isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede ser nulo o estar vacio");
            }
            tipoSolicitud.nombre = nombre;
            return this;
        }

        public Builder descripcion(String descripcion) {
            tipoSolicitud.descripcion = descripcion;
            return this;
        }

        public Builder tiempoEstimadoDias(int tiempoEstimadoDias) {
            if (tiempoEstimadoDias < 1) {
                throw new IllegalArgumentException("El tiempo estimado debe ser mayor o igual a 1");
            }
            tipoSolicitud.tiempoEstimadoDias = tiempoEstimadoDias;
            return this;
        }

        public TipoSolicitud build() {
            return tipoSolicitud;
        }

    }

    public int getIdTipoSolicitud() {
        return idTipoSolicitud;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getTiempoEstimadoDias() {
        return tiempoEstimadoDias;
    }

    @Override
    public String toString() {
        return "TipoSolicitud [idTipoSolicitud=" + idTipoSolicitud + ", nombre=" + nombre + ", descripcion="
                + descripcion + ", tiempoEstimadoDias=" + tiempoEstimadoDias + "]";
    }

}
