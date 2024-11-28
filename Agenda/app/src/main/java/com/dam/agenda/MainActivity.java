package com.dam.agenda;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText elNombre;
    private EditText elDato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        elNombre = findViewById(R.id.txtNombre);
        elDato = findViewById(R.id.txtDatos);
    }

    public void Guardar(View view){
        String nombre = elNombre.getText().toString();
        String datos = elDato.getText().toString();

        SharedPreferences preferencia = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferencia.edit();
        obj_editor.putString(nombre, datos);
        obj_editor.commit();
        Toast.makeText(this,"El contacto ha sido agregado", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void Buscar(View view){
        String nombre = elNombre.getText().toString();
        SharedPreferences preferencia = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        String datos = preferencia.getString(nombre,"");

        if(datos.length()==0){
            Toast.makeText(this, "No se encontro ningun registro", Toast.LENGTH_SHORT).show();
        }else{
            elDato.setText(datos);
        }
    }
}