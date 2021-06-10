package com.example.systemfoodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import com.example.systemfoodapp.modelo.Cliente;
import com.example.systemfoodapp.modelo.ClienteAdapter;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClubeDescontoActivity extends AppCompatActivity {

    EditText editNome, editCPF, editQuantidade;
    String idCliente;
    ListView listV_dados;
    Cliente clienteSelecionado;
    ArrayAdapter adapter;

    String urlListaClientes = "http://192.168.0.3/systemfood/listaClientes.php";
    String urlInsereCliente = "http://192.168.0.3/systemfood/insertCliente.php";
    String urlAtualizaCliente = "http://192.168.0.3/systemfood/updateCliente.php";
    String urlDeletaCliente = "http://192.168.0.3/systemfood/deletecliente.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clube_desconto);
        editNome = (EditText) findViewById(R.id.editNome);
        editCPF = (EditText) findViewById(R.id.editCpf);
        editQuantidade = (EditText) findViewById(R.id.editQuantidade);
        listV_dados = (ListView) findViewById(R.id.listV_dados);

        SimpleMaskFormatter smf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw = new MaskTextWatcher(editCPF, smf);
        editCPF.addTextChangedListener(mtw); 

        requestQueue = Volley.newRequestQueue(this);

        adapter = new ClienteAdapter(this, listarClientes());
        listV_dados.setAdapter(adapter);

        listV_dados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clienteSelecionado = (Cliente)parent.getItemAtPosition(position);
                idCliente = clienteSelecionado.getId();
                editNome.setText(clienteSelecionado.getNome());
                editCPF.setText(clienteSelecionado.getCpf());
                editQuantidade.setText(clienteSelecionado.getQuantidade());
            }
        });
    }

    private ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        stringRequest = new StringRequest(Request.Method.POST, urlListaClientes,
        new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jsonArray = new JSONArray(response);
                    Log.v("RESPONSE ====>", String.valueOf(jsonArray));

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject objCliente = jsonArray.getJSONObject(i);
                        Cliente c = new Cliente(
                                objCliente.getString("id"),
                                objCliente.getString("nomeCliente"),
                                objCliente.getString("cpf"),
                                objCliente.getString("quantidade")
                        );
                        clientes.add(c);
                        System.out.println(clientes.get(i));
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
        }) {};
        requestQueue.add(stringRequest);
        Log.v("VAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAI", String.valueOf(clientes));
        return clientes;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cliente_desconto, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_novo) {
            boolean validado = true;
            if(editNome.getText().length()==0) {
                editNome.setError("Campo nome obrigatório");
                editNome.requestFocus();
                validado = false;
            }
            if(editCPF.getText().length()==0) {
                editCPF.setError("Campo CPF obrigatório");
                editCPF.requestFocus();
                validado = false;
            }
            if(editQuantidade.getText().length()==0) {
                editQuantidade.setError("Campo CPF obrigatório");
                editQuantidade.requestFocus();
                validado = false;
            }
            if(validado) {
                validarCadastro();
            }


        } else if(id == R.id.menu_atualiza) {
            boolean validado = true;
            if(editNome.getText().length()==0) {
                editNome.setError("Campo nome obrigatório");
                editNome.requestFocus();
                validado = false;
            }
            if(editCPF.getText().length()==0) {
                editCPF.setError("Campo CPF obrigatório");
                editCPF.requestFocus();
                validado = false;
            }
            if(editQuantidade.getText().length()==0) {
                editQuantidade.setError("Campo CPF obrigatório");
                editQuantidade.requestFocus();
                validado = false;
            }
            if(validado) {
                atualizaCliente();
            }
        } else if(id == R.id.menu_deleta) {
            confirmDelete();
        }
        return true;
    }

    private void validarCadastro() {

        String nome = editNome.getText().toString();
        String cpf = editCPF.getText().toString();
        String quantidade = editQuantidade.getText().toString();

        stringRequest = new StringRequest(Request.Method.POST, urlInsereCliente,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.v("RESPONSE =>", response);

                            boolean isErro = jsonObject.getBoolean("erro");

                            if(isErro) {
                                Toast.makeText(getApplicationContext(), "Dados Inválidos!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Cadastrado com Sucesso!", Toast.LENGTH_LONG).show();
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
                params.put("nome", nome);
                params.put("cpf", cpf);
                params.put("quantidade", quantidade);
                return params;
            }
        };
        limparCampos();
        requestQueue.add(stringRequest);
        finish();
        startActivity(getIntent());
    }

    private void atualizaCliente() {

        String nome = editNome.getText().toString();
        String cpf = editCPF.getText().toString();
        String quantidade = editQuantidade.getText().toString();

        stringRequest = new StringRequest(Request.Method.POST, urlAtualizaCliente,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.v("RESPONSE =>", response);

                            boolean isErro = jsonObject.getBoolean("erro");

                            if(isErro) {
                                Toast.makeText(getApplicationContext(), "Erro ao Atualizar!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Atualizado com Sucesso!", Toast.LENGTH_LONG).show();
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
                params.put("id", idCliente);
                params.put("nome", nome);
                params.put("cpf", cpf);
                params.put("quantidade", quantidade);
                return params;
            }
        };
        limparCampos();
        requestQueue.add(stringRequest);
        finish();
        startActivity(getIntent());
    }

    private void deletaCliente() {

        stringRequest = new StringRequest(Request.Method.POST, urlDeletaCliente,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.v("RESPONSE =>", response);

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
                params.put("id", idCliente);
                return params;
            }
        };
        limparCampos();
        requestQueue.add(stringRequest);
        finish();
        startActivity(getIntent());
    }

    private void confirmDelete() {
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setTitle("Excluindo...");
        msgBox.setIcon(android.R.drawable.ic_menu_delete);
        msgBox.setMessage("Deseja excluir este Cliente?");
        msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deletaCliente();
            }
        });
        msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        msgBox.show();
    }

    private void limparCampos() {
        editCPF.setText("");
        editNome.setText("");
        editQuantidade.setText("");
    }

}
