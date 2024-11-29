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

public class Mesas extends AppCompatActivity {

    Button botMesa1;
    Button botMesa2;
    Button botMesa3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mesas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        botMesa1 = findViewById(R.id.botMesa1);
        botMesa2 = findViewById(R.id.botMesa2);
        botMesa3 = findViewById(R.id.botMesa3);

        botMesa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                añadirACarrito("Mesa de comedor", 250);
            }
        });

        botMesa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                añadirACarrito("Mesa de centro", 90);
            }
        });

        botMesa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                añadirACarrito("Escritorio", 150);
            }
        });
    }

    public void añadirACarrito(String nombre, int precio) {
        CarritoSingleton.getInstance().agregarProducto(nombre, precio);
        Toast.makeText(this, nombre + " añadida a la cesta", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Carrito.class);
        startActivity(intent);
    }

    public void irAtras(View view) {
        finish();
    }
}
