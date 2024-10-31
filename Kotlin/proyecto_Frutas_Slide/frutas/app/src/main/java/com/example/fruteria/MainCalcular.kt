package com.example.fruteria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.recyclerviewexamplo.R
import kotlin.math.roundToInt

class MainCalcular : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calular)

        val cantidad=findViewById<EditText>(R.id.cantidad)
        val button=findViewById<Button>(R.id.button_calcular)
        val spinner=findViewById<Spinner>(R.id.spinner)
        val resultado=findViewById<TextView>(R.id.TextCalcular)

        val lista = arrayOf(
            "Manzanas 2.0€/kg",
            "Cerezas 2.75€/kg",
            "Naranjas 1.8€/kg",
            "kiwis 3.2€/kg",
            "Fresas 2.4€/kg",
            "Sandias 4.4€/kg",
            "Platanos 1.7€/kg",
            "Pimientos 2.4€/kg",
            "Patatas 1.75€/kg",
            "Zanahorias 2.8€/kg",
            "Cebollas 1.2€/kg",
            "Esparragos 4.4€/kg",
            "Tomates 2.0€/kg",
            "Lechugas 0.7€/kg"
        )
        val adaptador1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista)
        spinner.adapter = adaptador1
        button.setOnClickListener {
            when (spinner.selectedItem.toString()) {
                "Manzanas 2.0€/kg" -> resultado.text = "${cantidad.text}kg de manzanas son: ${(((cantidad.text.toString().toDouble() * 2.0) * 100.0 ).roundToInt() / 100.0)}€"
                "Cerezas 2.75€/kg" -> resultado.text = "${cantidad.text}kg de Cerezas son: ${(((cantidad.text.toString().toDouble() * 2.75) * 100.0 ).roundToInt() / 100.0)}€"
                "Naranjas 1.8€/kg" -> resultado.text = "${cantidad.text}kg de Naranjas son: ${(((cantidad.text.toString().toDouble() * 1.8) * 100.0 ).roundToInt() / 100.0)}€"
                "kiwis 3.2€/kg" -> resultado.text = "${cantidad.text}kg de kiwis son: ${(((cantidad.text.toString().toDouble() * 3.2) * 100.0 ).roundToInt() / 100.0)}€"
                "Fresas 2.4€/kg" -> resultado.text = "${cantidad.text}kg de Fresas son: ${(((cantidad.text.toString().toDouble() * 2.4) * 100.0 ).roundToInt() / 100.0)}€"
                "Sandias 4.4€/kg" -> resultado.text = "${cantidad.text}kg de Sandias son: ${(((cantidad.text.toString().toDouble() * 4.4) * 100.0 ).roundToInt() / 100.0)}€"
                "Platanos 1.7€/kg" -> resultado.text = "${cantidad.text}kg de Platanos son: ${(((cantidad.text.toString().toDouble() * 1.7) * 100.0 ).roundToInt() / 100.0)}€"
                "Pimientos 2.4€/kg" -> resultado.text = "${cantidad.text}kg de Pimientos son: ${(((cantidad.text.toString().toDouble() * 2.4) * 100.0 ).roundToInt() / 100.0)}€"
                "Patatas 1.75€/kg" -> resultado.text = "${cantidad.text}kg de Patatas son: ${(((cantidad.text.toString().toDouble() * 1.75) * 100.0 ).roundToInt() / 100.0)}€"
                "Zanahorias 2.8€/k" -> resultado.text = "${cantidad.text}kg de Zanahorias son: ${(((cantidad.text.toString().toDouble() * 2.8) * 100.0 ).roundToInt() / 100.0)}€"
                "Cebollas 1.2€/kg" -> resultado.text = "${cantidad.text}kg de Cebollas son: ${(((cantidad.text.toString().toDouble() * 1.2) * 100.0 ).roundToInt() / 100.0)}€"
                "Esparragos 4.4€/kg" -> resultado.text = "${cantidad.text}kg de Esparragos son: ${(((cantidad.text.toString().toDouble() * 4.4) * 100.0 ).roundToInt() / 100.0)}€"
                "Tomates 2.0€/kg" -> resultado.text = "${cantidad.text}kg de Tomates son: ${(((cantidad.text.toString().toDouble() * 2.0) * 100.0 ).roundToInt() / 100.0)}€"
                "Lechugas 0.7€/kg" -> resultado.text = "${cantidad.text}kg de Lechugas son: ${(((cantidad.text.toString().toDouble() * 0.7) * 100.0 ).roundToInt() / 100.0)}€"
                else -> resultado.text = "Seleccione un valor y una cantidad"
            }
        }
    }
}