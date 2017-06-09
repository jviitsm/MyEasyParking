package br.com.whatsappandroid.cursoandroid.myeasyparking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by root on 29/05/17.
 */

public class TelaCadastro extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro);

        final EditText edtUser = (EditText) findViewById(R.id.edtLogin);
        final EditText edtSenha = (EditText) findViewById(R.id.edtSenha);
        final EditText edtRepetirSenha = (EditText) findViewById(R.id.edtSenhaRepetir);
        final EditText edtEstacionamento = (EditText) findViewById(R.id.edtEstacionamento);

        Button btCadastrar = (Button) findViewById(R.id.btCadastrar);



        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtUser.getText().toString().isEmpty() || edtSenha.getText().toString().isEmpty() ||
                        edtRepetirSenha.getText().toString().isEmpty() || edtEstacionamento.getText().toString().isEmpty()){
                    Toast.makeText(TelaCadastro.this,"Preencha todos os campos!",Toast.LENGTH_SHORT).show();
                }
                else {
                    EstacionamentoDAO estaD = new EstacionamentoDAO(TelaCadastro.this);
                    Estacionamento estacionamento = new Estacionamento();


                    estacionamento.setNome(edtEstacionamento.getText().toString());
                    estaD.salvar(estacionamento);

                    if (edtSenha.getText().toString().equals(edtRepetirSenha.getText().toString())) {
                        UsuarioDAO usuD = new UsuarioDAO(TelaCadastro.this);
                        Usuario user = new Usuario();
                        user.setLogin(edtUser.getText().toString());
                        user.setSenha(edtSenha.getText().toString());
                        user.setEstacionamento(estacionamento);
                        usuD.salvar(user);

                        edtUser.setText(null);
                        edtRepetirSenha.setText(null);
                        edtSenha.setText(null);
                        edtEstacionamento.setText(null);
                        Toast.makeText(TelaCadastro.this,"Cadastro Realizado Com Sucesso!",Toast.LENGTH_LONG).show();
                        finish();

                    } else {
                        Toast.makeText(TelaCadastro.this, "A senhas não correspondem!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void usuarioCadastrar(){

    }
}
