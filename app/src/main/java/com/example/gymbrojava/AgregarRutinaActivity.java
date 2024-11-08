package com.example.gymbrojava;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AgregarRutinaActivity extends AppCompatActivity {

    private EditText editTextNombreRutina, editTextDuracion, editTextFrecuencia;
    private AutoCompleteTextView autoCompleteUsuario;
    private Spinner spinnerObjetivo, spinnerNivelDificultad;
    private Button buttonAgregarEjercicio, buttonAgregarRutina, buttonAtras;
    private LinearLayout layoutEjercicios;
    private List<View> ejerciciosViews;
    private List<Usuario> listaUsuarios;
    private Usuario usuarioSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_rutina);

        // Inicializar vistas
        editTextNombreRutina = findViewById(R.id.editTextNombreRutina);
        autoCompleteUsuario = findViewById(R.id.autoCompleteUsuario);
        editTextDuracion = findViewById(R.id.editTextDuracion);
        editTextFrecuencia = findViewById(R.id.editTextFrecuencia);
        spinnerObjetivo = findViewById(R.id.spinnerObjetivo);
        spinnerNivelDificultad = findViewById(R.id.spinnerNivelDificultad);
        buttonAgregarEjercicio = findViewById(R.id.buttonAgregarEjercicio);
        buttonAgregarRutina = findViewById(R.id.buttonAgregarRutina);
        buttonAtras = findViewById(R.id.buttonAtras);
        layoutEjercicios = findViewById(R.id.layoutEjercicios);

        ejerciciosViews = new ArrayList<>();
        listaUsuarios = new ArrayList<>();

        // Cargar usuarios
        cargarUsuarios();

        // Configurar AutoCompleteTextView
        setupAutoComplete();

        // Configurar spinners
        setupSpinners();

        // Configurar listeners
        buttonAgregarEjercicio.setOnClickListener(v -> agregarEjercicioView());
        buttonAgregarRutina.setOnClickListener(v -> guardarRutina());
        buttonAtras.setOnClickListener(v -> finish());
    }

    private void cargarUsuarios() {
        // Obtener la lista de Gymbros desde AgregarGymbroActivity
        List<Gymbro> gymbros = AgregarGymbroActivity.getListaGymbros();

        // Limpiar la lista actual de usuarios
        listaUsuarios.clear();

        // Convertir cada Gymbro a Usuario y agregarlo a la lista
        for (Gymbro gymbro : gymbros) {
            listaUsuarios.add(new Usuario(
                    gymbro.getId(),
                    gymbro.getNombre(),
                    gymbro.getApellido(),
                    gymbro.getUsuario()
            ));
        }
    }

    private void setupAutoComplete() {
        ArrayAdapter<Usuario> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                listaUsuarios
        );
        autoCompleteUsuario.setAdapter(adapter);

        autoCompleteUsuario.setOnItemClickListener((parent, view, position, id) -> {
            usuarioSeleccionado = (Usuario) parent.getItemAtPosition(position);
            Toast.makeText(AgregarRutinaActivity.this,
                    "Usuario seleccionado: " + usuarioSeleccionado.getNombre(),
                    Toast.LENGTH_SHORT).show();
        });
    }

    private void setupSpinners() {
        // Configurar Spinner de Objetivo
        ArrayAdapter<CharSequence> objetivoAdapter = ArrayAdapter.createFromResource(this,
                R.array.objetivos_array, android.R.layout.simple_spinner_item);
        objetivoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerObjetivo.setAdapter(objetivoAdapter);

        // Configurar Spinner de Nivel de Dificultad
        ArrayAdapter<CharSequence> dificultadAdapter = ArrayAdapter.createFromResource(this,
                R.array.niveles_dificultad_array, android.R.layout.simple_spinner_item);
        dificultadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNivelDificultad.setAdapter(dificultadAdapter);

        // Establecer listeners para los Spinners
        spinnerObjetivo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedObjetivo = parent.getItemAtPosition(position).toString();
                if (view instanceof TextView) {
                    ((TextView) view).setTextColor(Color.WHITE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerNivelDificultad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedDificultad = parent.getItemAtPosition(position).toString();
                if (view instanceof TextView) {
                    ((TextView) view).setTextColor(Color.WHITE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void agregarEjercicioView() {
        View ejercicioView = getLayoutInflater().inflate(R.layout.item_ejercicio, null);
        layoutEjercicios.addView(ejercicioView);
        ejerciciosViews.add(ejercicioView);
    }

    private void guardarRutina() {
        String nombreRutina = editTextNombreRutina.getText().toString().trim();
        String objetivo = spinnerObjetivo.getSelectedItem().toString();
        String nivelDificultad = spinnerNivelDificultad.getSelectedItem().toString();
        String duracion = editTextDuracion.getText().toString().trim();
        String frecuencia = editTextFrecuencia.getText().toString().trim();

        if (nombreRutina.isEmpty() || duracion.isEmpty() || frecuencia.isEmpty() ||
                ejerciciosViews.isEmpty() || usuarioSeleccionado == null) {
            Toast.makeText(this,
                    "Por favor, complete todos los campos, seleccione un usuario y agregue al menos un ejercicio",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // Aquí iría la lógica para guardar la rutina en la base de datos
        // Por ejemplo:
        // Rutina nuevaRutina = new Rutina(
        //     nombreRutina,
        //     objetivo,
        //     nivelDificultad,
        //     Integer.parseInt(duracion),
        //     Integer.parseInt(frecuencia),
        //     usuarioSeleccionado.getId()
        // );
        // long rutinaId = database.insertRutina(nuevaRutina);

        Toast.makeText(this,
                "Rutina guardada para usuario: " + usuarioSeleccionado.getNombre() +
                        "\nRutina: " + nombreRutina +
                        "\nObjetivo: " + objetivo +
                        "\nDificultad: " + nivelDificultad,
                Toast.LENGTH_LONG).show();

        // Limpia los campos después de guardar
        limpiarCampos();
    }

    private void limpiarCampos() {
        editTextNombreRutina.setText("");
        autoCompleteUsuario.setText("");
        editTextDuracion.setText("");
        editTextFrecuencia.setText("");
        layoutEjercicios.removeAllViews();
        ejerciciosViews.clear();
        usuarioSeleccionado = null;
    }
}