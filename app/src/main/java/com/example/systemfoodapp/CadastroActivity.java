package com.example.systemfoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    AppCompatEditText edit_usuario, edit_senha;
    Button btn_salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edit_usuario = (AppCompatEditText) findViewById(R.id.edit_login);
        edit_senha = (AppCompatEditText) findViewById(R.id.edit_senha);

        btn_salvar = (Button) findViewById(R.id.btn_salvar);

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = edit_usuario.getText().toString();
                String senha = edit_senha.getText().toString();

                if(usuario.equals("")) {
                    Toast.makeText(CadastroActivity.this, "Insira o usuário", Toast.LENGTH_SHORT).show();
                } else if(senha.equals("")) {
                    Toast.makeText(CadastroActivity.this, "Insira a senha", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });
    }
}