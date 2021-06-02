package com.example.systemfoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

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

public class LoginActivity extends AppCompatActivity {

    String urlWebService = "http://192.168.0.3/systemfood/getUsuarios.php";
    String loginCriptografado;
    String senhaCriptografada;
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
                    criptografar();

            }

            }
        });
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
            validarLogin();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    private void validarLogin() {

        stringRequest = new StringRequest(Request.Method.POST, urlWebService,
               new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       try {
                           JSONObject jsonObject = new JSONObject(response);

                           boolean isErro = jsonObject.getBoolean("erro");

                           if(isErro) {
                               Toast.makeText(getApplicationContext(), "Dados Inválidos", Toast.LENGTH_LONG).show();
                           } else {
                               int tipoUsuario = jsonObject.getInt("tipoUsuario");

                               if(tipoUsuario == 1) {
                                   Intent tela = new Intent(LoginActivity.this, MenuAdminActivity.class);
                                   startActivity(tela);
                                   finish();
                               } else if(tipoUsuario == 2){
                                   Intent tela = new Intent(LoginActivity.this, MenuActivity.class);
                                   startActivity(tela);
                                   finish();
                               }
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
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}