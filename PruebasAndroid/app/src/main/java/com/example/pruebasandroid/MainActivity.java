package com.example.pruebasandroid;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private final StringBuilder input = new StringBuilder();
    private int num1 = 0;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencia al TextView que simula la pantalla
        tvDisplay = findViewById(R.id.tv_display);

        // Configurar botones numéricos y de operación
        configureButtons();
    }

    private void configureButtons() {
        int[] numberButtons = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
                R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9
        };

        // Configurar los botones numéricos
        for (int id : numberButtons) {
            Button button = findViewById(id);
            button.setOnClickListener(view -> {
                input.append(button.getText());
                updateDisplay();
            });
        }

        // Configurar botones de operación
        findViewById(R.id.btn_add).setOnClickListener(view -> setOperator("+"));
        findViewById(R.id.btn_subtract).setOnClickListener(view -> setOperator("-"));
        findViewById(R.id.btn_multiply).setOnClickListener(view -> setOperator("*"));
        findViewById(R.id.btn_divide).setOnClickListener(view -> setOperator("/"));

        // Botón igual para calcular
        findViewById(R.id.btn_equals).setOnClickListener(view -> calculateResult());

        // Botón de borrar
        findViewById(R.id.btn_clear).setOnClickListener(view -> clearCalculator());
    }

    private void setOperator(String op) {
        if (input.length() > 0) {
            num1 = Integer.parseInt(input.toString());
            operator = op;
            input.setLength(0); // Limpiar el input
            updateDisplay();
        } else {
            Toast.makeText(this, "Introduce un número primero", Toast.LENGTH_SHORT).show();
        }
    }

    private void calculateResult() {
        if (input.length() > 0 && !operator.isEmpty()) {
            int num2 = Integer.parseInt(input.toString());
            int result;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        Toast.makeText(this, "No se puede dividir entre cero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = num1 / num2;
                    break;
                default:
                    return;
            }

            input.setLength(0); // Limpiar el input
            input.append(result);
            operator = ""; // Reiniciar el operador
            updateDisplay();
        } else {
            Toast.makeText(this, "Operación incompleta", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearCalculator() {
        input.setLength(0);
        operator = "";
        num1 = 0;
        updateDisplay();
    }

    private void updateDisplay() {
        tvDisplay.setText(input.toString());
    }
}
