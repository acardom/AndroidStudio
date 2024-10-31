package com.example.fruteria

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerviewexamplo.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_calcular = findViewById<Button>(R.id.buttonCalcular)
        val btn_ftutas = findViewById<Button>(R.id.buttonFrutas)
        val btn_verduras = findViewById<Button>(R.id.buttonVerduras)
        val btn_llamar = findViewById<Button>(R.id.buttonLlamar)


        btn_calcular.setOnClickListener{
            val pantalla_calcular = Intent(this, MainCalcular::class.java)
            startActivity(pantalla_calcular)
        }

        btn_llamar.setOnClickListener{
            val i = Intent(Intent.ACTION_DIAL)
            i.data = Uri.parse("tel:666")
            startActivity(i)
        }

        btn_ftutas.setOnClickListener{
            val pantalla_frutas = Intent(this, MainFrutas::class.java)
            startActivity(pantalla_frutas)
        }

        btn_verduras.setOnClickListener{
            val pantalla_verduras = Intent(this, MainVerduras::class.java)
            startActivity(pantalla_verduras)
        }


    }


}