package com.example.fruteria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fruteria.adapter.verduras_adapter
import com.example.recyclerviewexamplo.R

class MainVerduras : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verduras)
        initRecycleView()
    }

    fun initRecycleView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerverduras)
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false)
        recyclerView.adapter = verduras_adapter(verdurasprovider.fruta_list)
    }
}