package com.dam.bbdd_volley;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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

    private EditText edtCodigo, edtProducto, edtFabricante, edtPrecio;
    RequestQueue requestQueue;

    String baseUrl = "http://10.0.2.2/registrar_productos/";


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

        edtCodigo = findViewById(R.id.etCodigo);
        edtProducto = findViewById(R.id.etProducto);
        edtFabricante = findViewById(R.id.etFabricante);
        edtPrecio = findViewById(R.id.etPrecio);
    }

    public void insertarProducto(View view){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, baseUrl+"insertar.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operacion Exitosa", Toast.LENGTH_SHORT).show();
                limpiarFormulario();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();
                params.put("codigo", edtCodigo.getText().toString());
                params.put("producto", edtProducto.getText().toString());
                params.put("precio", edtPrecio.getText().toString());
                params.put("fabricante", edtFabricante.getText().toString());

                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void buscarProducto(View view){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(baseUrl+"buscar.php?codigo=" +edtCodigo.getText().toString(),  new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        edtProducto.setText(jsonObject.getString("producto"));
                        edtPrecio.setText(jsonObject.getString("precio"));
                        edtFabricante.setText(jsonObject.getString("fabricante"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error al buscar el producto", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    public void editarProducto(View view){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, baseUrl + "editar.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Producto Editado", Toast.LENGTH_SHORT).show();
                limpiarFormulario();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("codigo", edtCodigo.getText().toString());
                parametros.put("producto", edtProducto.getText().toString());
                parametros.put("precio", edtPrecio.getText().toString());
                parametros.put("fabricante", edtFabricante.getText().toString());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void eliminarProducto(View view){
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, baseUrl + "eliminar.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Producto eliminado", Toast.LENGTH_SHORT).show();
                limpiarFormulario();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("codigo", edtCodigo.getText().toString());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void limpiarFormulario() {
        edtCodigo.setText("");
        edtProducto.setText("");
        edtFabricante.setText("");
        edtPrecio.setText("");
    }
}