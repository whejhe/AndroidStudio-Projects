package com.dam.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class Pregunta1Activity extends AppCompatActivity {

    private RadioGroup grupoOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta1);

        grupoOpciones = findViewById(R.id.grupo_opciones);
        Button botonSiguiente = findViewById(R.id.boton_siguiente);

        // Obtener el nombre del usuario desde el Intent
        String nombreUsuario = getIntent().getStringExtra("nombreUsuario");

        botonSiguiente.setOnClickListener(v -> {
            int idSeleccionado = grupoOpciones.getCheckedRadioButtonId();
            RadioButton opcionSeleccionada = findViewById(idSeleccionado);

            String respuesta1 = opcionSeleccionada.getText().toString();
            Intent intent = new Intent(Pregunta1Activity.this, Pregunta2Activity.class);
            intent.putExtra("respuesta1", respuesta1);
            intent.putExtra("nombreUsuario", nombreUsuario);
            startActivity(intent);
        });
    }
}