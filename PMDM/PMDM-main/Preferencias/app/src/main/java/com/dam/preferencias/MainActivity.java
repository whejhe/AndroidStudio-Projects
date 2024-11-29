package com.dam.preferencias;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferencias;
    private EditText mail;
    private Button boton;

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
        mail = findViewById(R.id.email_txt);

        preferencias = getSharedPreferences("Datos", MODE_PRIVATE);
        mail.setText(preferencias.getString("email", ""));

    }

    public void guardar(View view) {
        preferencias = getSharedPreferences("Datos", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("email", mail.getText().toString());
        editor.apply();
        finish();
    }


}