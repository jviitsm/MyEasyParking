package br.com.whatsappandroid.cursoandroid.myeasyparking;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 29/05/17.
 */

public class EstacionamentoDAO extends GenericDAO implements DAO<Estacionamento> {

    private SQLiteDatabase database;

    public EstacionamentoDAO(Context context) {
        super(context);
        database =getWritableDatabase();
    }

    @Override
    public boolean salvar(Estacionamento estacionamento) {
        database.execSQL("INSERT INTO estacionamento(nome)" +
                        "VALUES(?)",
                new Object[]{estacionamento.getNome()});
        return false;
    }

    @Override
    public List<Estacionamento> listar() {
        return null;
    }

    public List<Estacionamento> listar(String nome) {
        List<Estacionamento> listEstacionamento = null;
        Cursor c = database.rawQuery("SELECT * FROM estacionamento WHERE nome=?", new String[]{nome});
        if(c!=null) {
            c.moveToFirst();
            listEstacionamento = new ArrayList<Estacionamento>();
            if(c.moveToFirst()){
                do{
                    Estacionamento estacionamento = new Estacionamento();
                    estacionamento.setId(c.getInt(c.getColumnIndex("idestacionamento")));
                    estacionamento.setNome(c.getString(c.getColumnIndex("nome")));

                    listEstacionamento.add(estacionamento);
                } while(c.moveToNext());
            }

        }
        return listEstacionamento;

    }

    @Override
    public boolean deletar(int id) {
        return false;
    }

    @Override
    public boolean atualizar(Estacionamento estacionamento) {
        return false;
    }
}
