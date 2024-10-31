package com.example.proyecto_recuperacion_viviendas.adapter

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyecto_recuperacion_viviendas.R
import com.example.proyecto_recuperacion_viviendas.chalets
import com.example.proyecto_recuperacion_viviendas.piso_calcular
import com.example.proyecto_recuperacion_viviendas.pisos

class pisos_viewholder (val view: View): RecyclerView.ViewHolder(view){

    val pisos = view.findViewById<TextView>(R.id.pisos_name)
    val precio = view.findViewById<TextView>(R.id.pisos_precio)
    val foto = view.findViewById<ImageView>(R.id.ivpisos)
    val descripcion = view.findViewById<TextView>(R.id.pisos_descripcion)

    fun render(pisos_model: pisos){
        pisos.text = pisos_model.pisos
        precio.text = pisos_model.precio
        descripcion.text = pisos_model.descripcion
        Glide.with(foto.context).load(pisos_model.foto).into(foto)

        itemView.setOnClickListener {

            val pasar_calcular = Intent(itemView.context, piso_calcular::class.java)

            pasar_calcular.putExtra("foto", pisos_model.foto)
            pasar_calcular.putExtra("ciudad", pisos_model.pisos)
            pasar_calcular.putExtra("descripcion", pisos_model.descripcion)
            pasar_calcular.putExtra("precio", pisos_model.pago)

            itemView.context.startActivity(pasar_calcular)
        }
    }

}