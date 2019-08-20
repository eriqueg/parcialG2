package br.edu.unisep.utils;

import br.edu.unisep.model.vo.UsuarioVO;

public class UsuarioUtils {

    private static UsuarioVO usuario;

    public static UsuarioVO getUsuario() {
        return usuario;
    }

    public static void setUsuario(UsuarioVO usuario) {
        UsuarioUtils.usuario = usuario;
    }
}
