package com.dam.ejerciciolistaalumnos;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView nota;
    private ListView alumno;
    private ImageButton imagen;

    private String[] nombres = {"Antonio","Jose","Maria","Juan","Pedro","Ramon","Pepe","Luis","Ana","Julia","Sonia","Laura","Jesus","Mario","Josefa","Sonia","Laura","Jesus","Mario","Josefa"};

    private String[] numeros = {"10","9","8","7","6","5","4","3","2","1"};

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

        imagen = findViewById(R.id.boton_cara);
        nota = findViewById(R.id.tv1);
        alumno = findViewById(R.id.lv1);
        alumno.setAdapter(new ArrayAdapter<String>(this, R.layout.estilo_lista, nombres));

        alumno.setOnItemClickListener((parent, view, position, id) -> {
            if (position < numeros.length){
                nota.setText("La nota de " + nombres[position] + " es " +  numeros[position]);
            }else nota.setText(nombres[position] + " no tiene notas aún");

        });




    }
}