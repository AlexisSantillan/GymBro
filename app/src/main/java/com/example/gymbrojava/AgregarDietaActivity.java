package com.example.gymbrojava;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    private Spinner spinnerObjetivo;
    private Button buttonAgregarComida, buttonAgregarDieta, buttonAtras;
    private LinearLayout layoutComidas;
    private List<View> comidasViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_dieta);

        // Inicializar vistas
        editTextNombreDieta = findViewById(R.id.editTextNombreDieta);
        editTextCalorias = findViewById(R.id.editTextCalorias);
        editTextDuracion = findViewById(R.id.editTextDuracion);
        spinnerObjetivo = findViewById(R.id.spinnerObjetivo);
        buttonAgregarComida = findViewById(R.id.buttonAgregarComida);
        buttonAgregarDieta = findViewById(R.id.buttonAgregarDieta);
        buttonAtras = findViewById(R.id.buttonAtras);
        layoutComidas = findViewById(R.id.layoutComidas);

        comidasViews = new ArrayList<>();

        // Configurar spinner
        setupSpinner();

        // Configurar listeners
        buttonAgregarComida.setOnClickListener(v -> agregarComidaView());
        buttonAgregarDieta.setOnClickListener(v -> guardarDieta());
        buttonAtras.setOnClickListener(v -> finish());
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
                Toast.makeText(AgregarDietaActivity.this, "Objetivo seleccionado: " + selectedObjetivo, Toast.LENGTH_SHORT).show();

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
        View comidaView = getLayoutInflater().inflate(R.layout.item_comida, null);
        layoutComidas.addView(comidaView);
        comidasViews.add(comidaView);

        // Configurar el TimePicker para el horario de la comida
        EditText editTextHorario = comidaView.findViewById(R.id.editTextHorario);
        setupTimePicker(editTextHorario);
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

        if (nombreDieta.isEmpty() || calorias.isEmpty() || duracion.isEmpty() || comidasViews.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos y agregue al menos una comida", Toast.LENGTH_SHORT).show();
            return;
        }

        // Aquí iría la lógica para guardar la dieta en la base de datos
        // Por ejemplo:
        // Dieta nuevaDieta = new Dieta(nombreDieta, objetivo, Integer.parseInt(calorias), Integer.parseInt(duracion));
        // long dietaId = database.insertDieta(nuevaDieta);
        //
        // for (View comidaView : comidasViews) {
        //     String nombreComida = ((EditText) comidaView.findViewById(R.id.editTextNombreComida)).getText().toString();
        //     String horario = ((EditText) comidaView.findViewById(R.id.editTextHorario)).getText().toString();
        //     String caloriasComida = ((EditText) comidaView.findViewById(R.id.editTextCaloriasComida)).getText().toString();
        //
        //     Comida nuevaComida = new Comida(dietaId, nombreComida, horario, Integer.parseInt(caloriasComida));
        //     database.insertComida(nuevaComida);
        // }

        Toast.makeText(this, "Dieta guardada: " + nombreDieta + ", Objetivo: " + objetivo, Toast.LENGTH_LONG).show();

        // Limpia los campos después de guardar
        editTextNombreDieta.setText("");
        editTextCalorias.setText("");
        editTextDuracion.setText("");
        layoutComidas.removeAllViews();
        comidasViews.clear();
    }
}