package com.example.proyecto_recuperacion_viviendas

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.*
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import java.io.File.separator
import java.util.concurrent.Executors
import kotlin.math.roundToInt


class piso_calcular : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    @RequiresApi(Build.VERSION_CODES.R)
    private lateinit var switchButton: Switch

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.piso_1)


        val spinner = findViewById<Spinner>(R.id.spinner)

        val cuotas = arrayOf(
            "CUOTAS",
            "Ninguna (0% ectra)",
            "12 meses (5% extra)",
            "24 meses (10% extra)",
            "36 meses (15% extra)",
            "48 meses (20% extra)"
        )

        val ciudad = getIntent().getStringExtra("ciudad")
        val descripcion = getIntent().getStringExtra("descripcion")
        val foto = getIntent().getStringExtra("foto")
        val precio = getIntent().getIntExtra("precio", 0)


        val fot_cal = findViewById<ImageView>(R.id.foto_piso)
        val ciudad_cal = findViewById<TextView>(R.id.titulo_piso)
        val descripcion_cal = findViewById<TextView>(R.id.descripcion_piso)
        val resultado = findViewById<TextView>(R.id.resultado_cuota)
        val calcular = findViewById<Button>(R.id.calcular_cuota)

        resultado.text = ""

        val executor = Executors.newSingleThreadExecutor();
        val handler = Handler(Looper.getMainLooper())
        var image: Bitmap? = null


        var precio_final = precio.toInt()

        executor.execute {
            val imageUrl = foto

            try {
                val `in` = java.net.URL(imageUrl).openStream()
                image = BitmapFactory.decodeStream(`in`)

                handler.post {
                    fot_cal.setImageBitmap(image)
                }

            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }

        ciudad_cal.text = ciudad
        descripcion_cal.text = descripcion


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (resources.configuration.isNightModeActive) {
                window.setBackgroundDrawableResource(R.drawable.background_dark)
            } else {
                window.setBackgroundDrawableResource(R.drawable.background_light)
            }
        } else {
            window.setBackgroundDrawableResource(R.drawable.background_light)
        }


        val adaptador1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cuotas)
        spinner.adapter = adaptador1

        switchButton = findViewById(R.id.boton_iva)
        switchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                precio_final = (precio.toInt() * 1.21).toInt()
            }else{
                precio_final = precio.toInt()
            } }

        calcular.setOnClickListener {

                when (spinner.selectedItem.toString()) {
                    "Ninguna (0% ectra)" -> resultado.text =
                        "Tiene que pagar un total de: ${precio_final}€"
                    "12 meses (5% extra)" -> resultado.text =
                        "Tiene que pagar un total de: ${((precio_final * 1.05) / 12).toInt()}€ por mes en 12 meses"
                    "24 meses (10% extra)" -> resultado.text =
                        "Tiene que pagar un total de: ${((precio_final * 1.1) / 24).toInt()}€ por mes en 24 meses"
                    "36 meses (15% extra)" -> resultado.text =
                        "Tiene que pagar un total de: ${((precio_final * 1.15) / 36).toInt()}€ por mes en 36 meses"
                    "48 meses (20% extra)" -> resultado.text =
                        "Tiene que pagar un total de: ${((precio_final * 1.20) / 48).toInt()}€ por mes en 48 meses"
                    else -> resultado.text = "Seleccione un valor y una cantidad"
                }
            }


        }


    }




