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

    // on crée les variables
    private TextView texteResultat; //donne le texte du résultat
    private double premiereValeur = 0; //valeur du premier nombre de l'opération
    private String operateur = ""; //opérateur de calcul
    private boolean isnewNombre = true; //savoir si c'est un nouveau nombre

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //variable du texte du résultat pour l'affichage
        texteResultat = findViewById(R.id.resultat);

        //on crée la manière dont les boutons de nombre vont être écouté
        View.OnClickListener numberListener = v -> {
            Button b = (Button) v; //on convertit v qui est une view en un type button
            if (isnewNombre) { //si c'est un nouveau nombre
                texteResultat.setText(b.getText()); //on écrit le texte dans le view
                isnewNombre = false; //on change la variable en false pour permettre d'écrire des nombres à plusieurs chiffres
            } else {
                texteResultat.append(b.getText().toString()); //sinon on ajoute le chiffre appuyé à la fin du string qui s'affiche
            }
        };

        //on met des onClickListener sur les boutons avec leurs IDs en utilisant numberListener
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

        //on met des onClickListener sur les boutons d'opérateurs avec leurs IDs en utilisant numberListener
        findViewById(R.id.bouton_plus).setOnClickListener(v -> operateurPressed("+"));
        findViewById(R.id.bouton_moins).setOnClickListener(v -> operateurPressed("-"));
        findViewById(R.id.bouton_fois).setOnClickListener(v -> operateurPressed("*"));

        //listerner pour le bouton égal donc on appelle la méthode qui calcule le résultat
        findViewById(R.id.bouton_egal).setOnClickListener(v -> calculateResult());

        findViewById(R.id.bouton_virgule).setOnClickListener(v -> {
            if (!texteResultat.getText().toString().contains(".")) {
                texteResultat.append(".");
            }
        });
    }

    //méthode appelé quand un opérateur (+, -, *) est appuyé
    private void operateurPressed(String op) {
        try {
            premiereValeur = Double.parseDouble(texteResultat.getText().toString()); //on récupère le premier nombre et on le transforme en string
        } catch (NumberFormatException e) {
            texteResultat.setText(getString(R.string.error)); //si problème de format, on met l'exception
        }
        operateur = op; //on change la variable opérateur
        isnewNombre = true; //on remet la variable à vrai car on va écrire un nouveau nombre
    }

    //méthode quand le bouton égal est pressé pour calculer le résultat
    private void calculateResult() {
        double deuxiemeValeur;
        try {
            deuxiemeValeur = Double.parseDouble(texteResultat.getText().toString()); //on transforme la deuxième valeur en string
        } catch (NumberFormatException e) {
            texteResultat.setText(getString(R.string.error));//sauf si exception comme dans operateurPressed
            return;
        }

        double result = 0;
        //en fonction de l'opérateur on va faire une opération différente
        switch (operateur) {
            case "+":
                result = premiereValeur + deuxiemeValeur;
                break;
            case "-":
                result = premiereValeur - deuxiemeValeur;
                break;
            case "*":
                result = premiereValeur * deuxiemeValeur;
                break;
        }
        texteResultat.setText(String.valueOf(result));//on change la variable du texteResultat pour afficher le résultat
        isnewNombre = true;//on remet à true car on va écrire un nouveau nombre
    }
}