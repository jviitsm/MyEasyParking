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
    private static Estacionamento estacionamento;
    private static Usuario usu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro);

        final EditText edtUser = (EditText) findViewById(R.id.edtLogin);
        final EditText edtSenha = (EditText) findViewById(R.id.edtSenha);
        EditText edtRepetirSenha = (EditText) findViewById(R.id.edtSenhaRepetir);
        final EditText edtEstacionamento = (EditText) findViewById(R.id.edtEstacionamento);

        Button btCadastrar = (Button) findViewById(R.id.btCadastrar);


        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EstacionamentoDAO esta = new EstacionamentoDAO(Cadastro.this);
                if(estacionamento == null){
                    estacionamento = new Estacionamento();
                }
                estacionamento.setNome(edtEstacionamento.getText().toString());
                esta.salvar(estacionamento);
                UsuarioDAO usud = new UsuarioDAO(Cadastro.this);
                if(usu == null){
                    usu = new Usuario();
                }
                usu.setLogin(edtUser.getText().toString());
                usu.setSenha(edtSenha.getText().toString());
                usu.setEstacionamento(estacionamento);
                usud.salvar(usu);
                //nada
            }
        });
    }
}
