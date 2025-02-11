package com.dam.aplicacin_temtica_libre;

import android.content.Intent; // Importa la clase Intent para manejar intenciones de actividades
import android.view.LayoutInflater; // Importa la clase LayoutInflater para inflar vistas
import android.view.View; // Importa la clase View para manejar vistas
import android.view.ViewGroup; // Importa la clase ViewGroup para manejar grupos de vistas
import android.widget.ImageView; // Importa la clase ImageView para mostrar imágenes
import android.widget.TextView; // Importa la clase TextView para mostrar texto

import androidx.annotation.NonNull; // Importa la anotación NonNull para manejar valores nulos
import androidx.recyclerview.widget.RecyclerView; // Importa la clase RecyclerView para listas eficientes

import java.util.List; // Importa la clase List para manejar colecciones

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.SlideViewHolder> {

    private final List<product> productList; // Lista de productos

    // Constructor que recibe la lista de productos
    public SlideAdapter(List<product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout de cada elemento de la lista
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slide, parent, false);
        return new SlideViewHolder(view); // Retorna un nuevo ViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull SlideViewHolder holder, int position) {
        // Configurar el contenido de cada slide
        product product = productList.get(position); // Obtiene el producto en la posición actual
        holder.textViewName.setText(product.getName()); // Establece el nombre del producto
        holder.textViewPrice.setText(product.getPrice()); // Establece el precio del producto
        holder.imageViewSlide.setImageResource(product.getImageResId()); // Establece la imagen del producto

        // Configura un listener para la imagen que inicia la actividad de detalle del producto al hacer clic
        holder.imageViewSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductDetailActivity.class);
                // Pasar los datos del producto a la actividad de detalle
                intent.putExtra("productName", product.getName());
                intent.putExtra("productPrice", product.getPrice());
                intent.putExtra("productImage", product.getImageResId());
                intent.putExtra("productDescripcion", product.getDescripcion());
                // Iniciar la nueva actividad
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size(); // Retorna el tamaño de la lista de productos
    }

    // Clase interna ViewHolder que maneja las vistas de cada elemento
    static class SlideViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName; // Vista para mostrar el nombre del producto
        TextView textViewPrice; // Vista para mostrar el precio del producto
        ImageView imageViewSlide; // Vista para mostrar la imagen del producto

        public SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inicializa las vistas
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageViewSlide = itemView.findViewById(R.id.imageViewSlide);
        }
    }
}
