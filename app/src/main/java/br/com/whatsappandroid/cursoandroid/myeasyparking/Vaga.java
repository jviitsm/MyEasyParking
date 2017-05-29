package br.com.whatsappandroid.cursoandroid.myeasyparking;

import java.util.Date;

/**
 * Created by root on 29/05/17.
 */

public class Vaga {
    private int id;
    private String nome,carro,placa;
    private Date dataEntrada,DataSaida;
    private Estacionamento estacionamento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCarro() {
        return carro;
    }

    public void setCarro(String carro) {
        this.carro = carro;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return DataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        DataSaida = dataSaida;
    }

    public Estacionamento getEstacionamento() {
        return estacionamento;
    }

    public void setEstacionamento(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
    }
}
