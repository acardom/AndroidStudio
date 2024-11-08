package com.dam.habitos;

import android.app.Activity;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;

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

        //Buscamos los editText por su id
        et1 = findViewById(R.id.et_datos);
        et2 = findViewById(R.id.et_añadir);

        String archivos [] = fileList();

        //si existe mostramos lo que tiene dentro
        if(archivoExiste(archivos, "Habitos.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("Habitos.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String notaCompleta = "";
                while(linea != null){
                    notaCompleta = notaCompleta +  linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                et1.setText(notaCompleta);
            }catch (IOException e){
            }
        }
    }

    //comprobamos si existe
    private boolean archivoExiste(String archivos[], String nombreArchivo){
        boolean res = false;
        for(int i = 0; i < archivos.length; i++){
            if(nombreArchivo.equals(archivos[i])){
                res=true;
            }
        }
        return res;
    }

    //metodo añadir
    public void añadir(View view){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("Habitos.txt", Activity.MODE_PRIVATE));
            String añadir = ((et1.getText().toString())+"· "+(et2.getText().toString())+"\n");
            archivo.write(añadir);
            archivo.flush();
            archivo.close();
        }catch (IOException e){
        }
        Toast.makeText(this, "Se añadio la nueva tarea", Toast.LENGTH_SHORT).show();
        finish();
    }

    //metodo actualizar
    public void actualizar(View view){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("Habitos.txt", Activity.MODE_PRIVATE));
            archivo.write(et1.getText().toString());
            archivo.flush();
            archivo.close();
        }catch (IOException e){
        }
        Toast.makeText(this, "Actualizado correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }

    //metodo borrar
    public void borrar(View view){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("Habitos.txt", Activity.MODE_PRIVATE));
            archivo.write("");
            archivo.flush();
            archivo.close();
        }catch (IOException e){
        }
        Toast.makeText(this, "Se borro todo el contenido", Toast.LENGTH_SHORT).show();
        finish();
    }
}