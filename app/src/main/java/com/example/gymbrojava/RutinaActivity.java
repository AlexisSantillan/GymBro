package com.example.gymbrojava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RutinaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rutina);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Método para abrir la actividad de Clases
    public void openClasesActivity(View view) {
        Intent intent = new Intent(this, ClasesActivity.class);
        startActivity(intent);
        finish();
    }

    // Método para abrir la actividad de Rutina
    public void openRutinaActivity(View view) {
        Intent intent = new Intent(this, RutinaActivity.class);
        startActivity(intent);
        finish();
    }

    // Método para abrir la actividad de Dieta
    public void openDietaActivity(View view) {
        Intent intent = new Intent(this, DietaActivity.class);
        startActivity(intent);
        finish();
    }
}