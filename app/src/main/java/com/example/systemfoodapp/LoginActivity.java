package com.example.systemfoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

public class LoginActivity extends AppCompatActivity {

    AppCompatEditText edit_usuario, edit_senha;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_usuario = (AppCompatEditText) findViewById(R.id.edit_usuario);
        edit_senha = (AppCompatEditText) findViewById(R.id.edit_senha);
        
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Boa amigão!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}