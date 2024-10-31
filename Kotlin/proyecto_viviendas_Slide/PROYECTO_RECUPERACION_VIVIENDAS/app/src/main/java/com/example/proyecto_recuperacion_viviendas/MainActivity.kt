package com.example.proyecto_recuperacion_viviendas

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_acceso = findViewById<Button>(R.id.boton_acceso)
        val nombre = findViewById<EditText>(R.id.nombre_acceso)
        val apellido = findViewById<EditText>(R.id.apellido_acceso)


        btn_acceso.setOnClickListener{
            val pantalla_inicio = Intent(this, inicio::class.java)

            if ((apellido.text.toString() != "") and (nombre.text.toString() != "")){

                val pasar_inicio = Intent(this,inicio::class.java)
                pasar_inicio.putExtra("apellido", apellido.text.toString())
                pasar_inicio.putExtra("nombre", nombre.text.toString())
                startActivity(pasar_inicio)

            }
        }

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
}