package com.example.studentapp;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customAdapter extends RecyclerView.Adapter<customAdapter.AlunoViewHolder>{

    private Context context;
    private ArrayList<Alunos> alunos;

   public customAdapter(Context context, ArrayList<Alunos> alunos){
        this.context = context;
        this.alunos = alunos;
    }

    @NonNull
    @Override
    public AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =  inflater.inflate(R.layout.aluno,parent, false);
        return new AlunoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoViewHolder holder, int position) {
        holder.nome.setText(alunos.get(position).getNome());
        holder.notas.setText(String.valueOf(alunos.get(position).getNotaFinal()));
    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    public class AlunoViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
       TextView nome, notas;
       ImageView img_aluno;


       public AlunoViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nome_aluno);
            notas = itemView.findViewById(R.id.notas);
           itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(0,itemView.getId(),0,"Apagar aluno da lista").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    alunos.remove(getLayoutPosition());
                    notifyDataSetChanged();
                    return false;
                }
            });
        }
    }
}
