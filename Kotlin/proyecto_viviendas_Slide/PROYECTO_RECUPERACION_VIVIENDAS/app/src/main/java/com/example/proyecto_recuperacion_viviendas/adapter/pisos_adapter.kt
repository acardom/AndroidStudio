package com.example.proyecto_recuperacion_viviendas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_recuperacion_viviendas.R
import com.example.proyecto_recuperacion_viviendas.chalets
import com.example.proyecto_recuperacion_viviendas.pisos

class pisos_adapter(val pisos_list:List<pisos>) : RecyclerView.Adapter<pisos_viewholder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pisos_viewholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return pisos_viewholder(layoutInflater.inflate(R.layout.item_pisos, parent, false))
    }

    override fun onBindViewHolder(holder: pisos_viewholder, position: Int) {
        val item = pisos_list[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = pisos_list.size

}