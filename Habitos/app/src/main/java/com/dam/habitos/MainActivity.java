package com.dam.habitos;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText newHabito;
    private ListView listHabitos;

    private ArrayList<String> habitos = new ArrayList<>();

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

        newHabito = findViewById(R.id.nuevoHabito);
        listHabitos = findViewById(R.id.listaHabitos);
        listHabitos.setAdapter(new ArrayAdapter<String>(this, R.layout.lista_estilo, habitos));
    }

    public void annadirHabito(View view) {
        if (newHabito.getText() != null) {
            habitos.add(newHabito.getText().toString());
            try {
                OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("habitos.txt", MODE_PRIVATE));
                archivo.write(newHabito.getText().toString());
                archivo.flush();
                archivo.close();
                listHabitos.setAdapter(new ArrayAdapter<String>(this, R.layout.lista_estilo, habitos));
                Toast.makeText(this, "Habito añadido con éxito", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                System.out.println("Hola");
            }
        }
    }
}