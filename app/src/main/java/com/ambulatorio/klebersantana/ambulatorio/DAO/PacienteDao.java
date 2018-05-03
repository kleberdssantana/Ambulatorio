package com.ambulatorio.klebersantana.ambulatorio.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ambulatorio.klebersantana.ambulatorio.Models.Paciente;

/**
 * Created by Kleber Santana on 03/05/2018.
 */

public class PacienteDao extends SQLiteOpenHelper{

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
        String sql = "CREATE TABLE IF NOT EXISTS "+ TABELA +" ( " +
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
        String sql = "DROP TABLE IF EXISTS "+ TABELA;
        db.execSQL(sql);
        onCreate(db);
    }

    public long salvarPaciente(Paciente paciente){
        ContentValues values = new ContentValues();
        long retornoDB;

        values.put(NOME, paciente.getNome());
        values.put(DOENCA, paciente.getDoenca());
        values.put(MEDICACAO, paciente.getMedicacaoUtilizada());
        values.put(DATA, paciente.getDataChegada());
//        values.put(CUSTO, paciente.getCusto());

        retornoDB = getWritableDatabase().insert(TABELA, null, values);

        return retornoDB;
    }
}
