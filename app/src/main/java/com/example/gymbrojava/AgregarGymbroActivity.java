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

    private EditText editTextNombre, editTextApellido, editTextDireccion,
            editTextCorreo, editTextUsuario, editTextContrasena;
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
        editTextApellido = findViewById(R.id.editTextApellido);
        editTextDireccion = findViewById(R.id.editTextDireccion);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        editTextUsuario = findViewById(R.id.editTextUsuario);
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
        // Obtener valores de todos los campos
        String nombre = editTextNombre.getText().toString().trim();
        String apellido = editTextApellido.getText().toString().trim();
        String direccion = editTextDireccion.getText().toString().trim();
        String correo = editTextCorreo.getText().toString().trim();
        String usuario = editTextUsuario.getText().toString().trim();
        String contrasena = editTextContrasena.getText().toString().trim();

        // Validar que ningún campo esté vacío
        if (nombre.isEmpty() || apellido.isEmpty() || direccion.isEmpty() ||
                correo.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar formato de correo electrónico
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, "Por favor ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar longitud mínima de contraseña
        if (contrasena.length() < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        // Aquí iría la lógica para guardar el gymbro en la base de datos
        // Por ejemplo:
        // Gymbro nuevoGymbro = new Gymbro(nombre, apellido, direccion, correo, usuario, contrasena, LocalDateTime.now(), "activo");
        // database.insertGymbro(nuevoGymbro);

        // Mostrar mensaje de éxito
        Toast.makeText(this, "Gymbro agregado exitosamente", Toast.LENGTH_SHORT).show();

        // Limpiar los campos después de guardar
        limpiarCampos();
    }

    private void limpiarCampos() {
        editTextNombre.setText("");
        editTextApellido.setText("");
        editTextDireccion.setText("");
        editTextCorreo.setText("");
        editTextUsuario.setText("");
        editTextContrasena.setText("");
    }

    private void openPantallaInicio() {
        Intent intent = new Intent(this, AgregarUsuarioActivity.class);
        startActivity(intent);
        finish(); // Cierra esta actividad
    }
}