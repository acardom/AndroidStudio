package com.example.proyecto_recuperacion_viviendas

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_recuperacion_viviendas.adapter.casas_adapter
import com.example.proyecto_recuperacion_viviendas.adapter.chalets_adapter

class mainchalets : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chalets)
        initRecycleView()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (resources.configuration.isNightModeActive) {
                window.setBackgroundDrawableResource(R.drawable.background_dark)
            } else {
                window.setBackgroundDrawableResource(R.drawable.background_light)
            }
        } else {
            window.setBackgroundDrawableResource(R.drawable.background_light)
        }
    }

    fun initRecycleView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerChalets)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = chalets_adapter(chalets_provider.chalets_list)
    }


}