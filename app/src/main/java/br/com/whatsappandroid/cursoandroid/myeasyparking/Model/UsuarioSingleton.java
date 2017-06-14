package br.com.whatsappandroid.cursoandroid.myeasyparking.Model;

import br.com.whatsappandroid.cursoandroid.myeasyparking.Model.Usuario;

/**
 * Created by root on 08/06/17.
 */

public class UsuarioSingleton {
    private static Usuario usuario = null;

    public Usuario getInstance() {
        if (usuario == null) {
            usuario = new Usuario();
        }
            return usuario;
    }
    public void setInstance(Usuario usuario){
        this.usuario = usuario;
    }




}