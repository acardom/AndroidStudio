package com.dam.aplicacin_temtica_libre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Inicia extends AppCompatActivity {

    private String usuario; // Variable para almacenar el nombre de usuario

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicia); // Establecer el diseño de la actividad

        // Obtener referencia al TextView donde se mostrará el mensaje de bienvenida
        TextView textView = findViewById(R.id.tx_bienvenida); // Asegúrate de que este ID sea correcto
        usuario = getIntent().getStringExtra("usuario"); // Obtener el nombre de usuario pasado a través del Intent

        // Cambiar el texto del TextView según si el nombre de usuario está disponible
        if (usuario != null) {
            textView.setText("¡ Bienvenido  " + usuario + " !");
        } else {
            textView.setText("¡ Bienvenido !");
        }

        // Crear la lista de productos para mostrar en el RecyclerView
        List<product> productos = new ArrayList<>(); // Usar 'product' para la lista de productos
        // Agregar varios productos a la lista con sus detalles
        productos.add(new product("Chevrolet Corvette Z06", "3.200.000 €", R.drawable.chevrolet_corvette_z06,
                "Originalmente, la designación Z06 era un código de pedido para un paquete de opciones en el año modelo 1963..."));
        productos.add(new product("Cupra Formentor VZ5", "52.900 €", R.drawable.cupra_formentor_vz5,
                "El Cupra Formentor es un SUV compacto deportivo fabricado por la compañía española SEAT..."));
        productos.add(new product("Ferrari 296 GTB", "340.000 €", R.drawable.ferrari_296_gtb,
                "The Ferrari 296 (Type F171) is a sports car built since 2022..."));
        productos.add(new product("Honda Civic Type R", "61.890 €", R.drawable.honda_civic_type_r,
                "El Honda Civic Type R es una versión de automóvil deportivo más radical del Honda Civic..."));
        productos.add(new product("Mclaren Artura", "273.800 €", R.drawable.mclaren_artura,
                "El McLaren Artura es un automóvil superdeportivo híbrido eléctrico enchufable..."));
        productos.add(new product("Mercedes AMG SL", "151.350 €", R.drawable.mercedes_amg_sl,
                "El Mercedes-Benz Clase SL es un automóvil deportivo de lujo fabricado por la firma Mercedes-Benz..."));
        productos.add(new product("Toyota GR86", "36.900 €", R.drawable.toyota_gr86,
                "El Toyota GT86, también conocido como Subaru BRZ y Scion FR-S, son automóviles deportivos..."));
        productos.add(new product("Nissan Z", "1.377.900 €", R.drawable.nissan_z,
                "El Nissan Z es un automóvil deportivo producido por el fabricante japonés Nissan..."));

        // Configurar el RecyclerView con el adaptador
        RecyclerView recyclerView = findViewById(R.id.recyclerView); // Obtener referencia al RecyclerView
        SlideAdapter adapter = new SlideAdapter(productos); // Crear un adaptador para la lista de productos
        recyclerView.setAdapter(adapter); // Establecer el adaptador en el RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Usar LinearLayoutManager para mostrar los elementos uno debajo del otro
    }

    // Método que se llama al hacer clic en la vista del perfil
    public void Ver_Perfil(View view) {
        Intent i = new Intent(this, Perfil_usuario.class); // Crear un nuevo Intent para la actividad de perfil
        i.putExtra("usuario", usuario); // Pasar el nombre de usuario al Intent
        startActivity(i); // Iniciar la actividad de perfil
    }
}
