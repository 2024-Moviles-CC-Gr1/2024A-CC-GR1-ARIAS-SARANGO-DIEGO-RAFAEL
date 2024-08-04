package com.example.a04_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Biblioteca : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biblioteca)

        inicializarRecyclerView()
    }

    fun inicializarRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adaptador = BibliotecaAdapter(getBibliotecaItems())

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    private fun getBibliotecaItems(): List<BibliotecaItem> {
        return listOf(
            BibliotecaItem.HeaderItem("Shazams", R.drawable.ic_shazam, "14"),
            BibliotecaItem.HeaderItem("Artistas", R.drawable.ic_artists, ""),
            BibliotecaItem.HeaderItem("Listas de reproducción para ti", R.drawable.ic_playlists, ""),
            BibliotecaItem.SongItem("XX (Remix)", "Feid & Dalmata", R.drawable.album_cover_1),
            BibliotecaItem.SongItem("Toky", "Sossa, Bruno Marcel, Rich Ibra, Vysel & Hoppen", R.drawable.album_cover_2),
            BibliotecaItem.SongItem("Oh My God", "Adele", R.drawable.album_cover_3),
            BibliotecaItem.SongItem("My Heart", "Different Heaven & Eh!de", R.drawable.album_cover_4),
            BibliotecaItem.SongItem("Those Eyes", "New West", R.drawable.album_cover_5),
            BibliotecaItem.SongItem("HOLA PERDIDA", "Luck Ra & KHEA", R.drawable.album_cover_6),
            BibliotecaItem.SongItem("Orion", "Boza & ELENA ROSE", R.drawable.album_cover_7),
            BibliotecaItem.SongItem("The Great Gig In the Sky", "Pink Floyd", R.drawable.album_cover_8),
            BibliotecaItem.SongItem("Borderline", "Tame Impala", R.drawable.album_cover_9),
            BibliotecaItem.SongItem("Turn Me On (feat. Vula)", "Riton & Oliver Heldens", R.drawable.album_cover_10),
            BibliotecaItem.SongItem("Leave a Light On", "Tom Walker", R.drawable.album_cover_11),
            BibliotecaItem.SongItem("Last Train to London", "Electric Light Orchestra", R.drawable.album_cover_12),
            BibliotecaItem.SongItem("Life Goes On", "Oliver Tree", R.drawable.album_cover_13),
            BibliotecaItem.SongItem("Permíteme", "Tony Dize", R.drawable.album_cover_14)
            // Añadir más canciones
        )
    }
}

