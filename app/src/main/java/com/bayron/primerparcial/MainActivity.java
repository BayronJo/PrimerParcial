package com.bayron.primerparcial;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnnuevo;
    private Button btnlista;
    private  Button btnprocesar;
    private static int ContProgreso=0;
    private Handler manejarProcesos;
    public  static  final int GUARDADONUEVO =1;
    public  static  final int GUARDADOLISTA =2;
    private ArrayList<Encuestado> lstencuestados;
    private ProgressDialog carga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnnuevo = findViewById(R.id.btnregistrar);
        this.btnlista = findViewById(R.id.btnlista);
        this.btnprocesar = findViewById(R.id.btnprocesar);
        manejarProcesos = new Handler();
        carga = new ProgressDialog(this);
        lstencuestados = new ArrayList<>();
        this.btnnuevo.setOnClickListener(this);
        this.btnlista.setOnClickListener(this);
        this.btnprocesar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnregistrar:
                Intent nuevo = new Intent(MainActivity.this,FormularioEncuestado.class);
                startActivityForResult(nuevo,GUARDADONUEVO);
                break;
            case R.id.btnlista:
                Intent lista = new Intent(MainActivity.this,ListaEncuestado.class);
                startActivity(lista);
                break;
            case R.id.btnprocesar:
                if(lstencuestados.isEmpty()){
                    Toast.makeText(getApplicationContext(), "No hay Datos Para Procesar", Toast.LENGTH_SHORT).show();
                }else {
                    new Thread(new HiloSecundario()).start();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case GUARDADONUEVO:
                if(data==null)return;
                Encuestado encuestado = new Encuestado(data.getExtras().getString("NOMBRES"),data.getExtras().getString("APELLIDOS"),data.getExtras().getString("COMIDA"));
                lstencuestados.add(encuestado);
                break;
        }
    }

    final class  HiloSecundario implements Runnable {

        //Ejecucion de la tarea Secundaria
        @Override
        public void run() {
            //simular el avance del progressbar
            while (ContProgreso < 100) {
                MetodoEspera();
                //Establecemos un manejador para la parte visul
                manejarProcesos.post(new Runnable() {
                    @Override
                    public void run() {
                        carga.setMessage("Progeso : " + ContProgreso + "%");
                        carga.setSecondaryProgress(ContProgreso);
                        carga.setCancelable(true);
                        carga.show();
                        //progressBar.setProgress(ContProgreso);
                        //Preguntamos si ya se termino de ejecutar el progressbar
                        if (ContProgreso == 100) {
                            carga.dismiss();
                            Toast.makeText(getApplicationContext(), "Encuesta Procesada con Exito", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }

        //metodo que simula un tiempo de espera
        private void MetodoEspera() {
            try {
                Thread.sleep(80);
                ContProgreso++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
