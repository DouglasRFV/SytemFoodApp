package com.example.systemfoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.systemfoodapp.modelo.Criptografia;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CadastroActivity extends AppCompatActivity {

    String urlWebService = "http://192.168.0.3/systemfood/insertUsuario.php";
    String loginCriptografado;
    String senhaCriptografada;
    String tipoUsuario;
    StringRequest stringRequest;
    RequestQueue requestQueue;

    AppCompatEditText edit_login, edit_senha;
    RadioButton radioAdmin, radioUsuario;
    Button btn_salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        requestQueue = Volley.newRequestQueue(this);

        edit_login = findViewById(R.id.edit_login);
        edit_senha = findViewById(R.id.edit_senha);
        radioAdmin = findViewById(R.id.radioAdmin);
        radioUsuario = findViewById(R.id.radioUsuario);

        btn_salvar = (Button) findViewById(R.id.btn_salvar);

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validado = true;
                if(edit_login.getText().length()==0) {
                    edit_login.setError("Campo login obrigatório");
                    edit_login.requestFocus();
                    validado = false;
                }

                if(edit_senha.getText().length()==0) {
                    edit_senha.setError("Campo senha obrigatório");
                    edit_senha.requestFocus();
                    validado = false;
                }

                if (validado) {
                    criptografar();

                }
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radioAdmin:
                if (checked)
                    tipoUsuario = "1";
                    break;
            case R.id.radioUsuario:
                if (checked)
                    tipoUsuario = "2";
                    break;
        }
    }

    private void criptografar() {
        try {
            Criptografia criptografia = new Criptografia();
            String login = criptografia.encrypt(edit_login.getText().toString());
            String senha = criptografia.encrypt(edit_senha.getText().toString());
            Log.v("Criptografia Login", login);
            Log.v("Criptografia Senha", senha);
            loginCriptografado = login;
            senhaCriptografada = senha;
            validarCadastro();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    private void validarCadastro() {

        stringRequest = new StringRequest(Request.Method.POST, urlWebService,
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
                params.put("login", loginCriptografado);
                params.put("senha", senhaCriptografada);
                params.put("tipoUsuario", tipoUsuario);
                return params;
            }
        };
        edit_login.setText("");
        edit_senha.setText("");
        radioAdmin.setChecked(false);
        radioUsuario.setChecked(false);
        requestQueue.add(stringRequest);
    }
}