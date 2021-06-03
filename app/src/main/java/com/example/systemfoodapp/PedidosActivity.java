package com.example.systemfoodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.systemfoodapp.modelo.Lanche;
import com.example.systemfoodapp.modelo.LancheAdapter;

import java.util.ArrayList;

public class PedidosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        ListView lista = (ListView) findViewById(R.id.listLanches);
        ArrayAdapter adapter = new LancheAdapter(this, adicionarLanches());
        lista.setAdapter(adapter);
    }

    private ArrayList<Lanche> adicionarLanches() {
        ArrayList<Lanche> lanches = new ArrayList<Lanche>();
        Lanche e = new Lanche("Lanche 01", R.drawable.lanche1, "R$40,00");
                lanches.add(e);
        e = new Lanche("Lanche 02", R.drawable.lanche2, "R$35,00");
        lanches.add(e);
        e = new Lanche("Lanche 03", R.drawable.lanche3, "R$50,00");
        lanches.add(e);
        e = new Lanche("Lanche 04", R.drawable.lanche4, "R$25,00");
        lanches.add(e);
        e = new Lanche("Lanche 05", R.drawable.lanche5, "R$55,00");
        lanches.add(e);
        e = new Lanche("Lanche 06", R.drawable.lanche6, "R$35,00");
        lanches.add(e);
        e = new Lanche("Lanche 07", R.drawable.lanche7, "R$35,00");
        lanches.add(e);
        e = new Lanche("Lanche 08", R.drawable.lanche8, "R$55,00");
        lanches.add(e);
        e = new Lanche("Lanche 09", R.drawable.lanche9, "R$65,00");
        lanches.add(e);
        e = new Lanche("Lanche 10", R.drawable.lanche10, "R$50,00");
        lanches.add(e);
        return lanches;
    }
}