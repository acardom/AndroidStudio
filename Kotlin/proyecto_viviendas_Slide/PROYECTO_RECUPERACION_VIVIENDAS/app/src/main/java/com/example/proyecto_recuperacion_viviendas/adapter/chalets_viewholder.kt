package com.example.proyecto_recuperacion_viviendas.adapter

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyecto_recuperacion_viviendas.R
import com.example.proyecto_recuperacion_viviendas.casas
import com.example.proyecto_recuperacion_viviendas.chalets
import com.example.proyecto_recuperacion_viviendas.piso_calcular

class chalets_viewholder (val view: View): RecyclerView.ViewHolder(view){

    val chalets = view.findViewById<TextView>(R.id.chalets_name)
    val precio = view.findViewById<TextView>(R.id.chalets_precio)
    val foto = view.findViewById<ImageView>(R.id.ivchalets)
    val descripcion = view.findViewById<TextView>(R.id.chalets_descripcion)

    fun render(chalets_model: chalets){
        chalets.text = chalets_model.chalets
        precio.text = chalets_model.precio
        descripcion.text = chalets_model.descripcion
        Glide.with(foto.context).load(chalets_model.foto).into(foto)

        itemView.setOnClickListener {

            val pasar_calcular = Intent(itemView.context, piso_calcular::class.java)

            pasar_calcular.putExtra("foto", chalets_model.foto)
            pasar_calcular.putExtra("ciudad", chalets_model.chalets)
            pasar_calcular.putExtra("descripcion", chalets_model.descripcion)
            pasar_calcular.putExtra("precio", chalets_model.pago)

            itemView.context.startActivity(pasar_calcular)
        }
    }

}