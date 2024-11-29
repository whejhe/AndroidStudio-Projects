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

    private EditText txt_nombre, txt_datos;

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

        txt_nombre = findViewById(R.id.txt_nombre);
        txt_datos = findViewById(R.id.txt_datos);
    }

    public void guardar(View view) {
        //creo las variables
        String nombre = txt_nombre.getText().toString().toLowerCase();
        String datos = txt_datos.getText().toString();

        //añado el contacto a la agenda
        SharedPreferences prefs = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        //compruebo que los campos no estén vacíos
        if (nombre.isEmpty() || datos.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }else{
            editor.putString(nombre, datos);
            editor.apply();
        }
        //muestro un mensaje para indicar que se ha guardado correctamente
        Toast.makeText(this, "Contacto guardado", Toast.LENGTH_SHORT).show();
        //limpio los campos
        txt_nombre.setText("");
        txt_datos.setText("");
    }

    public void buscar(View view) {
        //creo la variable del nombre
        String nombre = txt_nombre.getText().toString();
        //busco el contacto en la agenda
        SharedPreferences prefs = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        String datos = prefs.getString(nombre, "");
        //muestro los datos del contacto si existe, si no, muestro un mensaje de error
        if (datos.isEmpty()) {
            Toast.makeText(this, "Contacto no encontrado", Toast.LENGTH_SHORT).show();
        } else {
            txt_datos.setText(datos);
        }
    }
}