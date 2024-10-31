package com.example.fruteria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fruteria.adapter.frutas_adapter
import com.example.recyclerviewexamplo.R

class MainFrutas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frutas)
        initRecycleView()
    }

    fun initRecycleView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerFrutas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = frutas_adapter(frutasprovider.fruta_list)
    }
}