package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Exame extends AppCompatActivity {


    private RecyclerView recycleView;
    private DataBaseHelper db;
    private ExameCustomAdapater customAdapter;
    private ArrayList<Alunos> alunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exame);

        db = new DataBaseHelper(this);
        alunos  = db.getTodosAlunosExame();
        recycleView = findViewById(R.id.recycle_view_aprovado);

        customAdapter = new ExameCustomAdapater(this,alunos);
        recycleView.setAdapter(customAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
    }
}