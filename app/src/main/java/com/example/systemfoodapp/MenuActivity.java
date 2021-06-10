package com.example.systemfoodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import java.util.Locale;

public class MenuActivity extends AppCompatActivity {

    String idioma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


    }

    public void idiomaPT(View v) {
        idioma = "pt";
        Locale lang = new Locale(idioma);
        Locale.setDefault(lang);

        Context context = this;
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());

        config.setLocale(lang);
        res.updateConfiguration(config, res.getDisplayMetrics());
        recreate();
    }

    public void idiomaEN(View v) {
        idioma = "en";
        Locale lang = new Locale(idioma);
        Locale.setDefault(lang);

        Context context = this;
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());

        config.setLocale(lang);
        res.updateConfiguration(config, res.getDisplayMetrics());
        recreate();
    }

    public void clubeDesconto (View v) {
        Intent tela = new Intent(this, ClubeDescontoActivity.class);
        startActivity(tela);
    }

    public void fazerPedido (View v) {
        Intent tela = new Intent(this, PedidosActivity.class);
        startActivity(tela);
    }

    public void listarPedido (View v) {
        Intent tela = new Intent(this, PedidosAndamentoActivity.class);
        startActivity(tela);
    }
}