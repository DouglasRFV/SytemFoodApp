package com.example.systemfoodapp.modelo;

public class Lanche {
    private String nome;
    private String preco;
    private int imagem;

    public Lanche(String nome, int imagem, String preco) {
        this.nome = nome;
        this.preco = preco;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
