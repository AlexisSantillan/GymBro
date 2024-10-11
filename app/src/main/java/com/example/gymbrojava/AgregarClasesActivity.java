package com.example.gymbrojava;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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

import java.util.Calendar;

public class AgregarClasesActivity extends AppCompatActivity {

    private EditText editTextNombreClase, editTextDiaClase, editTextHorarioClase, editTextCupoMaximo, editTextCoach;
    private Button buttonAgregarClase, buttonAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_clases);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar vistas
        editTextNombreClase = findViewById(R.id.editTextNombreClase);
        editTextDiaClase = findViewById(R.id.editTextDiaClase);
        editTextHorarioClase = findViewById(R.id.editTextHorarioClase);
        editTextCupoMaximo = findViewById(R.id.editTextCupoMaximo);
        editTextCoach = findViewById(R.id.editTextCoach);
        buttonAgregarClase = findViewById(R.id.buttonAgregarClase);
        buttonAtras = findViewById(R.id.buttonAtras);

        // Configurar listeners para los campos de fecha y hora
        setupDatePicker();
        setupTimePicker();

        // Configurar listener para el botón de agregar clase
        buttonAgregarClase.setOnClickListener(v -> agregarClase());

        // Configurar listener para el botón de atrás
        buttonAtras.setOnClickListener(this::openPantallaInicio);
    }

    private void setupDatePicker() {
        editTextDiaClase.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AgregarClasesActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        editTextDiaClase.setText(selectedDate);
                    }, year, month, day);
            datePickerDialog.show();
        });
    }

    private void setupTimePicker() {
        editTextHorarioClase.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    AgregarClasesActivity.this,
                    (view, selectedHour, selectedMinute) -> {
                        String selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute);
                        editTextHorarioClase.setText(selectedTime);
                    }, hour, minute, true);
            timePickerDialog.show();
        });
    }

    private void agregarClase() {
        String nombreClase = editTextNombreClase.getText().toString().trim();
        String diaClase = editTextDiaClase.getText().toString().trim();
        String horarioClase = editTextHorarioClase.getText().toString().trim();
        String cupoMaximo = editTextCupoMaximo.getText().toString().trim();
        String coach = editTextCoach.getText().toString().trim();

        if (nombreClase.isEmpty() || diaClase.isEmpty() || horarioClase.isEmpty() || cupoMaximo.isEmpty() || coach.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Aquí iría la lógica para guardar la clase en la base de datos
        // Por ahora, solo mostraremos un mensaje de éxito
        Toast.makeText(this, "Clase agregada exitosamente", Toast.LENGTH_SHORT).show();

        // Limpiar los campos después de agregar
        editTextNombreClase.setText("");
        editTextDiaClase.setText("");
        editTextHorarioClase.setText("");
        editTextCupoMaximo.setText("");
        editTextCoach.setText("");
    }

    public void openPantallaInicio(View view) {
        Intent intent = new Intent(this, PantallaInicioActivity.class);
        startActivity(intent);
        finish();
    }
}