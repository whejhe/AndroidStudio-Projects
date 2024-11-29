package com.dam.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private TextView tv1;
    private Spinner sp1;
    /*private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;*/

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

        et1 = (EditText) findViewById(R.id.txt_num1);
        et2 = (EditText) findViewById(R.id.txt_num2);
        tv1 = (TextView) findViewById(R.id.txt_resultado);
        sp1 = findViewById(R.id.desplegable);
        String[] opciones = {"Sumar", "Restar", "Multiplicar", "Dividir"};

        //sp1.setAdapter(new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, opciones));
        sp1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones));

        /*rb1 = findViewById(R.id.rb_sumar);
        rb2 = findViewById(R.id.rb_restar);
        rb3 = findViewById(R.id.rb_mult);
        rb4 = findViewById(R.id.rb_div);*/
    }

    public void calcular(View view) {
        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();
        int num1;
        int num2;
        int resultado = 0;

        try {
            num1 = Integer.parseInt(valor1);
        } catch (Exception e) {
            num1 = 0;

        }
        try {
            num2 = Integer.parseInt(valor2);
        } catch (Exception e) {
            num2 = 0;
        }

        switch (sp1.getSelectedItemPosition()) {
            case 0:
                resultado = num1 + num2;
                break;
            case 1:
                resultado = num1 - num2;
                break;
            case 2:
                resultado = num1 * num2;
                break;
            case 3:
                if (num2 != 0) {
                    resultado = num1 / num2;
                } else resultado = 0;
                break;
        }

        /*
        if (sp1.getSelectedItemPosition() == 0) {
            resultado = num1 + num2;
        } else if (sp1.getSelectedItemPosition() == 1) {
            resultado = num1 - num2;
        } else if (sp1.getSelectedItemPosition() == 2) {
            resultado = num1 * num2;
        } else if (sp1.getSelectedItemPosition() == 3) {
            if (num2 != 0) {
                resultado = num1 / num2;
            } else resultado = 0;
        }*/

        /*if (rb1.isChecked()) {
            resultado = num1 + num2;
        } else if (rb2.isChecked()) {
            resultado = num1 - num2;
        } else if (rb3.isChecked()) {
            resultado = num1 * num2;
        } else if (rb4.isChecked()) {
            if (num2 != 0){
                resultado = num1 / num2;
            }else resultado = 0;
        } else resultado = 0;*/

        tv1.setText("Resultado: " + resultado);
    }

/*
    public void sumar(View view) {
        String valor1 = et1.getText().toString();
        String valor2 = et2.getText().toString();
        int num1;
        int num2;
        int suma;

        try {
            num1 = Integer.parseInt(valor1);
        }catch (Exception e){
            num1 = 0;

        }try {
            num2 = Integer.parseInt(valor2);
        }catch (Exception e){
            num2 = 0;
        }
        suma = num1 + num2;
        tv1.setText("Resultado de la suma: " + suma);
    }*/
}