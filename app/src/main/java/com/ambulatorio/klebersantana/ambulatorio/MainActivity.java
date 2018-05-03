package com.ambulatorio.klebersantana.ambulatorio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private Button novoCadastro;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        novoCadastro = findViewById(R.id.btn_novo_cadastro);
        lista = findViewById(R.id.listViewId);

        novoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PacienteActivity.class);
                startActivity(intent);
            }
        });
    }
}
