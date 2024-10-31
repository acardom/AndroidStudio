package com.example.fruteria

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_recuperacion_viviendas.R
import com.example.proyecto_recuperacion_viviendas.adapter.casas_adapter
import com.example.proyecto_recuperacion_viviendas.casas_provider

class maincasas : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_casas)
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
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerCasas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = casas_adapter(casas_provider.casas_list)
    }



}