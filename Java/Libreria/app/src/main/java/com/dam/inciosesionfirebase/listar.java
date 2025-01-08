package com.dam.inciosesionfirebase;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class listar extends AppCompatActivity {

    private ListView listView;
    private FirebaseFirestore Firestore;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.list_view_books);
        Firestore = FirebaseFirestore.getInstance();

        bookList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookList);
        listView.setAdapter(adapter);

        loadBooks();
    }

    private void loadBooks() {
        try {
            Firestore.collection("libros").get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        bookList.clear();
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            String titulo = document.getString("titulo");
                            String autor = document.getString("autor");
                            String fecha = document.getString("fecha");

                            if (titulo != null && autor != null && fecha != null) {
                                bookList.add("\nTítulo: " + titulo + "\nAutor: " + autor + "\nFecha: " + fecha + "\n");
                            }
                        }
                        adapter.notifyDataSetChanged();
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Error al cargar libros: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        } catch (Exception e) {
            Toast.makeText(this, "Error inesperado: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
