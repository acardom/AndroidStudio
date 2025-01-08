package com.dam.inciosesionfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    private TextView username, password, mail;
    FirebaseAuth auth;
    FirebaseFirestore Firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        mail = findViewById(R.id.et_mail);
        auth = FirebaseAuth.getInstance();
        Firestore = FirebaseFirestore.getInstance();
    }

    public void registrarme (View view){
        String usernameValidar = username.getText().toString().trim();
        String passwordValidar = password.getText().toString().trim();
        String mailValidar = mail.getText().toString().trim();

        if(usernameValidar.isEmpty() || mailValidar.isEmpty() || passwordValidar.isEmpty()){
            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }else{
            registroUsuario(usernameValidar,mailValidar, passwordValidar);
        }
    }

    private void registroUsuario(String usernameValidar, String mailValidar, String passwordValidar) {

        auth.createUserWithEmailAndPassword(mailValidar, passwordValidar).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = auth.getCurrentUser().getUid();
                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("username", usernameValidar);
                map.put("password", passwordValidar);
                map.put("mail", mailValidar);

                Firestore.collection("usuario").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "guardado", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(Registro.this, inicio.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "error al guardar", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "error al registrar", Toast.LENGTH_SHORT).show();
            }
        });
    }


}