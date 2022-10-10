package com.example.login3000;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TelaLogin extends AppCompatActivity {
    DBHelper db;
    Button btLogar;
    EditText etLogin, etSenha;
    TextView txtCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        btLogar = findViewById(R.id.btLogar);
        etLogin = findViewById(R.id.etLogin);
        etSenha = findViewById(R.id.etSenha);
        txtCadastrar = findViewById(R.id.txtCadastrar);
        db = new DBHelper(this);

        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login, senha, res;

                if(!etLogin.getText().toString().equals("") && !etSenha.getText().toString().equals("")) {
                    login = etLogin.getText().toString();
                    senha = etSenha.getText().toString();

                    res = db.validarLogin(login, senha);

                    if (res.equals("OK")){
                        db.setEmail(login);
                        Intent i = new Intent(TelaLogin.this, TelaPrincipal.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(TelaLogin.this, "O email ou a senha não coincidem!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        txtCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TelaLogin.this, TelaCadastro.class);
                startActivity(i);
            }
        });
    }
}