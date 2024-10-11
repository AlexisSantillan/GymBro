package com.example.gymbrojava;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import java.util.concurrent.Executor;

public class LoginActivity extends AppCompatActivity {

    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupFullscreen();
        setupBiometricAuthentication();

        findViewById(R.id.buttonIniciarSesion).setOnClickListener(v -> biometricPrompt.authenticate(promptInfo));
    }

    private void setupFullscreen() {
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        WindowInsetsControllerCompat controller = new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
        controller.hide(WindowInsetsCompat.Type.systemBars());
        controller.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
    }

    private void setupBiometricAuthentication() {
        Executor executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(), "Autenticación exitosa!", Toast.LENGTH_SHORT).show();
                // Redirigir a otra actividad si la autenticación es exitosa
                Intent intent = new Intent(LoginActivity.this, ClasesActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Autenticación fallida", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                showLoginDialog();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Autenticación biométrica")
                .setSubtitle("Inicia sesión usando tu huella dactilar")
                .setNegativeButtonText("Usar otro método")
                .build();
    }

    private void showLoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        getLayoutInflater();
        final EditText userEditText;
        final EditText passwordEditText;
        final Button loginButton;

        // Inflar el layout para el diálogo
        final android.view.View view = getLayoutInflater().inflate(R.layout.dialog_login, null);

        // Inicializamos los elementos del layout
        userEditText = view.findViewById(R.id.editTextUser);
        passwordEditText = view.findViewById(R.id.editTextContraseña);
        loginButton = view.findViewById(R.id.buttonLogin);

        // Creamos el diálogo
        final AlertDialog dialog = builder.setView(view).create();

        // Manejamos el clic del botón de login
        loginButton.setOnClickListener(v -> {
            String username = userEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (username.equals("admin") && password.equals("1234")) {
                Intent intent = new Intent(LoginActivity.this, PantallaInicioActivity.class);
                startActivity(intent);
                finish();
                dialog.dismiss(); // Cerramos el diálogo
            }
            else if (username.equals("user") && password.equals("1234")) {
                Intent intent = new Intent(LoginActivity.this, ClasesActivity.class);
                startActivity(intent);
                finish();
                dialog.dismiss(); // Cerramos el diálogo
            }
            else {
                Toast.makeText(getApplicationContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        });

        // Manejamos el clic del botón de cancelar
        Button cancelButton = view.findViewById(R.id.buttonCancel);
        cancelButton.setOnClickListener(v -> dialog.dismiss()); // Cierra el diálogo al cancelar

        // Mostramos el diálogo
        dialog.show();
    }
}
