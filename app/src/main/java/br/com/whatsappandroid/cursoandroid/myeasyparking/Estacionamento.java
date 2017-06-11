package br.com.whatsappandroid.cursoandroid.myeasyparking;

/**
 * Created by root on 29/05/17.
 */

public class Estacionamento  {
    private int id,minutosGratis,precoFixo,minutosPago,horaExtra
            ;
    private String nome;

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

    public int getMinutosGratis() {
        return minutosGratis;
    }

    public void setMinutosGratis(int minutosGratis) {
        this.minutosGratis = minutosGratis;
    }

    public int getPrecoFixo() {
        return precoFixo;
    }

    public void setPrecoFixo(int precoFixo) {
        this.precoFixo = precoFixo;
    }

    public int getMinutosPago() {
        return minutosPago;
    }

    public void setMinutosPago(int minutosPago) {
        this.minutosPago = minutosPago;
    }

    public int getHoraExtra() {
        return horaExtra;
    }

    public void setHoraExtra(int horaExtra) {
        this.horaExtra = horaExtra;
    }
}
