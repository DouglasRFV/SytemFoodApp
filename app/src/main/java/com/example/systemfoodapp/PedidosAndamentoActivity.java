package com.example.systemfoodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.systemfoodapp.modelo.Cliente;
import com.example.systemfoodapp.modelo.PedidoAndamento;
import com.example.systemfoodapp.modelo.PedidoAndamentoAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PedidosAndamentoActivity extends AppCompatActivity {

    String numeroMesa, temDesconto;
    ListView listpedidos_andamento;
    Button mesa1, mesa2, mesa3, mesa4, mesa5, mesa6;
    TextView total, totalSemDesc;
    ArrayAdapter adapter;

    String urlListaPedidos = "http://192.168.0.3/systemfood/listaItensPedido.php";
    String urlDeletaPedido = "http://192.168.0.3/systemfood/deletePedido.php";
    String urlCalculaDesconto = "http://192.168.0.3/systemfood/callProcedureDesconto.php";
    String urlGetValores = "http://192.168.0.3/systemfood/getValoresPedido.php";
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
        total = findViewById(R.id.txtTotal);
        totalSemDesc = findViewById(R.id.txtTotalSemDesc);
        listpedidos_andamento = (ListView) findViewById(R.id.list_pedidos_andamento);

        requestQueue = Volley.newRequestQueue(this);

        mesa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroMesa = "1";
                confirmDesconto();
//                adapter = new PedidoAndamentoAdapter(PedidosAndamentoActivity.this, listarItensPedido());
//                listpedidos_andamento.setAdapter(adapter);
            }
        });
        mesa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroMesa = "2";
                confirmDesconto();
//                adapter = new PedidoAndamentoAdapter(PedidosAndamentoActivity.this, listarItensPedido());
//                listpedidos_andamento.setAdapter(adapter);
            }
        });
        mesa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroMesa = "3";
                confirmDesconto();
//                adapter = new PedidoAndamentoAdapter(PedidosAndamentoActivity.this, listarItensPedido());
//                listpedidos_andamento.setAdapter(adapter);
            }
        });
        mesa4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroMesa = "4";
                confirmDesconto();
//                adapter = new PedidoAndamentoAdapter(PedidosAndamentoActivity.this, listarItensPedido());
//                listpedidos_andamento.setAdapter(adapter);
            }
        });
        mesa5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroMesa = "5";
                confirmDesconto();
//                adapter = new PedidoAndamentoAdapter(PedidosAndamentoActivity.this, listarItensPedido());
//                listpedidos_andamento.setAdapter(adapter);
            }
        });
        mesa6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroMesa = "6";
                confirmDesconto();
//                adapter = new PedidoAndamentoAdapter(PedidosAndamentoActivity.this, listarItensPedido());
//                listpedidos_andamento.setAdapter(adapter);
            }
        });
    }

    private ArrayList<PedidoAndamento> listarItensPedido() {
        ArrayList<PedidoAndamento> itens = new ArrayList<>();

        stringRequest = new StringRequest(Request.Method.POST, urlListaPedidos,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonArray = new JSONArray(response);
//                            Log.v("RESPONSE ====>", String.valueOf(jsonArray));

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject objPedido = jsonArray.getJSONObject(i);
//                                Log.v("testetestestestets ====>", String.valueOf(objPedido));
                                PedidoAndamento p = new PedidoAndamento(
                                        objPedido.getString("id"),
                                        objPedido.getString("lanche"),
                                        objPedido.getString("quantidade")
                                );
                                itens.add(p);
                                adapter.notifyDataSetChanged();
//                                System.out.println(itens.get(i));
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
        calcularDesconto();
        return itens;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pedidos_andamento, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_deleta) {
            confirmDelete();
        }
        return true;
    }

    private void confirmDelete() {
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setTitle("Excluindo...");
        msgBox.setIcon(android.R.drawable.ic_menu_delete);
        msgBox.setMessage("Deseja excluir este Pedido?");
        msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deletaPedido();
            }
        });
        msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        msgBox.show();
    }

    private void deletaPedido() {

        stringRequest = new StringRequest(Request.Method.POST, urlDeletaPedido,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
//                            Log.v("RESPONSE =>", response);

                            boolean isErro = jsonObject.getBoolean("erro");

                            if(isErro) {
                                Toast.makeText(getApplicationContext(), "Erro ao Deletar!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Deletado com Sucesso!", Toast.LENGTH_LONG).show();
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
        finish();
        startActivity(getIntent());
    }

    private void confirmDesconto() {
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setTitle("Desconto...");
        msgBox.setIcon(android.R.drawable.ic_menu_delete);
        msgBox.setMessage("Cliente tem direito a desconto?");
        msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                temDesconto = "1";
                adapter = new PedidoAndamentoAdapter(PedidosAndamentoActivity.this, listarItensPedido());
                listpedidos_andamento.setAdapter(adapter);
            }
        });
        msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                temDesconto = "0";
                adapter = new PedidoAndamentoAdapter(PedidosAndamentoActivity.this, listarItensPedido());
                listpedidos_andamento.setAdapter(adapter);
            }
        });
        msgBox.show();
    }

    private void calcularDesconto() {

        stringRequest = new StringRequest(Request.Method.POST, urlCalculaDesconto,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.v("RESPONSE DESCONTO =>", response);

                            boolean isErro = jsonObject.getBoolean("erro");

                            if(isErro) {
//                                Toast.makeText(getApplicationContext(), "MESA SEM PEDIDOS!", Toast.LENGTH_LONG).show();
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
                params.put("temDesconto", temDesconto);
                return params;
            }
        };
        requestQueue.add(stringRequest);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getValoresPedido();
    }

    private void getValoresPedido() {

        stringRequest = new StringRequest(Request.Method.POST, urlGetValores,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.v("RESPONSE VALORES =>", response);
                            boolean isErro = jsonObject.getBoolean("erro");

                            if(isErro) {
                                Toast.makeText(getApplicationContext(), "SEM VALORES", Toast.LENGTH_LONG).show();
                            } else {
                                total.setText(jsonObject.getString("valorFinalDesc"));
                                totalSemDesc.setText(jsonObject.getString("valorFinal"));

                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "SEM VALORES", Toast.LENGTH_LONG).show();
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