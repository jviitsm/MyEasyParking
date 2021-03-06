package br.com.whatsappandroid.cursoandroid.myeasyparking.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.whatsappandroid.cursoandroid.myeasyparking.Model.Vaga;
import br.com.whatsappandroid.cursoandroid.myeasyparking.Model.UsuarioSingleton;

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

                ContentValues cv = new ContentValues();
                cv.put("nomeVaga", "Vaga " + vaga.getNome());
                cv.put("nomeCarro", vaga.getCarro());
                cv.put("placaCarro", vaga.getPlaca());
                cv.put("dataEntrada" , vaga.getDataEntrada());
                cv.put("idestacionamento", vaga.getEstacionamento().getId());
                try{
                    dataBase.insert("vaga", null, cv);
                } catch (Exception ex) {
                    Log.e("joao_v", ex.getMessage());
                }

        return false;
    }


    /*dataBase.execSQL("INSERT INTO vaga(nomeVaga, nomeCarro, placaCarro, dataEntrada,dataSaida,idestacionamento)" +
                          " VALUES(?,?,?,?,?,?)",
                  new Object[] {vaga.getNome(), vaga.getCarro(),vaga.getPlaca(),
                          vaga.getDataEntrada(),vaga.getDataSaida(),vaga.getEstacionamento()});
          return false;
  */

    public List<Vaga> listar(int id) {
       List<Vaga> lista = null;
        SQLiteDatabase db = getWritableDatabase();
        UsuarioSingleton us = new UsuarioSingleton();

        Cursor cursor = db.rawQuery("SELECT * FROM VAGA a " +
                "INNER JOIN ESTACIONAMENTO b ON a.idestacionamento = b.idestacionamento where a.IDESTACIONAMENTO='" + id + "'", null);
        if(cursor!=null){
            cursor.moveToFirst();
            if(cursor.getCount()>0){
                lista = new ArrayList<>();
                int idxId = cursor.getColumnIndex("idvaga");
                int idxNomeCarro = cursor.getColumnIndex("nomeCarro");
                int idxNomeVaga = cursor.getColumnIndex("nomeVaga");
                int idxPlaca = cursor.getColumnIndex("placaCarro");
                int idxDataEntrada = cursor.getColumnIndex("dataEntrada");
                int idxDataSaida = cursor.getColumnIndex("dataSaida");

                cursor.moveToFirst();
                do{
                    Vaga vaga = new Vaga();
                    lista.add(vaga);
                    vaga.setId(cursor.getInt(idxId));
                    vaga.setNome(cursor.getString(idxNomeVaga));
                    vaga.setPlaca(cursor.getString(idxPlaca));
                    vaga.setCarro(cursor.getString(idxNomeCarro));
                    vaga.setDataEntrada(cursor.getString(idxDataEntrada));
                    vaga.setDataSaida(cursor.getString(idxDataSaida));
                    vaga.setEstacionamento(us.getInstance().getEstacionamento());

                }while (cursor.moveToNext());
                return lista;
            }
        }
        return null;




    }
    public boolean listarPorNome(String nome, int idestacionamento){
        List<Vaga> lista = new ArrayList<>();
        UsuarioSingleton us = new UsuarioSingleton();
       SQLiteDatabase db = getWritableDatabase();
     //  Cursor c = dataBase.rawQuery("SELECT * FROM VAGA WHERE nome=? and idestacionamento=?",new Object[]{nome});
        Cursor cursor = db.rawQuery("SELECT * FROM VAGA  where nomeVaga='"+nome+"' and idestacionamento='"+idestacionamento+"'", null);



        if(cursor!=null){
            cursor.moveToFirst();
            if(cursor.getCount()>0){
                lista = new ArrayList<>();
                int idxId = cursor.getColumnIndex("idvaga");
                int idxNomeCarro = cursor.getColumnIndex("nomeCarro");
                int idxNomeVaga = cursor.getColumnIndex("nomeVaga");
                int idxPlaca = cursor.getColumnIndex("placaCarro");
                int idxDataEntrada = cursor.getColumnIndex("dataEntrada");
                int idxDataSaida = cursor.getColumnIndex("dataSaida");

                cursor.moveToFirst();
                do{
                    Vaga vaga = new Vaga();
                    lista.add(vaga);
                    vaga.setId(cursor.getInt(idxId));
                    vaga.setNome(cursor.getString(idxNomeVaga));
                    vaga.setPlaca(cursor.getString(idxPlaca));
                    vaga.setCarro(cursor.getString(idxNomeCarro));
                    vaga.setDataEntrada(cursor.getString(idxDataEntrada));
                    vaga.setDataSaida(cursor.getString(idxDataSaida));
                    vaga.setEstacionamento(us.getInstance().getEstacionamento());

                }while (cursor.moveToNext());

                return true;
            }
        }
        return false;
    }

    public List<Vaga> listar(){
        return null;
    }
    @Override
    public boolean deletar(int id) {
      SQLiteDatabase  db = getWritableDatabase();
        db.execSQL("DELETE FROM vaga WHERE idvaga=?",
                new Object[]{id});
        return false;
    }

    public List<Vaga> listarTudo(int idestacionamento) {
        List<Vaga> lista = new ArrayList<>();
        UsuarioSingleton us = new UsuarioSingleton();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM VAGA  where idestacionamento='" + idestacionamento + "'", null);

        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                lista = new ArrayList<>();
                int idxId = cursor.getColumnIndex("idvaga");
                int idxNomeCarro = cursor.getColumnIndex("nomeCarro");
                int idxNomeVaga = cursor.getColumnIndex("nomeVaga");
                int idxPlaca = cursor.getColumnIndex("placaCarro");
                int idxDataEntrada = cursor.getColumnIndex("dataEntrada");
                int idxDataSaida = cursor.getColumnIndex("dataSaida");

                cursor.moveToFirst();
                do {
                    Vaga vaga = new Vaga();
                    lista.add(vaga);
                    vaga.setId(cursor.getInt(idxId));
                    vaga.setNome(cursor.getString(idxNomeVaga));
                    vaga.setPlaca(cursor.getString(idxPlaca));
                    vaga.setCarro(cursor.getString(idxNomeCarro));
                    vaga.setDataEntrada(cursor.getString(idxDataEntrada));
                    vaga.setDataSaida(cursor.getString(idxDataSaida));
                    vaga.setEstacionamento(us.getInstance().getEstacionamento());

                } while (cursor.moveToNext());


            }


        }
        return lista;
    }

    @Override
    public boolean atualizar(Vaga vaga) {
        return false;
    }
}

