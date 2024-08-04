package com.example.a04_recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

sealed class BibliotecaItem {
    data class HeaderItem(val title: String, val iconRes: Int, val count: String) : BibliotecaItem()
    data class SongItem(val songTitle: String, val artistName: String, val albumCoverRes: Int) : BibliotecaItem()
}

class BibliotecaAdapter(private val items: List<BibliotecaItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_SONG = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is BibliotecaItem.HeaderItem -> VIEW_TYPE_HEADER
            is BibliotecaItem.SongItem -> VIEW_TYPE_SONG
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_biblioteca_header, parent, false)
                HeaderViewHolder(view)
            }
            VIEW_TYPE_SONG -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_biblioteca_song, parent, false)
                SongViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind(items[position] as BibliotecaItem.HeaderItem)
            is SongViewHolder -> holder.bind(items[position] as BibliotecaItem.SongItem)
        }
    }

    override fun getItemCount() = items.size

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val icon: ImageView = view.findViewById(R.id.item_icon)
        private val title: TextView = view.findViewById(R.id.item_title)
        private val count: TextView = view.findViewById(R.id.item_count)

        fun bind(item: BibliotecaItem.HeaderItem) {
            icon.setImageResource(item.iconRes)
            title.text = item.title
            count.text = item.count
        }
    }

    class SongViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val albumCover: ImageView = view.findViewById(R.id.item_album_cover)
        private val songTitle: TextView = view.findViewById(R.id.item_song_title)
        private val artistName: TextView = view.findViewById(R.id.item_artist_name)

        fun bind(item: BibliotecaItem.SongItem) {
            albumCover.setImageResource(item.albumCoverRes)
            songTitle.text = item.songTitle
            artistName.text = item.artistName
        }
    }
}
