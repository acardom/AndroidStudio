package com.example.dado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
/**
 * Esta actividad permite al usuario tirar un dado y ver el resultado en la pantalla.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener { rodarDado() }
    }
    /**
     * Método que tira los dados y actualiza la pantalla con el resultado.
     */
    private fun rodarDado() {
        // Crea un nuevo objeto Dado con 6 lados y lo lanza
        val dado = Dado(6)
        val diceRoll = dado.lanzar()
        val imagenDado: ImageView = findViewById(R.id.imageView)
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dado_1
            2 -> R.drawable.dado_2
            3 -> R.drawable.dado_3
            4 -> R.drawable.dado_4
            5 -> R.drawable.dado_5
            else -> R.drawable.dado_6
        }
        imagenDado.setImageResource(drawableResource)
        imagenDado.contentDescription = diceRoll.toString()
        // Actualiza la pantalla con la tirada de dados


        val myFirstDice = Dado(6)
        val valorDado = myFirstDice.lanzar()
        val luckyNumber = 4
        when (valorDado) {

            luckyNumber -> println("Ganaste!")

                1 -> println("Lo siento! salió el valor 1. Inténtalo de nuevo!")

                2 -> println("Tristemente salió el valor 2. Inténtalo de nuevo!")

                3 -> println("Desafortunadamente salió el valor 3. Inténtalo de nuevo!")

                5 -> println("No llores! salió el valor 5. Inténtalo de nuevo!")

                6 -> println("Hoy no es tu día de suerte! salió el valor 6. Inténtalo de nuevo!")

            }

        }



        class Dado (val numeroDeLados: Int) {

            fun lanzar(): Int {

                return (1..numeroDeLados).random()

            }

        }

    }


class Dado (val numeroDeLados: Int) {
    fun lanzar(): Int {
        return (1..numeroDeLados).random()
    }



}