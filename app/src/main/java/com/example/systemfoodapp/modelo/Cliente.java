package com.example.systemfoodapp.modelo;

public class Cliente {

    private String id;
    private String nome;
    private String cpf;
    private String quantidade;

    public Cliente(String id, String nome, String cpf, String quantidade) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.quantidade = quantidade;
    }

    public Cliente() {
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getId() {
        return id;
    }

    public void setUid(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return nome;
    }
}
