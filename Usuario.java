public class Usuario {
    private int id;
    private String nombre;
    private String correo;
    private String rol;

    private Usuario() {
    }

    public static class Builder {
        private Usuario usuario;

        public Builder() {
            usuario = new Usuario();
        }

        public Builder id(int id) {
            if (id <= 0) {
                throw new IllegalArgumentException("El id debe ser mayor a 0");
            }
            usuario.id = id;
            return this;
        }

        public Builder nombre(String nombre) {
            if (nombre == null || nombre.isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede ser nulo o estar vacio");
            }
            usuario.nombre = nombre;
            return this;
        }

        public Builder correo(String correo) {
            if (correo == null || correo.isEmpty()) {
                throw new IllegalArgumentException("El correo no puede ser nulo o estar vacio");
            }
            usuario.correo = correo;
            return this;
        }

        public Builder rol(String rol) {
            if (rol == null || rol.isEmpty()) {
                throw new IllegalArgumentException("El rol no puede ser nulo o estar vacio");
            }
            usuario.rol = rol;
            return this;
        }

        public Usuario build() {
            return usuario;
        }

    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRol() {
        return rol;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", rol=" + rol + "]";
    }

}
