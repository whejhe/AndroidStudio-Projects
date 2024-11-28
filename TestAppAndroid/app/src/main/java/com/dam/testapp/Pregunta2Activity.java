package com.dam.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class Pregunta2Activity extends AppCompatActivity {

    private RadioGroup grupoOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta2);

        grupoOpciones = findViewById(R.id.grupo_opciones);
        Button botonSiguiente = findViewById(R.id.boton_siguiente);

        botonSiguiente.setOnClickListener(v -> {
            int idSeleccionado = grupoOpciones.getCheckedRadioButtonId();
            RadioButton opcionSeleccionada = findViewById(idSeleccionado);

            // Obtener el nombre del usuario desde el Intent
            String nombreUsuario = getIntent().getStringExtra("nombreUsuario");

            // Obtener la respuesta de la pregunta anterior
            String respuesta1 = getIntent().getStringExtra("respuesta1");

            // Obtener la respuesta actual
            String respuesta2 = opcionSeleccionada.getText().toString();

            // Pasar las respuestas a la siguiente Activity
            Intent intent = new Intent(Pregunta2Activity.this, Pregunta3Activity.class);
            intent.putExtra("respuesta1", respuesta1);
            intent.putExtra("respuesta2", respuesta2);
            intent.putExtra("nombreUsuario", nombreUsuario);
            startActivity(intent);
        });
    }
}