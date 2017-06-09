package br.com.whatsappandroid.cursoandroid.myeasyparking;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by root on 08/06/17.
 */

public class TelaCadastrarVaga extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro_vaga);

        final EditText edtModelo = (EditText) findViewById(R.id.edtModeloCarro);
        final EditText edtPlaca = (EditText) findViewById(R.id.edtPlacaCarro);
        final EditText edtVaga = (EditText) findViewById(R.id.edtNomeVaga);
        Button btCadastrar = (Button) findViewById(R.id.btCadastrarVaga);


        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VagaDAO vDAO = new VagaDAO(TelaCadastrarVaga.this);
                UsuarioSingleton us = new UsuarioSingleton();
                Vaga vaga = new Vaga();


                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String formatDate = df.format(c.getTime());



                Estacionamento esta = new Estacionamento();
                esta.setId(us.getInstance().getEstacionamento().getId());
                esta.setNome(us.getInstance().getEstacionamento().getNome());


                vaga.setNome(edtVaga.getText().toString());
                vaga.setCarro(edtModelo.getText().toString());
                vaga.setPlaca(edtPlaca.getText().toString());
                vaga.setDataEntrada(formatDate);
                vaga.setEstacionamento(esta);
                vDAO.salvar(vaga);


            }
        });



    }
}
