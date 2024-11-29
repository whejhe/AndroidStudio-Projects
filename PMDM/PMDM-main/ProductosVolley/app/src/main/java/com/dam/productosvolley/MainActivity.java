package com.dam.productosvolley;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText codigo_et, producto_et, precio_et, fab_et;
    RequestQueue requestQueue;


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

        codigo_et = findViewById(R.id.codigo_et);
        producto_et = findViewById(R.id.producto_et);
        precio_et = findViewById(R.id.precio_et);
        fab_et = findViewById(R.id.fab_et);

    }

    public void insertarProducto(View view) {
        String codigo = codigo_et.getText().toString().trim();
        String producto = producto_et.getText().toString().trim();
        String precio = precio_et.getText().toString().trim();
        String fabricante = fab_et.getText().toString().trim();

        if (codigo.isEmpty() || producto.isEmpty() || precio.isEmpty() || fabricante.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.2.2/registrar_producto/insertar_producto.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operación exitosa", Toast.LENGTH_SHORT).show();
                // Limpiamos los campos después de insertar
                codigo_et.setText("");
                producto_et.setText("");
                precio_et.setText("");
                fab_et.setText("");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("codigo", codigo);
                parametros.put("producto", producto);
                parametros.put("precio", precio);
                parametros.put("fabricante", fabricante);
                return parametros;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void buscarProducto(View v) {

        if (codigo_et.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Debe insertar un código", Toast.LENGTH_SHORT).show();
            return;
        }

        JsonArrayRequest jar = new JsonArrayRequest("http://10.0.2.2/registrar_producto/buscar_producto.php?codigo=" + codigo_et.getText().toString(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject job = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        job = response.getJSONObject(i);
                        codigo_et.setText(job.getString("codigo"));
                        producto_et.setText(job.getString("producto"));
                        fab_et.setText(job.getString("fabricante"));
                        precio_et.setText(job.getString("precio"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

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

    public void eliminarProducto(View view) {

        if (codigo_et.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Debe insertar un código", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.2.2/registrar_producto/eliminar_producto.php?codigo=" + codigo_et.getText().toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operación exitosa", Toast.LENGTH_SHORT).show();
                codigo_et.setText("");
                producto_et.setText("");
                precio_et.setText("");
                fab_et.setText("");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                //Estas son las $variables del microservicio
                parametros.put("codigo", codigo_et.getText().toString());
                parametros.put("producto", producto_et.getText().toString());
                parametros.put("precio", precio_et.getText().toString());
                parametros.put("fabricante", fab_et.getText().toString());
                return parametros;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void editarProducto(View view) {

        if (codigo_et.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Debe insertar un código", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.2.2/registrar_producto/editar_producto.php?codigo=" + codigo_et.getText().toString() + "?producto=" + producto_et.getText().toString() + "?precio=" + precio_et.getText().toString() + "?fabricante=" + fab_et.getText().toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operación exitosa", Toast.LENGTH_SHORT).show();
                codigo_et.setText("");
                producto_et.setText("");
                precio_et.setText("");
                fab_et.setText("");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                //Estas son las $variables del microservicio
                parametros.put("codigo", codigo_et.getText().toString());
                parametros.put("producto", producto_et.getText().toString());
                parametros.put("precio", precio_et.getText().toString());
                parametros.put("fabricante", fab_et.getText().toString());
                return parametros;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void verProductos(View view) {
        Intent irLista = new Intent(this, ListaProductos.class);
        startActivity(irLista);
    }
}