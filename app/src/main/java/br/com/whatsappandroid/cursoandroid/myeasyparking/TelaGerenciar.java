package br.com.whatsappandroid.cursoandroid.myeasyparking;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;

/**
 * Created by root on 08/06/17.
 */

public class TelaGerenciar extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_gerenciar);

        TextView ed = (TextView) findViewById(R.id.txtUsuario);
        TextView txtEsta = (TextView) findViewById(R.id.txtEstacionamento);
        Button btCadastrarVaga = (Button) findViewById(R.id.btCadastrarVaga);
        Button btBaixa = (Button) findViewById(R.id.btDarBaixa);
        UsuarioSingleton us = new UsuarioSingleton();


                ed.setText("Usuário: " + us.getInstance().getLogin().toString());
                txtEsta.setText("Estacionamento: " + us.getInstance().getEstacionamento().getNome());

        btCadastrarVaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TelaGerenciar.this, TelaCadastrarVaga.class);
                startActivity(i);
            }
        });

        btBaixa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TelaGerenciar.this, ListaVagas.class);
                startActivity(i);
            }
        });
    }
}
