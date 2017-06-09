package br.com.whatsappandroid.cursoandroid.myeasyparking;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by root on 09/06/17.
 */

public class ListaVagas extends AppCompatActivity implements AdapterView.OnItemClickListener {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_baixa);

         VagaDAO vDAO = new VagaDAO(this);
        UsuarioSingleton us = new UsuarioSingleton();

        ListView lista = (ListView) findViewById(R.id.listView);
        ArrayAdapter<Vaga> adapter =
                new ArrayAdapter<Vaga>(this,android.R.layout.simple_list_item_1,
                        vDAO.listar(us.getInstance().getEstacionamento().getId()));
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Vaga vaga = (Vaga) parent.getAdapter().getItem(position);
        if(vaga !=null){
            new AlertDialog.Builder(ListaVagas.this)
                    .setTitle("Encerrar Uso Da Vaga")
                    .setMessage("O uso da vaga " + vaga.getNome() + " está em X R$")
                    .setCancelable(true)
                    .setPositiveButton("Dar Baixa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
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
}
