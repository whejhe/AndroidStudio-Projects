package com.dam.muelbles_ramon;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Carrito extends AppCompatActivity {

    private ListView lista;
    private TextView totalTextView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_carrito);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lista = findViewById(R.id.lista_carrito);
        totalTextView = findViewById(R.id.total_txt);

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                CarritoSingleton.getInstance().getProductos());
        lista.setAdapter(adapter);

        actualizarUI();
    }

    private void actualizarUI() {
        adapter.notifyDataSetChanged();
        totalTextView.setText(CarritoSingleton.getInstance().getTotal() + "€");
    }

    public void irAtras(View view) {
        finish();
    }

    public void comprar(View view) {
        CarritoSingleton.getInstance().limpiarCarrito();
        actualizarUI();
        Toast.makeText(this, "Compra realizada", Toast.LENGTH_SHORT).show();
    }
}