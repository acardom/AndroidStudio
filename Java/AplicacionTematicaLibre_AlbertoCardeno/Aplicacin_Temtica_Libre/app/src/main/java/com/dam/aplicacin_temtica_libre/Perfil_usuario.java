package com.dam.aplicacin_temtica_libre;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Perfil_usuario extends AppCompatActivity {

    // Declaración de variables para los elementos de la interfaz
    private TextView tvNombre, tvApellido, tvCorreo, tvUsuario, tvContraseña, tvGenero, tvHola; // TextViews para mostrar datos del usuario
    private String usuario; // Variable para almacenar el nombre de usuario
    private Button btnBorrarCuenta; // Botón para borrar la cuenta

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil); // Establecer el diseño de la actividad

        // Inicializar los TextViews y el botón desde el layout
        tvHola = findViewById(R.id.tv_hola);
        tvNombre = findViewById(R.id.tvNombre);
        tvApellido = findViewById(R.id.tvApellido);
        tvCorreo = findViewById(R.id.tvCorreo);
        tvUsuario = findViewById(R.id.tvUsuario);
        tvContraseña = findViewById(R.id.tvContraseña);
        tvGenero = findViewById(R.id.tvGenero);
        btnBorrarCuenta = findViewById(R.id.btnBorrarCuenta);

        // Obtener datos del usuario desde SharedPreferences
        SharedPreferences preferencias = getSharedPreferences("usuarios", Context.MODE_PRIVATE);
        usuario = getIntent().getStringExtra("usuario"); // Obtener el nombre de usuario de la Intent

        // Recuperar datos almacenados, proporcionando "No disponible" como valor por defecto si no existen
        String nombre = preferencias.getString("nombre_" + usuario, "No disponible");
        String apellido = preferencias.getString("apellido_" + usuario, "No disponible");
        String correo = preferencias.getString("correo_" + usuario, "No disponible");
        String contraseña = preferencias.getString("contraseña_" + usuario, "No disponible");
        String genero = preferencias.getString("genero_" + usuario, "No disponible");

        // Mostrar los datos recuperados en los TextViews
        tvHola.setText("Hola " + nombre + " " + apellido); // Saludo al usuario
        tvNombre.setText(Html.fromHtml("<u>Nombre: " + nombre + "</u>")); // Mostrar nombre
        tvApellido.setText(Html.fromHtml("<u>Apellido: " + apellido + "</u>")); // Mostrar apellido
        tvUsuario.setText(Html.fromHtml("<u>Usuario: " + usuario + "</u>")); // Mostrar usuario
        tvCorreo.setText(Html.fromHtml("<u>Correo: " + correo + "</u>")); // Mostrar correo
        tvContraseña.setText(Html.fromHtml("<u>Contraseña: " + contraseña + "</u>")); // Mostrar contraseña (considerar ocultar esta información)
        tvGenero.setText(Html.fromHtml("<u>Género: " + genero + "</u>")); // Mostrar género

        // Configurar el listener para el botón de borrar cuenta
        btnBorrarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarCuenta(usuario); // Llamar al método para borrar la cuenta del usuario
            }
        });
    }

    // Método para volver a la actividad principal
    public void volver_main(View view) {
        Intent i = new Intent(this, Inicia.class); // Crear un Intent para iniciar la actividad principal
        i.putExtra("usuario", usuario); // Pasar el nombre de usuario
        startActivity(i); // Iniciar la actividad
    }

    // Método para cerrar sesión y regresar a la pantalla de inicio de sesión
    public void CerrarSesion(View view) {
        Intent i = new Intent(this, MainActivity.class); // Crear un Intent para la actividad de inicio de sesión
        startActivity(i); // Iniciar la actividad
    }

    // Método para borrar la cuenta del usuario
    private void borrarCuenta(String usuario) {
        // Obtener las preferencias compartidas
        SharedPreferences preferencias = getSharedPreferences("usuarios", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit(); // Editor para modificar las preferencias

        // Remover los datos del usuario
        editor.remove("nombre_" + usuario);
        editor.remove("apellido_" + usuario);
        editor.remove("usuario_" + usuario);
        editor.remove("correo_" + usuario);
        editor.remove("contraseña_" + usuario);
        editor.remove("genero_" + usuario);
        editor.apply(); // Aplicar los cambios

        Toast.makeText(this, "Cuenta borrada exitosamente", Toast.LENGTH_SHORT).show(); // Mensaje de éxito

        // Regresar a la actividad de inicio de sesión
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent); // Iniciar la actividad de inicio de sesión
        finish(); // Finalizar esta actividad
    }
}
