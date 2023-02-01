package com.example.studentapp;

public class Alunos {

    private String nome;
    private int id;
    private float notaFinal, nota1, nota2;

    public Alunos(int id, String nome,float nota1, float nota2, float notaFinal) {
        this.id = id;
        this.nome = nome;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.notaFinal = notaFinal;
    }

    public Alunos(){ //como as variaveis vai se iniciar
        id = 0;
        nome = "";
        nota1 = 0;
        nota2 = 0;
        notaFinal = 0;
    }
    //getters e setters


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(float notaFinal) {
        this.notaFinal = notaFinal;
    }

    public float getNota1() {
        return nota1;
    }

    public void setNota1(float nota1) {
        this.nota1 = nota1;
    }

    public float getNota2() {
        return nota2;
    }

    public void setNota2(float nota2) {
        this.nota2 = nota2;
    }
}


