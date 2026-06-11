package capa_negocio;

import capa_acceso_datos.UsuarioRepositorio;

public class UsuarioQueryServise {

    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioQueryServise(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public Usuario buscarPorIdUsuario(int id) {
        return usuarioRepositorio.buscarPorIdUsuario(id);
    }

}
