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

public class LancheAdapter extends ArrayAdapter<Lanche> {

    private final Context context;
    private final ArrayList<Lanche> elementos;

    public LancheAdapter(Context context, ArrayList<Lanche> elementos) {
        super(context, R.layout.linha, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha, parent, false);

        TextView nomeEscola = (TextView) rowView.findViewById(R.id.nome);
        TextView endereco = (TextView) rowView.findViewById(R.id.endereco);
        ImageView imagem = (ImageView) rowView.findViewById(R.id.imagem);

        nomeEscola.setText(elementos.get(position).getNome());
        endereco.setText(elementos.get(position).getPreco());
        imagem.setImageResource(elementos.get(position).getImagem());
        return rowView;
    }
}
