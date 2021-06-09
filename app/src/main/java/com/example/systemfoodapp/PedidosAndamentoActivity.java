package com.example.systemfoodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.systemfoodapp.modelo.PedidoAndamento;
import com.example.systemfoodapp.modelo.PedidoAndamentoAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PedidosAndamentoActivity extends AppCompatActivity {

    String numeroMesa;
    ListView listpedidos_andamento;
    Button mesa1, mesa2, mesa3, mesa4, mesa5, mesa6;
    ArrayAdapter adapter;

    String urlListaPedidos = "http://192.168.0.3/systemfood/listaItensPedido.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos_andamento);

        mesa1 = findViewById(R.id.btn_mesa1);
        mesa2 = findViewById(R.id.btn_mesa2);
        mesa3 = findViewById(R.id.btn_mesa3);
        mesa4 = findViewById(R.id.btn_mesa4);
        mesa5 = findViewById(R.id.btn_mesa5);
        mesa6 = findViewById(R.id.btn_mesa6);
        listpedidos_andamento = (ListView) findViewById(R.id.list_pedidos_andamento);

        requestQueue = Volley.newRequestQueue(this);

        mesa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroMesa = "1";
                adapter = new PedidoAndamentoAdapter(PedidosAndamentoActivity.this, listarItensPedido());
                listpedidos_andamento.setAdapter(adapter);
            }
        });
        mesa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroMesa = "2";
                adapter = new PedidoAndamentoAdapter(PedidosAndamentoActivity.this, listarItensPedido());
                listpedidos_andamento.setAdapter(adapter);
            }
        });
        mesa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroMesa = "3";
                adapter = new PedidoAndamentoAdapter(PedidosAndamentoActivity.this, listarItensPedido());
                listpedidos_andamento.setAdapter(adapter);
            }
        });
        mesa4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroMesa = "4";
                adapter = new PedidoAndamentoAdapter(PedidosAndamentoActivity.this, listarItensPedido());
                listpedidos_andamento.setAdapter(adapter);
            }
        });
        mesa5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroMesa = "5";
                adapter = new PedidoAndamentoAdapter(PedidosAndamentoActivity.this, listarItensPedido());
                listpedidos_andamento.setAdapter(adapter);
            }
        });
        mesa6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroMesa = "6";
                adapter = new PedidoAndamentoAdapter(PedidosAndamentoActivity.this, listarItensPedido());
                listpedidos_andamento.setAdapter(adapter);
            }
        });
    }

    private ArrayList<PedidoAndamento> listarItensPedido() {
        ArrayList<PedidoAndamento> itens = new ArrayList<PedidoAndamento>();

        stringRequest = new StringRequest(Request.Method.POST, urlListaPedidos,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonArray = new JSONArray(response);
                            Log.v("RESPONSE ====>", String.valueOf(jsonArray));

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject objPedido = jsonArray.getJSONObject(i);
                                Log.v("testetestestestets ====>", String.valueOf(objPedido));
                                PedidoAndamento p = new PedidoAndamento(
                                        objPedido.getString("id"),
                                        objPedido.getString("lanche"),
                                        objPedido.getString("quantidade")
                                );
                                itens.add(p);
                                System.out.println(itens.get(i));
                            }
                        } catch (Exception e) {
                            Log.v("LogLoginErro", e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String err = (error.getMessage()==null)?"Sem mensagem de erro":error.getMessage();
                        Log.e("LogLogin", err);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("idMesa", numeroMesa);
                return params;
            }
        };
        requestQueue.add(stringRequest);
        Log.v("VAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAI", String.valueOf(itens));
        return itens;
    }

//        private ArrayList<PedidoAndamento> listarItensPedido() {
//        ArrayList<PedidoAndamento> lanches = new ArrayList<PedidoAndamento>();
//        PedidoAndamento e = new PedidoAndamento("1", "Lanche 01", "R$40,00");
//        lanches.add(e);
//        e = new PedidoAndamento("2", "Lanche 02", "R$35,00");
//        lanches.add(e);
//        e = new PedidoAndamento("3", "Lanche 03", "R$50,00");
//        lanches.add(e);
//        e = new PedidoAndamento("4", "Lanche 04", "R$25,00");
//        lanches.add(e);
//        e = new PedidoAndamento("5", "Lanche 05", "R$55,00");
//        lanches.add(e);
//        e = new PedidoAndamento("6", "Lanche 06", "R$35,00");
//        lanches.add(e);
//        e = new PedidoAndamento("7", "Lanche 07", "R$35,00");
//        lanches.add(e);
//        e = new PedidoAndamento("8", "Lanche 08", "R$55,00");
//        lanches.add(e);
//        e = new PedidoAndamento("9", "Lanche 09", "R$65,00");
//        lanches.add(e);
//        e = new PedidoAndamento("10", "Lanche 10", "R$50,00");
//        lanches.add(e);
//        return lanches;
//    }
}