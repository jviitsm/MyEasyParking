package br.com.whatsappandroid.cursoandroid.myeasyparking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by root on 29/05/17.
 */

public class VagaDAO extends GenericDAO implements DAO<Vaga> {

    private SQLiteDatabase dataBase;


    public VagaDAO(Context context) {
        super(context);
        dataBase = getWritableDatabase();


    }

    @Override
    public boolean salvar(Vaga vaga) {
        dataBase.execSQL("INSERT INTO vaga(nome, carro, placa, dataEntrada,dataSaida,estacionamento)" +
                        " VALUES(?,?,?,?,?,?)",
                new Object[] {vaga.getNome(), vaga.getCarro(),vaga.getPlaca(),
                        vaga.getDataEntrada(),vaga.getDataSaida(),vaga.getEstacionamento()});
        return false;
    }

    @Override
    public List<Vaga> listar(Cursor cursor) {
        return null;
    }

    @Override
    public boolean deletar(int id) {
        return false;
    }

    @Override
    public boolean atualizar(Vaga vaga) {
        return false;
    }
}
