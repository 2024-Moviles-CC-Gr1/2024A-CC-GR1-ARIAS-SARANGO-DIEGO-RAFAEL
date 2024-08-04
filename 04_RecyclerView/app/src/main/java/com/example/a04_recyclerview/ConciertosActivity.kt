package com.example.a04_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ConciertosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conciertos)

        inicializarRecyclerView()
    }

    fun inicializarRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adaptador = ConciertosAdapter(getConciertoItems())

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    private fun getConciertoItems(): List<ConciertoItem> {
        return listOf(
            ConciertoItem(R.drawable.sam_smith, "Sam Smith", "jue 8 ago. 2024", "Skanderborg Dyrehave, Skanderborg", "Ver la guía de conciertos"),
            ConciertoItem(R.drawable.major_lazer, "Major Lazer", "4 - 23 ago.", "No hay conciertos en E...", ""),
            ConciertoItem(R.drawable.david_guetta, "David Guetta", "5 ago. 2024 - 19 jul.", "No hay conciertos en E...", "")
            // Añade más elementos según sea necesario
        )
    }
}
