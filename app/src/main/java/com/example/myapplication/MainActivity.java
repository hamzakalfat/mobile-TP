package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
EditText nom, prix;
Button b;
    Helper h=new Helper(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nom=findViewById(R.id.nom);
        prix=findViewById(R.id.prix);
        b=findViewById(R.id.button);

        b.setOnClickListener(view -> {
            Produit p=new Produit(nom.getText().toString(), Double.parseDouble(prix.getText().toString()));

            h.insertBiens(p);
            Intent intent=new Intent(MainActivity.this, ListBiens.class);
            startActivity(intent);

        });
    }
}