package com.dam.blogdenotas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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
        et1 = findViewById(R.id.txtNotas);
        String archivos [] = fileList();

        if(archivoExiste(archivos, "blocdenotas.txt")){
            try{
                InputStreamReader archivo = new InputStreamReader(openFileInput("blocdenotas.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String notaCompleta = "";

                while(linea != null){
                    notaCompleta = notaCompleta + linea +"\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                et1.setText(notaCompleta);
            }catch(Exception e){
                e.getMessage();
            }
        }
    }

    private boolean archivoExiste(String archivos[], String nombreArchivo){
        boolean res = false;
        for(int i=0; i<archivos.length; i++){
            if(nombreArchivo.equals(archivos[i])){
                res=true;
            }
        }
        return res;
    }

    public void guardar(View view){
        try{
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("blocdenotas.txt", Activity.MODE_PRIVATE));
            archivo.write(et1.getText().toString());
            archivo.flush();
            archivo.close();
        }catch(IOException e){
            e.getMessage();
        }
        Toast.makeText(this, "Nota guardada", Toast.LENGTH_SHORT).show();
        finish();
    }
}