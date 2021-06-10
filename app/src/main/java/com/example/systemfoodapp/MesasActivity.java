package com.example.systemfoodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MesasActivity extends AppCompatActivity {

    String numeroMesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas);
    }

    public void fazerPedido(){
        Intent tela = new Intent(this, PedidosActivity.class);
        startActivity(tela);
    }

    public void mesaUm(View view) {
        SharedPreferences.Editor dadosPedido = getSharedPreferences("dadosMesa", MODE_PRIVATE).edit();
        dadosPedido.putString("numeroMesa", "1");
        dadosPedido.apply();
        fazerPedido();
    }

    public void mesaDois(View view) {
        SharedPreferences.Editor dadosPedido = getSharedPreferences("dadosMesa", MODE_PRIVATE).edit();
        dadosPedido.putString("numeroMesa", "2");
        dadosPedido.apply();
        fazerPedido();
    }

    public void mesaTres(View view) {
        SharedPreferences.Editor dadosPedido = getSharedPreferences("dadosMesa", MODE_PRIVATE).edit();
        dadosPedido.putString("numeroMesa", "3");
        dadosPedido.apply();
        fazerPedido();
    }

    public void mesaQuatro(View view) {
        SharedPreferences.Editor dadosPedido = getSharedPreferences("dadosMesa", MODE_PRIVATE).edit();
        dadosPedido.putString("numeroMesa", "4");
        dadosPedido.apply();
        fazerPedido();
    }

    public void mesaCinco(View view) {
        SharedPreferences.Editor dadosPedido = getSharedPreferences("dadosMesa", MODE_PRIVATE).edit();
        dadosPedido.putString("numeroMesa", "5");
        dadosPedido.apply();
        fazerPedido();
    }

    public void mesaSeis(View view) {
        SharedPreferences.Editor dadosPedido = getSharedPreferences("dadosMesa", MODE_PRIVATE).edit();
        dadosPedido.putString("numeroMesa", "6");
        dadosPedido.apply();
        fazerPedido();
    }
}