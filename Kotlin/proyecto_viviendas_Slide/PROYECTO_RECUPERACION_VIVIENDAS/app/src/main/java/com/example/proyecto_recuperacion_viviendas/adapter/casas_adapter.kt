package com.example.proyecto_recuperacion_viviendas.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_recuperacion_viviendas.R
import com.example.proyecto_recuperacion_viviendas.casas
import com.example.proyecto_recuperacion_viviendas.pisos

class casas_adapter(val casas_list:List<casas>) : RecyclerView.Adapter<casas_viewholder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): casas_viewholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return casas_viewholder(layoutInflater.inflate(R.layout.item_casas, parent, false))
    }

    override fun onBindViewHolder(holder: casas_viewholder, position: Int) {
        val item = casas_list[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = casas_list.size






}