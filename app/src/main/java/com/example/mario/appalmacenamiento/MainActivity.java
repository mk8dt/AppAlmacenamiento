package com.example.mario.appalmacenamiento;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvWelcome;
    String nombre;
    static final int SP_CODE = 1;
    static final int AI_CODE = 2;
    static final int EX_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvWelcome = findViewById(R.id.tvBienvenida);

        cargarDatos();
    }

    //Almacenar en Shared Preferences
    public void almacenamientoSP(View v){

        Intent i = new Intent(this,SPActivity.class);
        startActivityForResult(i,SP_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==SP_CODE){
            if(resultCode==RESULT_OK){
                cargarDatos();
            }
        }else if(requestCode==AI_CODE){
            if(resultCode==RESULT_OK){
                Toast.makeText(this, "Se ha realizado el almacenamiento interno con exito", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No se ha podido realizar el almacenamiento interno", Toast.LENGTH_SHORT).show();
            }

        }else if (requestCode==EX_CODE){
            if(resultCode==RESULT_OK){
                Toast.makeText(this, "Se ha realizado el almacenamiento externo con exito", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No se ha podido realizar el almacenamiento externo", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void cargarDatos() {
        SharedPreferences sp = getSharedPreferences("SPActivity",MODE_PRIVATE);
        nombre=sp.getString("nombre","Anonimo");//Segundo parametro es el valor por defecto.
        tvWelcome.setText("Bienvenido " + nombre);
    }

    //Almacenamiento en fichero interno
    public void almacenamientoIn(View v){

        Intent i = new Intent(this,AINActivity.class);
        startActivityForResult(i,AI_CODE);
    }

    //Almacenamiento externo
    public void almacenamientoEx(View v){

        Intent i = new Intent(this,AEXActivity.class);
        startActivityForResult(i,EX_CODE);
    }

    //Consultar el fichero interno
    public void consultarAI(View v){
        Intent i = new Intent(this,ConsultaAiActivity.class);
        startActivity(i);

    }

    //Consultar el fichero externo
    public void consultarAE(View v){
        Intent i = new Intent(this, ConsultaAEXActivity.class);
        startActivity(i);
    }

}
