package com.example.systemfoodapp.modelo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.systemfoodapp.R;

import java.util.ArrayList;

public class PedidoAdapter extends ArrayAdapter<Pedido> {

    private final Context context;
    private final ArrayList<Pedido> elementos;

    public PedidoAdapter(Context context, ArrayList<Pedido> elementos) {
        super(context, R.layout.linha_pedido, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_pedido, parent, false);

        TextView nomeLanche = (TextView) rowView.findViewById(R.id.nome);
        TextView quantidade = (TextView) rowView.findViewById(R.id.txt_qtde);
        ImageView imagem = (ImageView) rowView.findViewById(R.id.imagem);

        nomeLanche.setText(elementos.get(position).getNome());
        quantidade.setText(elementos.get(position).getQuantidade());
        imagem.setImageResource(elementos.get(position).getImagem());
        return rowView;
    }

    @Override
    public int getCount() {
        return elementos.size();
    }

    @Nullable
    @Override
    public Pedido getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
