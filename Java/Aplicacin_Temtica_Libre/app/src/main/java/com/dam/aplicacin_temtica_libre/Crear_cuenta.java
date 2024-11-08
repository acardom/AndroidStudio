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

    private EditText etNombre, etApellido, etCorreo, etUsuario, etContraseña, etContraseñaConfirmar;
    private RadioGroup rgGenero;
    private int rdGenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        // Inicializar EditTexts
        etNombre = findViewById(R.id.et_nombre);
        etApellido = findViewById(R.id.et_apellido);
        etCorreo = findViewById(R.id.et_correo);
        etUsuario = findViewById(R.id.et_usuario);
        etContraseña = findViewById(R.id.et_contraseñaCrear);
        etContraseñaConfirmar = findViewById(R.id.et_contraseñaVerificar);

        // Añadir TextWatchers para manejar el hint y el color
        addTextWatchers();
    }

    public void Crear(View view) {
        rgGenero = findViewById(R.id.rg_genero);
        rdGenero = rgGenero.getCheckedRadioButtonId();

        boolean allFieldsValid = true; // Flag para validar todos los campos

        // Verificar campos vacíos
        if (etNombre.getText().toString().trim().isEmpty()) {
            setErrorHint(etNombre, "❗ Nombre (requerido)");
            allFieldsValid = false;
        }
        if (etApellido.getText().toString().trim().isEmpty()) {
            setErrorHint(etApellido, "❗ Apellido (requerido)");
            allFieldsValid = false;
        }
        if (etCorreo.getText().toString().trim().isEmpty()) {
            setErrorHint(etCorreo, "❗ Correo (requerido)");
            allFieldsValid = false;
        } else if (!isValidEmail(etCorreo.getText().toString().trim())) {
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
        } else if (!etContraseña.getText().toString().trim().equals(etContraseñaConfirmar.getText().toString().trim())) {
            etContraseñaConfirmar.setText(""); // Borrar el texto en el campo de confirmación
            setErrorHint(etContraseñaConfirmar, "❗ Las contraseñas no coinciden");
            allFieldsValid = false;
        }

        // Validar género
        if (rdGenero == -1) {
            Toast.makeText(this, "Por favor selecciona un género", Toast.LENGTH_SHORT).show();
            allFieldsValid = false;
        }

        if (allFieldsValid) {
            guardarDatos();

            RadioButton radioButton = findViewById(rdGenero);
            String selectedValue = radioButton.getText().toString();
            Toast.makeText(this, "Seleccionado: " + selectedValue, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void guardarDatos() {
        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        String correo = etCorreo.getText().toString();
        String usuario = etUsuario.getText().toString();
        String contraseña = etContraseña.getText().toString();
        String genero = ((RadioButton) findViewById(rdGenero)).getText().toString();

        SharedPreferences preferencias = getSharedPreferences("usuarios", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferencias.edit();

        // Guardar cada dato utilizando una clave única
        obj_editor.putString("nombre_" + usuario, nombre);
        obj_editor.putString("apellido_" + usuario, apellido);
        obj_editor.putString("correo_" + usuario, correo);
        obj_editor.putString("usuario_" + usuario, usuario);
        obj_editor.putString("contraseña_" + usuario, contraseña);
        obj_editor.putString("genero_" + usuario, genero);
        obj_editor.commit(); // Utiliza commit() para guardar los datos

        Toast.makeText(this, "Los datos han sido guardados", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private void setErrorHint(EditText editText, String errorMessage) {
        editText.setHint(errorMessage);
        editText.setHintTextColor(Color.RED);
    }

    // Método para validar el correo
    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        return email.matches(emailPattern);
    }

    private void addTextWatchers() {
        etNombre.addTextChangedListener(new GenericTextWatcher(etNombre, "Introduzca su nombre"));
        etApellido.addTextChangedListener(new GenericTextWatcher(etApellido, "Introduzca su apellido"));
        etCorreo.addTextChangedListener(new GenericTextWatcher(etCorreo, "Introduzca su Correo"));
        etUsuario.addTextChangedListener(new GenericTextWatcher(etUsuario, "Introduzca su usuario"));
        etContraseña.addTextChangedListener(new GenericTextWatcher(etContraseña, "Introduzca su contraseña"));
        etContraseñaConfirmar.addTextChangedListener(new GenericTextWatcher(etContraseñaConfirmar, "Verifique su contraseña"));
    }

    private class GenericTextWatcher implements TextWatcher {
        private final EditText editText;
        private final String originalHint;

        GenericTextWatcher(EditText editText, String originalHint) {
            this.editText = editText;
            this.originalHint = originalHint; // Guardamos el hint original
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().isEmpty()) {
                editText.setHint(originalHint); // Restablecer el hint original si está vacío
                editText.setHintTextColor(Color.parseColor("#ffc340")); // Restablecer el color del hint
            } else {
                editText.setHint(""); // Eliminar el hint si hay texto
            }
        }
    }
}
