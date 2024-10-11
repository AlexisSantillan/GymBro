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

import java.time.LocalDateTime;

public class AgregarGymbroActivity extends AppCompatActivity {

    private EditText editTextNombre, editTextCorreo, editTextContrasena, editTextRol;
    private Button buttonAgregarGymbro, buttonAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_gymbro);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar vistas
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        editTextContrasena = findViewById(R.id.editTextContrasena);
        buttonAgregarGymbro = findViewById(R.id.buttonAgregarGymbro);
        buttonAtras = findViewById(R.id.buttonAtras);

        // Configurar listeners
        buttonAgregarGymbro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarGymbro();
            }
        });

        buttonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPantallaInicio();
            }
        });
    }

    private void agregarGymbro() {
        String nombre = editTextNombre.getText().toString().trim();
        String correo = editTextCorreo.getText().toString().trim();
        String contrasena = editTextContrasena.getText().toString().trim();

        if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Aquí iría la lógica para guardar el gymbro en la base de datos
        // Por ejemplo:
        // Gymbro nuevoGymbro = new Gymbro(nombre, correo, contrasena, rol, LocalDateTime.now(), "activo");
        // database.insertGymbro(nuevoGymbro);

        // Por ahora, solo mostraremos un mensaje de éxito
        Toast.makeText(this, "Gymbro agregado exitosamente", Toast.LENGTH_SHORT).show();

        // Limpia los campos después de guardar
        editTextNombre.setText("");
        editTextCorreo.setText("");
        editTextContrasena.setText("");

    }

    private void openPantallaInicio() {
        // Asumiendo que tienes una actividad llamada PantallaInicioActivity
        Intent intent = new Intent(this, AgregarUsuarioActivity.class);
        startActivity(intent);
        finish(); // Cierra esta actividad
    }
}