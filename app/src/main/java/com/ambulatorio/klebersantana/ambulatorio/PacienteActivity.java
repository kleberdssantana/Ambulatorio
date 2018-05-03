package com.ambulatorio.klebersantana.ambulatorio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ambulatorio.klebersantana.ambulatorio.DAO.PacienteDao;
import com.ambulatorio.klebersantana.ambulatorio.Models.Paciente;

public class PacienteActivity extends AppCompatActivity {

    EditText nomePaciente, doenca, medicacao, custo, data;
    Button salvar, cancelar;
    Paciente paciente, altPaciente;
    PacienteDao pacienteDao;
    long retornoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);

        Intent i = getIntent();
        altPaciente = (Paciente) i.getSerializableExtra("paciente-enviado");
        paciente = new Paciente();
        pacienteDao = new PacienteDao(PacienteActivity.this);

        nomePaciente = findViewById(R.id.nome_pacienteId);
        doenca = findViewById(R.id.doenca);
        medicacao = findViewById(R.id.medicacao);
        custo = (EditText) findViewById(R.id.custo);
        data = findViewById(R.id.data_atendimento);
        salvar = findViewById(R.id.salvar);

        if(altPaciente != null){
            salvar.setText("Alterar");
        }else{
            salvar.setText("Salvar");
        }

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paciente.setNome(nomePaciente.toString());
                paciente.setDoenca(doenca.toString());
                paciente.setMedicacaoUtilizada(medicacao.toString());
                paciente.setDataChegada(data.toString());
//                paciente.setCusto(Double.parseDouble(custo));

                if(salvar.getText().equals("Salvar")){
                    retornoDB = pacienteDao.salvarPaciente(paciente);
                    if(retornoDB == -1){
                        alert("Erro ao cadastrar");
                    }else{
                        alert("Salvo com sucesso");
                    }
                }else{

                }

                finish();
            }
        });


    }

    private void alert(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
