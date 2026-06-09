
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
            tipoSolicitud.nombre = nombre;
            return this;
        }

        public Builder descripcion(String descripcion) {
            tipoSolicitud.descripcion = descripcion;
            return this;
        }

        public Builder tiempoEstimadoDias(int tiempoEstimadoDias) {
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

}
