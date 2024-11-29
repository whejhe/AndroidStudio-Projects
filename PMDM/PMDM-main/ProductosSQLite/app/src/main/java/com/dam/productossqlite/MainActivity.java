package com.dam.productossqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText codigo_pt, desc_pt, precio_pt;

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

        codigo_pt = findViewById(R.id.codigo_pt);
        desc_pt = findViewById(R.id.desc_pt);
        precio_pt = findViewById(R.id.precio_pt);

    }

    public void registrar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codigo = codigo_pt.getText().toString();
        String descripcion = desc_pt.getText().toString();
        String precio = precio_pt.getText().toString();

        if (codigo.isEmpty() || descripcion.isEmpty() || precio.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            bd.insert("articulos", null, registro);

            codigo_pt.setText("");
            desc_pt.setText("");
            precio_pt.setText("");

            Toast.makeText(this, "Producto registrado", Toast.LENGTH_SHORT).show();
        }

        bd.close();
    }

    public void buscar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codigo = codigo_pt.getText().toString();

        if (codigo.isEmpty()) {
            Toast.makeText(this, "Ponga un código", Toast.LENGTH_SHORT).show();
        } else {
            Cursor fila = bd.rawQuery("SELECT DESCRIPCION, PRECIO FROM ARTICULOS WHERE CODIGO=" + codigo, null);
            if (fila.moveToFirst()) {
                desc_pt.setText(fila.getString(0));
                precio_pt.setText(fila.getString(1));

                Toast.makeText(this, "Producto encontrado", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "No existe un producto con ese código", Toast.LENGTH_SHORT).show();
            }
            fila.close();
        }

        bd.close();
    }

    public void eliminar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codigo = codigo_pt.getText().toString();

        if (codigo.isEmpty()) {
            Toast.makeText(this, "Rellena el campo", Toast.LENGTH_SHORT).show();
        } else {
            int cantidad = bd.delete("articulos", "codigo=" + codigo, null);
            codigo_pt.setText("");
            desc_pt.setText("");
            precio_pt.setText("");
            if (cantidad == 1) {
                Toast.makeText(this, "Producto eliminado", Toast.LENGTH_SHORT).show();
            } else if (cantidad > 1) {
                Toast.makeText(this, cantidad + " productos eliminados", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No existe un producto con ese código", Toast.LENGTH_SHORT).show();
            }
        }

        bd.close();
    }

    public void modificar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String codigo = codigo_pt.getText().toString();
        String descripcion = desc_pt.getText().toString();
        String precio = precio_pt.getText().toString();

        if (!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);
            int cantidad = bd.update("articulos", registro, "codigo=" + codigo, null);
            if (cantidad == 1) {
                Toast.makeText(this, "Producto modificado", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "No existe un producto con ese código", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Debes rellenar todos los datos", Toast.LENGTH_SHORT).show();
        }

        bd.close();
    }

    public void irLista(View view) {
        Intent pantallaLista = new Intent(this, ListaBD.class);
        startActivity(pantallaLista);
    }
}