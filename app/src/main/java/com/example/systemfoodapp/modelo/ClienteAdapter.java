package com.example.systemfoodapp.modelo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.systemfoodapp.R;

import java.util.ArrayList;
import java.util.List;

public class ClienteAdapter extends ArrayAdapter<Cliente> {

    private final Context context;
    private ArrayList<Cliente> elementos;

    public ClienteAdapter(Context context, ArrayList<Cliente> elementos) {
        super(context, R.layout.linha_cliente, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_cliente, parent, false);

        TextView nomeCliente = (TextView) rowView.findViewById(R.id.nome);
        TextView txt_cpf = (TextView) rowView.findViewById(R.id.txt_cpf);
        TextView cpf = (TextView) rowView.findViewById(R.id.cpf);
        TextView txt_qtde = (TextView) rowView.findViewById(R.id.txt_qtde);
        TextView quantidade = (TextView) rowView.findViewById(R.id.qtde);


        nomeCliente.setText(elementos.get(position).getNome());
        cpf.setText(elementos.get(position).getCpf());
        quantidade.setText(elementos.get(position).getQuantidade());
        return rowView;
    }
}
