package com.dam.muelbles_ramon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Sillas extends AppCompatActivity {

    Button botSilla1;
    Button botSilla2;
    Button botSilla3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sillas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        botSilla1 = findViewById(R.id.botSilla1);
        botSilla2 = findViewById(R.id.botSilla2);
        botSilla3 = findViewById(R.id.botSilla3);

        botSilla1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirACarritoSilla("Silla de comedor", 75);
            }
        });

        botSilla2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirACarritoSilla("Silla de terraza", 50);
            }
        });

        botSilla3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirACarritoSilla("Silla de escritorio", 180);
            }
        });

    }

    public void anadirACarritoSilla(String nombre, int precio) {
        CarritoSingleton.getInstance().agregarProducto(nombre, precio);
        Toast.makeText(this, nombre + " añadida a la cesta", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Carrito.class);
        startActivity(intent);
    }

    public void irAtras(View view) {
        finish();
    }
}
