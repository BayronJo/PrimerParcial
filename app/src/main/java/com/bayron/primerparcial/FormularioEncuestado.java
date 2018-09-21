package com.bayron.primerparcial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormularioEncuestado extends AppCompatActivity implements View.OnClickListener {
    private Button btnlistaA;
    private Button btnguardar;
    private EditText txtnombres;
    private EditText txtapellidos;
    private  String Comida ="";
    public  static  final int GUARDADOALIMENTOS=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_encuestado);
        this.btnlistaA = findViewById(R.id.btnAlimentos);
        this.txtapellidos = findViewById(R.id.txtapellidos);
        this.txtnombres = findViewById(R.id.txtnombre);
        this.btnguardar = findViewById(R.id.btnGuardar);
        this.btnguardar.setOnClickListener(this);
        this.btnlistaA.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAlimentos:
                Intent alimentos = new Intent(FormularioEncuestado.this,ListaAlimentos.class);
                startActivityForResult(alimentos,GUARDADOALIMENTOS);
                break;
            case R.id.btnGuardar:
                if(txtnombres.getText().toString().isEmpty() && txtapellidos.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"No deje los campos vacios",Toast.LENGTH_SHORT).show();
                }else{
                    Intent resultado = new Intent();
                    resultado.putExtra("NOMBRES",txtnombres.getText().toString());
                    resultado.putExtra("APELLIDOS",txtapellidos.getText().toString());
                    resultado.putExtra("COMIDA",Comida);
                    setResult(MainActivity.GUARDADONUEVO,resultado);
                    finish();
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case GUARDADOALIMENTOS:
                Comida = data.getExtras().getString("COMIDA");
                break;
        }
    }
}
