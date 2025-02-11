package com.dam.inciosesionfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class inicio extends AppCompatActivity {

    EditText correo, pass;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);

        // Configuración para la barra de estado y navegación.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        correo = findViewById(R.id.et_correo);
        pass = findViewById(R.id.et_contraseña);
        auth = FirebaseAuth.getInstance();

        // Verificar si el usuario ya está autenticado
        if (auth.getCurrentUser() != null) {
            // Si el usuario ya está autenticado, redirigir a la actividad principal
            startActivity(new Intent(inicio.this, MainActivity.class));
            finish(); // Cierra esta actividad
        }
    }

    public void crear (View view) {
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }

    public void iniciarSesion(View view){
        String correoUsuario = correo.getText().toString().trim();
        String passUsuario = pass.getText().toString().trim();
        if(correoUsuario.isEmpty() || passUsuario.isEmpty()){
            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_LONG).show();
        } else {
            inicioUsuario(correoUsuario, passUsuario);
        }
    }

    private void inicioUsuario(String correoUsuario, String passUsuario) {
        auth.signInWithEmailAndPassword(correoUsuario, passUsuario)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(inicio.this, MainActivity.class));
                            Toast.makeText(inicio.this, "Bienvenido/a", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(inicio.this, "Error al iniciar sesión", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(inicio.this, "Error al iniciar sesión", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
