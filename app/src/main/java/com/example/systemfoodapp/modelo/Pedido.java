package com.example.systemfoodapp.modelo;

import java.sql.Array;

public class Pedido {

    private String nome;
    private String quantidade;
    private int imagem;

    public Pedido(String nome, int imagem, String quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
