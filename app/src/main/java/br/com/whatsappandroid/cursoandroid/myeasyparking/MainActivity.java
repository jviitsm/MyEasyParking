package br.com.whatsappandroid.cursoandroid.myeasyparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
   // private Button login,cadastro;
   /// private EditText txtLogin,txtSenha;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);

       Button btLogin = (Button) findViewById(R.id.btLogin);
       Button btCadastro = (Button) findViewById(R.id.btCadastrar);


        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Cadastro.class);
                startActivity(intent);
            }
        });






    }




}
