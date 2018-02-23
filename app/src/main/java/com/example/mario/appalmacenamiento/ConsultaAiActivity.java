package com.example.mario.appalmacenamiento;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsultaAiActivity extends AppCompatActivity {

    private TextView tvTitulo,tvContenido;
    private String nombreFichero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_ai);

        tvTitulo = findViewById(R.id.tvTitulo);
        tvContenido = findViewById(R.id.tvContenido);

        recuperarNombreFichero();
        tvTitulo.setText(tvTitulo.getText().toString()+" : " +nombreFichero);
        cargarContenido();
    }

    private void recuperarNombreFichero() {
        SharedPreferences sp = getSharedPreferences("SPActivity",MODE_PRIVATE);
        nombreFichero=sp.getString("FICHERO_INTERNO","Anonimo");
    }

    private void cargarContenido() {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br =null;
        String linea ="",texto = "";
        try {
            fis = openFileInput(nombreFichero);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            while ((linea = br.readLine()) != null) {
                texto += linea + "\n";
            }
            tvContenido.setText(texto);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if(fis!=null){
                    fis.close();
                }
            }catch(IOException e){
                    e.printStackTrace();
                }
        }
    }
}
