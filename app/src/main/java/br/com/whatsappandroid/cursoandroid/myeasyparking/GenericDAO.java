package br.com.whatsappandroid.cursoandroid.myeasyparking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 29/05/17.
 */

public class GenericDAO extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "bddados";
    private static final int VERSAO = 1;

    private String sqlTabelaEstacionamento = "CREATE TABLE IF NOT EXISTS estacionamento(" +
            "idestacionamento INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nome VARCHAR(45) NOT NULL " +
            ");";
    private String sqlTabelaUsuario = "CREATE TABLE IF NOT EXISTS usuario(" +
            "idcliente INTEGER PRIMARY KEY AUTOINCREMENT," +
            "login VARCHAR(45) NOT NULL UNIQUE," +
            "senha VARCHAR(45) NOT NULL," +
            "idestacionamento INTEGER, " +
            "FOREIGN KEY (idestacionamento) REFERENCES estacionamento(idestacionamento)" +
            ");";
    private String sqlTabelaVagas = "CREATE TABLE IF NOT EXISTS vaga(" +
            "idvaga INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nomeVaga VARCHAR(45) NOT NULL, " +
            "nomeCarro VARCHAR(45) NOT NULL, " +
            "placaCarro VARCHAR(45) NOT NULL, " +
            "dataEntrada VARCHAR(45) NOT NULL," +
            "dataSaida VARCHAR(45)," +
            "idestacionamento INTEGER," +
            "FOREIGN KEY (idestacionamento) REFERENCES estacionamento(idestacionamento) " +
            ");";


    public GenericDAO(Context context){
        super(context, NOME_BANCO,null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlTabelaEstacionamento);
        db.execSQL(sqlTabelaUsuario);
        db.execSQL(sqlTabelaVagas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
