package com.ambulatorio.klebersantana.ambulatorio.Models;

/**
 * Created by Kleber Santana on 03/05/2018.
 */

public class Paciente {

    private int id;
    private String nome;
    private String doenca;
    private String medicacaoUtilizada;
    private String dataChegada;
    private double custo;

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

    public String getDoenca() {
        return doenca;
    }

    public void setDoenca(String doenca) {
        this.doenca = doenca;
    }

    public String getMedicacaoUtilizada() {
        return medicacaoUtilizada;
    }

    public void setMedicacaoUtilizada(String medicacaoUtilizada) {
        this.medicacaoUtilizada = medicacaoUtilizada;
    }

    public String getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(String dataChegada) {
        this.dataChegada = dataChegada;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    @Override
    public String toString() {
        return nome.toString();
    }
}
