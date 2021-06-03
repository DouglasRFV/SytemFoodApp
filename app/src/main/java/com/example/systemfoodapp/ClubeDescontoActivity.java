package com.example.systemfoodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.systemfoodapp.modelo.Cliente;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClubeDescontoActivity extends AppCompatActivity {

    EditText editNome, editCPF, editQuantidade;
    ListView listV_dados;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private List<Cliente> listCliente =  new ArrayList<Cliente>();
    private ArrayAdapter<Cliente> arrayAdapterCliente;

    Cliente clienteSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clube_desconto);
        editNome = (EditText)findViewById(R.id.editNome);
        editCPF = (EditText)findViewById(R.id.editCpf);
        editQuantidade = (EditText)findViewById(R.id.editQuantidade);
        listV_dados = (ListView)findViewById(R.id.listV_dados);

        inicializarFirebase();
        eventoDatabase();

        listV_dados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clienteSelecionado = (Cliente)parent.getItemAtPosition(position);
                editNome.setText(clienteSelecionado.getNome());
                editCPF.setText(clienteSelecionado.getCpf());
                editQuantidade.setText(clienteSelecionado.getQuantidade());
            }
        });
    }

    private void eventoDatabase() {
        databaseReference.child("Cliente").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listCliente.clear();
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()) {
                    Cliente c = objSnapshot.getValue(Cliente.class);
                    listCliente.add(c);
                }
                arrayAdapterCliente = new ArrayAdapter<Cliente>(ClubeDescontoActivity.this,
                        android.R.layout.simple_list_item_1, listCliente);

                listV_dados.setAdapter(arrayAdapterCliente);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {

        FirebaseApp.initializeApp(ClubeDescontoActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cliente_desconto, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_novo) {
            Cliente c = new Cliente();
            c.setUid(UUID.randomUUID().toString());
            c.setNome(editNome.getText().toString());
            c.setCpf(editCPF.getText().toString());
            c.setQuantidade(editQuantidade.getText().toString());
            databaseReference.child("Cliente").child(c.getUid()).setValue(c);
            limparCampos();
        } else if(id == R.id.menu_atualiza) {
            Cliente c = new Cliente();
            c.setUid(clienteSelecionado.getUid());
            c.setNome(editNome.getText().toString().trim());
            c.setQuantidade(editQuantidade.getText().toString().trim());
            c.setCpf(editCPF.getText().toString().trim());
            databaseReference.child("Cliente").child(c.getUid()).setValue(c);
            limparCampos();
        } else if(id == R.id.menu_deleta) {
            Cliente c = new Cliente();
            c.setUid(clienteSelecionado.getUid());
            databaseReference.child("Cliente").child(c.getUid()).removeValue();
            limparCampos();
        }
        return true;
    }

    private void limparCampos() {
        editCPF.setText("");
        editNome.setText("");
        editQuantidade.setText("");
    }
}