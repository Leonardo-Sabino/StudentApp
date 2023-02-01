package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarAluno extends AppCompatActivity {


    EditText nome, id_aluno, nota1,nota2;
    Button registrar, cancelar;
    DataBaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_aluno);

        nome = findViewById(R.id.nome_registar);
        id_aluno = findViewById(R.id.id_aluno);
        nota1 = findViewById(R.id.nota1);
        nota2 = findViewById(R.id.nota2);
        //butoes
        registrar = findViewById(R.id.butao_registar2);
        cancelar  = findViewById(R.id.butao_cancelar);

        dbHelper = new DataBaseHelper(RegistrarAluno.this);//chamando o meu DataBaseHelper com o paramentro dentro

    }

    public void registrar(View view) {

        int id = Integer.parseInt(id_aluno.getText().toString());
        String name = nome.getText().toString();
        float n1 = Float.parseFloat(nota1.getText().toString());
        float n2 = Float.parseFloat(nota2.getText().toString());
        float media = (n1 + n2) / 2;

        //para adicionar os alunos a base de dados
        Alunos aluno = new Alunos(id,name,n1,n2,media);
        dbHelper.addAluno(aluno); //para adicioanar os alunos na base

        //Toast.makeText(this, "Aluno registrado com sucesso!", Toast.LENGTH_SHORT).show();
    }

    public void cancelar(View view) {
        finish(); //faz retornar a activity
    }
}