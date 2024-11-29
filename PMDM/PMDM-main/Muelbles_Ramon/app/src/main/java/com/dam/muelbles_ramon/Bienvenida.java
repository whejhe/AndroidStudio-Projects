package com.dam.muelbles_ramon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Bienvenida extends AppCompatActivity {

    private EditText usuario_pt, contra_pt;
    private SharedPreferences usu_pref, cont_pref;
    String usuario;
    String contrasena;
    boolean esVisible = false;
    WebView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bienvenida);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        video = findViewById(R.id.video);
        video.setWebViewClient(new WebViewClient());
        String videoId = "-ZSnAyKgMHU";
        video.loadUrl("https://www.youtube.com/embed/" + videoId + "?autoplay=1");
        video.getSettings().setJavaScriptEnabled(true);

        usuario_pt = findViewById(R.id.usuario_pt);
        contra_pt = findViewById(R.id.contra_pt);

        usu_pref = getSharedPreferences("usuarios", Context.MODE_PRIVATE);
        cont_pref = getSharedPreferences("contrasena", Context.MODE_PRIVATE);

        // Recuperar el nombre de usuario desde SharedPreferences (guardado en la activity Cuenta)
        SharedPreferences sharedPreferences = getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE);
        usuario = sharedPreferences.getString("nombreUsuario", "Jefe Ramon"); // Valor por defecto si no existe
        contrasena = sharedPreferences.getString("contrasena", "Jefe Ramon"); // Valor por defecto si no existe

        // Mostrar el nombre de usuario en el campo de texto
        usuario_pt.setText(usu_pref.getString("usuario", usuario));
        contra_pt.setText(cont_pref.getString("contrasena", contrasena));
    }

    public void iniciarSesion(View view) {
        usu_pref = getSharedPreferences("usuarios", Context.MODE_PRIVATE);
        cont_pref = getSharedPreferences("contrasena", Context.MODE_PRIVATE);

        if (usuario_pt.getText().toString().isEmpty() || contra_pt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (usuario_pt.getText().toString().equals(usuario)) {
                if (contra_pt.getText().toString().equals(contrasena)) {
                    SharedPreferences.Editor editor_usu = usu_pref.edit();
                    SharedPreferences.Editor editor_cont = cont_pref.edit();

                    editor_usu.putString("usuario", usuario_pt.getText().toString());
                    editor_cont.putString("contrasena", contra_pt.getText().toString());

                    editor_usu.apply();
                    editor_cont.apply();

                    Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    Intent pantallaInicio = new Intent(this, inicio.class);
                    startActivity(pantallaInicio);
                } else {
                    Toast.makeText(this, "Contraseña incorrecta para "+usuario+". Pista("+contrasena+")", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "No eres "+usuario, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void ocultar(View view) {
        if (esVisible) {
            contra_pt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            esVisible = false;
        } else {
            contra_pt.setInputType(InputType.TYPE_CLASS_TEXT);
            esVisible = true;
        }

    }

}