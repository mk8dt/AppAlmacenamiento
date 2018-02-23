package com.example.mario.appalmacenamiento;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SPActivity extends AppCompatActivity {

    EditText etNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);

        etNombre = findViewById(R.id.etNombre);
    }

    public void guardarSP(View v){

        //Creamos el fichero y almacenamos los datos de usuario. Solo un valor por clave
        SharedPreferences sp = getSharedPreferences("SPActivity",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("nombre",etNombre.getText().toString());
        editor.commit();
        setResult(RESULT_OK,getIntent());
        finish();
    }
}
