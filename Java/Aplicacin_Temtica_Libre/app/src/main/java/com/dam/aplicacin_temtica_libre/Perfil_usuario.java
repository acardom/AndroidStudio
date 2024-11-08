package com.dam.aplicacin_temtica_libre;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Perfil_usuario extends AppCompatActivity {

    private TextView tvNombre, tvApellido, tvCorreo, tvUsuario, tvContraseña, tvGenero;
    private Button btnBorrarCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        tvNombre = findViewById(R.id.tvNombre);
        tvApellido = findViewById(R.id.tvApellido);
        tvCorreo = findViewById(R.id.tvCorreo);
        tvUsuario = findViewById(R.id.tvUsuario);
        tvContraseña = findViewById(R.id.tvContraseña);
        tvGenero = findViewById(R.id.tvGenero);
        btnBorrarCuenta = findViewById(R.id.btnBorrarCuenta);

        // Obtener datos de SharedPreferences
        SharedPreferences preferencias = getSharedPreferences("usuarios", Context.MODE_PRIVATE);
        String usuario = getIntent().getStringExtra("usuario");
        String nombre = preferencias.getString("nombre_" + usuario, "No disponible");
        String apellido = preferencias.getString("apellido_" + usuario, "No disponible");
        String correo = preferencias.getString("correo_" + usuario, "No disponible");
        String contraseña = preferencias.getString("contraseña_" + usuario, "No disponible");
        String genero = preferencias.getString("genero_" + usuario, "No disponible");

        // Mostrar datos
        tvNombre.setText("Nombre: " + nombre);
        tvApellido.setText("Apellido: " + apellido);
        tvUsuario.setText("Usuario: " + usuario);
        tvCorreo.setText("Correo: " + correo);
        tvContraseña.setText("Contraseña: " + contraseña); // Puedes considerar ocultar esta información
        tvGenero.setText("Género: " + genero);

        btnBorrarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarCuenta(usuario);
            }
        });
    }

    private void borrarCuenta(String usuario) {
        SharedPreferences preferencias = getSharedPreferences("usuarios", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.remove("nombre_" + usuario);
        editor.remove("apellido_" + usuario);
        editor.remove("usuario_" + usuario);
        editor.remove("correo_" + usuario);
        editor.remove("contraseña_" + usuario);
        editor.remove("genero_" + usuario);
        editor.apply();

        Toast.makeText(this, "Cuenta borrada exitosamente", Toast.LENGTH_SHORT).show();
        // Regresar a MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Finaliza PerfilActivity
    }

}