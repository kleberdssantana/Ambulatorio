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

import java.util.Calendar;

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

        nomePaciente = (EditText) findViewById(R.id.nome_pacienteId);
        doenca = (EditText) findViewById(R.id.doenca);
        medicacao = (EditText) findViewById(R.id.medicacao);
        custo = (EditText) findViewById(R.id.custo);
        data = (EditText) findViewById(R.id.data_atendimento);
        data.addTextChangedListener(new MaskWatcher("##/##/####"));

        salvar = (Button) findViewById(R.id.salvar);
        cancelar = (Button) findViewById(R.id.cancelar);

        if(altPaciente != null){
            salvar.setText("Alterar");

            double cust = Double.parseDouble(String.valueOf(altPaciente.getCusto()));

            nomePaciente.setText(altPaciente.getNome());
            doenca.setText(altPaciente.getDoenca());
            medicacao.setText(altPaciente.getMedicacaoUtilizada());
            data.setText(altPaciente.getDataChegada());
            custo.setText(String.valueOf(cust));

            paciente.setId(altPaciente.getId());

        }else{
            salvar.setText("Salvar");
        }

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paciente.setNome(nomePaciente.getText().toString());
                paciente.setDoenca(doenca.getText().toString());
                paciente.setMedicacaoUtilizada(medicacao.getText().toString());
                paciente.setDataChegada(data.getText().toString());
                paciente.setCusto(Double.parseDouble(custo.getText().toString()));

                if(salvar.getText().equals("Salvar")){
                    retornoDB = pacienteDao.salvarPaciente(paciente);
                    if(retornoDB == -1){
                        alert("Erro ao cadastrar");
                    }else{
                        alert("Salvo com sucesso");
                    }
                }else{
                    retornoDB = pacienteDao.atualizarPaciente(paciente);
                    if(retornoDB == -1){
                        alert("Erro ao atualizar");
                    }else{
                        alert("Atualizado com sucesso");
                    }
                }
                finish();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PacienteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void alert(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
