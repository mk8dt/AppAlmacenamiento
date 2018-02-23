package com.example.mario.appalmacenamiento;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AEXActivity extends AppCompatActivity {

    EditText etTexto;
    static  final String NOM_FICHERO_EXT="MificheroExterno.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aex);
        etTexto = findViewById(R.id.ettextoExterno);
    }

    public void guardarAe(View v){

        File rutaAE =this.getExternalFilesDir(null);
        File f = new File(rutaAE.getAbsolutePath(),NOM_FICHERO_EXT);

        OutputStreamWriter osw = null ;
        try{
            osw= new OutputStreamWriter(new FileOutputStream(f));
            osw.write(etTexto.getText().toString());

            //Guardamos en preferencias
            guardarPreferencias();

            setResult(RESULT_OK,getIntent());
        }catch(FileNotFoundException e){
            Toast.makeText(this,"Fichero no encontrado",Toast.LENGTH_LONG).show();
            setResult(RESULT_CANCELED,getIntent());
        }catch (IOException e){
            Toast.makeText(this,"Error de conexion con el fichero",Toast.LENGTH_LONG).show();
            setResult(RESULT_CANCELED,getIntent());
        }finally {
            if(osw!=null){
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            finish();
        }
    }

    private void guardarPreferencias(){
        //Establecemos la conexion con el fichero
        SharedPreferences sp = getSharedPreferences("SPActivity",MODE_PRIVATE);
        //Accedemos al nombre del fichero
        String nombreFichEx=sp.getString("FICHERO_EXT","");
        //Si es null creamos el nombre del fichero
        if(nombreFichEx.equals("")){
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("FICHERO_EXT",NOM_FICHERO_EXT);
            editor.commit();
        }

    }
}
