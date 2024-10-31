package com.example.fruteria.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fruteria.verduras
import com.example.recyclerviewexamplo.R

class verduras_adapter(val verduras_list:List<verduras>) : RecyclerView.Adapter<verduras_viewholder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): verduras_viewholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return verduras_viewholder(layoutInflater.inflate(R.layout.item_verdura, parent, false))
    }

    override fun onBindViewHolder(holder: verduras_viewholder, position: Int) {
        val item = verduras_list[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = verduras_list.size

}