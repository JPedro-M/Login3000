package com.example.login3000;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TelaPrincipal extends AppCompatActivity {
    TextView txtMensagem;
    Button btSair;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        txtMensagem = findViewById(R.id.txtMensagemLogado);
        btSair = findViewById(R.id.btDeslogar);
        db = new DBHelper(this);

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TelaPrincipal.this, TelaLogin.class);
                startActivity(i);
            }
        });
    }

}