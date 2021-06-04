package com.example.systemfoodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MesasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas);
    }

    public void fazerPedido(View view){
        Intent tela = new Intent(this, PedidosActivity.class);
        startActivity(tela);
    }
}