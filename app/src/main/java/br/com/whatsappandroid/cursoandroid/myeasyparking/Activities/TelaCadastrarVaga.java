package br.com.whatsappandroid.cursoandroid.myeasyparking.Activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.whatsappandroid.cursoandroid.myeasyparking.Model.Estacionamento;
import br.com.whatsappandroid.cursoandroid.myeasyparking.R;
import br.com.whatsappandroid.cursoandroid.myeasyparking.Model.UsuarioSingleton;
import br.com.whatsappandroid.cursoandroid.myeasyparking.Model.Vaga;
import br.com.whatsappandroid.cursoandroid.myeasyparking.DAO.VagaDAO;

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

                if(edtModelo.getText().toString().isEmpty() || edtPlaca.getText().toString().isEmpty() ||
                        edtVaga.getText().toString().isEmpty()){
                    Toast.makeText(TelaCadastrarVaga.this, "Preencha Todos os Campos!", Toast.LENGTH_SHORT).show();
                }
                else {


                    VagaDAO vDAO = new VagaDAO(TelaCadastrarVaga.this);
                    UsuarioSingleton us = new UsuarioSingleton();
                    Vaga vaga = new Vaga();


                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                    String formatDate = df.format(c.getTime());


                    Estacionamento esta = us.getInstance().getEstacionamento();
//a

                    if(!vDAO.listarPorNome("Vaga " + edtVaga.getText().toString(),us.getInstance().getEstacionamento().getId())){
                        vaga.setNome(edtVaga.getText().toString());
                        vaga.setCarro(edtModelo.getText().toString());
                        vaga.setPlaca(edtPlaca.getText().toString());
                        vaga.setDataEntrada(formatDate);
                        vaga.setEstacionamento(esta);
                        vDAO.salvar(vaga);


                        edtModelo.setText(null);
                        edtPlaca.setText(null);
                        edtVaga.setText(null);

                        finish();
                        Toast.makeText(TelaCadastrarVaga.this, "Vaga Cadastrada Com Sucesso!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(TelaCadastrarVaga.this, "Já Existe Uma Vaga Com Este Nome!", Toast.LENGTH_SHORT).show();
                        edtVaga.setText(null);
                    }



                }

            }
        });



    }
}
