package com.bayron.primerparcial;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaAlimentos extends ListActivity {
    private String[] COMIDA = {"Carnes","Ensaladas","Pastas","Pollo","Hamburguesas"};
    private ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_lista_alimentos););
        adapter = new ArrayAdapter(ListaAlimentos.this,android.R.layout.simple_dropdown_item_1line,COMIDA);
        this.setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String Comida = COMIDA[position].toString();
        Intent resultado = new Intent();
        resultado.putExtra("COMIDA",Comida);
        setResult(FormularioEncuestado.GUARDADOALIMENTOS,resultado);
        finish();
    }
}
