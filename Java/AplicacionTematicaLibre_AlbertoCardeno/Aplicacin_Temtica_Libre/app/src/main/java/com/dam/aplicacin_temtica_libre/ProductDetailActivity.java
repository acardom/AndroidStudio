package com.dam.aplicacin_temtica_libre;

import android.content.Intent; // Importa la clase Intent para manejar las intenciones de actividades
import android.net.Uri; // Importa la clase Uri para manejar enlaces
import android.os.Bundle; // Importa la clase Bundle para manejar datos de estado
import android.util.Log; // Importa la clase Log para el registro de mensajes de depuración
import android.widget.ImageView; // Importa la clase ImageView para mostrar imágenes
import android.widget.TextView; // Importa la clase TextView para mostrar texto

import androidx.appcompat.app.AppCompatActivity; // Importa la clase AppCompatActivity

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail); // Establece el diseño de la actividad

        // Obtener los datos pasados a través del Intent
        String productName = getIntent().getStringExtra("productName");
        String productPrice = getIntent().getStringExtra("productPrice");
        int productImageResId = getIntent().getIntExtra("productImage", -1);
        String descripcion = getIntent().getStringExtra("productDescripcion");

        // Referenciar las vistas
        TextView textViewName = findViewById(R.id.textViewProductName);
        TextView textViewPrice = findViewById(R.id.textViewProductPrice);
        TextView textViewDescripcion = findViewById(R.id.textViewProductDescripcion);
        ImageView imageViewProduct = findViewById(R.id.imageViewProduct);

        // Para prueba, establece una imagen estática directamente
        Log.d("ProductDetailActivity", "Product Image ID: " + productImageResId);

        // Configurar las vistas con los datos recibidos
        if (productName != null) {
            textViewName.setText(productName); // Muestra el nombre del producto
        }
        if (productPrice != null) {
            textViewPrice.setText(productPrice); // Muestra el precio del producto
        }
        if (productImageResId != -1) {
            imageViewProduct.setImageResource(productImageResId); // Establece la imagen del producto
        }
        if (descripcion != null) {
            textViewDescripcion.setText(descripcion); // Muestra la descripción del producto
        }
    }

    // Método para realizar una llamada telefónica
    public void Llamada() {
        Intent i = new Intent(android.content.Intent.ACTION_CALL,
                Uri.parse("tel:0000000")); // Cambia "0000000" por el número que deseas llamar
        startActivity(i); // Inicia la actividad de llamada
    }
}
