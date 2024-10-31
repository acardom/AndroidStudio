package com.example.fruteria.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fruteria.frutas
import com.example.recyclerviewexamplo.R

class frutas_adapter(val frutas_list:List<frutas>) : RecyclerView.Adapter<frutas_viewholder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): frutas_viewholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return frutas_viewholder(layoutInflater.inflate(R.layout.item_fruta, parent, false))
    }

    override fun onBindViewHolder(holder: frutas_viewholder, position: Int) {
        val item = frutas_list[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = frutas_list.size

}