package br.com.whatsappandroid.cursoandroid.myeasyparking;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 29/05/17.
 */

public class UsuarioDAO extends GenericDAO implements DAO<Usuario> {

    private SQLiteDatabase database;
    private EstacionamentoDAO estacionamento;

    public UsuarioDAO(Context context) {
        super(context);
        database = getWritableDatabase();
    }

    @Override
    public boolean salvar(Usuario usuario) {
        Cursor c = database.query("estacionamento", null, "nome='" + usuario.getEstacionamento().getNome() +"'", null, null, null, null);
        List<Estacionamento> estacionamentos = estacionamento.listar(c);

        database.execSQL("INSERT INTO usuario(login, senha, estacionamento)" +
            "VALUES(?,?,?)",
                new Object[]{usuario.getLogin(),usuario.getSenha(),estacionamentos.get(0).getId()});


        return false;
    }

    @Override
    public List<Usuario> listar(Cursor cursor) {
        return null;
    }


    @Override
    public boolean deletar(int id) {
        return false;
    }

    @Override
    public boolean atualizar(Usuario usuario) {
        return false;
    }
}
