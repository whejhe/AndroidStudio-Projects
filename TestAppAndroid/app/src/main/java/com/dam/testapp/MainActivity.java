package com.dam.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonComenzar = findViewById(R.id.boton_comenzar);
        EditText etUserName = findViewById(R.id.etUserName);
        TextView tvWelcomeMessage = findViewById(R.id.tvWelcomeMessage);

        botonComenzar.setOnClickListener(v -> {
            // Guardar el nombre del usuario
            String nombreUsuario = etUserName.getText().toString();

            if (!nombreUsuario.isEmpty()) {
                // Cambiar el texto de bienvenida si añadimos un nombre
                tvWelcomeMessage.setText("Muy bien, empecemos " + nombreUsuario);

                // Añadir un retraso antes de iniciar la siguiente actividad
                new Handler().postDelayed(() -> {
                    Intent intent = new Intent(MainActivity.this, Pregunta1Activity.class);
                    intent.putExtra("nombreUsuario", nombreUsuario);
                    startActivity(intent);
                }, 2000);

            } else {
                // Si no se ingresa ningún nombre, mostrar un mensaje para que ingrese uno
                etUserName.setError("Por favor, ingresa tu nombre");
            }
        });
    }
}
