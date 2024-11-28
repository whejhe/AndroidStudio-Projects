package com.dam.loginyregistroconfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Registro extends AppCompatActivity {

    private EditText nombre, correo, password;
    FirebaseAuth auth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nombre = findViewById(R.id.nombre);
        correo = findViewById(R.id.correo);
        password = findViewById(R.id.pass);
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }

    public void Registrarme(View view) {
        String nombreUsuario = nombre.getText().toString().trim();
        String correoUsuario = correo.getText().toString().trim();
        String passwordUsuario = password.getText().toString().trim();

        if (nombreUsuario.isEmpty() || correoUsuario.isEmpty() || passwordUsuario.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }else{
            registroUsuario(nombreUsuario,correoUsuario, passwordUsuario);
        }
    }



    private void registroUsuario(String nombreUsuario, String correoUsuario, String passwordUsuario){
        auth.createUserWithEmailAndPassword(correoUsuario, passwordUsuario).addOnCompleteListener(task -> {
            String id = Objects.requireNonNull(auth.getCurrentUser()).getUid();
            Map<String, Object> map = new HashMap<>();
            map.put("id",id);
            map.put("nombre", nombreUsuario);
            map.put("correo", correoUsuario);
            map.put("password", passwordUsuario);

            firestore.collection("usuario").document(id).set(map).addOnSuccessListener(unused -> {
                Toast.makeText(getApplicationContext(), "Usuario Registrado", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Registro.this, MainActivity.class));
                finish();
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Error al guardar los datos", Toast.LENGTH_SHORT).show();
                }
            });
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al registrar el Usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }
}