package com.example.proyecto_recuperacion_viviendas

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.fruteria.maincasas

class inicio : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)

        val btn_casas = findViewById<Button>(R.id.boton_casas)
        val btn_chalets = findViewById<Button>(R.id.boton_chalets)
        val btn_pisos = findViewById<Button>(R.id.boton_pisos)
        val btn_llamar = findViewById<Button>(R.id.button_llamar)

        val nombre = getIntent().getStringExtra("nombre")
        val apellido = getIntent().getStringExtra("apellido")

        val bienvenida = findViewById<TextView>(R.id.bienvenida)
        bienvenida.text = ("Bienvenido "+nombre+" "+apellido)



        btn_casas.setOnClickListener {
            val pantalla_casas = Intent(this, maincasas::class.java)
            startActivity(pantalla_casas)
        }

        btn_llamar.setOnClickListener{
            val i = Intent(Intent.ACTION_DIAL)
            i.data = Uri.parse("tel:666")
            startActivity(i)
        }

        btn_pisos.setOnClickListener {
            val pantalla_pisos = Intent(this, mainpisos::class.java)
            startActivity(pantalla_pisos)
        }

        btn_chalets.setOnClickListener {
            val pantalla_chalets = Intent(this, mainchalets::class.java)
            startActivity(pantalla_chalets)
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

