package com.ambulatorio.klebersantana.ambulatorio;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
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
    private SearchView botaoPesquisar;

    private AlertDialog.Builder dialog;

    private int itemListId;
    private boolean flag = true;

    public int getItemListId() {
        return itemListId;
    }

    public void setItemListId(int itemListId) {
        this.itemListId = itemListId;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        novoCadastro = findViewById(R.id.btn_novo_cadastro);
        lista = findViewById(R.id.listViewId);
        botaoPesquisar = findViewById(R.id.btn_pesquisar);

        novoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PacienteActivity.class);
                startActivity(intent);
            }
        });

        botaoPesquisar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                populaListView(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                populaListView(s);
                return false;
            }
        });

        lista.setLongClickable(true);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Exclusão de registro");
                dialog.setMessage("Deseja realmente excluir este paciente");
                dialog.setIcon(android.R.drawable.ic_delete);
                setItemListId(position);
                dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        flag = true;
                    }
                });
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        flag = true;
                        long retorno;
                        paciente = adapter.getItem(getItemListId());
                        retorno = pacienteDao.excluirPaciente(paciente);

                        if (retorno != -1) {
                            alert("Paciente excluído com sucesso");
                        } else {
                            alert("Não foi possível excluír o paciente");
                        }
                        populaListView("");
                    }
                });

                dialog.create();
                dialog.show();
                flag = false;
                return flag;
            }
        });

    }

    public void populaListView(String filtro) {
        pacienteDao = new PacienteDao(MainActivity.this);

        listaPaciente = pacienteDao.selecionarPacientes(filtro);
        pacienteDao.close();

        if (lista != null) {
            adapter = new ArrayAdapter<Paciente>(MainActivity.this, android.R.layout.simple_list_item_1, listaPaciente);
            lista.setAdapter(adapter);

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    paciente = adapter.getItem(position);
                    if(flag) {
                        Intent intent = new Intent(MainActivity.this, PacienteActivity.class);
                        intent.putExtra("paciente-enviado", paciente);
                        startActivity(intent);
                    }
                }
            });
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        populaListView("");
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}