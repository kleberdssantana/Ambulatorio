package com.ambulatorio.klebersantana.ambulatorio.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ambulatorio.klebersantana.ambulatorio.Models.Paciente;

import java.util.ArrayList;

/**
 * Created by Kleber Santana on 03/05/2018.
 */

public class PacienteDao extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "Ambulatorio.db";
    private static final int VERSION = 1;
    private static final String TABELA = "paciente";
    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String DOENCA = "doenca";
    private static final String MEDICACAO = "medicacao_utilizada";
    private static final String DATA = "data_chegada";
    private static final String CUSTO = "custo";

    public PacienteDao(Context context) {
        super(context, NOME_BANCO, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA + " ( " +
                " " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " " + NOME + " TEXT, " +
                " " + DOENCA + " TEXT, " +
                " " + MEDICACAO + " TEXT, " +
                " " + DATA + " DATE, " +
                " " + CUSTO + " FLOAT );";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        db.execSQL(sql);
        onCreate(db);
    }

    public long salvarPaciente(Paciente paciente) {
        ContentValues values = new ContentValues();
        long retornoDB;

        values.put(NOME, paciente.getNome());
        values.put(DOENCA, paciente.getDoenca());
        values.put(MEDICACAO, paciente.getMedicacaoUtilizada());
        values.put(DATA, paciente.getDataChegada());
        values.put(CUSTO, paciente.getCusto());

        retornoDB = getWritableDatabase().insert(TABELA, null, values);

        return retornoDB;
    }

    public long atualizarPaciente(Paciente paciente) {
        ContentValues values = new ContentValues();
        long retornoDB;

        values.put(NOME, paciente.getNome());
        values.put(DOENCA, paciente.getDoenca());
        values.put(MEDICACAO, paciente.getMedicacaoUtilizada());
        values.put(DATA, paciente.getDataChegada());
//        values.put(CUSTO, paciente.getCusto());

        String[] args = {String.valueOf(paciente.getId())};
        retornoDB = getWritableDatabase().update(TABELA, values, "id=?", args);

        return retornoDB;
    }

    public long excluirPaciente(Paciente paciente){
        long retornoDB;
        String[] args = {String.valueOf(paciente.getId())};
        retornoDB = getWritableDatabase().delete(TABELA, ID + "= ?", args);
        return retornoDB;
    }

    public ArrayList<Paciente> selecionarPacientes(String filtro) {
        String[] colunas = {ID, NOME, DOENCA, MEDICACAO, DATA, CUSTO};
        String consulta = null;
        if(!filtro.isEmpty()){
            consulta = "nome LIKE '%"+filtro+"%'";
        }


        Cursor cursor = getReadableDatabase().query(TABELA, colunas, consulta, null, null, null, ID + " DESC", null);

        ArrayList<Paciente> lista = new ArrayList<Paciente>();

        while (cursor.moveToNext()) {
            Paciente p = new Paciente();

            p.setId(cursor.getInt(0));
            p.setNome(cursor.getString(1));
            p.setDoenca(cursor.getString(2));
            p.setMedicacaoUtilizada(cursor.getString(3));
            p.setDataChegada(cursor.getString(4));
            p.setCusto(cursor.getDouble(5));

            lista.add(p);
        }

        return lista;

    }
}
