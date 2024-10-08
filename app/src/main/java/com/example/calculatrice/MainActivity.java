package com.example.calculatrice;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView resultText;
    private double firstValue = 0;
    private String operator = "";
    private boolean isNewOp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        resultText = findViewById(R.id.resultat);

        View.OnClickListener numberListener = v -> {
            Button b = (Button) v;
            if (isNewOp) {
                resultText.setText(b.getText());
                isNewOp = false;
            } else {
                resultText.append(b.getText().toString());
            }
        };

        findViewById(R.id.bouton_0).setOnClickListener(numberListener);
        findViewById(R.id.bouton_1).setOnClickListener(numberListener);
        findViewById(R.id.bouton_2).setOnClickListener(numberListener);
        findViewById(R.id.bouton_3).setOnClickListener(numberListener);
        findViewById(R.id.bouton_4).setOnClickListener(numberListener);
        findViewById(R.id.bouton_5).setOnClickListener(numberListener);
        findViewById(R.id.bouton_6).setOnClickListener(numberListener);
        findViewById(R.id.bouton_7).setOnClickListener(numberListener);
        findViewById(R.id.bouton_8).setOnClickListener(numberListener);
        findViewById(R.id.bouton_9).setOnClickListener(numberListener);

        findViewById(R.id.bouton_plus).setOnClickListener(v -> operatorPressed("+"));
        findViewById(R.id.bouton_moins).setOnClickListener(v -> operatorPressed("-"));
        findViewById(R.id.bouton_fois).setOnClickListener(v -> operatorPressed("*"));

        findViewById(R.id.bouton_egal).setOnClickListener(v -> calculateResult());

        findViewById(R.id.bouton_virgule).setOnClickListener(v -> {
            if (!resultText.getText().toString().contains(".")) {
                resultText.append(".");
            }
        });
    }

    private void operatorPressed(String op) {
        try {
            firstValue = Double.parseDouble(resultText.getText().toString());
        } catch (NumberFormatException e) {
            resultText.setText(getString(R.string.error));
        }
        operator = op;
        isNewOp = true;
    }

    private void calculateResult() {
        double secondValue;
        try {
            secondValue = Double.parseDouble(resultText.getText().toString());
        } catch (NumberFormatException e) {
            resultText.setText(getString(R.string.error));
            return;
        }

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
        }
        resultText.setText(String.valueOf(result));
        isNewOp = true;
    }
}