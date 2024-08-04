package com.example.a04_recyclerview

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Ir a Biblioteca
        val imageButtonBiblioteca = findViewById<ImageButton>(R.id.btn_biblioteca)
        imageButtonBiblioteca.setOnClickListener{
            irActividad((Biblioteca::class.java))
        }
        //Ir a Conciertos
        val imageButtonConciertos = findViewById<ImageButton>(R.id.btn_conciertos)
        imageButtonConciertos.setOnClickListener {
            irActividad((Conciertos::class.java))
        }
        //Ir a Shzam
        val shazamButton = findViewById<ImageButton>(R.id.btn_Shazam)
        shazamButton.setOnClickListener {
            irActividad((ListeningActivity::class.java))
        }
    }
    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}