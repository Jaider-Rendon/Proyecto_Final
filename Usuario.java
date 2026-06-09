package ProyectoFinal;

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
            usuario.id = id;
            return this;
        }

        public Builder nombre(String nombre) {
            usuario.nombre = nombre;
            return this;
        }

        public Builder correo(String correo) {
            usuario.correo = correo;
            return this;
        }

        public Builder rol(String rol) {
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

}
