package com.dam.aplicacin_temtica_libre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Crear_cuenta extends AppCompatActivity {

    // Declaración de variables para los campos de entrada y el grupo de radio
    private EditText etNombre, etApellido, etCorreo, etUsuario, etContraseña, etContraseñaConfirmar;
    private RadioGroup rgGenero; // Grupo para seleccionar el género
    private int rdGenero; // ID del botón de radio seleccionado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta); // Establecer el diseño de la actividad

        // Inicializar EditTexts
        etNombre = findViewById(R.id.et_nombre);
        etApellido = findViewById(R.id.et_apellido);
        etCorreo = findViewById(R.id.et_correo);
        etUsuario = findViewById(R.id.et_usuario);
        etContraseña = findViewById(R.id.et_contraseñaCrear);
        etContraseñaConfirmar = findViewById(R.id.et_contraseñaVerificar);

        // Añadir TextWatchers para manejar el hint y el color
        addTextWatchers(); // Añadir listeners para la validación en tiempo real
    }

    // Método que se llama al hacer clic en el botón para crear cuenta
    public void Crear(View view) {
        rgGenero = findViewById(R.id.rg_genero); // Obtener el grupo de radio de género
        rdGenero = rgGenero.getCheckedRadioButtonId(); // Obtener el ID del botón de radio seleccionado

        boolean allFieldsValid = true; // Flag para validar todos los campos

        // Verificar campos vacíos
        if (etNombre.getText().toString().trim().isEmpty()) {
            setErrorHint(etNombre, "❗ Nombre (requerido)"); // Establecer mensaje de error si está vacío
            allFieldsValid = false; // Marcar como inválido
        }
        if (etApellido.getText().toString().trim().isEmpty()) {
            setErrorHint(etApellido, "❗ Apellido (requerido)");
            allFieldsValid = false;
        }
        if (etCorreo.getText().toString().trim().isEmpty()) {
            setErrorHint(etCorreo, "❗ Correo (requerido)");
            allFieldsValid = false;
        } else if (!isValidEmail(etCorreo.getText().toString().trim())) { // Validar formato de correo
            etCorreo.setText(""); // Borrar el texto en el campo de confirmación
            setErrorHint(etCorreo, "❗ Correo no válido");
            allFieldsValid = false;
        }
        if (etUsuario.getText().toString().trim().isEmpty()) {
            setErrorHint(etUsuario, "❗ Usuario (requerido)");
            allFieldsValid = false;
        }
        if (etContraseña.getText().toString().trim().isEmpty()) {
            setErrorHint(etContraseña, "❗ Contraseña (requerido)");
            allFieldsValid = false;
        }
        if (etContraseñaConfirmar.getText().toString().trim().isEmpty()) {
            setErrorHint(etContraseñaConfirmar, "❗ Verifica Contraseña (requerido)");
            allFieldsValid = false;
        } else if (!etContraseña.getText().toString().trim().equals(etContraseñaConfirmar.getText().toString().trim())) { // Comprobar coincidencia de contraseñas
            etContraseñaConfirmar.setText(""); // Borrar el texto en el campo de confirmación
            setErrorHint(etContraseñaConfirmar, "❗ Las contraseñas no coinciden");
            allFieldsValid = false;
        }

        // Validar género
        if (rdGenero == -1) { // Verificar si no se ha seleccionado un género
            allFieldsValid = false;
        }

        // Si todos los campos son válidos, guardar datos
        if (allFieldsValid) {
            guardarDatos(); // Llamar al método para guardar los datos
            RadioButton radioButton = findViewById(rdGenero); // Obtener el botón de radio seleccionado
            String selectedValue = radioButton.getText().toString(); // Obtener el texto del género seleccionado
        }
    }

    // Método para guardar los datos ingresados en SharedPreferences
    public void guardarDatos() {
        String nombre = etNombre.getText().toString(); // Obtener nombre
        String apellido = etApellido.getText().toString(); // Obtener apellido
        String correo = etCorreo.getText().toString(); // Obtener correo
        String usuario = etUsuario.getText().toString(); // Obtener usuario
        String contraseña = etContraseña.getText().toString(); // Obtener contraseña
        String genero = ((RadioButton) findViewById(rdGenero)).getText().toString(); // Obtener género

        genero = genero.replace("Soy ", "").trim(); // Limpiar el texto del género

        SharedPreferences preferencias = getSharedPreferences("usuarios", Context.MODE_PRIVATE); // Obtener SharedPreferences
        SharedPreferences.Editor obj_editor = preferencias.edit(); // Editor para modificar las preferencias

        // Guardar cada dato utilizando una clave única
        obj_editor.putString("nombre_" + usuario, nombre);
        obj_editor.putString("apellido_" + usuario, apellido);
        obj_editor.putString("correo_" + usuario, correo);
        obj_editor.putString("usuario_" + usuario, usuario);
        obj_editor.putString("contraseña_" + usuario, contraseña);
        obj_editor.putString("genero_" + usuario, genero);
        obj_editor.commit(); // Guardar los datos

        // Mostrar un mensaje de confirmación
        Toast.makeText(this, "Los datos han sido guardados", Toast.LENGTH_SHORT).show();

        // Iniciar la actividad principal después de guardar
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    // Método para establecer un mensaje de error en el campo de texto
    private void setErrorHint(EditText editText, String errorMessage) {
        editText.setHint(errorMessage); // Establecer el mensaje de error como hint
        editText.setHintTextColor(Color.RED); // Cambiar el color del hint a rojo
    }

    // Método para volver a la actividad principal
    public void volver_main(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    // Método para validar el correo
    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"; // Patrón de expresión regular para el correo
        return email.matches(emailPattern); // Validar el correo con el patrón
    }

    // Método para agregar TextWatchers a los campos EditText
    private void addTextWatchers() {
        etNombre.addTextChangedListener(new GenericTextWatcher(etNombre, "Introduzca su nombre"));
        etApellido.addTextChangedListener(new GenericTextWatcher(etApellido, "Introduzca su apellido"));
        etCorreo.addTextChangedListener(new GenericTextWatcher(etCorreo, "Introduzca su Correo"));
        etUsuario.addTextChangedListener(new GenericTextWatcher(etUsuario, "Introduzca su usuario"));
        etContraseña.addTextChangedListener(new GenericTextWatcher(etContraseña, "Introduzca su contraseña"));
        etContraseñaConfirmar.addTextChangedListener(new GenericTextWatcher(etContraseñaConfirmar, "Verifique su contraseña"));
    }

    // Clase interna para manejar cambios en el texto de los EditText
    private class GenericTextWatcher implements TextWatcher {
        private final EditText editText; // EditText al que se le aplica el watcher
        private final String originalHint; // Guardamos el hint original

        // Constructor que inicializa el EditText y su hint
        GenericTextWatcher(EditText editText, String originalHint) {
            this.editText = editText;
            this.originalHint = originalHint; // Guardamos el hint original
        }

        // Método llamado antes de que el texto cambie (no se usa aquí)
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        // Método llamado mientras el texto está cambiando (no se usa aquí)
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        // Método llamado después de que el texto ha cambiado
        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().isEmpty()) { // Si el EditText está vacío
                editText.setHint(originalHint); // Restablecer el hint original
                editText.setHintTextColor(Color.parseColor("#ffc340")); // Cambiar el color del hint
            } else {
                editText.setHint(""); // Eliminar el hint si hay texto
            }
        }
    }
}
