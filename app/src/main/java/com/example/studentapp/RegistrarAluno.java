package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
        Alunos aluno = new Alunos(id,name,n1,n2,media);

        if (n1 < 0 || n1 > 20 || n2 < 0 || n2 > 20 ){
            Toast.makeText(this, "Valor da nota não é válido", Toast.LENGTH_SHORT).show();
        }
        else if( id == 0){
            Toast.makeText(this, "Id inválido", Toast.LENGTH_SHORT).show();
        }

        else {
            //para adicionar os alunos a base de dados
            dbHelper.addAluno(aluno); //para adicioanar os alunos na base
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }

    public void cancelar(View view) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                       Intent intent = new Intent(RegistrarAluno.this,MainActivity.class);
                       startActivity(intent);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Tem certeza que deseja cancelar?").setPositiveButton("Sim", dialogClickListener)
                .setNegativeButton("Não", dialogClickListener).show();
    }
}