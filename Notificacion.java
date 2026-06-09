package ProyectoFinal;

public class Notificacion {
    private int idNotificacion;
    private Solicitud solicitud;
    private String mensaje;
    private String fecha;
    private String estadoSolicitud;

    private Notificacion() {
    }

    public static class Builder {
        private Notificacion notificacion;

        public Builder() {
            notificacion = new Notificacion();
        }

        public Builder idNotificacion(int idNotificacion) {
            notificacion.idNotificacion = idNotificacion;
            return this;
        }

        public Builder solicitud(Solicitud solicitud) {
            notificacion.solicitud = solicitud;
            return this;
        }

        public Builder mensaje(String mensaje) {
            notificacion.mensaje = mensaje;
            return this;
        }

        public Builder fecha(String fecha) {
            notificacion.fecha = fecha;
            return this;
        }

        public Builder estadoSolicitud(String estadoSolicitud) {
            notificacion.estadoSolicitud = estadoSolicitud;
            return this;
        }

        public Notificacion build() {
            return notificacion;
        }

    }

    public int getIdNotificacion() {
        return idNotificacion;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

}
