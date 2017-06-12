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

public class EstacionamentoDAO extends GenericDAO implements DAO<Estacionamento> {

    private SQLiteDatabase database;

    public EstacionamentoDAO(Context context) {
        super(context);
        database = getWritableDatabase();
    }

    @Override
    public boolean salvar(Estacionamento estacionamento) {

        ContentValues cv = new ContentValues();
        cv.put("nome", estacionamento.getNome());
        cv.put("minutos_gratis", estacionamento.getMinutosGratis());
        cv.put("preco_fixo" , estacionamento.getPrecoFixo());
        cv.put("minutos_pago", estacionamento.getMinutosPago());
        cv.put("hora_extra", estacionamento.getHoraExtra());

        try{
            database.insert("estacionamento", null, cv);
        } catch (Exception ex) {
            Log.e("joao_v", ex.getMessage());
        }

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
