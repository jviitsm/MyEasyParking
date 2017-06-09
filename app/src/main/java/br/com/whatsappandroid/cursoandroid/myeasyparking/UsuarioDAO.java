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

    public boolean login(String login, String senha){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM USUARIO a " +
                "INNER JOIN ESTACIONAMENTO b ON a.idestacionamento = b.idestacionamento where a.login='" + login + "'", null);
        List<Usuario> usuarios = listar(c);

        if(usuarios.size() == 1 && usuarios.get(0).getLogin().equals(login) && usuarios.get(0).getSenha().equals(senha)){
            UsuarioSingleton us = new UsuarioSingleton();
            us.setInstance(usuarios.get(0));
            return true;
        }
        return false;
    }

    public List<Usuario> listar(Cursor c) {
        SQLiteDatabase db = getWritableDatabase();
        List<Usuario> usuarios = new ArrayList<Usuario>();
        if(c.moveToFirst()){
            do{
                Usuario usuario = new Usuario();
                usuarios.add(usuario);
                usuario.setId(c.getInt(c.getColumnIndex("idcliente")));
                usuario.setLogin(c.getString(c.getColumnIndex("login")));
                usuario.setSenha(c.getString(c.getColumnIndex("senha")));
                Estacionamento estacionamento = new Estacionamento();
                estacionamento.setId(c.getInt(c.getColumnIndex("idestacionamento")));
                estacionamento.setNome(c.getString(c.getColumnIndex("nome")));
                usuario.setEstacionamento(estacionamento);
            }while (c.moveToNext());
        }
        return usuarios;

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
