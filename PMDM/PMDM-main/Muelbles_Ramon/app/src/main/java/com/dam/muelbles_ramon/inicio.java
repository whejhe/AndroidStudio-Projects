package com.dam.muelbles_ramon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void irMesas(View view) {
        Intent pantallaMesas = new Intent(this, Mesas.class);
        startActivity(pantallaMesas);
    }

    public void irSillas(View view) {
        Intent pantallaSillas = new Intent(this, Sillas.class);
        startActivity(pantallaSillas);
    }

    public void irCuenta(View view) {
        Intent pantallaCuenta = new Intent(this, Cuenta.class);
        startActivity(pantallaCuenta);
    }

    public void irCarrito(View view) {
        Intent pantallaCarrito = new Intent(this, Carrito.class);
        startActivity(pantallaCarrito);
    }



}