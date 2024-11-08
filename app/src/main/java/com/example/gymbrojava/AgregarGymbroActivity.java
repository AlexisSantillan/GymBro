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
import java.util.ArrayList;
import java.util.List;

public class AgregarGymbroActivity extends AppCompatActivity {

    private EditText editTextNombre, editTextApellido, editTextDireccion,
            editTextCorreo, editTextUsuario, editTextContrasena;
    private Button buttonAgregarGymbro, buttonAtras;

    // Lista estática para mantener los gymbros en memoria
    private static List<Gymbro> listaGymbros = new ArrayList<>();

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
        buttonAgregarGymbro.setOnClickListener(v -> agregarGymbro());
        buttonAtras.setOnClickListener(v -> openPantallaInicio());
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

        // Verificar si el usuario ya existe
        if (existeUsuario(usuario)) {
            Toast.makeText(this, "Este nombre de usuario ya está en uso", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear y guardar el nuevo gymbro
        Gymbro nuevoGymbro = new Gymbro(
                listaGymbros.size() + 1, // ID simple
                nombre,
                apellido,
                direccion,
                correo,
                usuario,
                contrasena,
                LocalDateTime.now(),
                "activo"
        );

        listaGymbros.add(nuevoGymbro);

        // Mostrar mensaje de éxito
        Toast.makeText(this, "Gymbro agregado exitosamente\nTotal Gymbros: " + listaGymbros.size(), Toast.LENGTH_SHORT).show();

        // Limpiar los campos después de guardar
        limpiarCampos();
    }

    private boolean existeUsuario(String usuario) {
        for (Gymbro gymbro : listaGymbros) {
            if (gymbro.getUsuario().equals(usuario)) {
                return true;
            }
        }
        return false;
    }

    // Método público estático para acceder a la lista desde otras actividades
    public static List<Gymbro> getListaGymbros() {
        return listaGymbros;
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
        finish();
    }
}