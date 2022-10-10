package com.example.login3000;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaCadastro extends AppCompatActivity {
    EditText etNome, etEmail, etSenha, etConSenha;
    Button btCadastrar, btLogin;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        etNome = findViewById(R.id.etCadNome);
        etEmail = findViewById(R.id.etCadLogin);
        etSenha = findViewById(R.id.etCadSenha);
        etConSenha = findViewById(R.id.etCadConSenha);
        btCadastrar = findViewById(R.id.btCadastrar);
        btLogin = findViewById(R.id.btCadLogar);
        db = new DBHelper(this);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome, email, senha, conSenha;

                if (!etNome.getText().toString().equals("") && !etEmail.getText().toString().equals("") && !etSenha.getText().toString().equals("") && !etConSenha.getText().toString().equals("")) {
                    nome = etNome.getText().toString();
                    email = etEmail.getText().toString();
                    senha = etSenha.getText().toString();
                    conSenha = etConSenha.getText().toString();

                    if (db.checarEmail(email).equals("OK")) {
                        if (senha.equals(conSenha)) {
                            long res = db.criarUtilizador(email, nome, senha);
                            if (res > 0) {
                                Toast.makeText(TelaCadastro.this, "Usuario criado!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(TelaCadastro.this, TelaLogin.class);
                                startActivity(i);
                            }
                        }else {
                            Toast.makeText(TelaCadastro.this, "As senhas não são iguais!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(TelaCadastro.this, "Este email ja esta cadastrado!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(TelaCadastro.this, "Preenchan todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}