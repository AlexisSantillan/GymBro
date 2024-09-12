package com.example.gymbro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class Login : AppCompatActivity() {
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupFullscreen()
        setupBiometricAuthentication()

        findViewById<Button>(R.id.buttonIniciarSesion).setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }

    private fun setupFullscreen() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun setupBiometricAuthentication() {
        val executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(applicationContext, "Autenticación exitosa!", Toast.LENGTH_SHORT).show()
                    // Redirigir a otra actividad si la autenticación es exitosa
                    val intent = Intent(this@Login, PantallaInicio::class.java)
                    startActivity(intent)
                    finish()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Autenticación fallida", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                   showLoginDialog()
                    }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticación biométrica")
            .setSubtitle("Inicia sesión usando tu huella dactilar")
            .setNegativeButtonText("Usar otro método")
            .build()
    }

    private fun showLoginDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.dialog_login, null)

        // Inicializamos los elementos del layout
        val userEditText = view.findViewById<EditText>(R.id.editTextUser)
        val passwordEditText = view.findViewById<EditText>(R.id.editTextContraseña)
        val loginButton = view.findViewById<Button>(R.id.buttonLogin)

        // Creamos el diálogo
        val dialog = builder.setView(view).create()

        // Manejamos el clic del botón de login
        loginButton.setOnClickListener {
            val username = userEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username == "admin" && password == "1234") {
                val intent = Intent(this, PantallaInicio::class.java)
                startActivity(intent)
                finish()
                dialog.dismiss() // Cerramos el diálogo
            } else {
                Toast.makeText(applicationContext, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }

        // Manejamos el clic del botón de cancelar
        val cancelButton = view.findViewById<Button>(R.id.buttonCancel)
        cancelButton.setOnClickListener {
            dialog.dismiss() // Cierra el diálogo al cancelar
        }

        // Mostramos el diálogo
        dialog.show()
    }


}
