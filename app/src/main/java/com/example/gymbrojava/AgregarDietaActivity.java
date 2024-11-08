package com.example.gymbrojava;

import android.app.TimePickerDialog;
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
import java.util.Calendar;
import java.util.List;

public class AgregarDietaActivity extends AppCompatActivity {

    private EditText editTextNombreDieta, editTextCalorias, editTextDuracion;
    private AutoCompleteTextView autoCompleteUsuario;
    private Spinner spinnerObjetivo;
    private Button buttonAgregarComida, buttonAgregarDieta, buttonAtras;
    private LinearLayout layoutComidas;
    private List<View> comidasViews;
    private List<Usuario> listaUsuarios;
    private Usuario usuarioSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_dieta);

        // Inicializar vistas
        editTextNombreDieta = findViewById(R.id.editTextNombreDieta);
        autoCompleteUsuario = findViewById(R.id.autoCompleteUsuario);
        editTextCalorias = findViewById(R.id.editTextCalorias);
        editTextDuracion = findViewById(R.id.editTextDuracion);
        spinnerObjetivo = findViewById(R.id.spinnerObjetivo);
        buttonAgregarComida = findViewById(R.id.buttonAgregarComida);
        buttonAgregarDieta = findViewById(R.id.buttonAgregarDieta);
        buttonAtras = findViewById(R.id.buttonAtras);
        layoutComidas = findViewById(R.id.layoutComidas);

        comidasViews = new ArrayList<>();
        listaUsuarios = new ArrayList<>();

        // Cargar usuarios (esto debería venir de tu base de datos)
        cargarUsuarios();

        // Configurar AutoCompleteTextView
        setupAutoComplete();

        // Configurar spinner
        setupSpinner();

        // Configurar listeners
        buttonAgregarComida.setOnClickListener(v -> agregarComidaView());
        buttonAgregarDieta.setOnClickListener(v -> guardarDieta());
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
            Toast.makeText(AgregarDietaActivity.this,
                    "Usuario seleccionado: " + usuarioSeleccionado.getNombre(),
                    Toast.LENGTH_SHORT).show();
        });
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> objetivoAdapter = ArrayAdapter.createFromResource(this,
                R.array.objetivos_dieta_array, android.R.layout.simple_spinner_item);
        objetivoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerObjetivo.setAdapter(objetivoAdapter);

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
    }

    private void agregarComidaView() {
        try {
            View comidaView = getLayoutInflater().inflate(R.layout.item_comida, null);
            layoutComidas.addView(comidaView);
            comidasViews.add(comidaView);

            EditText editTextHorario = comidaView.findViewById(R.id.editTextHorario);
            setupTimePicker(editTextHorario);

            // Agregar botón para eliminar comida
            Button buttonEliminar = comidaView.findViewById(R.id.buttonEliminarComida);
            if (buttonEliminar != null) {
                buttonEliminar.setOnClickListener(v -> {
                    layoutComidas.removeView(comidaView);
                    comidasViews.remove(comidaView);
                });
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error al agregar comida: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void setupTimePicker(EditText editTextHorario) {
        editTextHorario.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    AgregarDietaActivity.this,
                    (view, selectedHour, selectedMinute) -> {
                        String selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute);
                        editTextHorario.setText(selectedTime);
                    }, hour, minute, true);
            timePickerDialog.show();
        });
    }

    private void guardarDieta() {
        String nombreDieta = editTextNombreDieta.getText().toString().trim();
        String objetivo = spinnerObjetivo.getSelectedItem().toString();
        String calorias = editTextCalorias.getText().toString().trim();
        String duracion = editTextDuracion.getText().toString().trim();

        if (nombreDieta.isEmpty() || calorias.isEmpty() || duracion.isEmpty() ||
                comidasViews.isEmpty() || usuarioSeleccionado == null) {
            Toast.makeText(this,
                    "Por favor, complete todos los campos, seleccione un usuario y agregue al menos una comida",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // Aquí iría la lógica para guardar la dieta en la base de datos
        // Por ejemplo:
        // Dieta nuevaDieta = new Dieta(
        //     nombreDieta,
        //     objetivo,
        //     Integer.parseInt(calorias),
        //     Integer.parseInt(duracion),
        //     usuarioSeleccionado.getId()
        // );
        // long dietaId = database.insertDieta(nuevaDieta);

        Toast.makeText(this,
                "Dieta guardada para usuario: " + usuarioSeleccionado.getNombre() +
                        "\nDieta: " + nombreDieta +
                        "\nObjetivo: " + objetivo,
                Toast.LENGTH_LONG).show();

        // Limpia los campos después de guardar
        limpiarCampos();
    }

    private void limpiarCampos() {
        editTextNombreDieta.setText("");
        autoCompleteUsuario.setText("");
        editTextCalorias.setText("");
        editTextDuracion.setText("");
        layoutComidas.removeAllViews();
        comidasViews.clear();
        usuarioSeleccionado = null;
    }
}