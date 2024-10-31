package com.example.proyecto_recuperacion_viviendas

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_recuperacion_viviendas.adapter.pisos_adapter

class mainpisos : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pisos)
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
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerPisos)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = pisos_adapter(pisos_provider.pisos_list)
    }
}