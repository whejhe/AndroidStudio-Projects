package com.dam.productossqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import java.util.ArrayList;

public class ListaBD extends AppCompatActivity {
    ArrayList<String> lista = new ArrayList<>();
    private ListView listaBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.lista_productos_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);

        Cursor fila = admin.getReadableDatabase().rawQuery("SELECT CODIGO FROM ARTICULOS", null);

        listaBD = findViewById(R.id.lista_productos);

        if (fila.moveToFirst()) {
            do {
                lista.add(fila.getString(0));
            } while (fila.moveToNext());
        } else Toast.makeText(this, "No hay productos para mostrar", Toast.LENGTH_SHORT).show();

        listaBD.setAdapter(new ArrayAdapter<String>(this, R.layout.estilo_lista, lista));

        fila.close();
    }

    public void irAtras(View view) {
        finish();
    }
}