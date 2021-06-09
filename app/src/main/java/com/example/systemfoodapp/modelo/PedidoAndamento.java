package com.example.systemfoodapp.modelo;

public class PedidoAndamento {

    private String id;
    private String nome;
    private String quantidade;

    public PedidoAndamento(String id, String nome,  String quantidade) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public PedidoAndamento() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return nome;
    }
}
