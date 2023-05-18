package com.example.parcial4janeth.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcial4janeth.ClienteActivity;
import com.example.parcial4janeth.R;
import com.example.parcial4janeth.models.MdClientesModel;

import java.util.ArrayList;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder>{

    ArrayList<MdClientesModel> lista;
    ArrayList<MdClientesModel> listaO;

    public ClientAdapter(ArrayList<MdClientesModel> li) {
        this.lista = li;
        listaO = new ArrayList<>();
        listaO.addAll(li);
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_client, null, false);
        return new ClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, int position) {
        holder.viewNombre.setText(lista.get(position).getsNombreCliente());
        holder.viewApellido.setText(lista.get(position).getsApellidosCliente());
        holder.viewDireccion.setText(lista.get(position).getsDireccionCliente());
        holder.viewCiudad.setText(lista.get(position).getsCuidadCliente());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ClientViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewDireccion, viewCiudad, viewApellido;

        public ClientViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewApellido = itemView.findViewById(R.id.viewApellido);
            viewDireccion = itemView.findViewById(R.id.viewDireccion);
            viewCiudad = itemView.findViewById(R.id.viewCiudad);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ClienteActivity.class);
                    intent.putExtra("ID", lista.get(getAdapterPosition()).getIdCliente());
                    context.startActivity(intent);
                }
            });
        }
    }
}
