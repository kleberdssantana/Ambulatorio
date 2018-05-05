package com.ambulatorio.klebersantana.ambulatorio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ambulatorio.klebersantana.ambulatorio.DAO.PacienteDao;
import com.ambulatorio.klebersantana.ambulatorio.Models.Paciente;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button novoCadastro;
    private ListView lista;
    Paciente paciente;
    PacienteDao pacienteDao;
    ArrayList<Paciente> listaPaciente;
    ArrayAdapter<Paciente> adapter;


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

//        lista.setLongClickable(true);
//        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
//
//                deletaPaciente();
//                return true;
//            }
//        });


    }

    public void populaListView(){
        pacienteDao = new PacienteDao(MainActivity.this);

        listaPaciente = pacienteDao.selecionarPacientes();
        pacienteDao.close();

        if(lista != null){
            adapter = new ArrayAdapter<Paciente>(MainActivity.this, android.R.layout.simple_list_item_1, listaPaciente);
            lista.setAdapter(adapter);
        }

    }



    @Override
    protected void onResume() {
        super.onResume();
        populaListView();
    }
}
