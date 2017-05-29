package br.com.whatsappandroid.cursoandroid.myeasyparking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by root on 29/05/17.
 */

public class Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro);

        EditText edtUser = (EditText) findViewById(R.id.edtLogin);
        EditText edtSenha = (EditText) findViewById(R.id.edtSenha);
        EditText edtRepetirSenha = (EditText) findViewById(R.id.edtSenhaRepetir);
        EditText edtEstacionamento = (EditText) findViewById(R.id.edtEstacionamento);

        Button btCadastrar = (Button) findViewById(R.id.btCadastrar);


        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
