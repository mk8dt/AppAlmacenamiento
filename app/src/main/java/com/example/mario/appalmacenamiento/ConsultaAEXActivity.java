package com.example.mario.appalmacenamiento;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsultaAEXActivity extends AppCompatActivity {

    private TextView tvTitulo,tvContenido;
    private String nombreFichero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_aex);

        tvTitulo = findViewById(R.id.tvTituloExterno);
        tvContenido = findViewById(R.id.tvContenidoExterno);

        recuperarNombreFichero();
        tvTitulo.setText(tvTitulo.getText().toString()+" : " +nombreFichero);
        cargarContenido();
    }

    private void recuperarNombreFichero() {
        SharedPreferences sp = getSharedPreferences("SPActivity",MODE_PRIVATE);
        nombreFichero=sp.getString("FICHERO_EXT","Anonimo");
    }

    private  void cargarContenido() {

        File rutaAE = this.getExternalFilesDir(null);
        File f = new File(rutaAE.getAbsolutePath(), nombreFichero);
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String linea =br.readLine();
            String texto ="";

            while(linea!=null){
                texto += linea + "\n";
                linea =br.readLine();
            }
            tvContenido.setText(texto);

        } catch (FileNotFoundException e) {
            Toast.makeText(this,"EL fichero no ha sido encontrado",Toast.LENGTH_LONG).show();
        }catch(IOException e){
            Toast.makeText(this,"Error de conexion con el fichero",Toast.LENGTH_LONG).show();
        }finally{
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
