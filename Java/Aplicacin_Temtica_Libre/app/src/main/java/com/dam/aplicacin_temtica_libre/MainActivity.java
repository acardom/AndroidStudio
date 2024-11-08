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

    private EditText etUsuario, et_contraseñaCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etUsuario = findViewById(R.id.et_usuario);
        et_contraseñaCrear = findViewById(R.id.et_contraseñaCrear);
    }

    public void Crear_Cuenta (View view){
        Intent i = new Intent(this, Crear_cuenta.class);
        startActivity(i);
    }

    public void iniciarSesion(View view) {
        String usuario = etUsuario.getText().toString().trim();
        String contraseña = et_contraseñaCrear.getText().toString().trim();

        // Obtener SharedPreferences
        SharedPreferences preferencias = getSharedPreferences("usuarios", Context.MODE_PRIVATE);

        // Recuperar los datos almacenados
        String storedUsuario = preferencias.getString("usuario_" + usuario, null);
        String storedContraseña = preferencias.getString("contraseña_" + usuario, null);

        // Verificar si el usuario y la contraseña coinciden
        if (storedUsuario != null && storedContraseña != null) {
            if (contraseña.equals(storedContraseña)) {
                Intent intent = new Intent(this, Perfil_usuario.class);
                intent.putExtra("usuario", usuario); // Pasa el nombre de usuario
                startActivity(intent);
                finish();
                // Iniciar la siguiente actividad o realizar otra acción
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
        }
    }

}