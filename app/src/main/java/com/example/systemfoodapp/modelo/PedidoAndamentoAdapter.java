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

public class PedidoAndamentoAdapter extends ArrayAdapter<PedidoAndamento> {

    private final Context context;
    private final ArrayList<PedidoAndamento> elementos;

    public PedidoAndamentoAdapter(Context context, ArrayList<PedidoAndamento> elementos) {
        super(context, R.layout.linha_pedido_andamento, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_pedido_andamento, parent, false);

        TextView nomeLanche = (TextView) rowView.findViewById(R.id.nome);
        TextView title_quantidade = (TextView) rowView.findViewById(R.id.title_quantidade);
        TextView quantidade = (TextView) rowView.findViewById(R.id.txt_qtde);

        nomeLanche.setText(elementos.get(position).getNome());
        quantidade.setText(elementos.get(position).getQuantidade());
        return rowView;
    }

    @Override
    public int getCount() {
        return elementos.size();
    }

    @Nullable
    @Override
    public PedidoAndamento getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
