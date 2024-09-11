package com.example.gymbro

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
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
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
    private fun setupBiometricAuthentication() {
        val executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    // Autenticación exitosa
                    Toast.makeText(applicationContext, "Autenticación exitosa!", Toast.LENGTH_SHORT).show()
                    // Aquí puedes agregar el código para pasar a la siguiente actividad
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Autenticación fallida", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(applicationContext, "Error de autenticación: $errString", Toast.LENGTH_SHORT).show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticación biométrica")
            .setSubtitle("Inicia sesión usando tu huella dactilar")
            .setNegativeButtonText("Cancelar")
            .build()
    }
}