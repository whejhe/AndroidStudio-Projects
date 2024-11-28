package com.dam.almacenamientodedatos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText et1;

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
        et1 = findViewById(R.id.txt_email);

        SharedPreferences preference = getSharedPreferences("datos", Context.MODE_PRIVATE);
        et1.setText(preference.getString("email", ""));
    }

    public void guardar(View view){
        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferencias.edit();
        obj_editor.putString("email", et1.getText().toString());
        obj_editor.commit();
        finish();
    }
}