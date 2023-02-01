package com.example.studentapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    private  Context context;
    private  static  final String DATABASE_NAME = "Alunos.db";
    private  static  final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "alunos"; //nome da tabela
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ID_ALUNO = "id_aluno";
    private static final String COLUMN_NAME = "nome";
    private static final String COLUMN_GRADE1 = "nota1";
    private static final String COLUMN_GRADE2 = "nota2";
    private static final String COLUMN_GRADE = "notaFinal";


    //construtor
    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    //metodos implementados
    @Override
    public void onCreate(SQLiteDatabase db) { //criar uma query sql,para criar as tableas e nome da base de dados (esquema da db)

        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_GRADE1 + " INTEGER, " +   //SE DE ERRO ALTERAR PARA ALEX
                        COLUMN_GRADE2 + " INTEGER, " +
                        COLUMN_GRADE + " INTEGER) ;" ;
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,  int i, int i1) { //para atualizar a base de dados
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db); //para cada vez que atualizarmos a tabela para chamar o on create

    }

    public void addAluno(Alunos alunos) {
        SQLiteDatabase db = this.getWritableDatabase(); //se referere a classe sql helper,getWritableDatabase para que possamos escrever na nossas tabelas
        ContentValues cv = new ContentValues(); //ContentValues vamos guardar todos os nossos dados da nossa aplicacao e passar para as tabelas do nosso db
        //parametros
        cv.put(COLUMN_NAME, alunos.getNome());
        cv.put(COLUMN_GRADE1, alunos.getNota1());
        cv.put(COLUMN_GRADE2, alunos.getNota2());
        cv.put(COLUMN_GRADE, alunos.getNotaFinal());

        //para inserir os dados nas tabelas da minha db
        long result = db.insert(TABLE_NAME,null,cv);

        //verificar se os dados estao ou nao indo para as tabelas
        if (result ==  -1) {
            Toast.makeText(context, "Falhou", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Os dados foram adicionados com sucesso", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData(){ //para ler os dados da nossa base de dados
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        }

        public ArrayList<Alunos> getTodosAlunosAprovados() {
            ArrayList<Alunos> alunos = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase(); // getReadableDatabase para ler os dados
            //cursor vai contenter todas os dados da nossas tabelas quando retornarmos o metodo getReadableDatabase
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE notaFinal >= 12.0", null); //ele vai selecionar no tabela todos os alunos que a nota for maior ou igual 12
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String nome = cursor.getString(2);
                    float nota1 = cursor.getFloat(3);
                    float nota2 = cursor.getFloat(4);
                    float media = cursor.getFloat(5);
                    alunos.add(new Alunos(id, nome, nota1, nota2, media));
                } while (cursor.moveToNext());
            }
            cursor.close();
            return alunos;
        }

        //para o alunos que vao para exame

    public ArrayList<Alunos> getTodosAlunosExame() {
        ArrayList<Alunos> alunos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE notaFinal  < 12.0", null); //ele vai selecionar no tabela todos os alunos que a nota for menor do que 12
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nome = cursor.getString(2);
                float nota1 = cursor.getFloat(3);
                float nota2 = cursor.getFloat(4);
                float media = cursor.getFloat(5);
                alunos.add(new Alunos(id, nome, nota1, nota2, media));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return alunos;

    }

        //para eliminar um aluno
//        public void deleteStudent(Alunos aluno) {
//            SQLiteDatabase db = this.getWritableDatabase(); //usa a classe SQLiteDatabase para abrir a base de dados para escrita
//            db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(student.getId())});
//            db.close();
//        }
    }


