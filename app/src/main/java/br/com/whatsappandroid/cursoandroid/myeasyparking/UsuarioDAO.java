package br.com.whatsappandroid.cursoandroid.myeasyparking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
        estacionamento = new EstacionamentoDAO(context);
    }

    @Override
    public boolean salvar(Usuario usuario) {

            List<Estacionamento> estacionamentos = estacionamento.listar(usuario.getEstacionamento().getNome());
            if(estacionamentos!=null){
                if(estacionamentos.size()>0){
                    ContentValues cv = new ContentValues();
                    cv.put("login", usuario.getLogin());
                    cv.put("senha", usuario.getSenha());
                    cv.put("idestacionamento", estacionamentos.get(0).getId());
                    try{
                        database.insert("usuario", null, cv);
                    } catch (Exception ex) {
                        Log.e("joao_v", ex.getMessage());
                    }

                }

        }






        return false;
    }

    @Override
    public List<Usuario> listar() {
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
