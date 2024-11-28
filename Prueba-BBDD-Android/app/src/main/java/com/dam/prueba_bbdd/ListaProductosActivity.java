package com.dam.prueba_bbdd;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListaProductosActivity extends AppCompatActivity {

    private ListView codigoEt, descripcionEt, precioEt;
    private String consulta;

    AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(
            this, "Lista de Productos", null, 1
    );

    String codigo, precio, descripcion;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
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

        codigoEt = findViewById(R.id.txt_codigo);
        descripcionEt = findViewById(R.id.txt_descripcion);
        precioEt = findViewById(R.id.txt_precio);

    }

    public void listarProductos(View view){
        SQLiteDatabase sqLiteDatabase = adminSQLiteOpenHelper.getWritableDatabase();

        if(!codigo.isEmpty()){
            Cursor cursor = sqLiteDatabase.rawQuery(
                    "Select * from articulos",null
            );
        }
    }

    public void listarProducto(View view){
        SQLiteDatabase sqLiteDatabase = adminSQLiteOpenHelper.getWritableDatabase();

        if(!codigo.isEmpty() && codigo.equals(consulta)){
            Cursor cursor = sqLiteDatabase.rawQuery(
                    "Select * from articulos where codigo="+consulta,null
            );
        }
    }

}

