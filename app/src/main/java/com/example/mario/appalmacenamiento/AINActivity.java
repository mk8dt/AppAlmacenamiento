package com.example.mario.appalmacenamiento;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AINActivity extends AppCompatActivity {

    EditText etTexto;
    static final String NOM_FICHERO="Mifichero.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ain);

        etTexto = findViewById(R.id.ettextoInterno);
    }

    //Almacenamos el texto escrito internamente
    public void guardarAi(View v){
        String string = etTexto.getText().toString()+"\n";
        FileOutputStream fos =null;
        try{
            fos = openFileOutput(NOM_FICHERO, Context.MODE_APPEND);
            fos.write(string.getBytes());
            almacenarSP();
            setResult(RESULT_OK,getIntent());

        } catch(FileNotFoundException e){
            Toast.makeText(this,"Fichero no encontrado",Toast.LENGTH_LONG).show();
            setResult(RESULT_CANCELED,getIntent());

        }catch(IOException e){
            Toast.makeText(this,"Error de conexion con el fichero",Toast.LENGTH_LONG).show();
            setResult(RESULT_CANCELED,getIntent());

        }finally{
            if (fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            finish();
        }
    }

    //Almacenar el nombre del fichero en el fichero de preferencias
    private void almacenarSP() {
        SharedPreferences sp = getSharedPreferences("SPActivity",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("FICHERO_INTERNO",NOM_FICHERO);
        editor.commit();
    }
}
