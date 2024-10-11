package com.example.gymbrojava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AgregarCoachActivity extends AppCompatActivity {

    private EditText editTextNombreEntrenador, editTextEspecialidad, editTextCorreo;
    private Button buttonAgregarCoach, buttonAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_coach);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar vistas
        editTextNombreEntrenador = findViewById(R.id.editTextNombreEntrenador);
        editTextEspecialidad = findViewById(R.id.editTextEspecialidad);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        buttonAgregarCoach = findViewById(R.id.buttonAgregarCoach);
        buttonAtras = findViewById(R.id.buttonAtras);

        // Configurar listeners
        buttonAgregarCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarCoach();
            }
        });

        buttonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPantallaInicio();
            }
        });
    }

    private void agregarCoach() {
        String nombreEntrenador = editTextNombreEntrenador.getText().toString().trim();
        String especialidad = editTextEspecialidad.getText().toString().trim();
        String correo = editTextCorreo.getText().toString().trim();

        if (nombreEntrenador.isEmpty() || correo.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Aquí iría la lógica para guardar el coach en la base de datos
        // Por ahora, solo mostraremos un mensaje de éxito
        Toast.makeText(this, "Coach agregado exitosamente", Toast.LENGTH_SHORT).show();

        // Limpia los campos después de guardar
        editTextNombreEntrenador.setText("");
        editTextEspecialidad.setText("");
        editTextCorreo.setText("");
    }

    private void openPantallaInicio() {
        // Asumiendo que tienes una actividad llamada PantallaInicioActivity
        Intent intent = new Intent(this, AgregarUsuarioActivity.class);
        startActivity(intent);

    }
}