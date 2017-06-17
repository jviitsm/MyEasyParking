package br.com.whatsappandroid.cursoandroid.myeasyparking.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import br.com.whatsappandroid.cursoandroid.myeasyparking.Model.Estacionamento;
import br.com.whatsappandroid.cursoandroid.myeasyparking.Model.Usuario;
import br.com.whatsappandroid.cursoandroid.myeasyparking.Model.Vaga;
import br.com.whatsappandroid.cursoandroid.myeasyparking.R;
import br.com.whatsappandroid.cursoandroid.myeasyparking.Model.UsuarioSingleton;
import br.com.whatsappandroid.cursoandroid.myeasyparking.DAO.VagaDAO;

/**
 * Created by root on 08/06/17.
 */

public class TelaGerenciar extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lista;
    private FloatingActionButton floatingMensagem;
    private CardView cardCadastro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_gerenciar);

        final UsuarioSingleton us = new UsuarioSingleton();
        VagaDAO v = new VagaDAO(TelaGerenciar.this);

        TextView txtUser = (TextView) findViewById(R.id.txtUsuario);
        TextView txtEsta = (TextView) findViewById(R.id.txtEstacionamento);
        Button btCadastrarVaga = (Button) findViewById(R.id.btCadastrarVaga);
        lista = (ListView) findViewById(R.id.listView);
        floatingMensagem = (FloatingActionButton) findViewById(R.id.floatingMensagem);
        cardCadastro = (CardView) findViewById(R.id.cardCadastro);
        ImageButton imgBt = (ImageButton) findViewById(R.id.imgButton);
        final EditText edtNomeVaga = (EditText) findViewById(R.id.edtNomeVaga);
        final EditText edtNomeCarro = (EditText) findViewById(R.id.edtModeloCarro);
        final EditText edtPlacaCarro = (EditText) findViewById(R.id.edtPlacaCarro);


        imgBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardCadastro.setVisibility(View.GONE);

            }
        });


        txtUser.setText("Usuário: " + us.getInstance().getLogin().toString());
        txtEsta.setText("Estacionamento: " + us.getInstance().getEstacionamento().getNome());



        //Cadastro de vaga
        btCadastrarVaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNomeCarro.getText().toString().isEmpty() || edtPlacaCarro.getText().toString().isEmpty() ||
                        edtNomeVaga.getText().toString().isEmpty()) {
                    Toast.makeText(TelaGerenciar.this, "Preencha Todos os Campos!", Toast.LENGTH_SHORT).show();
                } else {


                    VagaDAO vDAO = new VagaDAO(TelaGerenciar.this);
                    UsuarioSingleton us = new UsuarioSingleton();
                    Vaga vaga = new Vaga();


                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                    String formatDate = df.format(c.getTime());


                    Estacionamento esta = us.getInstance().getEstacionamento();
//a

                    if (!vDAO.listarPorNome("Vaga " + edtNomeVaga.getText().toString(), us.getInstance().getEstacionamento().getId())) {
                        vaga.setNome(edtNomeVaga.getText().toString());
                        vaga.setCarro(edtNomeCarro.getText().toString());
                        vaga.setPlaca(edtPlacaCarro.getText().toString());
                        vaga.setDataEntrada(formatDate);
                        vaga.setEstacionamento(esta);
                        vDAO.salvar(vaga);


                        edtNomeCarro.setText(null);
                        edtPlacaCarro.setText(null);
                        edtNomeVaga.setText(null);

                        cardCadastro.setVisibility(View.GONE);
                        atualizaListaVagas();
                        Toast.makeText(TelaGerenciar.this, "Vaga Cadastrada Com Sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(TelaGerenciar.this, "Já Existe Uma Vaga Com Este Nome!", Toast.LENGTH_SHORT).show();
                        edtNomeVaga.setText(null);
                    }


                }
            }
        });


        //Tela de cadastro de vagas
        floatingMensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                cardCadastro.setVisibility(View.VISIBLE);
                atualizaListaVagas();

            }
        });

        atualizaListaVagas();
        lista.setOnItemClickListener(this);


    }


    //Método para dar baixa na vaga
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Vaga vaga = (Vaga) parent.getAdapter().getItem(position);
        StringBuilder builder = new StringBuilder();
        builder.append("A Vaga " + vaga.getNome() + " \n");
        builder.append("Com o Carro " + vaga.getCarro() + "\n");
        builder.append("De Placa " + vaga.getPlaca() + "\n");
        builder.append("Tempo de estadia " + getDateDiff(calcularDaVaga(vaga), calcularDataAtual(), TimeUnit.MINUTES) + " minutos\n");
        builder.append("Valor " +
                calcularValor(getDateDiff(calcularDaVaga(vaga), calcularDataAtual(), TimeUnit.MINUTES), vaga) + "R$");
        if (vaga != null) {
            new AlertDialog.Builder(TelaGerenciar.this)


                    .setTitle("Encerrar Uso Da Vaga")
                    .setMessage(builder.toString())
                    .setPositiveButton("Dar Baixa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            VagaDAO vd = new VagaDAO(TelaGerenciar.this);


                            getDateDiff(calcularDaVaga(vaga), calcularDataAtual(), TimeUnit.MINUTES);
                            vd.deletar(vaga.getId());
                            Toast.makeText(TelaGerenciar.this, "Vaga Finalizada Com Sucesso!", Toast.LENGTH_SHORT).show();

                            atualizaListaVagas();


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






    //Métodos de auxilio


    private void atualizaListaVagas() {
        VagaDAO dao = new VagaDAO(this);
        UsuarioSingleton us = new UsuarioSingleton();

        if (dao.listar(us.getInstance().getEstacionamento().getId()) != null) {
            ArrayAdapter<Vaga> arrayAdapter = new ArrayAdapter<Vaga>(this,
                    android.R.layout.simple_list_item_1, dao.listar(us.getInstance().getEstacionamento().getId()));

            lista.setAdapter(arrayAdapter);
        } else {
            lista.setAdapter(null);
        }

    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }


    public Date calcularDaVaga(Vaga vaga) {


        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataDeEntrada = vaga.getDataEntrada();

        try {
            Date dataDaVaga = df.parse(dataDeEntrada);
            return dataDaVaga;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Date calcularDataAtual() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String dataAtual = df.format(c.getTime());
        try {
            Date dataNow = df.parse(dataAtual);
            return dataNow;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public long calcularValor(long minutos, Vaga vaga) {
        Estacionamento estaVaga = vaga.getEstacionamento();
        long hora = 60;
        long a = 0;

        if (minutos <= estaVaga.getMinutosGratis()) {
            return 0;
        } else if (minutos >= estaVaga.getMinutosGratis() && minutos < estaVaga.getMinutosPago()) {
            a = estaVaga.getPrecoFixo();
            return a;
        } else if (minutos >= estaVaga.getMinutosPago() && minutos < estaVaga.getMinutosPago() + hora) {
            a = estaVaga.getPrecoFixo() + estaVaga.getHoraExtra();
            return a;
        } else if (minutos >= estaVaga.getMinutosPago() && minutos < estaVaga.getMinutosPago() + (hora * 2)) {
            a = estaVaga.getPrecoFixo() + estaVaga.getHoraExtra() + estaVaga.getHoraExtra();
            return a;
        } else {
            return 30;
        }

    }


}