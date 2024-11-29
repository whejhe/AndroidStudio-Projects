package com.dam.iniciosesionfirebase;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    private EditText nombre;
    private EditText correo;
    private EditText pass;
    FirebaseAuth auth;
    FirebaseFirestore db;

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
        pass = findViewById(R.id.pass);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

    }

    public void resgistrarme(View view) {
        String nombreUsuario = this.nombre.getText().toString().trim();
        String correoUsuario = this.correo.getText().toString().trim();
        String passUsuario = this.pass.getText().toString().trim();

        if (nombreUsuario.isEmpty() || correoUsuario.isEmpty() || passUsuario.isEmpty()) {
            Toast.makeText(this, "Rellene los campos", Toast.LENGTH_SHORT).show();
        } else {
            registroUsuario(nombreUsuario, correoUsuario, passUsuario);
        }

    }

    private void registroUsuario(String nombre, String correo, String pass) {
        auth.createUserWithEmailAndPassword(correo, pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String id = auth.getCurrentUser().getUid();
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", id);
                        map.put("nombre", nombre);
                        map.put("correo", correo);
                        map.put("pass", pass);

                        db.collection("usuarios").document(id)
                                .set(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getApplicationContext(), "Usuario registrado", Toast.LENGTH_SHORT).show();
                                        Intent pantallaPrincipal = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(pantallaPrincipal);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(), "Error al guardar datos: " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                });
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Error al registrar: " + task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}