package capa_acceso_datos;

import capa_negocio.Usuario;

public interface UsuarioRepositorio {

    void registrarUsuario(Usuario usuario);

    Usuario buscarPorIdUsuario(int id);

}
