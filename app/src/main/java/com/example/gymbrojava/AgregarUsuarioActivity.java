package com.example.gymbrojava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AgregarUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    // Método para abrir la actividad de Agregar GymBro
    public void openAgregarGymBroActivity(View view) {
        Intent intent = new Intent(this, AgregarGymbroActivity.class);
        startActivity(intent);
    }
    // Método para abrir la actividad de Agregar GymBro
    public void openAgregarCoachActivity(View view) {
        Intent intent = new Intent(this, AgregarCoachActivity.class);
        startActivity(intent);
    }
    public void atras(View view) {
        Intent intent = new Intent(this, PantallaInicioActivity.class);
        startActivity(intent);
    }
}