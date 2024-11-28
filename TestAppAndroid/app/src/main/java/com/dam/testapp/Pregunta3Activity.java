package com.dam.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class Pregunta3Activity extends AppCompatActivity {

    private RadioGroup grupoOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta3);

        grupoOpciones = findViewById(R.id.grupo_opciones);
        Button botonSiguiente = findViewById(R.id.boton_siguiente);

        botonSiguiente.setOnClickListener(v -> {
            int idSeleccionado = grupoOpciones.getCheckedRadioButtonId();
            RadioButton opcionSeleccionada = findViewById(idSeleccionado);

            // Obtener las respuestas anteriores
            String respuesta1 = getIntent().getStringExtra("respuesta1");
            String respuesta2 = getIntent().getStringExtra("respuesta2");

            // Obtener el nombre del usuario desde Intent
            String nombreUsuario = getIntent().getStringExtra("nombreUsuario");

            // Obtener la respuesta actual
            String respuesta3 = opcionSeleccionada.getText().toString();

            // Pasar las respuestas a la siguiente Activity
            Intent intent = new Intent(Pregunta3Activity.this, ResultadoActivity.class);
            intent.putExtra("respuesta1", respuesta1);
            intent.putExtra("respuesta2", respuesta2);
            intent.putExtra("respuesta3", respuesta3);
            intent.putExtra("nombreUsuario", nombreUsuario);
            startActivity(intent);
        });
    }
}