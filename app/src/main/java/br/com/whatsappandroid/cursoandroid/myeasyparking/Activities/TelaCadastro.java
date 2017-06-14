package br.com.whatsappandroid.cursoandroid.myeasyparking.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.whatsappandroid.cursoandroid.myeasyparking.Model.Estacionamento;
import br.com.whatsappandroid.cursoandroid.myeasyparking.DAO.EstacionamentoDAO;
import br.com.whatsappandroid.cursoandroid.myeasyparking.R;
import br.com.whatsappandroid.cursoandroid.myeasyparking.Model.Usuario;
import br.com.whatsappandroid.cursoandroid.myeasyparking.DAO.UsuarioDAO;

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

        final EditText  edtTempoGratis = (EditText) findViewById(R.id.edtTempoGratis);
        final EditText edtTempoMinimo =  (EditText) findViewById(R.id.edtTempoMinimo);
        final EditText edtValorHoraExtra = (EditText) findViewById(R.id.edtValorHoraExtra);
        final EditText edtPrecoFixo = (EditText) findViewById(R.id.edtValorCobradoPeloMinimo);


        Button btCadastrar = (Button) findViewById(R.id.btCadastrar);




        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtUser.getText().toString().isEmpty() || edtSenha.getText().toString().isEmpty() ||
                        edtRepetirSenha.getText().toString().isEmpty() || edtEstacionamento.getText().toString().isEmpty() ||
                        edtPrecoFixo.getText().toString().isEmpty() || edtTempoGratis.getText().toString().isEmpty() ||
                        edtTempoMinimo.getText().toString().isEmpty() || edtValorHoraExtra.getText().toString().isEmpty()){
                    Toast.makeText(TelaCadastro.this,"Preencha todos os campos!",Toast.LENGTH_SHORT).show();
                }
                else {
                    EstacionamentoDAO estaD = new EstacionamentoDAO(TelaCadastro.this);
                    Estacionamento estacionamento = new Estacionamento();


                    estacionamento.setNome(edtEstacionamento.getText().toString());
                    estacionamento.setHoraExtra(Integer.parseInt(edtValorHoraExtra.getText().toString()));
                    estacionamento.setMinutosGratis(Integer.parseInt(edtTempoGratis.getText().toString()));
                    estacionamento.setMinutosPago(Integer.parseInt(edtTempoMinimo.getText().toString()));
                    estacionamento.setPrecoFixo(Integer.parseInt(edtPrecoFixo.getText().toString()));

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

                        finish();
                        //a
                        Toast.makeText(TelaCadastro.this,"Cadastro Realizado Com Sucesso!",Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(TelaCadastro.this, "A senhas não correspondem!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }



}
