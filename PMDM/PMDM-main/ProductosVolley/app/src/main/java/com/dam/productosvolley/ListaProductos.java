package com.dam.productosvolley;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaProductos extends AppCompatActivity {

    ListView listaCodigos;
    ListView listaProductos;
    ListView listaPrecios;
    ArrayList<String> codigos = new ArrayList<>();
    ArrayList<String> productos = new ArrayList<>();
    ArrayList<String> precios = new ArrayList<>();
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_productos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listaProductos = findViewById(R.id.lista_productos);
        listaCodigos = findViewById(R.id.lista_codigos);
        listaPrecios = findViewById(R.id.lista_precios);

        listaProductos.setDividerHeight(5);
        listaCodigos.setDividerHeight(5);
        listaPrecios.setDividerHeight(5);

        JsonArrayRequest jar = new JsonArrayRequest("http://10.0.2.2/registrar_producto/buscar_todos.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject job = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        job = response.getJSONObject(i);
                        codigos.add(job.getString("codigo"));
                        productos.add(job.getString("producto"));
                        precios.add(job.getString("precio") + "€");
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                listaProductos.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.estilo_lista, productos));
                listaCodigos.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.estilo_lista, codigos));
                listaPrecios.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.estilo_lista, precios));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jar);


    }

    public void volver(View view) {
        finish();
    }
}