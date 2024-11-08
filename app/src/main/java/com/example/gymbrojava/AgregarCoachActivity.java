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
import java.util.ArrayList;
import java.util.List;

public class AgregarCoachActivity extends AppCompatActivity {

    private EditText editTextNombreEntrenador, editTextApellidoEntrenador, editTextEspecialidad;
    private EditText editTextDireccion, editTextTelefono, editTextCorreo;
    private EditText editTextInstagram, editTextFacebook;
    private EditText editTextUsuario, editTextContrasena;
    private Button buttonAgregarCoach, buttonAtras;

    // Lista estática para mantener los coaches en memoria
    private static List<Coach> listaCoaches = new ArrayList<>();

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

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {
        editTextNombreEntrenador = findViewById(R.id.editTextNombreEntrenador);
        editTextApellidoEntrenador = findViewById(R.id.editTextApellidoEntrenador);
        editTextEspecialidad = findViewById(R.id.editTextEspecialidad);
        editTextDireccion = findViewById(R.id.editTextDireccion);
        editTextTelefono = findViewById(R.id.editTextTelefono);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        editTextInstagram = findViewById(R.id.editTextInstagram);
        editTextFacebook = findViewById(R.id.editTextFacebook);
        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextContrasena = findViewById(R.id.editTextContrasena);
        buttonAgregarCoach = findViewById(R.id.buttonAgregarCoach);
        buttonAtras = findViewById(R.id.buttonAtras);
    }

    private void setupListeners() {
        buttonAgregarCoach.setOnClickListener(v -> agregarCoach());
        buttonAtras.setOnClickListener(v -> openPantallaInicio());
    }

    private void agregarCoach() {
        // Obtener todos los valores de los campos
        String nombreEntrenador = editTextNombreEntrenador.getText().toString().trim();
        String apellidoEntrenador = editTextApellidoEntrenador.getText().toString().trim();
        String especialidad = editTextEspecialidad.getText().toString().trim();
        String direccion = editTextDireccion.getText().toString().trim();
        String telefono = editTextTelefono.getText().toString().trim();
        String correo = editTextCorreo.getText().toString().trim();
        String instagram = editTextInstagram.getText().toString().trim();
        String facebook = editTextFacebook.getText().toString().trim();
        String usuario = editTextUsuario.getText().toString().trim();
        String contrasena = editTextContrasena.getText().toString().trim();

        if (!validarCampos(nombreEntrenador, apellidoEntrenador, correo, telefono, usuario, contrasena)) {
            return;
        }

        if (existeUsuario(usuario)) {
            Toast.makeText(this, "Este nombre de usuario ya está en uso", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear y guardar el nuevo coach
        Coach nuevoCoach = new Coach(
                listaCoaches.size() + 1, // ID simple
                nombreEntrenador,
                apellidoEntrenador,
                especialidad,
                direccion,
                telefono,
                correo,
                instagram,
                facebook,
                usuario,
                contrasena,
                "activo"
        );

        listaCoaches.add(nuevoCoach);

        Toast.makeText(this, "Coach agregado exitosamente\nTotal Coaches: " + listaCoaches.size(), Toast.LENGTH_SHORT).show();
        limpiarCampos();
    }

    private boolean validarCampos(String nombre, String apellido, String correo,
                                  String telefono, String usuario, String contrasena) {
        // Validar campos obligatorios
        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() ||
                telefono.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos obligatorios", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validar formato de correo electrónico
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, "Por favor, ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validar formato de teléfono
        if (!android.util.Patterns.PHONE.matcher(telefono).matches()) {
            Toast.makeText(this, "Por favor, ingrese un número de teléfono válido", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validar longitud mínima de la contraseña
        if (contrasena.length() < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validar longitud mínima del usuario
        if (usuario.length() < 4) {
            Toast.makeText(this, "El nombre de usuario debe tener al menos 4 caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean existeUsuario(String usuario) {
        for (Coach coach : listaCoaches) {
            if (coach.getUsuario().equals(usuario)) {
                return true;
            }
        }
        return false;
    }

    // Método público estático para acceder a la lista desde otras actividades
    public static List<Coach> getListaCoaches() {
        return listaCoaches;
    }

    private void limpiarCampos() {
        editTextNombreEntrenador.setText("");
        editTextApellidoEntrenador.setText("");
        editTextEspecialidad.setText("");
        editTextDireccion.setText("");
        editTextTelefono.setText("");
        editTextCorreo.setText("");
        editTextInstagram.setText("");
        editTextFacebook.setText("");
        editTextUsuario.setText("");
        editTextContrasena.setText("");
    }

    private void openPantallaInicio() {
        Intent intent = new Intent(this, AgregarUsuarioActivity.class);
        startActivity(intent);
    }
}