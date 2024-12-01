package com.dam.productosvolley;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RequestQueue;
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

    EditText cod, desc, fabr, precio;
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
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        cod = findViewById(R.id.cod);
        desc = findViewById(R.id.desc);
        fabr = findViewById(R.id.fabr);
        precio = findViewById(R.id.precio);
    }

    public void insertarProducto(View v) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.2.2/registrar_producto/insertar_producto.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operacion exitosa", Toast.LENGTH_SHORT).show();
                cod.setText("");
                desc.setText("");
                fabr.setText("");
                precio.setText("");
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

                // Estas son las $variables del microservicio
                if (!cod.getText().toString().isEmpty() && !desc.getText().toString().isEmpty() && !fabr.getText().toString().isEmpty() && !precio.getText().toString().isEmpty()) {
                    parametros.put("codigo", cod.getText().toString());
                    parametros.put("producto", desc.getText().toString());
                    parametros.put("fabricante", fabr.getText().toString());
                    parametros.put("precio", precio.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                }

                return parametros;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void buscarProducto(View v) {
        JsonArrayRequest jar = new JsonArrayRequest("http://10.0.2.2/registrar_producto/buscar_producto.php?codigo=" + cod.getText().toString(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject job = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        job = response.getJSONObject(i);
                        cod.setText(job.getString("codigo"));
                        desc.setText(job.getString("producto"));
                        fabr.setText(job.getString("fabricante"));
                        precio.setText(job.getString("precio"));
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

    public void eliminarProducto(View v) {
        // Verifica que el campo del código no esté vacío
        if (cod.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Introduce el código del producto a eliminar", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.2.2/registrar_producto/eliminar_producto.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Producto eliminado exitosamente", Toast.LENGTH_SHORT).show();
                        cod.setText("");
                        desc.setText("");
                        fabr.setText("");
                        precio.setText("");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error al eliminar el producto: " + error.toString(), Toast.LENGTH_SHORT).show();
                cod.setText("");
                desc.setText("");
                fabr.setText("");
                precio.setText("");
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("codigo", cod.getText().toString());
                return parametros;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void editarProducto(View v) {
        if (cod.getText().toString().isEmpty() || desc.getText().toString().isEmpty() ||
                fabr.getText().toString().isEmpty() || precio.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Rellena todos los campos para editar el producto", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.2.2/registrar_producto/editar_producto.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Producto editado exitosamente", Toast.LENGTH_SHORT).show();
                        cod.setText("");
                        desc.setText("");
                        fabr.setText("");
                        precio.setText("");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error al editar el producto: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("codigo", cod.getText().toString());
                parametros.put("producto", desc.getText().toString());
                parametros.put("precio", precio.getText().toString());
                parametros.put("fabricante", fabr.getText().toString());
                return parametros;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void siguiente(View v) {
        Intent i = new Intent(this, listarProductos.class);
        startActivity(i);
    }
}