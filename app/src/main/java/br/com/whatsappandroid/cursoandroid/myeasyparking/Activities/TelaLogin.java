package br.com.whatsappandroid.cursoandroid.myeasyparking.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.whatsappandroid.cursoandroid.myeasyparking.R;
import br.com.whatsappandroid.cursoandroid.myeasyparking.DAO.UsuarioDAO;

public class TelaLogin extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);

        Button btLogin = (Button) findViewById(R.id.btLogin);
        Button btCadastro = (Button) findViewById(R.id.btCadastrar);
        final EditText edtLogin = (EditText) findViewById(R.id.edtLogin);
        final EditText edtSenha = (EditText) findViewById(R.id.edtSenha);


        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaLogin.this,TelaCadastro.class);
                startActivity(intent);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtLogin.getText().toString().isEmpty() || edtSenha.toString().isEmpty() ){
                    Toast.makeText(TelaLogin.this,"Preencha Todos Os Campos!",Toast.LENGTH_SHORT).show();
                }
                UsuarioDAO usuD = new UsuarioDAO(TelaLogin.this);
                if(usuD.login(edtLogin.getText().toString(),edtSenha.getText().toString())){
                    Intent i = new Intent(TelaLogin.this,TelaGerenciar.class);
                    startActivity(i);
                }
                else Toast.makeText(TelaLogin.this,"Login ou Senha Invalido(a)",Toast.LENGTH_SHORT).show();




            }
        });






    }




}
