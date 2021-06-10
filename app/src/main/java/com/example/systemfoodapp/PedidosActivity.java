package com.example.systemfoodapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.systemfoodapp.modelo.Lanche;
import com.example.systemfoodapp.modelo.LancheAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PedidosActivity extends AppCompatActivity {

    String urlInsertItem = "http://192.168.0.3/systemfood/insertItemPedido.php";
    String lanche, quantidadeStr, numeroMesa, precoStr;
    Integer quantlanche;
    double valorlanche;
    double preco;
    StringRequest stringRequest;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        requestQueue = Volley.newRequestQueue(this);

        ListView lista = (ListView) findViewById(R.id.listLanches);
        ArrayAdapter adapter = new LancheAdapter(this, adicionarLanches());
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Long selectedItem = (Long) parent.getItemIdAtPosition(position);
                SharedPreferences dadosPedido = getSharedPreferences("dadosMesa", MODE_PRIVATE);
                numeroMesa = dadosPedido.getString("numeroMesa", "0");
                if(selectedItem == 0) {
                    lanche = "1";
                    preco = 40.00;
                    confirmaQuantidade();
                } else if(selectedItem == 1) {
                    lanche = "2";
                    preco = 35.00;
                    confirmaQuantidade();
                } else if(selectedItem == 2) {
                    lanche = "3";
                    preco = 50.00;
                    confirmaQuantidade();
                } else if(selectedItem == 3) {
                    lanche = "4";
                    preco = 25.00;
                    confirmaQuantidade();
                } else if(selectedItem == 4) {
                    lanche = "5";
                    preco = 55.00;
                    confirmaQuantidade();
                } else if(selectedItem == 5) {
                    lanche = "6";
                    preco = 35.00;
                    confirmaQuantidade();
                } else if(selectedItem == 6) {
                    lanche = "7";
                    preco = 35.00;
                    confirmaQuantidade();
                } else if(selectedItem == 7) {
                    lanche = "8";
                    preco = 55.00;
                    confirmaQuantidade();
                } else if(selectedItem == 8) {
                    lanche = "9";
                    preco = 65.00;
                    confirmaQuantidade();
                } else if(selectedItem == 9) {
                    lanche = "10";
                    preco = 50.00;
                    confirmaQuantidade();
                }
            }
        });
    }

    private void confirmaQuantidade() {
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setTitle("Quantidade...");
        msgBox.setMessage("Informe a quantidade desejada?");

        final EditText input = new EditText(PedidosActivity.this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        msgBox.setView(input);


        msgBox.setPositiveButton("Confirma", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                quantidadeStr = input.getText().toString();
                quantlanche = Integer.parseInt(quantidadeStr);
                valorlanche = (preco * quantlanche);
                precoStr = String.valueOf(valorlanche);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                System.out.println("NÚMERO MESA " + numeroMesa);
                System.out.println("LANCHE " + lanche);
                System.out.println("QUANTIDADE " + quantidadeStr);
                System.out.println("PREÇO " + preco);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                salvarItemPedido();
            }
        });
        msgBox.setNegativeButton("Cancela", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        msgBox.show();
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

    private void salvarItemPedido() {

        stringRequest = new StringRequest(Request.Method.POST, urlInsertItem,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            boolean isErro = jsonObject.getBoolean("erro");

                            if(isErro) {
                                Toast.makeText(getApplicationContext(), "Pedido não efetuado", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Pedido efetuado", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Dados Inválidos", Toast.LENGTH_LONG).show();
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
                params.put("idLanche", lanche);
                params.put("quantidade", quantidadeStr);
                params.put("preco", precoStr);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}