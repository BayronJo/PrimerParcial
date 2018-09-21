package com.bayron.primerparcial;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ListaEncuestado extends ListActivity {
    private ArrayList<Encuestado> lstEncuestados;
    private ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lstEncuestados = new ArrayList<>();
        adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,lstEncuestados);
        setListAdapter(adapter);

    }
}
