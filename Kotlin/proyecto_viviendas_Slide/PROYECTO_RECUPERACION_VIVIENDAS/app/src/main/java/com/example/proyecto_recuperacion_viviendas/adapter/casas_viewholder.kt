package com.example.proyecto_recuperacion_viviendas.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fruteria.maincasas
import com.example.proyecto_recuperacion_viviendas.*


class casas_viewholder (val view: View): RecyclerView.ViewHolder(view){

    val casas = view.findViewById<TextView>(R.id.casas_name)
    val precio = view.findViewById<TextView>(R.id.casas_precio)
    val foto = view.findViewById<ImageView>(R.id.ivcasas)
    val descripcion = view.findViewById<TextView>(R.id.casas_descripcion)

    fun render(casas_model: casas){
        casas.text = casas_model.casa
        precio.text = casas_model.precio
        descripcion.text = casas_model.descripcion
        Glide.with(foto.context).load(casas_model.foto).into(foto)


        itemView.setOnClickListener {

            val pasar_calcular = Intent(itemView.context, piso_calcular::class.java)

            pasar_calcular.putExtra("foto", casas_model.foto)
            pasar_calcular.putExtra("ciudad", casas_model.casa)
            pasar_calcular.putExtra("descripcion", casas_model.descripcion)
            pasar_calcular.putExtra("precio", casas_model.pago)

            itemView.context.startActivity(pasar_calcular)
        }


    }



}