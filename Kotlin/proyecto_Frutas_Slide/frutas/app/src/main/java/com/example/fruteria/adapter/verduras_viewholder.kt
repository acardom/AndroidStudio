package com.example.fruteria.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fruteria.verduras
import com.example.recyclerviewexamplo.R

class verduras_viewholder (val view:View):RecyclerView.ViewHolder(view){

    val verduras = view.findViewById<TextView>(R.id.verduras_name)
    val precio = view.findViewById<TextView>(R.id.verduras_precio)
    val foto = view.findViewById<ImageView>(R.id.ivverduras)

    fun render(frutas_model: verduras){
        verduras.text = frutas_model.verdura
        precio.text = frutas_model.precio
        Glide.with(foto.context).load(frutas_model.foto).into(foto)
    }

}