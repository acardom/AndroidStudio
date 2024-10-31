package com.example.proyecto_recuperacion_viviendas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_recuperacion_viviendas.R
import com.example.proyecto_recuperacion_viviendas.casas
import com.example.proyecto_recuperacion_viviendas.chalets


class chalets_adapter(val chalets_list:List<chalets>) : RecyclerView.Adapter<chalets_viewholder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): chalets_viewholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return chalets_viewholder(layoutInflater.inflate(R.layout.item_chalets, parent, false))
    }

    override fun onBindViewHolder(holder: chalets_viewholder, position: Int) {
        val item = chalets_list[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = chalets_list.size

}