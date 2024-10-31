package com.example.fruteria.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fruteria.frutas
import com.example.recyclerviewexamplo.R

class frutas_viewholder (val view:View):RecyclerView.ViewHolder(view){

    val frutas = view.findViewById<TextView>(R.id.frutas_name)
    val precio = view.findViewById<TextView>(R.id.frutas_precio)
    val foto = view.findViewById<ImageView>(R.id.ivfrutas)

    fun render(frutas_model: frutas){
        frutas.text = frutas_model.fruta
        precio.text = frutas_model.precio
        Glide.with(foto.context).load(frutas_model.foto).into(foto)
    }

}