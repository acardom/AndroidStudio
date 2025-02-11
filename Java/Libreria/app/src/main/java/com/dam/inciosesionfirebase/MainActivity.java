package com.dam.inciosesionfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText titulo, autor, fecha;
    private FirebaseFirestore Firestore;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        titulo = findViewById(R.id.et_titulo);
        autor = findViewById(R.id.et_autor);
        fecha = findViewById(R.id.et_año);

        Firestore = FirebaseFirestore.getInstance();
    }

    public void registrar(View view) {
        String tituloValidar = titulo.getText().toString().trim();
        String autorValidar = autor.getText().toString().trim();
        String fechaValidar = fecha.getText().toString().trim();

        if (tituloValidar.isEmpty() || autorValidar.isEmpty() || fechaValidar.isEmpty()) {
            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
        } else {
            registroLibro(tituloValidar, autorValidar, fechaValidar);
        }
    }

    private void registroLibro(String tituloValidar, String autorValidar, String fechaValidar) {

        String documentId = Firestore.collection("libros").document().getId();

        Map<String, Object> libro = new HashMap<>();
        libro.put("id", documentId);
        libro.put("titulo", tituloValidar);
        libro.put("autor", autorValidar);
        libro.put("fecha", fechaValidar);

        Firestore.collection("libros").document(documentId)
                .set(libro)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(getApplicationContext(), "Libro guardado con éxito", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getApplicationContext(), "Error al guardar el libro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    public void Cerrar_sesion(View view) {
        auth.signOut();

        startActivity(new Intent(MainActivity.this, inicio.class));
        finish();
    }

    public void Listar_libros(View view) {
        startActivity(new Intent(MainActivity.this, listar.class));
    }
}

