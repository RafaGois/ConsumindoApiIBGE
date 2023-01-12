package com.example.consumindoapiibge.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.consumindoapiibge.R;
import com.example.consumindoapiibge.objetos.Estado;

import java.util.ArrayList;
import java.util.List;

//colocar os valoressaq talvez

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Estado> listaEstados;

    public Adapter(List<Estado> lista) {
        this.listaEstados = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lista,parent,false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Estado estado = listaEstados.get(position);

        holder.idEstado.setText(estado.getId() + "");
        holder.siglaEstado.setText(estado.getSigla());
        holder.nomeEstado.setText(estado.getNome());

        holder.siglaReg.setText(estado.getRegiao().getSigla());
        holder.nomeReg.setText(estado.getRegiao().getNome());

    }

    @Override
    public int getItemCount() {
        return listaEstados.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {

        TextView idEstado;
        TextView siglaEstado;
        TextView nomeEstado;

        TextView siglaReg;
        TextView nomeReg;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            idEstado = itemView.findViewById(R.id.txtId);
            siglaEstado = itemView.findViewById(R.id.txtSigla);
            nomeEstado = itemView.findViewById(R.id.txtNome);

            siglaReg = itemView.findViewById(R.id.txtRegSigla);
            nomeReg = itemView.findViewById(R.id.txtRegNome);

        }
    }

    public void filteredList(ArrayList<Estado> filteredList) {
        listaEstados = filteredList;
        notifyDataSetChanged();
    }
}
