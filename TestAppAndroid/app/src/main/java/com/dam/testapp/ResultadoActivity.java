package com.dam.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        TextView textoResultado = findViewById(R.id.texto_resultado);
        Button botonReintentar = findViewById(R.id.button);

        // Obtener el nombre del usuario desde el Intent
        String nombreUsuario = getIntent().getStringExtra("nombreUsuario");

        // Obtener las respuestas
        String respuesta1 = getIntent().getStringExtra("respuesta1");
        String respuesta2 = getIntent().getStringExtra("respuesta2");
        String respuesta3 = getIntent().getStringExtra("respuesta3");

        String respCorrecta1 = "setContentView()";
        String respCorrecta2 = "onStart()";
        String respCorrecta3 = "onCreate()";

        String resultado1;
        String resultado2;
        String resultado3;

        if(respuesta1.equals(respCorrecta1)){
            resultado1 = "Correcto";
        }else{
            resultado1 = "Incorrecto";
        }

        if(respuesta2.equals(respCorrecta2)){
            resultado2 = "Correcto";
        }else{
            resultado2 = "Incorrecto";
        }

        if(respuesta3.equals(respCorrecta3)){
            resultado3 = "Correcto";
        }else{
            resultado3 = "Incorrecto";
        }

        // Mostrar resultado
        String resultado = "Estas son tus respuestas "+nombreUsuario+":\n\n" +
                "Pregunta 1: " + respuesta1 + resultado1 + "\n" +
                "Pregunta 2: " + respuesta2 + resultado2 + "\n" +
                "Pregunta 3: " + respuesta3 + resultado3;

        textoResultado.setText(resultado);
    }

    public void reintentar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}