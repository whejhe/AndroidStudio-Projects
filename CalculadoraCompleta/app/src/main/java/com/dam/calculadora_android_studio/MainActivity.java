package com.dam.calculadora_android_studio;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView resultView;
    private String currentInput = "";
    private String operator = "";
    private double firstValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultView = findViewById(R.id.result);
        // default result = 0
        resultView.setText(getString(R.string.default_result));

        // Configuración de los botones
        setNumberButtonListeners();
        setOperatorButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberButtons = {
                R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3,
                R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7,
                R.id.button_8, R.id.button_9
        };

        View.OnClickListener listener = v -> {
            Button b = (Button) v;
            currentInput += b.getText().toString();
            resultView.setText(currentInput);
        };

        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorButtonListeners() {
        int[] operatorButtons = {
                R.id.button_plus, R.id.button_minus,
                R.id.button_multiply, R.id.button_divide
        };

        View.OnClickListener operatorListener = v -> {
            Button b = (Button) v;
            operator = b.getText().toString();
            firstValue = Double.parseDouble(currentInput);
            currentInput = "";
        };

        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(operatorListener);
        }

        findViewById(R.id.button_equals).setOnClickListener(v -> {
            double secondValue = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstValue + secondValue;
                    break;
                case "-":
                    result = firstValue - secondValue;
                    break;
                case "*":
                    result = firstValue * secondValue;
                    break;
                case "/":
                    if (secondValue != 0) {
                        result = firstValue / secondValue;
                    } else {
                        resultView.setText(getString(R.string.error_message)); 
                        return;
                    }
                    break;
            }

            resultView.setText(String.valueOf(result));
            currentInput = "";
        });

        findViewById(R.id.button_clear).setOnClickListener(v -> {
            currentInput = "";
            // default result = 0
            resultView.setText(getString(R.string.default_result));
        });
    }
}
