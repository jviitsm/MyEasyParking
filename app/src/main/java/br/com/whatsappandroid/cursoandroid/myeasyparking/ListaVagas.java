package br.com.whatsappandroid.cursoandroid.myeasyparking;

import android.content.DialogInterface;
import android.icu.util.DateInterval;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by root on 09/06/17.
 */

public class ListaVagas extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lista;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_baixa);

        VagaDAO vDAO = new VagaDAO(this);
        UsuarioSingleton us = new UsuarioSingleton();



        lista = (ListView) findViewById(R.id.listView);


        atualizaListaVagas();


        lista.setOnItemClickListener(this);



    }

    public long calcularValor(long minutos, Vaga vaga){
       Estacionamento estaVaga = vaga.getEstacionamento();
        long hora = 60;
        if(minutos <= estaVaga.getMinutosGratis()){
            return 0;
        }
        else if(minutos >= estaVaga.getMinutosGratis() && minutos < estaVaga.getMinutosPago()){
            long a = estaVaga.getPrecoFixo();
            return a;
        }
        else if(minutos >= estaVaga.getMinutosPago() && minutos < estaVaga.getMinutosPago() + hora){
            long a =0;
            a = estaVaga.getPrecoFixo() + estaVaga.getHoraExtra();
            return a;
        }
        else if(minutos >= estaVaga.getMinutosPago() && minutos < estaVaga.getMinutosPago() + (hora *2)){
            long a =0;
            a = estaVaga.getPrecoFixo() + estaVaga.getHoraExtra() + estaVaga.getHoraExtra();
            return a;
        }
        else{
            return 30;
        }




    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Vaga vaga = (Vaga) parent.getAdapter().getItem(position);
        if (vaga != null) {
            new AlertDialog.Builder(ListaVagas.this)


                    .setTitle("Encerrar Uso Da Vaga")
                    .setMessage("O uso da vaga " + vaga.getNome() + " está em " +
                            calcularValor(getDateDiff(calcularDaVaga(vaga),calcularDataAtual(),TimeUnit.MINUTES),vaga) + "R$"
 )                  .setCancelable(true)
                    .setPositiveButton("Dar Baixa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            VagaDAO vd = new VagaDAO(ListaVagas.this);



                            getDateDiff(calcularDaVaga(vaga),calcularDataAtual(),TimeUnit.MINUTES);
                            vd.deletar(vaga.getId());
                            atualizaListaVagas();


                        }
                    })
                    .setNegativeButton("Cancelar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                }
                            }).show();

        }
    }

    private void atualizaListaVagas() {
        VagaDAO dao = new VagaDAO(this);
        UsuarioSingleton us = new UsuarioSingleton();



        ArrayAdapter<Vaga> arrayAdapter = new ArrayAdapter<Vaga>(this,
                android.R.layout.simple_list_item_1, dao.listar(us.getInstance().getEstacionamento().getId()));


            lista.setAdapter(arrayAdapter);


    }


    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }


    public Date calcularDaVaga(Vaga vaga){


        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataDeEntrada = vaga.getDataEntrada();

        try{
            Date dataDaVaga = df.parse(dataDeEntrada);
            return dataDaVaga;
        }catch (ParseException e){
            e.printStackTrace();
            return null;
        }
    }
    public Date calcularDataAtual(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String dataAtual = df.format(c.getTime());
        try{
            Date dataNow = df.parse(dataAtual);
            return dataNow;
        }catch (ParseException e){
            e.printStackTrace();
            return null;
        }

    }



      /* public Date calcularDatas(Vaga vaga){

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String dataAtual = df.format(c.getTime());
        String dataDeEntrada = vaga.getDataEntrada();
        try {
            Date dataDeSaida = df.parse(dataAtual);
            System.out.println(df.format(dataDeSaida));
            Date datadeEntrada = df.parse(dataDeEntrada);
            System.out.println(df.format(datadeEntrada));

        return dataDeSaida;
        return datadeEntrada;

        } catch (ParseException e) {
            e.printStackTrace();

        }
    }*/
}

