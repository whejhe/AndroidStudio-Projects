package com.dam.calculadora_real;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button boton1;
    private Button boton2;
    private Button boton3;
    private Button boton4;
    private Button boton5;
    private Button boton6;
    private Button boton7;
    private Button boton8;
    private Button boton9;
    private Button boton0;
    private TextView numeros;

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
        boton1 = findViewById(R.id.b1);
        boton2 = findViewById(R.id.b2);
        boton3 = findViewById(R.id.b3);
        boton4 = findViewById(R.id.b4);
        boton5 = findViewById(R.id.b5);
        boton6 = findViewById(R.id.b6);
        boton7 = findViewById(R.id.b7);
        boton8 = findViewById(R.id.b8);
        boton9 = findViewById(R.id.b9);
        boton0 = findViewById(R.id.b0);
    }
}