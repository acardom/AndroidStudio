package com.dam.aplicacin_temtica_libre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etUsuario, et_contraseñaCrear; // EditText para el nombre de usuario y la contraseña

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Habilitar el modo de borde a borde
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main); // Establecer el diseño de la actividad

        // Configurar el comportamiento de los insets de ventana
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom); // Ajustar los paddings
            return insets;
        });

        // Obtener referencias a los EditText
        etUsuario = findViewById(R.id.et_usuario); // Campo de texto para el nombre de usuario
        et_contraseñaCrear = findViewById(R.id.et_contraseñaCrear); // Campo de texto para la contraseña
    }

    // Método para iniciar la actividad de creación de cuenta
    public void Crear_Cuenta(View view) {
        Intent i = new Intent(this, Crear_cuenta.class); // Crear un nuevo Intent para la actividad de crear cuenta
        startActivity(i); // Iniciar la actividad
    }

    // Método para iniciar sesión
    public void iniciarSesion(View view) {
        // Obtener el texto del EditText y eliminar espacios en blanco al principio y al final
        String usuario = etUsuario.getText().toString().trim(); // Obtener el nombre de usuario
        String contraseña = et_contraseñaCrear.getText().toString().trim(); // Obtener la contraseña

        // Obtener SharedPreferences para almacenar datos de usuarios
        SharedPreferences preferencias = getSharedPreferences("usuarios", Context.MODE_PRIVATE);

        // Recuperar los datos almacenados en SharedPreferences
        String storedUsuario = preferencias.getString("usuario_" + usuario, null); // Obtener el usuario almacenado
        String storedContraseña = preferencias.getString("contraseña_" + usuario, null); // Obtener la contraseña almacenada

        // Verificar si el usuario y la contraseña coinciden
        if (storedUsuario != null && storedContraseña != null) {
            if (contraseña.equals(storedContraseña)) {
                // Si la contraseña es correcta, iniciar la actividad de inicio
                Intent intent = new Intent(this, Inicia.class);
                intent.putExtra("usuario", usuario); // Pasar el nombre de usuario a la siguiente actividad
                startActivity(intent); // Iniciar la actividad
                finish(); // Finalizar esta actividad
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show(); // Mensaje de éxito

            } else {
                // Si la contraseña es incorrecta, mostrar un mensaje
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Si el usuario no se encuentra, mostrar un mensaje
            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
        }
    }
}
