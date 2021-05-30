package com.example.systemfoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    String urlWebService = "http://192.168.0.23/systemfood/getUsuarios.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;

    AppCompatEditText edit_login, edit_senha;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        requestQueue = Volley.newRequestQueue(this);

        btn_login = findViewById(R.id.btn_login);
        edit_login = findViewById(R.id.edit_login);
        edit_senha = findViewById(R.id.edit_senha);

        btn_login.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(LoginActivity.this, "Validando seus dados...aguarde.", Toast.LENGTH_SHORT).show();
                    validarLogin();
                }

            }
        });
    }

    private void validarLogin() {

        stringRequest = new StringRequest(Request.Method.POST, urlWebService,
                response -> {
                    Log.v("LogLogin", response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        boolean isErro = jsonObject.getBoolean("erro");

                        if(isErro) {
                            Toast.makeText(getApplicationContext(), "Dados Inválidos", Toast.LENGTH_LONG).show();
                        } else {
                            int tipoUsuario = jsonObject.getInt("tipoUsuario");

                            if(tipoUsuario == 1) {
                                Intent tela = new Intent(this, MainActivity.class);
                                startActivity(tela);
                                finish();
                            } else if(tipoUsuario == 2){
                                Intent tela = new Intent(this, ClienteDescontoActivity.class);
                                startActivity(tela);
                                finish();
                            }
                        }
                    } catch (Exception e) {
                        Log.v("LogLogin", e.getMessage());
                    }
                },
                error -> {
                    String err = (error.getMessage()==null)?"Sem mensagem de erro":error.getMessage();
                    Log.e("LogLogin", err);
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("login", edit_login.getText().toString());
                params.put("senha", edit_senha.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}